package fallk.utils;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

/**
 * Takes a linear value in the range of 0-1 and outputs a (usually) non-linear, interpolated value.
 *
 * @author Nathan Sweet
 */
public enum Interpolation {
    //

    linear {
        @Override
        public float apply(final float a) {
            return a;
        }
    },

    //

    /** Aka "smoothstep". */
    smooth {
        @Override
        public float apply(final float a) {
            return a * a * (3 - 2 * a);
        }
    },
    smooth2 {
        @Override
        public float apply(float a) {
            a = a * a * (3 - 2 * a);
            return a * a * (3 - 2 * a);
        }
    },
    /** Smooths down to perfection, very slow */
    smoothN {
        @Override
        public float apply(final float a) {
            float f = smooth.apply(a);
            float of = f;
            while ((f = smooth.apply(f)) != of) {
                of = f;
            }
            return f;
        }
    },

    /** By Ken Perlin. */
    smoother {
        @Override
        public float apply(final float a) {
            return clamp(a * a * a * (a * (a * 6 - 15) + 10), 0, 1);
        }
    },
    fade(smoother::apply), pow2InInverse {
        @Override
        public float apply(final float a) {
            return (float) Math.sqrt(a);
        }
    },
    pow2OutInverse {
        @Override
        public float apply(final float a) {
            return 1 - (float) Math.sqrt(-(a - 1));
        }
    },
    pow3InInverse {
        @Override
        public float apply(final float a) {
            return (float) Math.cbrt(a);
        }
    },
    pow3OutInverse {
        @Override
        public float apply(final float a) {
            return 1 - (float) Math.cbrt(-(a - 1));
        }
    },

    sine {
        @Override
        public float apply(final float a) {
            return (1 - cos(a * PI)) / 2;
        }
    },

    sineIn {
        @Override
        public float apply(final float a) {
            return 1 - cos(a * PI / 2);
        }
    },

    sineOut {
        @Override
        public float apply(final float a) {
            return sin(a * PI / 2);
        }
    },

    circle {
        @Override
        public float apply(float a) {
            if (a <= 0.5f) {
                a *= 2;
                return (1 - (float) Math.sqrt(1 - a * a)) / 2;
            }
            a--;
            a *= 2;
            return ((float) Math.sqrt(1 - a * a) + 1) / 2;
        }
    },

    circleIn {
        @Override
        public float apply(final float a) {
            return 1 - (float) Math.sqrt(1 - a * a);
        }
    },

    circleOut {
        @Override
        public float apply(float a) {
            a--;
            return (float) Math.sqrt(1 - a * a);
        }
    },

    pow2(new Pow(2)),
    /** Slow, then fast. */
    pow2In(new PowIn(2)),
    /** Fast, then slow. */
    pow2Out(new PowOut(2)),

    pow3(new Pow(3)), pow3In(new PowIn(3)), pow3Out(new PowOut(3)),

    pow4(new Pow(4)), pow4In(new PowIn(4)), pow4Out(new PowOut(4)),

    pow5(new Pow(5)), pow5In(new PowIn(5)), pow5Out(new PowOut(5)),

    exp10(new Exp(2, 10)), exp10In(new ExpIn(2, 10)), exp10Out(new ExpOut(2, 10)),

    exp5(new Exp(2, 5)), exp5In(new ExpIn(2, 5)), exp5Out(new ExpOut(2, 5)),

    elastic(new Elastic(2, 10, 7, 1)), elasticIn(new ElasticIn(2, 10, 6, 1)), elasticOut(new ElasticOut(2, 10, 7, 1)),

    swing(new Swing(1.5f)), swingIn(new SwingIn(2f)), swingOut(new SwingOut(2f)),

    bounce(new Bounce(4)), bounceIn(new BounceIn(4)), bounceOut(new BounceOut(4)),

    ;
    //

    static private final float PI = 3.1415927f;
    static private final int SIN_BITS = 14; // 16KB. Adjust for accuracy.
    static private final int SIN_MASK = ~(-1 << SIN_BITS);
    static private final int SIN_COUNT = SIN_MASK + 1;

    static private final float radFull = PI * 2;
    static private final float degFull = 360;
    static private final float radToIndex = SIN_COUNT / radFull;
    static private final float degToIndex = SIN_COUNT / degFull;

    /** multiply by this to convert from degrees to radians */
    static private final float degreesToRadians = PI / 180;

    static private class Sin {
        static final float[] table = new float[SIN_COUNT];

        static {
            for (int i = 0; i < SIN_COUNT; i++) {
                table[i] = (float) Math.sin((i + 0.5f) / SIN_COUNT * radFull);
            }
            for (int i = 0; i < 360; i += 90) {
                table[(int) (i * degToIndex) & SIN_MASK] = (float) Math.sin(i * degreesToRadians);
            }
        }
    }

    /** Returns the sine in radians from a lookup table. */
    static private float sin(final float radians) {
        return Sin.table[(int) (radians * radToIndex) & SIN_MASK];
    }

    /** Returns the cosine in radians from a lookup table. */
    static private float cos(final float radians) {
        return Sin.table[(int) ((radians + PI / 2) * radToIndex) & SIN_MASK];
    }

    // ---

    // ---

    // ---

    // ---

    static private float clamp(final float value, final float min, final float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    // ---

    // ---

    //

    static private class Pow implements Interpolable {
        final int power;

        private Pow(final int power) {
            this.power = power;
        }

        @Override
        public float apply(final float a) {
            if (a <= 0.5f) {
                return (float) Math.pow(a * 2, power) / 2;
            }
            return (float) Math.pow((a - 1) * 2, power) / (power % 2 == 0 ? -2 : 2) + 1;
        }
    }

    static private class PowIn extends Pow {
        private PowIn(final int power) {
            super(power);
        }

        @Override
        public float apply(final float a) {
            return (float) Math.pow(a, power);
        }
    }

    static private class PowOut extends Pow {
        private PowOut(final int power) {
            super(power);
        }

        @Override
        public float apply(final float a) {
            return (float) Math.pow(a - 1, power) * (power % 2 == 0 ? -1 : 1) + 1;
        }
    }

    //

    static private class Exp implements Interpolable {
        final float value, power, min, scale;

        private Exp(final float value, final float power) {
            this.value = value;
            this.power = power;
            min = (float) Math.pow(value, -power);
            scale = 1 / (1 - min);
        }

        @Override
        public float apply(final float a) {
            if (a <= 0.5f) {
                return ((float) Math.pow(value, power * (a * 2 - 1)) - min) * scale / 2;
            }
            return (2 - ((float) Math.pow(value, -power * (a * 2 - 1)) - min) * scale) / 2;
        }
    };

    static private class ExpIn extends Exp {
        private ExpIn(final float value, final float power) {
            super(value, power);
        }

        @Override
        public float apply(final float a) {
            return ((float) Math.pow(value, power * (a - 1)) - min) * scale;
        }
    }

    static private class ExpOut extends Exp {
        private ExpOut(final float value, final float power) {
            super(value, power);
        }

        @Override
        public float apply(final float a) {
            return 1 - ((float) Math.pow(value, -power * a) - min) * scale;
        }
    }

    //

    static private class Elastic implements Interpolable {
        final float value, power, scale, bounces;

        private Elastic(final float value, final float power, final int bounces, final float scale) {
            this.value = value;
            this.power = power;
            this.scale = scale;
            this.bounces = bounces * PI * (bounces % 2 == 0 ? 1 : -1);
        }

        @Override
        public float apply(float a) {
            if (a <= 0.5f) {
                a *= 2;
                return (float) Math.pow(value, power * (a - 1)) * sin(a * bounces) * scale / 2;
            }
            a = 1 - a;
            a *= 2;
            return 1 - (float) Math.pow(value, power * (a - 1)) * sin(a * bounces) * scale / 2;
        }
    }

    static private class ElasticIn extends Elastic {
        private ElasticIn(final float value, final float power, final int bounces, final float scale) {
            super(value, power, bounces, scale);
        }

        @Override
        public float apply(final float a) {
            if (a >= 0.99) {
                return 1;
            }
            return (float) Math.pow(value, power * (a - 1)) * sin(a * bounces) * scale;
        }
    }

    static private class ElasticOut extends Elastic {
        private ElasticOut(final float value, final float power, final int bounces, final float scale) {
            super(value, power, bounces, scale);
        }

        @Override
        public float apply(float a) {
            if (a == 0) {
                return 0;
            }
            a = 1 - a;
            return 1 - (float) Math.pow(value, power * (a - 1)) * sin(a * bounces) * scale;
        }
    }

    //

    static private class Bounce extends BounceOut {
        private Bounce(final float[] widths, final float[] heights) {
            super(widths, heights);
        }

        private Bounce(final int bounces) {
            super(bounces);
        }

        private float out(final float a) {
            final float test = a + widths[0] / 2;
            if (test < widths[0]) {
                return test / (widths[0] / 2) - 1;
            }
            return super.apply(a);
        }

        @Override
        public float apply(final float a) {
            if (a <= 0.5f) {
                return (1 - out(1 - a * 2)) / 2;
            }
            return out(a * 2 - 1) / 2 + 0.5f;
        }
    }

    static private class BounceOut implements Interpolable {
        final float[] widths, heights;

        private BounceOut(final float[] widths, final float[] heights) {
            if (widths.length != heights.length) {
                throw new IllegalArgumentException("Must be the same number of widths and heights.");
            }
            this.widths = widths;
            this.heights = heights;
        }

        private BounceOut(final int bounces) {
            if (bounces < 2 || bounces > 5) {
                throw new IllegalArgumentException("bounces cannot be < 2 or > 5: " + bounces);
            }
            widths = new float[bounces];
            heights = new float[bounces];
            heights[0] = 1;
            switch (bounces) {
                case 2:
                    widths[0] = 0.6f;
                    widths[1] = 0.4f;
                    heights[1] = 0.33f;
                    break;
                case 3:
                    widths[0] = 0.4f;
                    widths[1] = 0.4f;
                    widths[2] = 0.2f;
                    heights[1] = 0.33f;
                    heights[2] = 0.1f;
                    break;
                case 4:
                    widths[0] = 0.34f;
                    widths[1] = 0.34f;
                    widths[2] = 0.2f;
                    widths[3] = 0.15f;
                    heights[1] = 0.26f;
                    heights[2] = 0.11f;
                    heights[3] = 0.03f;
                    break;
                case 5:
                    widths[0] = 0.3f;
                    widths[1] = 0.3f;
                    widths[2] = 0.2f;
                    widths[3] = 0.1f;
                    widths[4] = 0.1f;
                    heights[1] = 0.45f;
                    heights[2] = 0.3f;
                    heights[3] = 0.15f;
                    heights[4] = 0.06f;
                    break;
            }
            widths[0] *= 2;
        }

        @Override
        public float apply(float a) {
            if (a == 1) {
                return 1;
            }
            a += widths[0] / 2;
            float width = 0, height = 0;
            for (int i = 0, n = widths.length; i < n; i++) {
                width = widths[i];
                if (a <= width) {
                    height = heights[i];
                    break;
                }
                a -= width;
            }
            a /= width;
            final float z = 4 / width * height * a;
            return 1 - (z - z * a) * width;
        }
    }

    static private class BounceIn extends BounceOut {
        private BounceIn(final float[] widths, final float[] heights) {
            super(widths, heights);
        }

        private BounceIn(final int bounces) {
            super(bounces);
        }

        @Override
        public float apply(final float a) {
            return 1 - super.apply(1 - a);
        }
    }

    //

    static private class Swing implements Interpolable {
        private final float scale;

        private Swing(final float scale) {
            this.scale = scale * 2;
        }

        @Override
        public float apply(float a) {
            if (a <= 0.5f) {
                a *= 2;
                return a * a * ((scale + 1) * a - scale) / 2;
            }
            a--;
            a *= 2;
            return a * a * ((scale + 1) * a + scale) / 2 + 1;
        }
    }

    static private class SwingOut implements Interpolable {
        private final float scale;

        private SwingOut(final float scale) {
            this.scale = scale;
        }

        @Override
        public float apply(float a) {
            a--;
            return a * a * ((scale + 1) * a + scale) + 1;
        }
    }

    static private class SwingIn implements Interpolable {
        private final float scale;

        private SwingIn(final float scale) {
            this.scale = scale;
        }

        @Override
        public float apply(final float a) {
            return a * a * ((scale + 1) * a - scale);
        }
    }

    //
    private interface Interpolable {
        float apply(float a);
    }

    private Interpolable impl = a -> a;

    Interpolation(final Interpolable impl) {
        this.impl = impl;
    }

    Interpolation() {
    }

    /** @param a Alpha value between 0 and 1. */
    public float apply(final float a) {
        return impl.apply(a);
    }

    /** @param a Alpha value between 0 and 1. */
    public float apply(final float start, final float end, final float a) {
        return start + (end - start) * apply(a);
    }

}
