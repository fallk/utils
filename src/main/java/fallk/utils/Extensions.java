package fallk.utils;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextComponent;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.relation.Role;
import javax.management.relation.RoleList;
import javax.management.relation.RoleUnresolved;
import javax.management.relation.RoleUnresolvedList;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.CellRendererPane;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultTreeCellEditor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Node;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import club.bonerbrew.logmaster.HLogger;
import lombok.NonNull;
import lombok.val;
import lombok.experimental.ExtensionMethod;

@ExtensionMethod({
        Collections.class, Arrays.class, FileUtils.class, IOUtils.class
})
public class Extensions {
    /*
     * Licensed to the Apache Software Foundation (ASF) under one or more
     * contributor license agreements.  See the NOTICE file distributed with
     * this work for additional information regarding copyright ownership.
     * The ASF licenses this file to You under the Apache License, Version 2.0
     * (the "License"); you may not use this file except in compliance with
     * the License.  You may obtain a copy of the License at
     *
     *      http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */

    /**
     * <p>
     * Operations on {@link CharSequence} that are {@code null} safe.
     * </p>
     *
     * @see CharSequence
     * @since 3.0
     */
    public static class CharSequenceUtils {

        private static final int NOT_FOUND = -1;

        /**
         * <p>
         * {@code CharSequenceUtils} instances should NOT be constructed in standard programming.
         * </p>
         * <p>
         * This constructor is public to permit tools that require a JavaBean instance to operate.
         * </p>
         */
        public CharSequenceUtils() {
            super();
        }

        //-----------------------------------------------------------------------
        /**
         * <p>
         * Returns a new {@code CharSequence} that is a subsequence of this sequence starting with the {@code char} value at the specified index.
         * </p>
         * <p>
         * This provides the {@code CharSequence} equivalent to {@link String#substring(int)}. The length (in {@code char}) of the returned sequence is {@code length() - start}, so if {@code start == end} then an empty sequence is returned.
         * </p>
         *
         * @param cs the specified subsequence, null returns null
         * @param start the start index, inclusive, valid
         * @return a new subsequence, may be null
         * @throws IndexOutOfBoundsException if {@code start} is negative or if {@code start} is greater than {@code length()}
         */
        public static CharSequence subSequence(final CharSequence cs, final int start) {
            return cs == null ? null : cs.subSequence(start, cs.length());
        }

        //-----------------------------------------------------------------------
        /**
         * <p>
         * Finds the first index in the {@code CharSequence} that matches the specified character.
         * </p>
         *
         * @param cs the {@code CharSequence} to be processed, not null
         * @param searchChar the char to be searched for
         * @param start the start index, negative starts at the string start
         * @return the index where the search char was found, -1 if not found
         */
        static int indexOf(final CharSequence cs, final int searchChar, int start) {
            if (cs instanceof String) {
                return ((String) cs).indexOf(searchChar, start);
            }
            final int sz = cs.length();
            if (start < 0) {
                start = 0;
            }
            for (int i = start; i < sz; i++) {
                if (cs.charAt(i) == searchChar) {
                    return i;
                }
            }
            return NOT_FOUND;
        }

        /**
         * Used by the indexOf(CharSequence methods) as a green implementation of indexOf.
         *
         * @param cs the {@code CharSequence} to be processed
         * @param searchChar the {@code CharSequence} to be searched for
         * @param start the start index
         * @return the index where the search sequence was found
         */
        static int indexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
            return cs.toString().indexOf(searchChar.toString(), start);
            //            if (cs instanceof String && searchChar instanceof String) {
            //                // TODO: Do we assume searchChar is usually relatively small;
            //                //       If so then calling toString() on it is better than reverting to
            //                //       the green implementation in the else block
            //                return ((String) cs).indexOf((String) searchChar, start);
            //            } else {
            //                // TODO: Implement rather than convert to String
            //                return cs.toString().indexOf(searchChar.toString(), start);
            //            }
        }

        /**
         * <p>
         * Finds the last index in the {@code CharSequence} that matches the specified character.
         * </p>
         *
         * @param cs the {@code CharSequence} to be processed
         * @param searchChar the char to be searched for
         * @param start the start index, negative returns -1, beyond length starts at end
         * @return the index where the search char was found, -1 if not found
         */
        static int lastIndexOf(final CharSequence cs, final int searchChar, int start) {
            if (cs instanceof String) {
                return ((String) cs).lastIndexOf(searchChar, start);
            }
            final int sz = cs.length();
            if (start < 0) {
                return NOT_FOUND;
            }
            if (start >= sz) {
                start = sz - 1;
            }
            for (int i = start; i >= 0; --i) {
                if (cs.charAt(i) == searchChar) {
                    return i;
                }
            }
            return NOT_FOUND;
        }

        /**
         * Used by the lastIndexOf(CharSequence methods) as a green implementation of lastIndexOf
         *
         * @param cs the {@code CharSequence} to be processed
         * @param searchChar the {@code CharSequence} to be searched for
         * @param start the start index
         * @return the index where the search sequence was found
         */
        static int lastIndexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
            return cs.toString().lastIndexOf(searchChar.toString(), start);
            //            if (cs instanceof String && searchChar instanceof String) {
            //                // TODO: Do we assume searchChar is usually relatively small;
            //                //       If so then calling toString() on it is better than reverting to
            //                //       the green implementation in the else block
            //                return ((String) cs).lastIndexOf((String) searchChar, start);
            //            } else {
            //                // TODO: Implement rather than convert to String
            //                return cs.toString().lastIndexOf(searchChar.toString(), start);
            //            }
        }

        /**
         * Green implementation of toCharArray.
         *
         * @param cs the {@code CharSequence} to be processed
         * @return the resulting char array
         */
        static char[] toCharArray(final CharSequence cs) {
            if (cs instanceof String) {
                return ((String) cs).toCharArray();
            }
            final int sz = cs.length();
            final char[] array = new char[cs.length()];
            for (int i = 0; i < sz; i++) {
                array[i] = cs.charAt(i);
            }
            return array;
        }

        /**
         * Green implementation of regionMatches.
         *
         * @param cs the {@code CharSequence} to be processed
         * @param ignoreCase whether or not to be case insensitive
         * @param thisStart the index to start on the {@code cs} CharSequence
         * @param substring the {@code CharSequence} to be looked for
         * @param start the index to start on the {@code substring} CharSequence
         * @param length character length of the region
         * @return whether the region matched
         */
        static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart, final CharSequence substring, final int start, final int length) {
            if (cs instanceof String && substring instanceof String) {
                return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
            }
            int index1 = thisStart;
            int index2 = start;
            int tmpLen = length;

            // Extract these first so we detect NPEs the same as the java.lang.String version
            final int srcLen = cs.length() - thisStart;
            final int otherLen = substring.length() - start;

            // Check for invalid parameters
            if (thisStart < 0 || start < 0 || length < 0) {
                return false;
            }

            // Check that the regions are long enough
            if (srcLen < length || otherLen < length) {
                return false;
            }

            while (tmpLen-- > 0) {
                final char c1 = cs.charAt(index1++);
                final char c2 = substring.charAt(index2++);

                if (c1 == c2) {
                    continue;
                }

                if (!ignoreCase) {
                    return false;
                }

                // The same check as in String.regionMatches():
                if (Character.toUpperCase(c1) != Character.toUpperCase(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * A String for a space character.
     *
     * @since 3.2
     */
    public static final String SPACE = " ";

    /**
     * The empty String {@code ""}.
     * 
     * @since 2.0
     */
    public static final String EMPTY = "";

    /**
     * A String for linefeed LF ("\n").
     *
     * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences for Character and String Literals</a>
     * @since 3.2
     */
    public static final String LF = "\n";

    /**
     * A String for carriage return CR ("\r").
     *
     * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences for Character and String Literals</a>
     * @since 3.2
     */
    public static final String CR = "\r";

    /**
     * Represents a failed index search.
     * 
     * @since 2.1
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * <p>
     * Finds the n-th index within a CharSequence, handling {@code null}. This method uses {@link String#indexOf(String)} if possible.
     * </p>
     * <p>
     * <b>Note:</b> The code starts looking for a match at the start of the target, incrementing the starting index by one after each successful match (unless {@code searchStr} is an empty string in which case the position is never incremented and {@code 0} is returned immediately). This means that matches may overlap.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}.
     * </p>
     *
     * <pre>
     * StringUtils.ordinalIndexOf(null, *, *)          = -1
     * StringUtils.ordinalIndexOf(*, null, *)          = -1
     * StringUtils.ordinalIndexOf("", "", *)           = 0
     * StringUtils.ordinalIndexOf("aabaabaa", "a", 1)  = 0
     * StringUtils.ordinalIndexOf("aabaabaa", "a", 2)  = 1
     * StringUtils.ordinalIndexOf("aabaabaa", "b", 1)  = 2
     * StringUtils.ordinalIndexOf("aabaabaa", "b", 2)  = 5
     * StringUtils.ordinalIndexOf("aabaabaa", "ab", 1) = 1
     * StringUtils.ordinalIndexOf("aabaabaa", "ab", 2) = 4
     * StringUtils.ordinalIndexOf("aabaabaa", "", 1)   = 0
     * StringUtils.ordinalIndexOf("aabaabaa", "", 2)   = 0
     * </pre>
     * <p>
     * Matches may overlap:
     * </p>
     * 
     * <pre>
     * StringUtils.ordinalIndexOf("ababab","aba", 1)   = 0
     * StringUtils.ordinalIndexOf("ababab","aba", 2)   = 2
     * StringUtils.ordinalIndexOf("ababab","aba", 3)   = -1
     *
     * StringUtils.ordinalIndexOf("abababab", "abab", 1) = 0
     * StringUtils.ordinalIndexOf("abababab", "abab", 2) = 2
     * StringUtils.ordinalIndexOf("abababab", "abab", 3) = 4
     * StringUtils.ordinalIndexOf("abababab", "abab", 4) = -1
     * </pre>
     * <p>
     * Note that 'head(CharSequence str, int n)' may be implemented as:
     * </p>
     *
     * <pre>
     * str.substring(0, lastOrdinalIndexOf(str, "\n", n))
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @param ordinal the n-th {@code searchStr} to find
     * @return the n-th index of the search CharSequence, {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
     * @since 2.1
     * @since 3.0 Changed signature from ordinalIndexOf(String, String, int) to ordinalIndexOf(CharSequence, CharSequence, int)
     */
    public static int ordinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, false);
    }

    /**
     * <p>
     * Finds the n-th index within a String, handling {@code null}. This method uses {@link String#indexOf(String)} if possible.
     * </p>
     * <p>
     * Note that matches may overlap
     * <p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}.
     * </p>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @param ordinal the n-th {@code searchStr} to find, overlapping matches are allowed.
     * @param lastIndex true if lastOrdinalIndexOf() otherwise false if ordinalIndexOf()
     * @return the n-th index of the search CharSequence, {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
     */
    // Shared code between ordinalIndexOf(String,String,int) and lastOrdinalIndexOf(String,String,int)
    private static int ordinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal, final boolean lastIndex) {
        if (str == null || searchStr == null || ordinal <= 0) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return lastIndex ? str.length() : 0;
        }
        int found = 0;
        // set the initial index beyond the end of the string
        // this is to allow for the initial index decrement/increment
        int index = lastIndex ? str.length() : INDEX_NOT_FOUND;
        do {
            if (lastIndex) {
                index = CharSequenceUtils.lastIndexOf(str, searchStr, index - 1); // step backwards thru string
            } else {
                index = CharSequenceUtils.indexOf(str, searchStr, index + 1); // step forwards through string
            }
            if (index < 0) {
                return index;
            }
            found++;
        } while (found < ordinal);
        return index;
    }

    /**
     * <p>
     * Case in-sensitive find of the first index within a CharSequence.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A negative start position is treated as zero. An empty ("") search CharSequence always matches. A start position greater than the string length only matches an empty search CharSequence.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfIgnoreCase(null, *)          = -1
     * StringUtils.indexOfIgnoreCase(*, null)          = -1
     * StringUtils.indexOfIgnoreCase("", "")           = 0
     * StringUtils.indexOfIgnoreCase("aabaabaa", "a")  = 0
     * StringUtils.indexOfIgnoreCase("aabaabaa", "b")  = 2
     * StringUtils.indexOfIgnoreCase("aabaabaa", "ab") = 1
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @return the first index of the search CharSequence, -1 if no match or {@code null} string input
     * @since 2.5
     * @since 3.0 Changed signature from indexOfIgnoreCase(String, String) to indexOfIgnoreCase(CharSequence, CharSequence)
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        return indexOfIgnoreCase(str, searchStr, 0);
    }

    /**
     * <p>
     * Case in-sensitive find of the first index within a CharSequence from the specified position.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A negative start position is treated as zero. An empty ("") search CharSequence always matches. A start position greater than the string length only matches an empty search CharSequence.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfIgnoreCase(null, *, *)          = -1
     * StringUtils.indexOfIgnoreCase(*, null, *)          = -1
     * StringUtils.indexOfIgnoreCase("", "", 0)           = 0
     * StringUtils.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
     * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
     * StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
     * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
     * StringUtils.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
     * StringUtils.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
     * StringUtils.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
     * StringUtils.indexOfIgnoreCase("abc", "", 9)        = -1
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @param startPos the start position, negative treated as zero
     * @return the first index of the search CharSequence (always &ge; startPos), -1 if no match or {@code null} string input
     * @since 2.5
     * @since 3.0 Changed signature from indexOfIgnoreCase(String, String, int) to indexOfIgnoreCase(CharSequence, CharSequence, int)
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (startPos < 0) {
            startPos = 0;
        }
        final int endLimit = str.length() - searchStr.length() + 1;
        if (startPos > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return startPos;
        }
        for (int i = startPos; i < endLimit; i++) {
            if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>
     * Finds the n-th last index within a String, handling {@code null}. This method uses {@link String#lastIndexOf(String)}.
     * </p>
     * <p>
     * A {@code null} String will return {@code -1}.
     * </p>
     *
     * <pre>
     * StringUtils.lastOrdinalIndexOf(null, *, *)          = -1
     * StringUtils.lastOrdinalIndexOf(*, null, *)          = -1
     * StringUtils.lastOrdinalIndexOf("", "", *)           = 0
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1)  = 7
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2)  = 6
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1)  = 5
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2)  = 2
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1) = 4
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2) = 1
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1)   = 8
     * StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2)   = 8
     * </pre>
     * <p>
     * Note that 'tail(CharSequence str, int n)' may be implemented as:
     * </p>
     *
     * <pre>
     * str.substring(lastOrdinalIndexOf(str, "\n", n) + 1)
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @param ordinal the n-th last {@code searchStr} to find
     * @return the n-th last index of the search CharSequence, {@code -1} ({@code INDEX_NOT_FOUND}) if no match or {@code null} string input
     * @since 2.5
     * @since 3.0 Changed signature from lastOrdinalIndexOf(String, String, int) to lastOrdinalIndexOf(CharSequence, CharSequence, int)
     */
    public static int lastOrdinalIndexOf(final CharSequence str, final CharSequence searchStr, final int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, true);
    }

    /**
     * <p>
     * Case in-sensitive find of the last index within a CharSequence.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A negative start position returns {@code -1}. An empty ("") search CharSequence always matches unless the start position is negative. A start position greater than the string length searches the whole string.
     * </p>
     *
     * <pre>
     * StringUtils.lastIndexOfIgnoreCase(null, *)          = -1
     * StringUtils.lastIndexOfIgnoreCase(*, null)          = -1
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A")  = 7
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B")  = 5
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB") = 4
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @return the first index of the search CharSequence, -1 if no match or {@code null} string input
     * @since 2.5
     * @since 3.0 Changed signature from lastIndexOfIgnoreCase(String, String) to lastIndexOfIgnoreCase(CharSequence, CharSequence)
     */
    public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        return lastIndexOfIgnoreCase(str, searchStr, str.length());
    }

    /**
     * <p>
     * Case in-sensitive find of the last index within a CharSequence from the specified position.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A negative start position returns {@code -1}. An empty ("") search CharSequence always matches unless the start position is negative. A start position greater than the string length searches the whole string. The search starts at the startPos and works backwards; matches starting after the start position are ignored.
     * </p>
     *
     * <pre>
     * StringUtils.lastIndexOfIgnoreCase(null, *, *)          = -1
     * StringUtils.lastIndexOfIgnoreCase(*, null, *)          = -1
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8)  = 7
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8)  = 5
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8) = 4
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9)  = 5
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1) = -1
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0)  = 0
     * StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0)  = -1
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @param startPos the start position
     * @return the last index of the search CharSequence (always &le; startPos), -1 if no match or {@code null} input
     * @since 2.5
     * @since 3.0 Changed signature from lastIndexOfIgnoreCase(String, String, int) to lastIndexOfIgnoreCase(CharSequence, CharSequence, int)
     */
    public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (startPos > str.length() - searchStr.length()) {
            startPos = str.length() - searchStr.length();
        }
        if (startPos < 0) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return startPos;
        }

        for (int i = startPos; i >= 0; i--) {
            if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>
     * Checks if CharSequence contains a search CharSequence irrespective of case, handling {@code null}. Case-insensitivity is defined as by {@link String#equalsIgnoreCase(String)}.
     * <p>
     * A {@code null} CharSequence will return {@code false}.
     * </p>
     *
     * <pre>
     * StringUtils.containsIgnoreCase(null, *) = false
     * StringUtils.containsIgnoreCase(*, null) = false
     * StringUtils.containsIgnoreCase("", "") = true
     * StringUtils.containsIgnoreCase("abc", "") = true
     * StringUtils.containsIgnoreCase("abc", "a") = true
     * StringUtils.containsIgnoreCase("abc", "z") = false
     * StringUtils.containsIgnoreCase("abc", "A") = true
     * StringUtils.containsIgnoreCase("abc", "Z") = false
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStr the CharSequence to find, may be null
     * @return true if the CharSequence contains the search CharSequence irrespective of case or false if not or {@code null} string input
     * @since 3.0 Changed signature from containsIgnoreCase(String, String) to containsIgnoreCase(CharSequence, CharSequence)
     */
    public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        final int len = searchStr.length();
        final int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     * Check whether the given CharSequence contains any whitespace characters.
     * </p>
     * </p>
     * Whitespace is defined by {@link Character#isWhitespace(char)}.
     * </p>
     * 
     * @param seq the CharSequence to check (may be {@code null})
     * @return {@code true} if the CharSequence is not empty and contains at least 1 (breaking) whitespace character
     * @since 3.0
     */
    // From org.springframework.util.StringUtils, under Apache License 2.0
    public static boolean containsWhitespace(final CharSequence seq) {
        if (isEmpty(seq)) {
            return false;
        }
        final int strLen = seq.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(seq.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // IndexOfAny chars
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Search a CharSequence to find the first index of any character in the given set of characters.
     * </p>
     * <p>
     * A {@code null} String will return {@code -1}. A {@code null} or zero length search array will return {@code -1}.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfAny(null, *)                = -1
     * StringUtils.indexOfAny("", *)                  = -1
     * StringUtils.indexOfAny(*, null)                = -1
     * StringUtils.indexOfAny(*, [])                  = -1
     * StringUtils.indexOfAny("zzabyycdxx",['z','a']) = 0
     * StringUtils.indexOfAny("zzabyycdxx",['b','y']) = 3
     * StringUtils.indexOfAny("aba", ['z'])           = -1
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     * @since 3.0 Changed signature from indexOfAny(String, char[]) to indexOfAny(CharSequence, char...)
     */
    public static int indexOfAny(final CharSequence cs, final char... searchChars) {
        if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
            return INDEX_NOT_FOUND;
        }
        final int csLen = cs.length();
        final int csLast = csLen - 1;
        final int searchLen = searchChars.length;
        final int searchLast = searchLen - 1;
        for (int i = 0; i < csLen; i++) {
            final char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (i < csLast && j < searchLast && Character.isHighSurrogate(ch)) {
                        // ch is a supplementary character
                        if (searchChars[j + 1] == cs.charAt(i + 1)) {
                            return i;
                        }
                    } else {
                        return i;
                    }
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>
     * Search a CharSequence to find the first index of any character in the given set of characters.
     * </p>
     * <p>
     * A {@code null} String will return {@code -1}. A {@code null} search string will return {@code -1}.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfAny(null, *)            = -1
     * StringUtils.indexOfAny("", *)              = -1
     * StringUtils.indexOfAny(*, null)            = -1
     * StringUtils.indexOfAny(*, "")              = -1
     * StringUtils.indexOfAny("zzabyycdxx", "za") = 0
     * StringUtils.indexOfAny("zzabyycdxx", "by") = 3
     * StringUtils.indexOfAny("aba","z")          = -1
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     * @since 3.0 Changed signature from indexOfAny(String, String) to indexOfAny(CharSequence, String)
     */
    public static int indexOfAny(final CharSequence cs, final String searchChars) {
        if (isEmpty(cs) || isEmpty(searchChars)) {
            return INDEX_NOT_FOUND;
        }
        return indexOfAny(cs, searchChars.toCharArray());
    }

    // ContainsAny
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the CharSequence contains any character in the given set of characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code false}. A {@code null} or zero length search array will return {@code false}.
     * </p>
     *
     * <pre>
     * StringUtils.containsAny(null, *)                = false
     * StringUtils.containsAny("", *)                  = false
     * StringUtils.containsAny(*, null)                = false
     * StringUtils.containsAny(*, [])                  = false
     * StringUtils.containsAny("zzabyycdxx",['z','a']) = true
     * StringUtils.containsAny("zzabyycdxx",['b','y']) = true
     * StringUtils.containsAny("zzabyycdxx",['z','y']) = true
     * StringUtils.containsAny("aba", ['z'])           = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the {@code true} if any of the chars are found, {@code false} if no match or null input
     * @since 2.4
     * @since 3.0 Changed signature from containsAny(String, char[]) to containsAny(CharSequence, char...)
     */
    public static boolean containsAny(final CharSequence cs, final char... searchChars) {
        if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
            return false;
        }
        final int csLength = cs.length();
        final int searchLength = searchChars.length;
        final int csLast = csLength - 1;
        final int searchLast = searchLength - 1;
        for (int i = 0; i < csLength; i++) {
            final char ch = cs.charAt(i);
            for (int j = 0; j < searchLength; j++) {
                if (searchChars[j] == ch) {
                    if (Character.isHighSurrogate(ch)) {
                        if (j == searchLast) {
                            // missing low surrogate, fine, like String.indexOf(String)
                            return true;
                        }
                        if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
                            return true;
                        }
                    } else {
                        // ch is in the Basic Multilingual Plane
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * <p>
     * Checks if the CharSequence contains any character in the given set of characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code false}. A {@code null} search CharSequence will return {@code false}.
     * </p>
     *
     * <pre>
     * StringUtils.containsAny(null, *)               = false
     * StringUtils.containsAny("", *)                 = false
     * StringUtils.containsAny(*, null)               = false
     * StringUtils.containsAny(*, "")                 = false
     * StringUtils.containsAny("zzabyycdxx", "za")    = true
     * StringUtils.containsAny("zzabyycdxx", "by")    = true
     * StringUtils.containsAny("zzabyycdxx", "zy")    = true
     * StringUtils.containsAny("zzabyycdxx", "\tx")   = true
     * StringUtils.containsAny("zzabyycdxx", "$.#yF") = true
     * StringUtils.containsAny("aba","z")             = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the {@code true} if any of the chars are found, {@code false} if no match or null input
     * @since 2.4
     * @since 3.0 Changed signature from containsAny(String, String) to containsAny(CharSequence, CharSequence)
     */
    public static boolean containsAny(final CharSequence cs, final CharSequence searchChars) {
        if (searchChars == null) {
            return false;
        }
        return containsAny(cs, CharSequenceUtils.toCharArray(searchChars));
    }

    /**
     * <p>
     * Checks if the CharSequence contains any of the CharSequences in the given array.
     * </p>
     * <p>
     * A {@code null} {@code cs} CharSequence will return {@code false}. A {@code null} or zero length search array will return {@code false}.
     * </p>
     *
     * <pre>
     * StringUtils.containsAny(null, *)            = false
     * StringUtils.containsAny("", *)              = false
     * StringUtils.containsAny(*, null)            = false
     * StringUtils.containsAny(*, [])              = false
     * StringUtils.containsAny("abcd", "ab", null) = true
     * StringUtils.containsAny("abcd", "ab", "cd") = true
     * StringUtils.containsAny("abc", "d", "abc")  = true
     * </pre>
     *
     * @param cs The CharSequence to check, may be null
     * @param searchCharSequences The array of CharSequences to search for, may be null. Individual CharSequences may be null as well.
     * @return {@code true} if any of the search CharSequences are found, {@code false} otherwise
     * @since 3.4
     */
    public static boolean containsAny(final CharSequence cs, final CharSequence... searchCharSequences) {
        if (isEmpty(cs) || ArrayUtils.isEmpty(searchCharSequences)) {
            return false;
        }
        for (final CharSequence searchCharSequence : searchCharSequences) {
            if (cs.toString().contains(searchCharSequence)) {
                return true;
            }
        }
        return false;
    }

    // IndexOfAnyBut chars
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Searches a CharSequence to find the first index of any character not in the given set of characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A {@code null} or zero length search array will return {@code -1}.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)                              = -1
     * StringUtils.indexOfAnyBut("", *)                                = -1
     * StringUtils.indexOfAnyBut(*, null)                              = -1
     * StringUtils.indexOfAnyBut(*, [])                                = -1
     * StringUtils.indexOfAnyBut("zzabyycdxx", new char[] {'z', 'a'} ) = 3
     * StringUtils.indexOfAnyBut("aba", new char[] {'z'} )             = 0
     * StringUtils.indexOfAnyBut("aba", new char[] {'a', 'b'} )        = -1
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     * @since 3.0 Changed signature from indexOfAnyBut(String, char[]) to indexOfAnyBut(CharSequence, char...)
     */
    public static int indexOfAnyBut(final CharSequence cs, final char... searchChars) {
        if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
            return INDEX_NOT_FOUND;
        }
        final int csLen = cs.length();
        final int csLast = csLen - 1;
        final int searchLen = searchChars.length;
        final int searchLast = searchLen - 1;
        outer: for (int i = 0; i < csLen; i++) {
            final char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (i < csLast && j < searchLast && Character.isHighSurrogate(ch)) {
                        if (searchChars[j + 1] == cs.charAt(i + 1)) {
                            continue outer;
                        }
                    } else {
                        continue outer;
                    }
                }
            }
            return i;
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * <p>
     * Search a CharSequence to find the first index of any character not in the given set of characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A {@code null} or empty search string will return {@code -1}.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfAnyBut(null, *)            = -1
     * StringUtils.indexOfAnyBut("", *)              = -1
     * StringUtils.indexOfAnyBut(*, null)            = -1
     * StringUtils.indexOfAnyBut(*, "")              = -1
     * StringUtils.indexOfAnyBut("zzabyycdxx", "za") = 3
     * StringUtils.indexOfAnyBut("zzabyycdxx", "")   = -1
     * StringUtils.indexOfAnyBut("aba","ab")         = -1
     * </pre>
     *
     * @param seq the CharSequence to check, may be null
     * @param searchChars the chars to search for, may be null
     * @return the index of any of the chars, -1 if no match or null input
     * @since 2.0
     * @since 3.0 Changed signature from indexOfAnyBut(String, String) to indexOfAnyBut(CharSequence, CharSequence)
     */
    public static int indexOfAnyBut(final CharSequence seq, final CharSequence searchChars) {
        if (isEmpty(seq) || isEmpty(searchChars)) {
            return INDEX_NOT_FOUND;
        }
        final int strLen = seq.length();
        for (int i = 0; i < strLen; i++) {
            final char ch = seq.charAt(i);
            final boolean chFound = CharSequenceUtils.indexOf(searchChars, ch, 0) >= 0;
            if (i + 1 < strLen && Character.isHighSurrogate(ch)) {
                final char ch2 = seq.charAt(i + 1);
                if (chFound && CharSequenceUtils.indexOf(searchChars, ch2, 0) < 0) {
                    return i;
                }
            } else {
                if (!chFound) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    // ContainsOnly
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Checks if the CharSequence contains only certain characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code false}. A {@code null} valid character array will return {@code false}. An empty CharSequence (length()=0) always returns {@code true}.
     * </p>
     *
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly("", *)         = true
     * StringUtils.containsOnly("ab", '')      = false
     * StringUtils.containsOnly("abab", 'abc') = true
     * StringUtils.containsOnly("ab1", 'abc')  = false
     * StringUtils.containsOnly("abz", 'abc')  = false
     * </pre>
     *
     * @param cs the String to check, may be null
     * @param valid an array of valid chars, may be null
     * @return true if it only contains valid chars and is non-null
     * @since 3.0 Changed signature from containsOnly(String, char[]) to containsOnly(CharSequence, char...)
     */
    public static boolean containsOnly(final CharSequence cs, final char... valid) {
        // All these pre-checks are to maintain API with an older version
        if (valid == null || cs == null) {
            return false;
        }
        if (cs.length() == 0) {
            return true;
        }
        if (valid.length == 0) {
            return false;
        }
        return indexOfAnyBut(cs, valid) == INDEX_NOT_FOUND;
    }

    /**
     * <p>
     * Checks if the CharSequence contains only certain characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code false}. A {@code null} valid character String will return {@code false}. An empty String (length()=0) always returns {@code true}.
     * </p>
     *
     * <pre>
     * StringUtils.containsOnly(null, *)       = false
     * StringUtils.containsOnly(*, null)       = false
     * StringUtils.containsOnly("", *)         = true
     * StringUtils.containsOnly("ab", "")      = false
     * StringUtils.containsOnly("abab", "abc") = true
     * StringUtils.containsOnly("ab1", "abc")  = false
     * StringUtils.containsOnly("abz", "abc")  = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param validChars a String of valid chars, may be null
     * @return true if it only contains valid chars and is non-null
     * @since 2.0
     * @since 3.0 Changed signature from containsOnly(String, String) to containsOnly(CharSequence, String)
     */
    public static boolean containsOnly(final CharSequence cs, final String validChars) {
        if (cs == null || validChars == null) {
            return false;
        }
        return containsOnly(cs, validChars.toCharArray());
    }

    // ContainsNone
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Checks that the CharSequence does not contain certain characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code true}. A {@code null} invalid character array will return {@code true}. An empty CharSequence (length()=0) always returns true.
     * </p>
     *
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone("", *)         = true
     * StringUtils.containsNone("ab", '')      = true
     * StringUtils.containsNone("abab", 'xyz') = true
     * StringUtils.containsNone("ab1", 'xyz')  = true
     * StringUtils.containsNone("abz", 'xyz')  = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param searchChars an array of invalid chars, may be null
     * @return true if it contains none of the invalid chars, or is null
     * @since 2.0
     * @since 3.0 Changed signature from containsNone(String, char[]) to containsNone(CharSequence, char...)
     */
    public static boolean containsNone(final CharSequence cs, final char... searchChars) {
        if (cs == null || searchChars == null) {
            return true;
        }
        final int csLen = cs.length();
        final int csLast = csLen - 1;
        final int searchLen = searchChars.length;
        final int searchLast = searchLen - 1;
        for (int i = 0; i < csLen; i++) {
            final char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (Character.isHighSurrogate(ch)) {
                        if (j == searchLast) {
                            // missing low surrogate, fine, like String.indexOf(String)
                            return false;
                        }
                        if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
                            return false;
                        }
                    } else {
                        // ch is in the Basic Multilingual Plane
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks that the CharSequence does not contain certain characters.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code true}. A {@code null} invalid character array will return {@code true}. An empty String ("") always returns true.
     * </p>
     *
     * <pre>
     * StringUtils.containsNone(null, *)       = true
     * StringUtils.containsNone(*, null)       = true
     * StringUtils.containsNone("", *)         = true
     * StringUtils.containsNone("ab", "")      = true
     * StringUtils.containsNone("abab", "xyz") = true
     * StringUtils.containsNone("ab1", "xyz")  = true
     * StringUtils.containsNone("abz", "xyz")  = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @param invalidChars a String of invalid chars, may be null
     * @return true if it contains none of the invalid chars, or is null
     * @since 2.0
     * @since 3.0 Changed signature from containsNone(String, String) to containsNone(CharSequence, String)
     */
    public static boolean containsNone(final CharSequence cs, final String invalidChars) {
        if (cs == null || invalidChars == null) {
            return true;
        }
        return containsNone(cs, invalidChars.toCharArray());
    }

    // IndexOfAny strings
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Find the first index of any of a set of potential substrings.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A {@code null} or zero length search array will return {@code -1}. A {@code null} search array entry will be ignored, but a search array containing "" will return {@code 0} if {@code str} is not null. This method uses {@link String#indexOf(String)} if possible.
     * </p>
     *
     * <pre>
     * StringUtils.indexOfAny(null, *)                     = -1
     * StringUtils.indexOfAny(*, null)                     = -1
     * StringUtils.indexOfAny(*, [])                       = -1
     * StringUtils.indexOfAny("zzabyycdxx", ["ab","cd"])   = 2
     * StringUtils.indexOfAny("zzabyycdxx", ["cd","ab"])   = 2
     * StringUtils.indexOfAny("zzabyycdxx", ["mn","op"])   = -1
     * StringUtils.indexOfAny("zzabyycdxx", ["zab","aby"]) = 1
     * StringUtils.indexOfAny("zzabyycdxx", [""])          = 0
     * StringUtils.indexOfAny("", [""])                    = 0
     * StringUtils.indexOfAny("", ["a"])                   = -1
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStrs the CharSequences to search for, may be null
     * @return the first index of any of the searchStrs in str, -1 if no match
     * @since 3.0 Changed signature from indexOfAny(String, String[]) to indexOfAny(CharSequence, CharSequence...)
     */
    public static int indexOfAny(final CharSequence str, final CharSequence... searchStrs) {
        if (str == null || searchStrs == null) {
            return INDEX_NOT_FOUND;
        }
        // String's can't have a MAX_VALUEth index.
        int ret = Integer.MAX_VALUE;

        int tmp = 0;
        for (final CharSequence search : searchStrs) {
            if (search == null) {
                continue;
            }
            tmp = CharSequenceUtils.indexOf(str, search, 0);
            if (tmp == INDEX_NOT_FOUND) {
                continue;
            }

            if (tmp < ret) {
                ret = tmp;
            }
        }

        return ret == Integer.MAX_VALUE ? INDEX_NOT_FOUND : ret;
    }

    /**
     * <p>
     * Find the latest index of any of a set of potential substrings.
     * </p>
     * <p>
     * A {@code null} CharSequence will return {@code -1}. A {@code null} search array will return {@code -1}. A {@code null} or zero length search array entry will be ignored, but a search array containing "" will return the length of {@code str} if {@code str} is not null. This method uses {@link String#indexOf(String)} if possible
     * </p>
     *
     * <pre>
     * StringUtils.lastIndexOfAny(null, *)                   = -1
     * StringUtils.lastIndexOfAny(*, null)                   = -1
     * StringUtils.lastIndexOfAny(*, [])                     = -1
     * StringUtils.lastIndexOfAny(*, [null])                 = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["ab","cd"]) = 6
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["cd","ab"]) = 6
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn","op"]) = -1
     * StringUtils.lastIndexOfAny("zzabyycdxx", ["mn",""])   = 10
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param searchStrs the CharSequences to search for, may be null
     * @return the last index of any of the CharSequences, -1 if no match
     * @since 3.0 Changed signature from lastIndexOfAny(String, String[]) to lastIndexOfAny(CharSequence, CharSequence)
     */
    public static int lastIndexOfAny(final CharSequence str, final CharSequence... searchStrs) {
        if (str == null || searchStrs == null) {
            return INDEX_NOT_FOUND;
        }
        int ret = INDEX_NOT_FOUND;
        int tmp = 0;
        for (final CharSequence search : searchStrs) {
            if (search == null) {
                continue;
            }
            tmp = CharSequenceUtils.lastIndexOf(str, search, str.length());
            if (tmp > ret) {
                ret = tmp;
            }
        }
        return ret;
    }

    // Left/Right/Mid
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Gets the leftmost {@code len} characters of a String.
     * </p>
     * <p>
     * If {@code len} characters are not available, or the String is {@code null}, the String will be returned without an exception. An empty String is returned if len is negative.
     * </p>
     *
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = ""
     * StringUtils.left("", *)      = ""
     * StringUtils.left("abc", 0)   = ""
     * StringUtils.left("abc", 2)   = "ab"
     * StringUtils.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str the String to get the leftmost characters from, may be null
     * @param len the length of the required String
     * @return the leftmost characters, {@code null} if null String input
     */
    public static String left(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * <p>
     * Gets the rightmost {@code len} characters of a String.
     * </p>
     * <p>
     * If {@code len} characters are not available, or the String is {@code null}, the String will be returned without an an exception. An empty String is returned if len is negative.
     * </p>
     *
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = ""
     * StringUtils.right("", *)      = ""
     * StringUtils.right("abc", 0)   = ""
     * StringUtils.right("abc", 2)   = "bc"
     * StringUtils.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str the String to get the rightmost characters from, may be null
     * @param len the length of the required String
     * @return the rightmost characters, {@code null} if null String input
     */
    public static String right(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    /**
     * <p>
     * Gets {@code len} characters from the middle of a String.
     * </p>
     * <p>
     * If {@code len} characters are not available, the remainder of the String will be returned without an exception. If the String is {@code null}, {@code null} will be returned. An empty String is returned if len is negative or exceeds the length of {@code str}.
     * </p>
     *
     * <pre>
     * StringUtils.mid(null, *, *)    = null
     * StringUtils.mid(*, *, -ve)     = ""
     * StringUtils.mid("", 0, *)      = ""
     * StringUtils.mid("abc", 0, 2)   = "ab"
     * StringUtils.mid("abc", 0, 4)   = "abc"
     * StringUtils.mid("abc", 2, 4)   = "c"
     * StringUtils.mid("abc", 4, 2)   = ""
     * StringUtils.mid("abc", -2, 2)  = "ab"
     * </pre>
     *
     * @param str the String to get the characters from, may be null
     * @param pos the position to start from, negative treated as zero
     * @param len the length of the required String
     * @return the middle characters, {@code null} if null String input
     */
    public static String mid(final String str, int pos, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0 || pos > str.length()) {
            return EMPTY;
        }
        if (pos < 0) {
            pos = 0;
        }
        if (str.length() <= pos + len) {
            return str.substring(pos);
        }
        return str.substring(pos, pos + len);
    }

    // SubStringAfter/SubStringBefore
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Gets the substring before the first occurrence of a separator. The separator is not returned.
     * </p>
     * <p>
     * A {@code null} string input will return {@code null}. An empty ("") string input will return the empty string. A {@code null} separator will return the input string.
     * </p>
     * <p>
     * If nothing is found, the string input is returned.
     * </p>
     *
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the first occurrence of the separator, {@code null} if null String input
     * @since 2.0
     */
    public static String substringBefore(final String str, final String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.isEmpty()) {
            return EMPTY;
        }
        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>
     * Gets the substring after the first occurrence of a separator. The separator is not returned.
     * </p>
     * <p>
     * A {@code null} string input will return {@code null}. An empty ("") string input will return the empty string. A {@code null} separator will return the empty string if the input string is not {@code null}.
     * </p>
     * <p>
     * If nothing is found, the empty string is returned.
     * </p>
     *
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the first occurrence of the separator, {@code null} if null String input
     * @since 2.0
     */
    public static String substringAfter(final String str, final String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * <p>
     * Gets the substring before the last occurrence of a separator. The separator is not returned.
     * </p>
     * <p>
     * A {@code null} string input will return {@code null}. An empty ("") string input will return the empty string. An empty or {@code null} separator will return the input string.
     * </p>
     * <p>
     * If nothing is found, the string input is returned.
     * </p>
     *
     * <pre>
     * StringUtils.substringBeforeLast(null, *)      = null
     * StringUtils.substringBeforeLast("", *)        = ""
     * StringUtils.substringBeforeLast("abcba", "b") = "abc"
     * StringUtils.substringBeforeLast("abc", "c")   = "ab"
     * StringUtils.substringBeforeLast("a", "a")     = ""
     * StringUtils.substringBeforeLast("a", "z")     = "a"
     * StringUtils.substringBeforeLast("a", null)    = "a"
     * StringUtils.substringBeforeLast("a", "")      = "a"
     * </pre>
     *
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring before the last occurrence of the separator, {@code null} if null String input
     * @since 2.0
     */
    public static String substringBeforeLast(final String str, final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * <p>
     * Gets the substring after the last occurrence of a separator. The separator is not returned.
     * </p>
     * <p>
     * A {@code null} string input will return {@code null}. An empty ("") string input will return the empty string. An empty or {@code null} separator will return the empty string if the input string is not {@code null}.
     * </p>
     * <p>
     * If nothing is found, the empty string is returned.
     * </p>
     *
     * <pre>
     * StringUtils.substringAfterLast(null, *)      = null
     * StringUtils.substringAfterLast("", *)        = ""
     * StringUtils.substringAfterLast(*, "")        = ""
     * StringUtils.substringAfterLast(*, null)      = ""
     * StringUtils.substringAfterLast("abc", "a")   = "bc"
     * StringUtils.substringAfterLast("abcba", "b") = "a"
     * StringUtils.substringAfterLast("abc", "c")   = ""
     * StringUtils.substringAfterLast("a", "a")     = ""
     * StringUtils.substringAfterLast("a", "z")     = ""
     * </pre>
     *
     * @param str the String to get a substring from, may be null
     * @param separator the String to search for, may be null
     * @return the substring after the last occurrence of the separator, {@code null} if null String input
     * @since 2.0
     */
    public static String substringAfterLast(final String str, final String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (isEmpty(separator)) {
            return EMPTY;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == INDEX_NOT_FOUND || pos == str.length() - separator.length()) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    // Substring between
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Gets the String that is nested in between two instances of the same String.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} tag returns {@code null}.
     * </p>
     *
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween("", "")             = ""
     * StringUtils.substringBetween("", "tag")          = null
     * StringUtils.substringBetween("tagabctag", null)  = null
     * StringUtils.substringBetween("tagabctag", "")    = ""
     * StringUtils.substringBetween("tagabctag", "tag") = "abc"
     * </pre>
     *
     * @param str the String containing the substring, may be null
     * @param tag the String before and after the substring, may be null
     * @return the substring, {@code null} if no match
     * @since 2.0
     */
    public static String substringBetween(final String str, final String tag) {
        return substringBetween(str, tag, tag);
    }

    /**
     * <p>
     * Gets the String that is nested in between two Strings. Only the first match is returned.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} open/close returns {@code null} (no match). An empty ("") open and close returns an empty string.
     * </p>
     *
     * <pre>
     * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
     * StringUtils.substringBetween(null, *, *)          = null
     * StringUtils.substringBetween(*, null, *)          = null
     * StringUtils.substringBetween(*, *, null)          = null
     * StringUtils.substringBetween("", "", "")          = ""
     * StringUtils.substringBetween("", "", "]")         = null
     * StringUtils.substringBetween("", "[", "]")        = null
     * StringUtils.substringBetween("yabcz", "", "")     = ""
     * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
     * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
     * </pre>
     *
     * @param str the String containing the substring, may be null
     * @param open the String before the substring, may be null
     * @param close the String after the substring, may be null
     * @return the substring, {@code null} if no match
     * @since 2.0
     */
    public static String substringBetween(final String str, final String open, final String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            final int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    /**
     * <p>
     * Searches a String for substrings delimited by a start and end tag, returning all matching substrings in an array.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} open/close returns {@code null} (no match). An empty ("") open/close returns {@code null} (no match).
     * </p>
     *
     * <pre>
     * StringUtils.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
     * StringUtils.substringsBetween(null, *, *)            = null
     * StringUtils.substringsBetween(*, null, *)            = null
     * StringUtils.substringsBetween(*, *, null)            = null
     * StringUtils.substringsBetween("", "[", "]")          = []
     * </pre>
     *
     * @param str the String containing the substrings, null returns null, empty returns empty
     * @param open the String identifying the start of the substring, empty returns null
     * @param close the String identifying the end of the substring, empty returns null
     * @return a String Array of substrings, or {@code null} if no match
     * @since 2.3
     */
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final int strLen = str.length();
        if (strLen == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified.
     * </p>
     * <p>
     * The separator(s) will not be included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     *
     * <pre>
     * StringUtils.splitByWholeSeparator(null, *)               = null
     * StringUtils.splitByWholeSeparator("", *)                 = []
     * StringUtils.splitByWholeSeparator("ab de fg", null)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab   de fg", null)    = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String was input
     */
    public static String[] splitByWholeSeparator(final String str, final String separator) {
        return splitByWholeSeparatorWorker(str, separator, -1, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified. Returns a maximum of {@code max} substrings.
     * </p>
     * <p>
     * The separator(s) will not be included in the returned String array. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     *
     * <pre>
     * StringUtils.splitByWholeSeparator(null, *, *)               = null
     * StringUtils.splitByWholeSeparator("", *, *)                 = []
     * StringUtils.splitByWholeSeparator("ab de fg", null, 0)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab   de fg", null, 0)    = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparator("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
     * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
     * </pre>
     *
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no limit.
     * @return an array of parsed Strings, {@code null} if null String was input
     */
    public static String[] splitByWholeSeparator(final String str, final String separator, final int max) {
        return splitByWholeSeparatorWorker(str, separator, max, false);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     *
     * <pre>
     * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *)               = null
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *)                 = []
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null)    = ["ab", "", "", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":")       = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String was input
     * @since 2.4
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator) {
        return splitByWholeSeparatorWorker(str, separator, -1, true);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator string specified. Returns a maximum of {@code max} substrings.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separator splits on whitespace.
     * </p>
     *
     * <pre>
     * StringUtils.splitByWholeSeparatorPreserveAllTokens(null, *, *)               = null
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("", *, *)                 = []
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab de fg", null, 0)      = ["ab", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab   de fg", null, 0)    = ["ab", "", "", "de", "fg"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab:cd:ef", ":", 2)       = ["ab", "cd:ef"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 5) = ["ab", "cd", "ef"]
     * StringUtils.splitByWholeSeparatorPreserveAllTokens("ab-!-cd-!-ef", "-!-", 2) = ["ab", "cd-!-ef"]
     * </pre>
     *
     * @param str the String to parse, may be null
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no limit.
     * @return an array of parsed Strings, {@code null} if null String was input
     * @since 2.4
     */
    public static String[] splitByWholeSeparatorPreserveAllTokens(final String str, final String separator, final int max) {
        return splitByWholeSeparatorWorker(str, separator, max, true);
    }

    /**
     * Performs the logic for the {@code splitByWholeSeparatorPreserveAllTokens} methods.
     *
     * @param str the String to parse, may be {@code null}
     * @param separator String containing the String to be used as a delimiter, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the returned array. A zero or negative value implies no limit.
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty token separators; if {@code false}, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    private static String[] splitByWholeSeparatorWorker(final String str, final String separator, final int max, final boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }

        final int len = str.length();

        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        if (separator == null || EMPTY.equals(separator)) {
            // Split on whitespace.
            return splitWorker(str, null, max, preserveAllTokens);
        }

        final int separatorLength = separator.length();

        final ArrayList<String> substrings = new ArrayList<>();
        int numberOfSubstrings = 0;
        int beg = 0;
        int end = 0;
        while (end < len) {
            end = str.indexOf(separator, beg);

            if (end > -1) {
                if (end > beg) {
                    numberOfSubstrings += 1;

                    if (numberOfSubstrings == max) {
                        end = len;
                        substrings.add(str.substring(beg));
                    } else {
                        // The following is OK, because String.substring( beg, end ) excludes
                        // the character at the position 'end'.
                        substrings.add(str.substring(beg, end));

                        // Set the starting point for the next search.
                        // The following is equivalent to beg = end + (separatorLength - 1) + 1,
                        // which is the right calculation:
                        beg = end + separatorLength;
                    }
                } else {
                    // We found a consecutive occurrence of the separator, so skip it.
                    if (preserveAllTokens) {
                        numberOfSubstrings += 1;
                        if (numberOfSubstrings == max) {
                            end = len;
                            substrings.add(str.substring(beg));
                        } else {
                            substrings.add(EMPTY);
                        }
                    }
                    beg = end + separatorLength;
                }
            } else {
                // String.substring( beg ) goes from 'beg' to the end of the String.
                substrings.add(str.substring(beg));
                end = len;
            }
        }

        return substrings.toArray(new String[substrings.size()]);
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Splits the provided text into an array, using whitespace as the separator, preserving all tokens, including empty tokens created by adjacent separators. This is an alternative to using StringTokenizer. Whitespace is defined by {@link Character#isWhitespace(char)}.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}.
     * </p>
     *
     * <pre>
     * StringUtils.splitPreserveAllTokens(null)       = null
     * StringUtils.splitPreserveAllTokens("")         = []
     * StringUtils.splitPreserveAllTokens("abc def")  = ["abc", "def"]
     * StringUtils.splitPreserveAllTokens("abc  def") = ["abc", "", "def"]
     * StringUtils.splitPreserveAllTokens(" abc ")    = ["", "abc", ""]
     * </pre>
     *
     * @param str the String to parse, may be {@code null}
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str) {
        return splitWorker(str, null, -1, true);
    }

    /**
     * <p>
     * Splits the provided text into an array, separator specified, preserving all tokens, including empty tokens created by adjacent separators. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}.
     * </p>
     *
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *)         = null
     * StringUtils.splitPreserveAllTokens("", *)           = []
     * StringUtils.splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
     * StringUtils.splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("a b c ", ' ')   = ["a", "b", "c", ""]
     * StringUtils.splitPreserveAllTokens("a b c  ", ' ')   = ["a", "b", "c", "", ""]
     * StringUtils.splitPreserveAllTokens(" a b c", ' ')   = ["", a", "b", "c"]
     * StringUtils.splitPreserveAllTokens("  a b c", ' ')  = ["", "", a", "b", "c"]
     * StringUtils.splitPreserveAllTokens(" a b c ", ' ')  = ["", a", "b", "c", ""]
     * </pre>
     *
     * @param str the String to parse, may be {@code null}
     * @param separatorChar the character used as the delimiter, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str, final char separatorChar) {
        return splitWorker(str, separatorChar, true);
    }

    /**
     * Performs the logic for the {@code split} and {@code splitPreserveAllTokens} methods that do not return a maximum array length.
     *
     * @param str the String to parse, may be {@code null}
     * @param separatorChar the separate character
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty token separators; if {@code false}, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     */
    private static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * <p>
     * Splits the provided text into an array, separators specified, preserving all tokens, including empty tokens created by adjacent separators. This is an alternative to using StringTokenizer.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for empty tokens. For more control over the split use the StrTokenizer class.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     *
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *)           = null
     * StringUtils.splitPreserveAllTokens("", *)             = []
     * StringUtils.splitPreserveAllTokens("abc def", null)   = ["abc", "def"]
     * StringUtils.splitPreserveAllTokens("abc def", " ")    = ["abc", "def"]
     * StringUtils.splitPreserveAllTokens("abc  def", " ")   = ["abc", "", def"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":")   = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef:", ":")  = ["ab", "cd", "ef", ""]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef::", ":") = ["ab", "cd", "ef", "", ""]
     * StringUtils.splitPreserveAllTokens("ab::cd:ef", ":")  = ["ab", "", cd", "ef"]
     * StringUtils.splitPreserveAllTokens(":cd:ef", ":")     = ["", cd", "ef"]
     * StringUtils.splitPreserveAllTokens("::cd:ef", ":")    = ["", "", cd", "ef"]
     * StringUtils.splitPreserveAllTokens(":cd:ef:", ":")    = ["", cd", "ef", ""]
     * </pre>
     *
     * @param str the String to parse, may be {@code null}
     * @param separatorChars the characters used as the delimiters, {@code null} splits on whitespace
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str, final String separatorChars) {
        return splitWorker(str, separatorChars, -1, true);
    }

    /**
     * <p>
     * Splits the provided text into an array with a maximum length, separators specified, preserving all tokens, including empty tokens created by adjacent separators.
     * </p>
     * <p>
     * The separator is not included in the returned String array. Adjacent separators are treated as separators for empty tokens. Adjacent separators are treated as one separator.
     * </p>
     * <p>
     * A {@code null} input String returns {@code null}. A {@code null} separatorChars splits on whitespace.
     * </p>
     * <p>
     * If more than {@code max} delimited substrings are found, the last returned string includes all characters after the first {@code max - 1} returned strings (including separator characters).
     * </p>
     *
     * <pre>
     * StringUtils.splitPreserveAllTokens(null, *, *)            = null
     * StringUtils.splitPreserveAllTokens("", *, *)              = []
     * StringUtils.splitPreserveAllTokens("ab de fg", null, 0)   = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 0) = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
     * StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 2) = ["ab", "  de fg"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 3) = ["ab", "", " de fg"]
     * StringUtils.splitPreserveAllTokens("ab   de fg", null, 4) = ["ab", "", "", "de fg"]
     * </pre>
     *
     * @param str the String to parse, may be {@code null}
     * @param separatorChars the characters used as the delimiters, {@code null} splits on whitespace
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.1
     */
    public static String[] splitPreserveAllTokens(final String str, final String separatorChars, final int max) {
        return splitWorker(str, separatorChars, max, true);
    }

    /**
     * Performs the logic for the {@code split} and {@code splitPreserveAllTokens} methods that return a maximum array length.
     *
     * @param str the String to parse, may be {@code null}
     * @param separatorChars the separate character
     * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit.
     * @param preserveAllTokens if {@code true}, adjacent separators are treated as empty token separators; if {@code false}, adjacent separators are treated as one separator.
     * @return an array of parsed Strings, {@code null} if null String input
     */
    private static String[] splitWorker(final String str, final String separatorChars, final int max, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()

        if (str == null) {
            return null;
        }
        final int len = str.length();
        if (len == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final List<String> list = new ArrayList<>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by {@code java.lang.Character.getType(char)}. Groups of contiguous characters of the same type are returned as complete tokens.
     * 
     * <pre>
     * StringUtils.splitByCharacterType(null)         = null
     * StringUtils.splitByCharacterType("")           = []
     * StringUtils.splitByCharacterType("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtils.splitByCharacterType("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtils.splitByCharacterType("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtils.splitByCharacterType("number5")    = ["number", "5"]
     * StringUtils.splitByCharacterType("fooBar")     = ["foo", "B", "ar"]
     * StringUtils.splitByCharacterType("foo200Bar")  = ["foo", "200", "B", "ar"]
     * StringUtils.splitByCharacterType("ASFRules")   = ["ASFR", "ules"]
     * </pre>
     * 
     * @param str the String to split, may be {@code null}
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    public static String[] splitByCharacterType(final String str) {
        return splitByCharacterType(str, false);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by {@code java.lang.Character.getType(char)}. Groups of contiguous characters of the same type are returned as complete tokens, with the following exception: the character of type {@code Character.UPPERCASE_LETTER}, if any, immediately preceding a token of type {@code Character.LOWERCASE_LETTER} will belong to the following token rather than to the preceding, if any, {@code Character.UPPERCASE_LETTER} token.
     * 
     * <pre>
     * StringUtils.splitByCharacterTypeCamelCase(null)         = null
     * StringUtils.splitByCharacterTypeCamelCase("")           = []
     * StringUtils.splitByCharacterTypeCamelCase("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtils.splitByCharacterTypeCamelCase("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtils.splitByCharacterTypeCamelCase("number5")    = ["number", "5"]
     * StringUtils.splitByCharacterTypeCamelCase("fooBar")     = ["foo", "Bar"]
     * StringUtils.splitByCharacterTypeCamelCase("foo200Bar")  = ["foo", "200", "Bar"]
     * StringUtils.splitByCharacterTypeCamelCase("ASFRules")   = ["ASF", "Rules"]
     * </pre>
     * 
     * @param str the String to split, may be {@code null}
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    public static String[] splitByCharacterTypeCamelCase(final String str) {
        return splitByCharacterType(str, true);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by {@code java.lang.Character.getType(char)}. Groups of contiguous characters of the same type are returned as complete tokens, with the following exception: if {@code camelCase} is {@code true}, the character of type {@code Character.UPPERCASE_LETTER}, if any, immediately preceding a token of type {@code Character.LOWERCASE_LETTER} will belong to the following token rather than to the preceding, if any, {@code Character.UPPERCASE_LETTER} token.
     * 
     * @param str the String to split, may be {@code null}
     * @param camelCase whether to use so-called "camel-case" for letter types
     * @return an array of parsed Strings, {@code null} if null String input
     * @since 2.4
     */
    private static String[] splitByCharacterType(final String str, final boolean camelCase) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final char[] c = str.toCharArray();
        final List<String> list = new ArrayList<>();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++) {
            final int type = Character.getType(c[pos]);
            if (type == currentType) {
                continue;
            }
            if (camelCase && type == Character.LOWERCASE_LETTER && currentType == Character.UPPERCASE_LETTER) {
                final int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart) {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            } else {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }
        list.add(new String(c, tokenStart, c.length - tokenStart));
        return list.toArray(new String[list.size()]);
    }

    // Defaults
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Returns either the passed in String, or if the String is {@code null}, an empty String ("").
     * </p>
     *
     * <pre>
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * </pre>
     *
     * @see ObjectUtils#toString(Object)
     * @see String#valueOf(Object)
     * @param str the String to check, may be null
     * @return the passed in String, or the empty String if it was {@code null}
     */
    public static String defaultString(final String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * <p>
     * Returns either the passed in String, or if the String is {@code null}, the value of {@code defaultStr}.
     * </p>
     *
     * <pre>
     * StringUtils.defaultString(null, "NULL")  = "NULL"
     * StringUtils.defaultString("", "NULL")    = ""
     * StringUtils.defaultString("bat", "NULL") = "bat"
     * </pre>
     *
     * @see ObjectUtils#toString(Object,String)
     * @see String#valueOf(Object)
     * @param str the String to check, may be null
     * @param defaultStr the default String to return if the input is {@code null}, may be null
     * @return the passed in String, or the default if it was {@code null}
     */
    public static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;
    }

    /**
     * <p>
     * Returns either the passed in CharSequence, or if the CharSequence is whitespace, empty ("") or {@code null}, the value of {@code defaultStr}.
     * </p>
     * </p>
     * Whitespace is defined by {@link Character#isWhitespace(char)}.
     * </p>
     *
     * <pre>
     * StringUtils.defaultIfBlank(null, "NULL")  = "NULL"
     * StringUtils.defaultIfBlank("", "NULL")    = "NULL"
     * StringUtils.defaultIfBlank(" ", "NULL")   = "NULL"
     * StringUtils.defaultIfBlank("bat", "NULL") = "bat"
     * StringUtils.defaultIfBlank("", null)      = null
     * </pre>
     * 
     * @param <T> the specific kind of CharSequence
     * @param str the CharSequence to check, may be null
     * @param defaultStr the default CharSequence to return if the input is whitespace, empty ("") or {@code null}, may be null
     * @return the passed in CharSequence, or the default
     * @see StringUtils#defaultString(String, String)
     */
    public static <T extends CharSequence> T defaultIfBlank(final T str, final T defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * <p>
     * Returns either the passed in CharSequence, or if the CharSequence is empty or {@code null}, the value of {@code defaultStr}.
     * </p>
     *
     * <pre>
     * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
     * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
     * StringUtils.defaultIfEmpty(" ", "NULL")   = " "
     * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
     * StringUtils.defaultIfEmpty("", null)      = null
     * </pre>
     * 
     * @param <T> the specific kind of CharSequence
     * @param str the CharSequence to check, may be null
     * @param defaultStr the default CharSequence to return if the input is empty ("") or {@code null}, may be null
     * @return the passed in CharSequence, or the default
     * @see StringUtils#defaultString(String, String)
     */
    public static <T extends CharSequence> T defaultIfEmpty(final T str, final T defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * <p>
     * Joins the elements of the provided varargs into a single String containing the provided elements.
     * </p>
     * <p>
     * No delimiter is added before or after the list. {@code null} elements and separator are treated as empty Strings ("").
     * </p>
     *
     * <pre>
     * StringUtils.joinWith(",", {"a", "b"})        = "a,b"
     * StringUtils.joinWith(",", {"a", "b",""})     = "a,b,"
     * StringUtils.joinWith(",", {"a", null, "b"})  = "a,,b"
     * StringUtils.joinWith(null, {"a", "b"})       = "ab"
     * </pre>
     *
     * @param separator the separator character to use, null treated as ""
     * @param objects the varargs providing the values to join together. {@code null} elements are treated as ""
     * @return the joined String.
     * @throws java.lang.IllegalArgumentException if a null varargs is provided
     * @since 3.5
     */
    public static String joinWith(final String separator, final Object... objects) {
        if (objects == null) {
            throw new IllegalArgumentException("Object varargs must not be null");
        }

        final String sanitizedSeparator = defaultString(separator, EMPTY);

        final StringBuilder result = new StringBuilder();

        final Iterator<Object> iterator = Arrays.asList(objects).iterator();
        while (iterator.hasNext()) {
            final String value = Objects.toString(iterator.next(), "");
            result.append(value);

            if (iterator.hasNext()) {
                result.append(sanitizedSeparator);
            }
        }

        return result.toString();
    }

    // Delete
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Deletes all whitespaces from a String as defined by {@link Character#isWhitespace(char)}.
     * </p>
     *
     * <pre>
     * StringUtils.deleteWhitespace(null)         = null
     * StringUtils.deleteWhitespace("")           = ""
     * StringUtils.deleteWhitespace("abc")        = "abc"
     * StringUtils.deleteWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str the String to delete whitespace from, may be null
     * @return the String without whitespaces, {@code null} if null String input
     */
    public static String deleteWhitespace(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        final int sz = str.length();
        final char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }

    // Remove
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Removes a substring only if it is at the beginning of a source string, otherwise returns the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string. A {@code null} search string will return the source string.
     * </p>
     *
     * <pre>
     * StringUtils.removeStart(null, *)      = null
     * StringUtils.removeStart("", *)        = ""
     * StringUtils.removeStart(*, null)      = *
     * StringUtils.removeStart("www.domain.com", "www.")   = "domain.com"
     * StringUtils.removeStart("domain.com", "www.")       = "domain.com"
     * StringUtils.removeStart("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeStart("abc", "")    = "abc"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null String input
     * @since 2.1
     */
    public static String removeStart(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * <p>
     * Case insensitive removal of a substring if it is at the beginning of a source string, otherwise returns the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string. A {@code null} search string will return the source string.
     * </p>
     *
     * <pre>
     * StringUtils.removeStartIgnoreCase(null, *)      = null
     * StringUtils.removeStartIgnoreCase("", *)        = ""
     * StringUtils.removeStartIgnoreCase(*, null)      = *
     * StringUtils.removeStartIgnoreCase("www.domain.com", "www.")   = "domain.com"
     * StringUtils.removeStartIgnoreCase("www.domain.com", "WWW.")   = "domain.com"
     * StringUtils.removeStartIgnoreCase("domain.com", "www.")       = "domain.com"
     * StringUtils.removeStartIgnoreCase("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeStartIgnoreCase("abc", "")    = "abc"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for (case insensitive) and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null String input
     * @since 2.4
     */
    public static String removeStartIgnoreCase(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (startsWithIgnoreCase(str, remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * <p>
     * Case insensitive check if a CharSequence starts with a specified prefix.
     * </p>
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The comparison is case insensitive.
     * </p>
     *
     * <pre>
     * StringUtils.startsWithIgnoreCase(null, null)      = true
     * StringUtils.startsWithIgnoreCase(null, "abc")     = false
     * StringUtils.startsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.startsWithIgnoreCase("abcdef", "abc") = true
     * StringUtils.startsWithIgnoreCase("ABCDEF", "abc") = true
     * </pre>
     *
     * @see java.lang.String#startsWith(String)
     * @param str the CharSequence to check, may be null
     * @param prefix the prefix to find, may be null
     * @return {@code true} if the CharSequence starts with the prefix, case insensitive, or both {@code null}
     * @since 2.4
     * @since 3.0 Changed signature from startsWithIgnoreCase(String, String) to startsWithIgnoreCase(CharSequence, CharSequence)
     */
    public static boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
        return _startsWith(str, prefix, true);
    }

    /**
     * <p>
     * Check if a CharSequence starts with a specified prefix (optionally case insensitive).
     * </p>
     *
     * @see java.lang.String#startsWith(String)
     * @param str the CharSequence to check, may be null
     * @param prefix the prefix to find, may be null
     * @param ignoreCase indicates whether the compare should ignore case (case insensitive) or not.
     * @return {@code true} if the CharSequence starts with the prefix or both {@code null}
     */
    private static boolean _startsWith(final CharSequence str, final CharSequence prefix, final boolean ignoreCase) {
        if (str == null || prefix == null) {
            return str == null && prefix == null;
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return CharSequenceUtils.regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
    }

    /**
     * <p>
     * Removes a substring only if it is at the end of a source string, otherwise returns the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string. A {@code null} search string will return the source string.
     * </p>
     *
     * <pre>
     * StringUtils.removeEnd(null, *)      = null
     * StringUtils.removeEnd("", *)        = ""
     * StringUtils.removeEnd(*, null)      = *
     * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
     * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeEnd("abc", "")    = "abc"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null String input
     * @since 2.1
     */
    public static String removeEnd(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * <p>
     * Case insensitive removal of a substring if it is at the end of a source string, otherwise returns the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string. A {@code null} search string will return the source string.
     * </p>
     *
     * <pre>
     * StringUtils.removeEndIgnoreCase(null, *)      = null
     * StringUtils.removeEndIgnoreCase("", *)        = ""
     * StringUtils.removeEndIgnoreCase(*, null)      = *
     * StringUtils.removeEndIgnoreCase("www.domain.com", ".com.")  = "www.domain.com"
     * StringUtils.removeEndIgnoreCase("www.domain.com", ".com")   = "www.domain"
     * StringUtils.removeEndIgnoreCase("www.domain.com", "domain") = "www.domain.com"
     * StringUtils.removeEndIgnoreCase("abc", "")    = "abc"
     * StringUtils.removeEndIgnoreCase("www.domain.com", ".COM") = "www.domain")
     * StringUtils.removeEndIgnoreCase("www.domain.COM", ".com") = "www.domain")
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for (case insensitive) and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null String input
     * @since 2.4
     */
    public static String removeEndIgnoreCase(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (endsWithIgnoreCase(str, remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * <p>
     * Case insensitive check if a CharSequence ends with a specified suffix.
     * </p>
     * <p>
     * {@code null}s are handled without exceptions. Two {@code null} references are considered to be equal. The comparison is case insensitive.
     * </p>
     *
     * <pre>
     * StringUtils.endsWithIgnoreCase(null, null)      = true
     * StringUtils.endsWithIgnoreCase(null, "def")     = false
     * StringUtils.endsWithIgnoreCase("abcdef", null)  = false
     * StringUtils.endsWithIgnoreCase("abcdef", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "def") = true
     * StringUtils.endsWithIgnoreCase("ABCDEF", "cde") = false
     * </pre>
     *
     * @see java.lang.String#endsWith(String)
     * @param str the CharSequence to check, may be null
     * @param suffix the suffix to find, may be null
     * @return {@code true} if the CharSequence ends with the suffix, case insensitive, or both {@code null}
     * @since 2.4
     * @since 3.0 Changed signature from endsWithIgnoreCase(String, String) to endsWithIgnoreCase(CharSequence, CharSequence)
     */
    public static boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
        return _endsWith(str, suffix, true);
    }

    /**
     * <p>
     * Check if a CharSequence ends with a specified suffix (optionally case insensitive).
     * </p>
     *
     * @see java.lang.String#endsWith(String)
     * @param str the CharSequence to check, may be null
     * @param suffix the suffix to find, may be null
     * @param ignoreCase indicates whether the compare should ignore case (case insensitive) or not.
     * @return {@code true} if the CharSequence starts with the prefix or both {@code null}
     */
    private static boolean _endsWith(final CharSequence str, final CharSequence suffix, final boolean ignoreCase) {
        if (str == null || suffix == null) {
            return str == null && suffix == null;
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        final int strOffset = str.length() - suffix.length();
        return CharSequenceUtils.regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
    }

    /**
     * <p>
     * Removes all occurrences of a substring from within the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string. A {@code null} remove string will return the source string. An empty ("") remove string will return the source string.
     * </p>
     *
     * <pre>
     * StringUtils.remove(null, *)        = null
     * StringUtils.remove("", *)          = ""
     * StringUtils.remove(*, null)        = *
     * StringUtils.remove(*, "")          = *
     * StringUtils.remove("queued", "ue") = "qd"
     * StringUtils.remove("queued", "zz") = "queued"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null String input
     * @since 2.1
     */
    public static String remove(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        return _replace(str, remove, EMPTY, -1);
    }

    /**
     * <p>
     * Replaces a String with another String inside a larger String, for the first {@code max} values of the search String.
     * </p>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for, may be null
     * @param replacement the String to replace it with, may be null
     * @param max maximum number of values to replace, or {@code -1} if no maximum
     * @return the text with any replacements processed, {@code null} if null String input
     */
    public static String _replace(final String text, final String searchString, final String replacement, final int max) {
        return _replace(text, searchString, replacement, max, false);
    }

    /**
     * <p>
     * Replaces a String with another String inside a larger String, for the first {@code max} values of the search String, case sensitively/insensisitively based on {@code ignoreCase} value.
     * </p>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replace(null, *, *, *, false)         = null
     * StringUtils.replace("", *, *, *, false)           = ""
     * StringUtils.replace("any", null, *, *, false)     = "any"
     * StringUtils.replace("any", *, null, *, false)     = "any"
     * StringUtils.replace("any", "", *, *, false)       = "any"
     * StringUtils.replace("any", *, *, 0, false)        = "any"
     * StringUtils.replace("abaa", "a", null, -1, false) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1, false)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0, false)   = "abaa"
     * StringUtils.replace("abaa", "A", "z", 1, false)   = "abaa"
     * StringUtils.replace("abaa", "A", "z", 1, true)   = "zbaa"
     * StringUtils.replace("abAa", "a", "z", 2, true)   = "zbza"
     * StringUtils.replace("abAa", "a", "z", -1, true)  = "zbzz"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for (case insensitive), may be null
     * @param replacement the String to replace it with, may be null
     * @param max maximum number of values to replace, or {@code -1} if no maximum
     * @param ignoreCase if true replace is case insensitive, otherwise case sensitive
     * @return the text with any replacements processed, {@code null} if null String input
     */
    private static String _replace(final String text, String searchString, final String replacement, int max, final boolean ignoreCase) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        String searchText = text;
        if (ignoreCase) {
            searchText = text.toLowerCase();
            searchString = searchString.toLowerCase();
        }
        int start = 0;
        int end = searchText.indexOf(searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        final int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase < 0 ? 0 : increase;
        increase *= max < 0 ? 16 : max > 64 ? 64 : max;
        final StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = searchText.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * <p>
     * Case insensitive removal of all occurrences of a substring from within the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string. A {@code null} remove string will return the source string. An empty ("") remove string will return the source string.
     * </p>
     *
     * <pre>
     * StringUtils.removeIgnoreCase(null, *)        = null
     * StringUtils.removeIgnoreCase("", *)          = ""
     * StringUtils.removeIgnoreCase(*, null)        = *
     * StringUtils.removeIgnoreCase(*, "")          = *
     * StringUtils.removeIgnoreCase("queued", "ue") = "qd"
     * StringUtils.removeIgnoreCase("queued", "zz") = "queued"
     * StringUtils.removeIgnoreCase("quEUed", "UE") = "qd"
     * StringUtils.removeIgnoreCase("queued", "zZ") = "queued"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the String to search for (case insensitive) and remove, may be null
     * @return the substring with the string removed if found, {@code null} if null String input
     * @since 3.5
     */
    public static String removeIgnoreCase(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        return replaceIgnoreCase(str, remove, EMPTY, -1);
    }

    /**
     * <p>
     * Case insensitively replaces a String with another String inside a larger String, for the first {@code max} values of the search String.
     * </p>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replaceIgnoreCase(null, *, *, *)         = null
     * StringUtils.replaceIgnoreCase("", *, *, *)           = ""
     * StringUtils.replaceIgnoreCase("any", null, *, *)     = "any"
     * StringUtils.replaceIgnoreCase("any", *, null, *)     = "any"
     * StringUtils.replaceIgnoreCase("any", "", *, *)       = "any"
     * StringUtils.replaceIgnoreCase("any", *, *, 0)        = "any"
     * StringUtils.replaceIgnoreCase("abaa", "a", null, -1) = "abaa"
     * StringUtils.replaceIgnoreCase("abaa", "a", "", -1)   = "b"
     * StringUtils.replaceIgnoreCase("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replaceIgnoreCase("abaa", "A", "z", 1)   = "zbaa"
     * StringUtils.replaceIgnoreCase("abAa", "a", "z", 2)   = "zbza"
     * StringUtils.replaceIgnoreCase("abAa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for (case insensitive), may be null
     * @param replacement the String to replace it with, may be null
     * @param max maximum number of values to replace, or {@code -1} if no maximum
     * @return the text with any replacements processed, {@code null} if null String input
     * @since 3.5
     */
    public static String replaceIgnoreCase(final String text, final String searchString, final String replacement, final int max) {
        return _replace(text, searchString, replacement, max, true);
    }

    /**
     * <p>
     * Removes all occurrences of a character from within the source string.
     * </p>
     * <p>
     * A {@code null} source string will return {@code null}. An empty ("") source string will return the empty string.
     * </p>
     *
     * <pre>
     * StringUtils.remove(null, *)       = null
     * StringUtils.remove("", *)         = ""
     * StringUtils.remove("queued", 'u') = "qeed"
     * StringUtils.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str the source String to search, may be null
     * @param remove the char to search for and remove, may be null
     * @return the substring with the char removed if found, {@code null} if null String input
     * @since 2.1
     */
    public static String remove(final String str, final char remove) {
        if (isEmpty(str) || str.indexOf(remove) == INDEX_NOT_FOUND) {
            return str;
        }
        final char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * <p>
     * Removes each substring of the text String that matches the given regular expression.
     * </p>
     * This method is a {@code null} safe equivalent to:
     * <ul>
     * <li>{@code text.replaceAll(regex, StringUtils.EMPTY)}</li>
     * <li>{@code Pattern.compile(regex).matcher(text).replaceAll(StringUtils.EMPTY)}</li>
     * </ul>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     * <p>
     * Unlike in the {@link #removePattern(String, String)} method, the {@link Pattern#DOTALL} option is NOT automatically added. To use the DOTALL option prepend <code>"(?s)"</code> to the regex. DOTALL is also know as single-line mode in Perl.
     * </p>
     *
     * <pre>
     * StringUtils.removeAll(null, *)      = null
     * StringUtils.removeAll("any", null)  = "any"
     * StringUtils.removeAll("any", "")    = "any"
     * StringUtils.removeAll("any", ".*")  = ""
     * StringUtils.removeAll("any", ".+")  = ""
     * StringUtils.removeAll("abc", ".?")  = ""
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\nB"
     * StringUtils.removeAll("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
     * StringUtils.removeAll("ABCabc123abc", "[a-z]")     = "ABC123"
     * </pre>
     *
     * @param text text to remove from, may be null
     * @param regex the regular expression to which this string is to be matched
     * @return the text with any removes processed, {@code null} if null String input
     * @throws java.util.regex.PatternSyntaxException if the regular expression's syntax is invalid
     * @see #replaceAll(String, String, String)
     * @see #removePattern(String, String)
     * @see String#replaceAll(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     * @since 3.5
     */
    public static String removeAll(final String text, final String regex) {
        return _replaceAll(text, regex, EMPTY);
    }

    /**
     * <p>
     * Replaces each substring of the text String that matches the given regular expression with the given replacement.
     * </p>
     * This method is a {@code null} safe equivalent to:
     * <ul>
     * <li>{@code text.replaceAll(regex, replacement)}</li>
     * <li>{@code Pattern.compile(regex).matcher(text).replaceAll(replacement)}</li>
     * </ul>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     * <p>
     * Unlike in the {@link #replacePattern(String, String, String)} method, the {@link Pattern#DOTALL} option is NOT automatically added. To use the DOTALL option prepend <code>"(?s)"</code> to the regex. DOTALL is also know as single-line mode in Perl.
     * </p>
     *
     * <pre>
     * StringUtils.replaceAll(null, *, *)       = null
     * StringUtils.replaceAll("any", null, *)   = "any"
     * StringUtils.replaceAll("any", *, null)   = "any"
     * StringUtils.replaceAll("", "", "zzz")    = "zzz"
     * StringUtils.replaceAll("", ".*", "zzz")  = "zzz"
     * StringUtils.replaceAll("", ".+", "zzz")  = ""
     * StringUtils.replaceAll("abc", "", "ZZ")  = "ZZaZZbZZcZZ"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\nz"
     * StringUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
     * StringUtils.replaceAll("ABCabc123", "[a-z]", "_")       = "ABC___123"
     * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
     * StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
     * StringUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param regex the regular expression to which this string is to be matched
     * @param replacement the string to be substituted for each match
     * @return the text with any replacements processed, {@code null} if null String input
     * @throws java.util.regex.PatternSyntaxException if the regular expression's syntax is invalid
     * @see #replacePattern(String, String, String)
     * @see String#replaceAll(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     * @since 3.5
     */
    public static String _replaceAll(final String text, final String regex, final String replacement) {
        if (text == null || regex == null || replacement == null) {
            return text;
        }
        return text.replaceAll(regex, replacement);
    }

    /**
     * <p>
     * Removes the first substring of the text string that matches the given regular expression.
     * </p>
     * This method is a {@code null} safe equivalent to:
     * <ul>
     * <li>{@code text.replaceFirst(regex, StringUtils.EMPTY)}</li>
     * <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(StringUtils.EMPTY)}</li>
     * </ul>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     * <p>
     * The {@link Pattern#DOTALL} option is NOT automatically added. To use the DOTALL option prepend <code>"(?s)"</code> to the regex. DOTALL is also know as single-line mode in Perl.
     * </p>
     *
     * <pre>
     * StringUtils.removeFirst(null, *)      = null
     * StringUtils.removeFirst("any", null)  = "any"
     * StringUtils.removeFirst("any", "")    = "any"
     * StringUtils.removeFirst("any", ".*")  = ""
     * StringUtils.removeFirst("any", ".+")  = ""
     * StringUtils.removeFirst("abc", ".?")  = "bc"
     * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")      = "A\n&lt;__&gt;B"
     * StringUtils.removeFirst("A&lt;__&gt;\n&lt;__&gt;B", "(?s)&lt;.*&gt;")  = "AB"
     * StringUtils.removeFirst("ABCabc123", "[a-z]")          = "ABCbc123"
     * StringUtils.removeFirst("ABCabc123abc", "[a-z]+")      = "ABC123abc"
     * </pre>
     *
     * @param text text to remove from, may be null
     * @param regex the regular expression to which this string is to be matched
     * @return the text with the first replacement processed, {@code null} if null String input
     * @throws java.util.regex.PatternSyntaxException if the regular expression's syntax is invalid
     * @see #replaceFirst(String, String, String)
     * @see String#replaceFirst(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     * @since 3.5
     */
    public static String removeFirst(final String text, final String regex) {
        return _replaceFirst(text, regex, EMPTY);
    }

    /**
     * <p>
     * Replaces the first substring of the text string that matches the given regular expression with the given replacement.
     * </p>
     * This method is a {@code null} safe equivalent to:
     * <ul>
     * <li>{@code text.replaceFirst(regex, replacement)}</li>
     * <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(replacement)}</li>
     * </ul>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     * <p>
     * The {@link Pattern#DOTALL} option is NOT automatically added. To use the DOTALL option prepend <code>"(?s)"</code> to the regex. DOTALL is also know as single-line mode in Perl.
     * </p>
     *
     * <pre>
     * StringUtils.replaceFirst(null, *, *)       = null
     * StringUtils.replaceFirst("any", null, *)   = "any"
     * StringUtils.replaceFirst("any", *, null)   = "any"
     * StringUtils.replaceFirst("", "", "zzz")    = "zzz"
     * StringUtils.replaceFirst("", ".*", "zzz")  = "zzz"
     * StringUtils.replaceFirst("", ".+", "zzz")  = ""
     * StringUtils.replaceFirst("abc", "", "ZZ")  = "ZZabc"
     * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\n&lt;__&gt;"
     * StringUtils.replaceFirst("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
     * StringUtils.replaceFirst("ABCabc123", "[a-z]", "_")          = "ABC_bc123"
     * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_")  = "ABC_123abc"
     * StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "")   = "ABC123abc"
     * StringUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum  dolor   sit"
     * </pre>
     *
     * @param text text to search and replace in, may be null
     * @param regex the regular expression to which this string is to be matched
     * @param replacement the string to be substituted for the first match
     * @return the text with the first replacement processed, {@code null} if null String input
     * @throws java.util.regex.PatternSyntaxException if the regular expression's syntax is invalid
     * @see String#replaceFirst(String, String)
     * @see java.util.regex.Pattern
     * @see java.util.regex.Pattern#DOTALL
     * @since 3.5
     */
    public static String _replaceFirst(final String text, final String regex, final String replacement) {
        if (text == null || regex == null || replacement == null) {
            return text;
        }
        return text.replaceFirst(regex, replacement);
    }

    // Replacing
    //-----------------------------------------------------------------------
    /**
     * <p>
     * Replaces a String with another String inside a larger String, once.
     * </p>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replaceOnce(null, *, *)        = null
     * StringUtils.replaceOnce("", *, *)          = ""
     * StringUtils.replaceOnce("any", null, *)    = "any"
     * StringUtils.replaceOnce("any", *, null)    = "any"
     * StringUtils.replaceOnce("any", "", *)      = "any"
     * StringUtils.replaceOnce("aba", "a", null)  = "aba"
     * StringUtils.replaceOnce("aba", "a", "")    = "ba"
     * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
     * </pre>
     *
     * @see #replace(String text, String searchString, String replacement, int max)
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for, may be null
     * @param replacement the String to replace with, may be null
     * @return the text with any replacements processed, {@code null} if null String input
     */
    public static String replaceOnce(final String text, final String searchString, final String replacement) {
        return _replace(text, searchString, replacement, 1);
    }

    /**
     * <p>
     * Case insensitively replaces a String with another String inside a larger String, once.
     * </p>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replaceOnceIgnoreCase(null, *, *)        = null
     * StringUtils.replaceOnceIgnoreCase("", *, *)          = ""
     * StringUtils.replaceOnceIgnoreCase("any", null, *)    = "any"
     * StringUtils.replaceOnceIgnoreCase("any", *, null)    = "any"
     * StringUtils.replaceOnceIgnoreCase("any", "", *)      = "any"
     * StringUtils.replaceOnceIgnoreCase("aba", "a", null)  = "aba"
     * StringUtils.replaceOnceIgnoreCase("aba", "a", "")    = "ba"
     * StringUtils.replaceOnceIgnoreCase("aba", "a", "z")   = "zba"
     * StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "") = "Foofoo"
     * </pre>
     *
     * @see #replaceIgnoreCase(String text, String searchString, String replacement, int max)
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for (case insensitive), may be null
     * @param replacement the String to replace with, may be null
     * @return the text with any replacements processed, {@code null} if null String input
     * @since 3.5
     */
    public static String replaceOnceIgnoreCase(final String text, final String searchString, final String replacement) {
        return replaceIgnoreCase(text, searchString, replacement, 1);
    }

    /**
     * <p>
     * Replaces each substring of the source String that matches the given regular expression with the given replacement using the {@link Pattern#DOTALL} option. DOTALL is also know as single-line mode in Perl.
     * </p>
     * This call is a {@code null} safe equivalent to:
     * <ul>
     * <li>{@code source.replaceAll(&quot;(?s)&quot; + regex, replacement)}</li>
     * <li>{@code Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(replacement)}</li>
     * </ul>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replacePattern(null, *, *)       = null
     * StringUtils.replacePattern("any", null, *)   = "any"
     * StringUtils.replacePattern("any", *, null)   = "any"
     * StringUtils.replacePattern("", "", "zzz")    = "zzz"
     * StringUtils.replacePattern("", ".*", "zzz")  = "zzz"
     * StringUtils.replacePattern("", ".+", "zzz")  = ""
     * StringUtils.replacePattern("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")       = "z"
     * StringUtils.replacePattern("ABCabc123", "[a-z]", "_")       = "ABC___123"
     * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
     * StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
     * StringUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
     * </pre>
     *
     * @param source the source string
     * @param regex the regular expression to which this string is to be matched
     * @param replacement the string to be substituted for each match
     * @return The resulting {@code String}
     * @see #replaceAll(String, String, String)
     * @see String#replaceAll(String, String)
     * @see Pattern#DOTALL
     * @since 3.2
     * @since 3.5 Changed {@code null} reference passed to this method is a no-op.
     */
    public static String replacePattern(final String source, final String regex, final String replacement) {
        if (source == null || regex == null || replacement == null) {
            return source;
        }
        return Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(replacement);
    }

    /**
     * <p>
     * Removes each substring of the source String that matches the given regular expression using the DOTALL option.
     * </p>
     * This call is a {@code null} safe equivalent to:
     * <ul>
     * <li>{@code source.replaceAll(&quot;(?s)&quot; + regex, StringUtils.EMPTY)}</li>
     * <li>{@code Pattern.compile(regex, Pattern.DOTALL).matcher(source).replaceAll(StringUtils.EMPTY)}</li>
     * </ul>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.removePattern(null, *)       = null
     * StringUtils.removePattern("any", null)   = "any"
     * StringUtils.removePattern("A&lt;__&gt;\n&lt;__&gt;B", "&lt;.*&gt;")  = "AB"
     * StringUtils.removePattern("ABCabc123", "[a-z]")    = "ABC123"
     * </pre>
     *
     * @param source the source string
     * @param regex the regular expression to which this string is to be matched
     * @return The resulting {@code String}
     * @see #replacePattern(String, String, String)
     * @see String#replaceAll(String, String)
     * @see Pattern#DOTALL
     * @since 3.2
     * @since 3.5 Changed {@code null} reference passed to this method is a no-op.
     */
    public static String removePattern(final String source, final String regex) {
        return replacePattern(source, regex, EMPTY);
    }

    /**
     * <p>
     * Case insensitively replaces all occurrences of a String within another String.
     * </p>
     * <p>
     * A {@code null} reference passed to this method is a no-op.
     * </p>
     *
     * <pre>
     * StringUtils.replaceIgnoreCase(null, *, *)        = null
     * StringUtils.replaceIgnoreCase("", *, *)          = ""
     * StringUtils.replaceIgnoreCase("any", null, *)    = "any"
     * StringUtils.replaceIgnoreCase("any", *, null)    = "any"
     * StringUtils.replaceIgnoreCase("any", "", *)      = "any"
     * StringUtils.replaceIgnoreCase("aba", "a", null)  = "aba"
     * StringUtils.replaceIgnoreCase("abA", "A", "")    = "b"
     * StringUtils.replaceIgnoreCase("aba", "A", "z")   = "zbz"
     * </pre>
     *
     * @see #replaceIgnoreCase(String text, String searchString, String replacement, int max)
     * @param text text to search and replace in, may be null
     * @param searchString the String to search for (case insensitive), may be null
     * @param replacement the String to replace it with, may be null
     * @return the text with any replacements processed, {@code null} if null String input
     * @since 3.5
     */
    public static String replaceIgnoreCase(final String text, final String searchString, final String replacement) {
        return replaceIgnoreCase(text, searchString, replacement, -1);
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    /*
     * THROWABLE UTILS
     */
    public static void logStackTrace(Throwable t) {
        HLogger.error(t);
    }

    /*
     * 
     * W3C DOM UTILS
     * 
     */

    /**
     * Get an attribute from a W3C DOM {@link Node} as a string
     * 
     * @param choose the node to get the attribute from
     * @param string the attribute to get
     * @return the attribute value
     * @throws NullPointerException if the node does not contain the attribute {@code string}
     */
    public static String getAttribute(Node choose, String string) {
        return choose.getAttributes().getNamedItem(string).getTextContent();
    }

    /*
     * 
     * URL UTILS
     * 
     */

    public static ByteArrayOutputStream readAllBytes(final URL url) {
        val baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = url.openStream();
            val byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.
            int n;

            while ((n = is.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }
            is.close();
        } catch (final IOException e) {
            System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace();
            // Perform any other exception handling that's appropriate.
        }
        return baos;
    }

    /*
     * 
     * STRING UTILS
     * 
     */

    /**
     * Returns a string that is a substring of this string. The substring begins at the start of the string and ends end of the string, minus {@code amt} characters. Thus the length of the substring is {@link String#length()}<tt> - amt</tt>.
     * 
     * @param me myself!
     * @param amt the amount of characters to trim from the end
     * @return the specified substring.
     */
    public static String subToEnd(String me, int amt) {
        return me.substring(0, me.length() - amt);
    }

    /**
     * Returns a string that is a substring of this string. The substring begins at the end of the string and inversely extends {@code amt} characters. Thus the length of the substring is {@code amt}.
     * 
     * @param me myself!
     * @param amt the amount of characters to extract from the end
     * @return the specified substring.
     */
    public static String subFromEnd(String me, int amt) {
        return me.substring(me.length() - amt);
    }

    private static <E> Collection<E> _toCollection(Iterable<E> iterable) {
        return (iterable instanceof Collection) ? (Collection<E>) iterable : Lists.newArrayList(iterable.iterator());
    }

    public static String join(String[] a, String delimiter) {
        return String.join(delimiter, a);
    }

    public static String join(String... bulk) {
        Objects.requireNonNull(bulk);
        int j = bulk.length - 1;
        val delimiter = bulk[j];
        Objects.requireNonNull(delimiter);
        // Number of elements not likely worth Arrays.stream overhead.
        StringJoiner joiner = new StringJoiner(delimiter);
        for (int i = 0; i < j; i++) {
            joiner.add(bulk[i]);
        }
        return joiner.toString();
    }

    /**
     * Returns a new {@link String} composed of copies of the {@link Stream Stream<}{@link String}{@link Stream >} {@code a} joined together with a copy of the specified {@code delimiter}.
     *
     * @param a the {@link Stream} of {@code String}s to join
     * @param delimiter the symbol to join each string
     * @return a {@code String} containing every string in {@code a} joined by {@code delimiter}
     */
    public static String join(Stream<String> a, String delimiter) {
        return String.join(delimiter, collectList(a));
    }

    /**
     * Returns a new {@link String} composed of copies of the {@link Iterable Iterable<}{@link String}{@link Iterable >} {@code a} joined together with a copy of the specified {@code delimiter}.
     *
     * @param a the {@link Iterable} of {@code String}s to join
     * @param delimiter the symbol to join each string
     * @return a {@code String} containing every string in {@code a} joined by {@code delimiter}
     */
    public static String join(Iterable<String> a, String delimiter) {
        return String.join(delimiter, _toCollection(a));
    }

    /**
     * Returns a new {@link String} composed of copies of the {@link Collection Collection<}{@link String}{@link Collection >} {@code a} joined together with a copy of the specified {@code delimiter}.
     *
     * @param a the {@link Collection} of {@code String}s to join
     * @param delimiter the symbol to join each string
     * @return a {@code String} containing every string in {@code a} joined by {@code delimiter}
     */
    public static String join(Collection<String> a, String delimiter) {
        return String.join(delimiter, a);
    }

    public static <E> String joinAll(E[] a, E delimiter) {
        String[] str = new String[a.length];
        for (int i = 0; i < a.length; i++) {
            str[i] = a[i].toString();
        }
        return String.join(delimiter.toString(), str);
    }

    @SafeVarargs
    public static <E> String joinAll(E... bulk) {
        Objects.requireNonNull(bulk);
        int j = bulk.length - 1;
        val delimiter = bulk[j];
        Objects.requireNonNull(delimiter);
        // Number of elements not likely worth Arrays.stream overhead.
        StringJoiner joiner = new StringJoiner(delimiter.toString());
        for (int i = 0; i < j; i++) {
            joiner.add(bulk[i].toString());
        }
        return joiner.toString();
    }

    public static <E> String joinAll(Stream<E> a, E delimiter) {
        List<String> list = new ArrayList<>();
        a.forEach(s -> list.add(s.toString()));

        return String.join(delimiter.toString(), list);
    }

    public static <E> String joinAll(Iterable<E> a, E delimiter) {
        List<String> list = new ArrayList<>();
        a.forEach(s -> list.add(s.toString()));

        return String.join(delimiter.toString(), list);
    }

    public static <E> String joinAll(Collection<E> a, E delimiter) {
        List<String> list = new ArrayList<>();
        a.forEach(s -> list.add(s.toString()));

        return String.join(delimiter.toString(), list);
    }

    public static String[] toStringArray(@NonNull char[] cs) {
        val out = new String[cs.length];
        for (int i = 0; i < cs.length; i++) {
            out[i] = String.valueOf(cs[i]);
        }
        return out;
    }

    /**
     * Convert the first character in a string to uppercase
     * 
     * @param string the string to convert
     * @return a string equal to the input string, but with the first character converted to its UTF16 equivalent in uppercase
     */
    public static String firstLetterToUppercase(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static Iterable<Character> iterator(String string) {
        return new Iterable<Character>() {

            @Override
            public Iterator<Character> iterator() {
                return new Iterator<Character>() {

                    private int p = 0;
                    private final int length = string.length();

                    @Override
                    public boolean hasNext() {
                        return p < length;
                    }

                    @Override
                    public Character next() {
                        return string.charAt(p++);
                    }
                };
            }
        };
    }
    
    /**
     * Returns {@code true} if, and only if {@link String#length()}<tt> == 0</tt>
     * @param string the string
     * @return {@code true} if, and only if {@link String#length()}<tt> == 0</tt>
     */
    public static boolean isEmpty(CharSequence string) {
        return string == null ? true : string.length() == 0;
    }

    /**
     * Checks if a string consists of only whitespace characters.
     * 
     * @param string the string to check
     * @return {@code true} if the string consists of only whitespace characters as defined by <a href="https://en.wikipedia.org/wiki/Whitespace_character#Unicode">Wikipedia - "Whitespace character"</a>
     */
    public static boolean isBlank(CharSequence string) {
        if (string == null) return true;
        
        final int len = string.length();
        if (len == 0) return true;
        
        char c;
        for (int i = 0; i < len; i++) {
            c = string.charAt(i);
            if (c != 0x0009 && c != 0x000A && c != 0x000B && c != 0x000C && c != 0x000D && c != 0x0020 && c != 0x0085 && c != 0x00A0 && c != 0x1680 && c != 0x2000 && c != 0x2002 && c != 0x2001 && c != 0x2003 && c != 0x2004 && c != 0x2005 && c != 0x2006 && c != 0x2007 && c != 0x2008 && c != 0x2009 && c != 0x200A && c != 0x2028 && c != 0x2029 && c != 0x202F && c != 0x205F && c != 0x3000 && c != 0x180E && c != 0x200B && c != 0x200C && c != 0x200D && c != 0x2060 && c != 0xFEFF) {
                return false;
            }
        }
        return true;
    }

    /**
     * Split a string by a char. Not much more useful than {@link String#split(String)}, really
     * 
     * @param source
     * @param ch
     * @return
     */
    public static String[] split(final String source, final char ch) {
        return split(source, ch, source.length());
    }

    public static String[] split(final String source, final String ch) {
        return source.split(ch);
    }

    public static String[] split(final String source) {
        return toStringArray(source.toCharArray());
    }

    public static String[] split(final String source, final char ch, final int limit) {
        /* fastpath if the regex is a
        (1)one-char String and this character is not one of the
          RegEx's meta characters ".$|()[{^?*+\\", or
        (2)two-char String and the first char is the backslash and
          the second is not the ascii digit or ascii letter.
        */
        int off = 0;
        int next = 0;
        val limited = limit > 0;
        final ArrayList<String> list = new ArrayList<>();
        val arrlen = source.length();
        while ((next = source.indexOf(ch, off)) != -1) {
            if (!limited || list.size() < limit - 1) {
                list.add(source.substring(off, next));
                off = next + 1;
            } else { // last one
                //assert (list.size() == limit - 1);
                list.add(source.substring(off, arrlen));
                off = arrlen;
                break;
            }
        }
        // If no match was found, return this
        if (off == 0) {
            return new String[] {
                    source
            };
        }

        // Add remaining segment
        if (!limited || list.size() < limit) {
            list.add(source.substring(off, arrlen));
        }

        // Construct result
        int resultSize = list.size();
        if (limit == 0) {
            while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
                resultSize--;
            }
        }
        val result = new String[resultSize];
        return list.subList(0, resultSize).toArray(result);
    }

    public static String[] splitNoRegex(final String source, final String by) {
        return _split(source, by, source.length());
    }

    private static String[] _split(final String source, final String by, final int limit) {
        /* fastpath if the regex is a
        (1)one-char String and this character is not one of the
          RegEx's meta characters ".$|()[{^?*+\\", or
        (2)two-char String and the first char is the backslash and
          the second is not the ascii digit or ascii letter.
        */
        int off = 0;
        int next = 0;
        val limited = limit > 0;
        final ArrayList<String> list = new ArrayList<>();
        val arrlen = source.length();
        while ((next = source.indexOf(by, off)) != -1) {
            if (!limited || list.size() < limit - 1) {
                list.add(source.substring(off, next));
                off = next + 1;
            } else { // last one
                //assert (list.size() == limit - 1);
                list.add(source.substring(off, arrlen));
                off = arrlen;
                break;
            }
        }
        // If no match was found, return this
        if (off == 0) {
            return new String[] {
                    source
            };
        }

        // Add remaining segment
        if (!limited || list.size() < limit) {
            list.add(source.substring(off, arrlen));
        }

        // Construct result
        int resultSize = list.size();
        if (limit == 0) {
            while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
                resultSize--;
            }
        }
        val result = new String[resultSize];
        return list.subList(0, resultSize).toArray(result);
    }

    /*
     * 
     * MAP UTILS
     * 
     */

    /**
     * @param map the map
     * @return A map's entries as a string.
     */
    public static <K, V> String prettyPrint(final Map<K, V> map) {
        return Arrays.toString(map.entrySet().toArray());
    }

    /*
     * 
     * LIST UTILS
     * 
     */

    /**
     * Checks whether a {@link List} is not null and contains at least one element. Nullity of the element itself is not checked.
     * 
     * @param e the list to check
     * @return true if and only if the list is both not null and its {@link List#size()} reports at least one element
     */
    public static <E> boolean isTruthy(List<E> e) {
        return e != null && e.size() != 0;
    }

    /**
     * Check if all elements in a list are distinct.
     * 
     * @param e the list to check
     * @return {@code true} if either the {@code e} is null, {@code e} is empty or {@code e}'s elements are all distinct from each other as per {@link Objects#equals(Object, Object)} semantics
     */
    public static <E> boolean hasNoDuplicates(List<E> e) {
        if (e == null || e.size() == 0)
            return true;
        
        for (val f : e) for (val g : e) if (f == g || f != null && f.equals(g)) return false;
        
        return true;
    }

    /**
     * Create an immutable {@link List} backed by an array, from a varargs array
     * 
     * @param els the list elements
     * @return an immutable list containing the elements in {@code els}
     */
    @SafeVarargs
    public static <E> List<E> asList(E... els) {
        return Arrays.asList(els);
    }

    /**
     * Create a mutable {@link Set} backed by hashes, from a varargs array
     * 
     * @param els the set elements
     * @return a mutable set containing the elements in {@code els}
     */
    @SafeVarargs
    public static <E> HashSet<E> asSet(E... els) {
        return new HashSet<E>(Arrays.asList(els));
    }

    /**
     * Create a mutable {@link List} backed by an array, from a varargs array
     * 
     * @param els the set elements
     * @return a mutable list containing the elements in {@code els}
     */
    @SafeVarargs
    public static <E> List<E> asMutableList(E... els) {
        return new ArrayList<E>(Arrays.asList(els));
    }

    /**
     * Create an immutable {@link List} backed by an array, from a collection
     * 
     * @param els the list elements
     * @return an immutable list containing the elements in {@code els}
     */
    public static <E> List<E> asList(Collection<E> els) {
        return withAddAll(new ArrayList<E>(), els).unmodifiableList();
    }

    /**
     * Create a mutable {@link Set} backed by hashes, from a collection
     * 
     * @param els the set elements
     * @return a mutable set containing the elements in {@code els}
     */
    public static <E> Set<E> asSet(Collection<E> els) {
        return (Set<E>) withAddAll(new HashSet<E>(), els);
    }

    /**
     * Create a mutable {@link List} backed by an array, from a collection
     * 
     * @param els the set elements
     * @return a mutable list containing the elements in {@code els}
     */
    public static <E> List<E> asMutableList(Collection<E> els) {
        return withAddAll(new ArrayList<E>(), els);
    }

    /**
     * Create an immutable {@link List} backed by an array, from a single element
     * 
     * @param els the element
     * @return an immutable list containing the elements in {@code els}
     */
    public static <E> List<E> asList(E els) {
        return Arrays.asList(els);
    }

    /**
     * Create a mutable {@link Set} backed by hashes, from a single element
     * 
     * @param els the element
     * @return a mutable set containing the elements in {@code els}
     */
    public static <E> HashSet<E> asSet(E els) {
        return new HashSet<E>(Arrays.asList(els));
    }

    /**
     * Create a mutable {@link List} backed by an array, from a single element
     * 
     * @param els the element
     * @return a mutable list containing the elements in {@code els}
     */
    public static <E> List<E> asMutableList(E els) {
        return new ArrayList<E>(Arrays.asList(els));
    }

    /**
     * Collect the remaining contents of a stream into a {@link List}
     * 
     * @param <T> the type of the input elements
     * @param stream the stream to collect
     * @return a {@link List} containing the remaining contents of the stream
     */
    public static <T> List<T> collectList(Stream<T> stream) {
        return stream.collect(Collectors.toList());
    }

    /**
     * Collect the remaining contents of a stream into a {@link Set}
     * 
     * @param <T> the type of the input elements
     * @param stream the stream to collect
     * @return a {@link Set} containing the remaining contents of the stream
     */
    public static <T> Set<T> collectSet(Stream<T> stream) {
        return stream.collect(Collectors.toSet());
    }

    /**
     * Collect the remaining contents of a stream into a custom collection object
     * 
     * @param <T> the type of the input elements
     * @param <C> the type of the resulting {@code Collection}
     * @param stream the stream to collect
     * @param collectionFactory the {@link Supplier} to create the collection
     * @return a custom collection object containing the remaining contents of the stream
     */
    public static <C extends Collection<T>, T> C collectTo(Stream<T> stream, Supplier<C> collectionFactory) {
        return stream.collect(Collectors.toCollection(collectionFactory));
    }

    /**
     * Accumulate this stream's elements into a {@code Map} whose keys and values are the result of applying the provided mapping functions to the input elements.
     * 
     * @param <T> the type of the input elements
     * @param <K> the output type of the key mapping function
     * @param <U> the output type of the value mapping function
     * @param keyMapper a mapping function to produce keys
     * @param valueMapper a mapping function to produce values
     * @return a {@code Map} whose keys and values are the result of applying mapping functions to the input elements
     */
    public static <T, K, U> Map<K, U> collectMap(Stream<T> stream, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        return stream.collect(Collectors.toMap(keyMapper, valueMapper));
    }

    /**
     * Ensures that this collection contains the specified element (optional operation). Returns <tt>true</tt> if this collection changed as a result of the call. (Returns <tt>false</tt> if this collection does not permit duplicates and already contains the specified element.)
     * <p>
     * Collections that support this operation may place limitations on what elements may be added to this collection. In particular, some collections will refuse to add <tt>null</tt> elements, and others will impose restrictions on the type of elements that may be added. Collection classes should clearly specify in their documentation any restrictions on what elements may be added.
     * <p>
     * If a collection refuses to add a particular element for any reason other than that it already contains the element, it <i>must</i> throw an exception (rather than returning <tt>false</tt>). This preserves the invariant that a collection always contains the specified element after this call returns.
     *
     * @param e element whose presence in this collection is to be ensured
     * @return <tt>this</tt>, always
     * @throws UnsupportedOperationException if the <tt>add</tt> operation is not supported by this collection
     * @throws ClassCastException if the class of the specified element prevents it from being added to this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     * @throws IllegalArgumentException if some property of the element prevents it from being added to this collection
     * @throws IllegalStateException if the element cannot be added at this time due to insertion restrictions
     */
    public static <E> Collection<E> withAdd(Collection<E> list, E e) {
        list.add(e);
        return list;
    }

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation). The behavior of this operation is undefined if the specified collection is modified while the operation is in progress. (This implies that the behavior of this call is undefined if the specified collection is this collection, and this collection is nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>this</tt>, always
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation is not supported by this collection
     * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this collection
     * @throws NullPointerException if the specified collection contains a null element and this collection does not permit null elements, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the specified collection prevents it from being added to this collection
     * @throws IllegalStateException if not all the elements can be added at this time due to insertion restrictions
     * @see #add(Object)
     */
    public static <E> Collection<E> withAddAll(Collection<E> list, Collection<? extends E> e) {
        for (E el : e)
            list.add(el);
        return list;
    }

    /**
     * Removes all of the elements from this collection (optional operation). The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
     * @return <tt>this</tt>, always
     */
    public static <E> Collection<E> withClear(Collection<E> list) {
        list.clear();
        return list;
    }

    /**
     * Ensures that this collection contains the specified element (optional operation). Returns <tt>true</tt> if this collection changed as a result of the call. (Returns <tt>false</tt> if this collection does not permit duplicates and already contains the specified element.)
     * <p>
     * Collections that support this operation may place limitations on what elements may be added to this collection. In particular, some collections will refuse to add <tt>null</tt> elements, and others will impose restrictions on the type of elements that may be added. Collection classes should clearly specify in their documentation any restrictions on what elements may be added.
     * <p>
     * If a collection refuses to add a particular element for any reason other than that it already contains the element, it <i>must</i> throw an exception (rather than returning <tt>false</tt>). This preserves the invariant that a collection always contains the specified element after this call returns.
     *
     * @param e element whose presence in this collection is to be ensured
     * @return <tt>this</tt>, always
     * @throws UnsupportedOperationException if the <tt>add</tt> operation is not supported by this collection
     * @throws ClassCastException if the class of the specified element prevents it from being added to this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     * @throws IllegalArgumentException if some property of the element prevents it from being added to this collection
     * @throws IllegalStateException if the element cannot be added at this time due to insertion restrictions
     */
    public static <E> List<E> withAdd(List<E> list, E e) {
        list.add(e);
        return list;
    }

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation). The behavior of this operation is undefined if the specified collection is modified while the operation is in progress. (This implies that the behavior of this call is undefined if the specified collection is this collection, and this collection is nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>this</tt>, always
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation is not supported by this collection
     * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this collection
     * @throws NullPointerException if the specified collection contains a null element and this collection does not permit null elements, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the specified collection prevents it from being added to this collection
     * @throws IllegalStateException if not all the elements can be added at this time due to insertion restrictions
     * @see #add(Object)
     */
    public static <E> List<E> withAddAll(List<E> list, Collection<? extends E> e) {
        for (E el : e)
            list.add(el);
        return list;
    }

    /**
     * Removes all of the elements from this collection (optional operation). The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
     * @return <tt>this</tt>, always
     */
    public static <E> List<E> withClear(List<E> list) {
        list.clear();
        return list;
    }

    /**
     * Returns the first element of this list.
     * 
     * @param list
     * @return the first element of this list, effectively equivalent to {@code list.get(0)}.
     * @throws IndexOutOfBoundsException if this list is empty
     * @throws NullPointerException if this list is null
     */
    public static <E> E first(List<E> list) {
        if (list.size() == 0) {
            throw new IndexOutOfBoundsException("List is not large enough to access first element");
        }
        return list.get(0);
    }

    /**
     * Returns the last element of this list.
     * 
     * @param list
     * @return the last element of this list, effectively equivalent to {@code list.get(list.size() - 1)}.
     * @throws IndexOutOfBoundsException if this list is empty
     * @throws NullPointerException if this list is null
     */
    public static <E> E last(List<E> list) {
        int size = list.size();
        if (size == 0) {
            throw new IndexOutOfBoundsException("List is not large enough to access last element");
        }
        return list.get(list.size() - 1);
    }

    /*
     * sets
     */
    public static <T> TreeSet<T> toTreeSet(Collection<T> targ) {
        return new TreeSet<T>(targ);
    }

    public static <T> HashSet<T> toHashSet(Collection<T> targ) {
        return new HashSet<T>(targ);
    }

    public static <T> LinkedHashSet<T> toLinkedHashSet(Collection<T> targ) {
        return new LinkedHashSet<T>(targ);
    }

    public static <T> CopyOnWriteArraySet<T> toCopyOnWriteArraySet(Collection<T> targ) {
        return new CopyOnWriteArraySet<T>(targ);
    }

    /*
     * stacks
     */
    public static <T> Vector<T> toVector(Collection<T> targ) {
        return new Vector<T>(targ);
    }

    public static <T> Stack<T> toStack(Collection<T> targ) {
        Stack<T> stack = new Stack<T>();
        targ.forEach(stack::push);
        return stack;
    }

    public static <T> ArrayBlockingQueue<T> toArrayBlockingQueue(Collection<T> targ) {
        return new ArrayBlockingQueue<T>(targ.size(), true, targ);
    }

    public static <T> SynchronousQueue<T> toSynchronousQueue(Collection<T> targ) throws InterruptedException {
        SynchronousQueue<T> queue = new SynchronousQueue<T>();
        for (val t : targ) {
            queue.put(t);
        }
        return queue;
    }

    /*
     * lists
     */
    public static <T> ArrayList<T> toArrayList(Collection<T> targ) {
        return new ArrayList<T>(targ);
    }

    public static <T> LinkedList<T> toLinkedList(Collection<T> targ) {
        return new LinkedList<T>(targ);
    }

    public static <T> PriorityQueue<T> toPriorityQueue(Collection<T> targ) {
        return new PriorityQueue<T>(targ);
    }

    public static AttributeList toAttributeList(List<Attribute> targ) {
        return new AttributeList(targ);
    }

    public static RoleList toRoleList(List<Role> targ) {
        return new RoleList(targ);
    }

    public static RoleUnresolvedList toRoleUnresolvedList(List<RoleUnresolved> targ) {
        return new RoleUnresolvedList(targ);
    }

    public static <T> CopyOnWriteArrayList<T> toCopyOnWriteArrayList(Collection<T> targ) {
        return new CopyOnWriteArrayList<T>(targ);
    }

    /*
     * maps
     */
    public static <K, V> TreeMap<K, V> toTreeMap(Map<K, V> targ) {
        return new TreeMap<K, V>(targ);
    }

    public static <K, V> HashMap<K, V> toHashMap(Map<K, V> targ) {
        return new HashMap<K, V>(targ);
    }

    public static <K, V> Hashtable<K, V> toHashtable(Map<K, V> targ) {
        return new Hashtable<K, V>(targ);
    }

    public static <K, V> WeakHashMap<K, V> toWeakHashMap(Map<K, V> targ) {
        return new WeakHashMap<K, V>(targ);
    }

    public static <K, V> ConcurrentHashMap<K, V> toConcurrentHashMap(Map<K, V> targ) {
        return new ConcurrentHashMap<K, V>(targ);
    }

    public static <K, V> LinkedHashMap<K, V> toLinkedHashMap(Map<K, V> targ) {
        return new LinkedHashMap<K, V>(targ);
    }

    public static <K, V> IdentityHashMap<K, V> toIdentityHashMap(Map<K, V> targ) {
        return new IdentityHashMap<K, V>(targ);
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> toEnumMap(Map<K, V> targ) {
        return new EnumMap<K, V>(targ);
    }

    public static <T, C extends Collection<T>> C toCollection(Collection<T> targ, Supplier<C> constructor) {
        val coll = constructor.get();
        targ.forEach(coll::add);
        return coll;
    }

    /*
     * 
     * 
     * 
     * FILE UTILS
     * 
     * 
     * 
     */
    public static boolean hasSibling(File f, String sibling) {
        return new File(f.getParent(), sibling).exists();
    }

    /**
     * @param f
     * @return true if the file could fit in a byte array, assuming there are at least 2GB of free heap memory.
     */
    public static boolean fitsInHeap(File f) {
        return f.length() < Integer.MAX_VALUE - 128;
    }

    /*
     * 
     * 
     * FUNCTIONAL UTILS
     * 
     * 
     * 
     */

    /**
     * Returns the element contained in an optional or {@code null} if none is present
     * 
     * @param t
     * @return the element contained in an optional or {@code null} if none is present
     */
    public static <T> T getLazy(Optional<T> t) {
        return t.isPresent() ? t.get() : null;
    }

    /**
     * Returns the first argument if it is not null, the second argument otherwise
     * 
     * @param t1 the first element
     * @param t2 the second element
     * @return the first argument if it is not null, the second argument otherwise
     */
    public static <T> T or(T t1, T t2) {
        return t1 != null ? t1 : t2;
    }

    /**
     * Returns the first argument if it is not null, or the first non-null element in {@code ts}, or {@code null} if none were found
     * 
     * @param t1 the initial element
     * @param t2 the following elements
     * @return the first argument if it is not null, or the first non-null element in {@code ts}, or {@code null} if none were found
     */
    @SafeVarargs
    public static <T> T or(T t1, T... ts) {
        return t1 != null ? t1 : getLazy(ts.stream().filter(t -> t != null).findFirst());
    }

    /**
     * Executes a function. If it succeeds, return the result. If it fails, return null.
     */
    public static <T, R> R tryCall(T me, Function<T, R> func) {
        try {
            return func.apply(me);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Executes a predicate. If it succeeds, return the result of the predicate. If it fails, return false.
     */
    public static <T> boolean tryCall(T me, Predicate<T> func) {
        try {
            return func.apply(me);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Calls a consumer. Will not throw on failure.
     */
    public static <T> void tryCall(T me, Consumer<T> func) {
        try {
            func.accept(me);
        } catch (Exception e) {
        }
    }

    /**
     * Executes a function. If it succeeds, return an {@link Optional} containing the result. If it fails, return an empty {@code Optional}.
     */
    public static <T, R> Optional<R> attemptCall(T me, Function<T, R> func) {
        try {
            return Optional.of(func.apply(me));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static enum SuccessState {
        TRUE, FALSE, ERROR;
        public static SuccessState of(boolean b) {
            return b ? TRUE : FALSE;
        }

        public static SuccessState of(Object b) {
            Class<? extends Object> cls;                                            //
            return b == null                                                        //
                    ? ERROR                                                         //
                    : (cls = b.getClass()) == Boolean.class || cls == boolean.class //
                        ? (boolean) b                                               //
                            ? TRUE                                                  //
                            : FALSE                                                 //
                        : b instanceof CharSequence                                 //
                            ? b.toString().equalsIgnoreCase("true")                 //
                                ? TRUE                                              //
                                : FALSE                                             //
                            : TRUE;                                                 //
        }
    }

    public static <T> SuccessState attemptCall(T me, Predicate<T> func) {
        try {
            return func.apply(me) ? SuccessState.TRUE : SuccessState.FALSE;
        } catch (Exception e) {
            return SuccessState.ERROR;
        }
    }

    public static <T> boolean attemptCall(T me, Consumer<T> func) {
        try {
            func.accept(me);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * 
     * 
     * RANDOM UTILS
     * 
     * 
     * 
     */

    public static float f(ThreadLocalRandom r) {
        return r.nextFloat();
    }
    public static double d(ThreadLocalRandom r) {
        return r.nextDouble();
    }
    public static int i(ThreadLocalRandom r) {
        return r.nextInt();
    }
    public static short s(ThreadLocalRandom r) {
        return (short)r.nextInt();
    }
    public static char c(ThreadLocalRandom r) {
        return (char)r.nextInt();
    }
    public static byte by(ThreadLocalRandom r) {
        return (byte)r.nextInt();
    }
    public static long l(ThreadLocalRandom r) {
        return r.nextLong();
    }
    public static boolean b(ThreadLocalRandom r) {
        return r.nextBoolean();
    }
    public static float f(ThreadLocalRandom r, float bound) {
        return (float)r.nextDouble(bound);
    }
    public static double d(ThreadLocalRandom r, double bound) {
        return r.nextDouble(bound);
    }
    public static int i(ThreadLocalRandom r, int bound) {
        return r.nextInt(bound);
    }
    public static short s(ThreadLocalRandom r, short bound) {
        return (short)r.nextInt(bound);
    }
    public static char c(ThreadLocalRandom r, char bound) {
        return (char)r.nextInt(bound);
    }
    public static byte by(ThreadLocalRandom r, byte bound) {
        return (byte)r.nextInt(bound);
    }
    public static long l(ThreadLocalRandom r, long bound) {
        return r.nextLong(bound);
    }
    public static float f(ThreadLocalRandom r, float origin, float bound) {
        return (float)r.nextDouble(origin, bound);
    }
    public static double d(ThreadLocalRandom r, double origin, double bound) {
        return r.nextDouble(origin, bound);
    }
    public static int i(ThreadLocalRandom r, int origin, int bound) {
        return r.nextInt(origin, bound);
    }
    public static short s(ThreadLocalRandom r, short origin, short bound) {
        return (short)r.nextInt(origin, bound);
    }
    public static char c(ThreadLocalRandom r, char origin, char bound) {
        return (char)r.nextInt(origin, bound);
    }
    public static byte by(ThreadLocalRandom r, byte origin, byte bound) {
        return (byte)r.nextInt(origin, bound);
    }
    public static long l(ThreadLocalRandom r, long origin, long bound) {
        return r.nextLong(origin, bound);
    }
    /*
     * 
     */
    public static float nextFloat(ThreadLocalRandom r) {
        return r.nextFloat();
    }
    public static double nextDouble(ThreadLocalRandom r) {
        return r.nextDouble();
    }
    public static int nextInt(ThreadLocalRandom r) {
        return r.nextInt();
    }
    public static short nextShort(ThreadLocalRandom r) {
        return (short)r.nextInt();
    }
    public static char nextChar(ThreadLocalRandom r) {
        return (char)r.nextInt();
    }
    public static byte nextByte(ThreadLocalRandom r) {
        return (byte)r.nextInt();
    }
    public static long nextLong(ThreadLocalRandom r) {
        return r.nextLong();
    }
    public static boolean nextBoolean(ThreadLocalRandom r) {
        return r.nextBoolean();
    }
    public static float nextFloat(ThreadLocalRandom r, float bound) {
        return (float)r.nextDouble(bound);
    }
    public static double nextDouble(ThreadLocalRandom r, double bound) {
        return r.nextDouble(bound);
    }
    public static int nextInt(ThreadLocalRandom r, int bound) {
        return r.nextInt(bound);
    }
    public static short nextShort(ThreadLocalRandom r, short bound) {
        return (short)r.nextInt(bound);
    }
    public static char nextChar(ThreadLocalRandom r, char bound) {
        return (char)r.nextInt(bound);
    }
    public static byte nextByte(ThreadLocalRandom r, byte bound) {
        return (byte)r.nextInt(bound);
    }
    public static long nextLong(ThreadLocalRandom r, long bound) {
        return r.nextLong(bound);
    }
    public static float nextFloat(ThreadLocalRandom r, float origin, float bound) {
        return (float)r.nextDouble(origin, bound);
    }
    public static double nextDouble(ThreadLocalRandom r, double origin, double bound) {
        return r.nextDouble(origin, bound);
    }
    public static int nextInt(ThreadLocalRandom r, int origin, int bound) {
        return r.nextInt(origin, bound);
    }
    public static short nextShort(ThreadLocalRandom r, short origin, short bound) {
        return (short)r.nextInt(origin, bound);
    }
    public static char nextChar(ThreadLocalRandom r, char origin, char bound) {
        return (char)r.nextInt(origin, bound);
    }
    public static byte nextByte(ThreadLocalRandom r, byte origin, byte bound) {
        return (byte)r.nextInt(origin, bound);
    }
    public static long nextLong(ThreadLocalRandom r, long origin, long bound) {
        return r.nextLong(origin, bound);
    }

    /*
     * 
     * 
     * SWING UTILS
     * 
     * 
     * 
     */

    public static JButton withAddChangeListener(JButton c, ChangeListener l) {
   c.addChangeListener(l);
   return c;
    }
    public static JButton withAddActionListener(JButton c, ActionListener l) {
   c.addActionListener(l);
   return c;
    }
    public static JButton withAddItemListener(JButton c, ItemListener l) {
   c.addItemListener(l);
   return c;
    }
    public static JButton withAddVetoableChangeListener(JButton c, VetoableChangeListener listener) {
   c.addVetoableChangeListener(listener);
   return c;
    }
    public static JButton withAddAncestorListener(JButton c, AncestorListener listener) {
   c.addAncestorListener(listener);
   return c;
    }
    public static JButton withAddContainerListener(JButton c, ContainerListener l) {
   c.addContainerListener(l);
   return c;
    }
    public static JButton withAddPropertyChangeListener(JButton c, PropertyChangeListener listener) {
   c.addPropertyChangeListener(listener);
   return c;
    }
    public static JButton withAddComponentListener(JButton c, ComponentListener l) {
   c.addComponentListener(l);
   return c;
    }
    public static JButton withAddHierarchyListener(JButton c, HierarchyListener l) {
   c.addHierarchyListener(l);
   return c;
    }
    public static JButton withAddHierarchyBoundsListener(JButton c, HierarchyBoundsListener l) {
   c.addHierarchyBoundsListener(l);
   return c;
    }
    public static JButton withAddKeyListener(JButton c, KeyListener l) {
   c.addKeyListener(l);
   return c;
    }
    public static JButton withAddMouseListener(JButton c, MouseListener l) {
   c.addMouseListener(l);
   return c;
    }
    public static JButton withAddMouseMotionListener(JButton c, MouseMotionListener l) {
   c.addMouseMotionListener(l);
   return c;
    }
    public static JButton withAddMouseWheelListener(JButton c, MouseWheelListener l) {
   c.addMouseWheelListener(l);
   return c;
    }
    public static JButton withAddInputMethodListener(JButton c, InputMethodListener l) {
   c.addInputMethodListener(l);
   return c;
    }
    public static JButton withAddFocusListener(JButton c, FocusListener l) {
   c.addFocusListener(l);
   return c;
    }

    public static JMenuItem withAddChangeListener(JMenuItem c, ChangeListener l) {
   c.addChangeListener(l);
   return c;
    }
    public static JMenuItem withAddActionListener(JMenuItem c, ActionListener l) {
   c.addActionListener(l);
   return c;
    }
    public static JMenuItem withAddItemListener(JMenuItem c, ItemListener l) {
   c.addItemListener(l);
   return c;
    }
    public static JMenuItem withAddVetoableChangeListener(JMenuItem c, VetoableChangeListener listener) {
   c.addVetoableChangeListener(listener);
   return c;
    }
    public static JMenuItem withAddAncestorListener(JMenuItem c, AncestorListener listener) {
   c.addAncestorListener(listener);
   return c;
    }
    public static JMenuItem withAddContainerListener(JMenuItem c, ContainerListener l) {
   c.addContainerListener(l);
   return c;
    }
    public static JMenuItem withAddPropertyChangeListener(JMenuItem c, PropertyChangeListener listener) {
   c.addPropertyChangeListener(listener);
   return c;
    }
    public static JMenuItem withAddComponentListener(JMenuItem c, ComponentListener l) {
   c.addComponentListener(l);
   return c;
    }
    public static JMenuItem withAddHierarchyListener(JMenuItem c, HierarchyListener l) {
   c.addHierarchyListener(l);
   return c;
    }
    public static JMenuItem withAddHierarchyBoundsListener(JMenuItem c, HierarchyBoundsListener l) {
   c.addHierarchyBoundsListener(l);
   return c;
    }
    public static JMenuItem withAddKeyListener(JMenuItem c, KeyListener l) {
   c.addKeyListener(l);
   return c;
    }
    public static JMenuItem withAddMouseListener(JMenuItem c, MouseListener l) {
   c.addMouseListener(l);
   return c;
    }
    public static JMenuItem withAddMouseMotionListener(JMenuItem c, MouseMotionListener l) {
   c.addMouseMotionListener(l);
   return c;
    }
    public static JMenuItem withAddMouseWheelListener(JMenuItem c, MouseWheelListener l) {
   c.addMouseWheelListener(l);
   return c;
    }
    public static JMenuItem withAddInputMethodListener(JMenuItem c, InputMethodListener l) {
   c.addInputMethodListener(l);
   return c;
    }
    public static JMenuItem withAddFocusListener(JMenuItem c, FocusListener l) {
   c.addFocusListener(l);
   return c;
    }

    public static JToggleButton withAddChangeListener(JToggleButton c, ChangeListener l) {
   c.addChangeListener(l);
   return c;
    }
    public static JToggleButton withAddActionListener(JToggleButton c, ActionListener l) {
   c.addActionListener(l);
   return c;
    }
    public static JToggleButton withAddItemListener(JToggleButton c, ItemListener l) {
   c.addItemListener(l);
   return c;
    }
    public static JToggleButton withAddVetoableChangeListener(JToggleButton c, VetoableChangeListener listener) {
   c.addVetoableChangeListener(listener);
   return c;
    }
    public static JToggleButton withAddAncestorListener(JToggleButton c, AncestorListener listener) {
   c.addAncestorListener(listener);
   return c;
    }
    public static JToggleButton withAddContainerListener(JToggleButton c, ContainerListener l) {
   c.addContainerListener(l);
   return c;
    }
    public static JToggleButton withAddPropertyChangeListener(JToggleButton c, PropertyChangeListener listener) {
   c.addPropertyChangeListener(listener);
   return c;
    }
    public static JToggleButton withAddComponentListener(JToggleButton c, ComponentListener l) {
   c.addComponentListener(l);
   return c;
    }
    public static JToggleButton withAddHierarchyListener(JToggleButton c, HierarchyListener l) {
   c.addHierarchyListener(l);
   return c;
    }
    public static JToggleButton withAddHierarchyBoundsListener(JToggleButton c, HierarchyBoundsListener l) {
   c.addHierarchyBoundsListener(l);
   return c;
    }
    public static JToggleButton withAddKeyListener(JToggleButton c, KeyListener l) {
   c.addKeyListener(l);
   return c;
    }
    public static JToggleButton withAddMouseListener(JToggleButton c, MouseListener l) {
   c.addMouseListener(l);
   return c;
    }
    public static JToggleButton withAddMouseMotionListener(JToggleButton c, MouseMotionListener l) {
   c.addMouseMotionListener(l);
   return c;
    }
    public static JToggleButton withAddMouseWheelListener(JToggleButton c, MouseWheelListener l) {
   c.addMouseWheelListener(l);
   return c;
    }
    public static JToggleButton withAddInputMethodListener(JToggleButton c, InputMethodListener l) {
   c.addInputMethodListener(l);
   return c;
    }
    public static JToggleButton withAddFocusListener(JToggleButton c, FocusListener l) {
   c.addFocusListener(l);
   return c;
    }
    public static AbstractButton withAddChangeListener(AbstractButton c, ChangeListener l) {
        c.addChangeListener(l);
        return c;
    }
    public static AbstractButton withAddActionListener(AbstractButton c, ActionListener l) {
        c.addActionListener(l);
        return c;
    }
    public static AbstractButton withAddItemListener(AbstractButton c, ItemListener l) {
        c.addItemListener(l);
        return c;
    }
    public static AbstractButton withAddVetoableChangeListener(AbstractButton c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static AbstractButton withAddAncestorListener(AbstractButton c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static AbstractButton withAddContainerListener(AbstractButton c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static AbstractButton withAddPropertyChangeListener(AbstractButton c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static AbstractButton withAddComponentListener(AbstractButton c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static AbstractButton withAddHierarchyListener(AbstractButton c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static AbstractButton withAddHierarchyBoundsListener(AbstractButton c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static AbstractButton withAddKeyListener(AbstractButton c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static AbstractButton withAddMouseListener(AbstractButton c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static AbstractButton withAddMouseMotionListener(AbstractButton c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static AbstractButton withAddMouseWheelListener(AbstractButton c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static AbstractButton withAddInputMethodListener(AbstractButton c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static AbstractButton withAddFocusListener(AbstractButton c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddVetoableChangeListener(BasicInternalFrameTitlePane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddAncestorListener(BasicInternalFrameTitlePane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddContainerListener(BasicInternalFrameTitlePane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddPropertyChangeListener(BasicInternalFrameTitlePane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddComponentListener(BasicInternalFrameTitlePane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddHierarchyListener(BasicInternalFrameTitlePane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddHierarchyBoundsListener(BasicInternalFrameTitlePane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddKeyListener(BasicInternalFrameTitlePane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddMouseListener(BasicInternalFrameTitlePane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddMouseMotionListener(BasicInternalFrameTitlePane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddMouseWheelListener(BasicInternalFrameTitlePane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddInputMethodListener(BasicInternalFrameTitlePane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static BasicInternalFrameTitlePane withAddFocusListener(BasicInternalFrameTitlePane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static Box withAddVetoableChangeListener(Box c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static Box withAddAncestorListener(Box c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static Box withAddContainerListener(Box c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static Box withAddPropertyChangeListener(Box c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static Box withAddComponentListener(Box c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static Box withAddHierarchyListener(Box c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static Box withAddHierarchyBoundsListener(Box c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static Box withAddKeyListener(Box c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static Box withAddMouseListener(Box c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static Box withAddMouseMotionListener(Box c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static Box withAddMouseWheelListener(Box c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static Box withAddInputMethodListener(Box c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static Box withAddFocusListener(Box c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static Box.Filler withAddVetoableChangeListener(Box.Filler c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static Box.Filler withAddAncestorListener(Box.Filler c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static Box.Filler withAddContainerListener(Box.Filler c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static Box.Filler withAddPropertyChangeListener(Box.Filler c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static Box.Filler withAddComponentListener(Box.Filler c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static Box.Filler withAddHierarchyListener(Box.Filler c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static Box.Filler withAddHierarchyBoundsListener(Box.Filler c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static Box.Filler withAddKeyListener(Box.Filler c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static Box.Filler withAddMouseListener(Box.Filler c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static Box.Filler withAddMouseMotionListener(Box.Filler c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static Box.Filler withAddMouseWheelListener(Box.Filler c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static Box.Filler withAddInputMethodListener(Box.Filler c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static Box.Filler withAddFocusListener(Box.Filler c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JColorChooser withAddVetoableChangeListener(JColorChooser c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JColorChooser withAddAncestorListener(JColorChooser c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JColorChooser withAddContainerListener(JColorChooser c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JColorChooser withAddPropertyChangeListener(JColorChooser c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JColorChooser withAddComponentListener(JColorChooser c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JColorChooser withAddHierarchyListener(JColorChooser c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JColorChooser withAddHierarchyBoundsListener(JColorChooser c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JColorChooser withAddKeyListener(JColorChooser c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JColorChooser withAddMouseListener(JColorChooser c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JColorChooser withAddMouseMotionListener(JColorChooser c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JColorChooser withAddMouseWheelListener(JColorChooser c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JColorChooser withAddInputMethodListener(JColorChooser c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JColorChooser withAddFocusListener(JColorChooser c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddVetoableChangeListener(JComboBox<E> c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static <E> JComboBox<E> withAddAncestorListener(JComboBox<E> c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static <E> JComboBox<E> withAddContainerListener(JComboBox<E> c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddPropertyChangeListener(JComboBox<E> c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static <E> JComboBox<E> withAddComponentListener(JComboBox<E> c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddHierarchyListener(JComboBox<E> c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddHierarchyBoundsListener(JComboBox<E> c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddKeyListener(JComboBox<E> c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddMouseListener(JComboBox<E> c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddMouseMotionListener(JComboBox<E> c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddMouseWheelListener(JComboBox<E> c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddInputMethodListener(JComboBox<E> c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static <E> JComboBox<E> withAddFocusListener(JComboBox<E> c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JFileChooser withAddVetoableChangeListener(JFileChooser c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JFileChooser withAddAncestorListener(JFileChooser c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JFileChooser withAddContainerListener(JFileChooser c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JFileChooser withAddPropertyChangeListener(JFileChooser c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JFileChooser withAddComponentListener(JFileChooser c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JFileChooser withAddHierarchyListener(JFileChooser c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JFileChooser withAddHierarchyBoundsListener(JFileChooser c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JFileChooser withAddKeyListener(JFileChooser c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JFileChooser withAddMouseListener(JFileChooser c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JFileChooser withAddMouseMotionListener(JFileChooser c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JFileChooser withAddMouseWheelListener(JFileChooser c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JFileChooser withAddInputMethodListener(JFileChooser c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JFileChooser withAddFocusListener(JFileChooser c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JInternalFrame withAddVetoableChangeListener(JInternalFrame c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JInternalFrame withAddAncestorListener(JInternalFrame c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JInternalFrame withAddContainerListener(JInternalFrame c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JInternalFrame withAddPropertyChangeListener(JInternalFrame c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JInternalFrame withAddComponentListener(JInternalFrame c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JInternalFrame withAddHierarchyListener(JInternalFrame c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JInternalFrame withAddHierarchyBoundsListener(JInternalFrame c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JInternalFrame withAddKeyListener(JInternalFrame c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JInternalFrame withAddMouseListener(JInternalFrame c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JInternalFrame withAddMouseMotionListener(JInternalFrame c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JInternalFrame withAddMouseWheelListener(JInternalFrame c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JInternalFrame withAddInputMethodListener(JInternalFrame c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JInternalFrame withAddFocusListener(JInternalFrame c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddVetoableChangeListener(JInternalFrame.JDesktopIcon c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddAncestorListener(JInternalFrame.JDesktopIcon c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddContainerListener(JInternalFrame.JDesktopIcon c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddPropertyChangeListener(JInternalFrame.JDesktopIcon c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddComponentListener(JInternalFrame.JDesktopIcon c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddHierarchyListener(JInternalFrame.JDesktopIcon c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddHierarchyBoundsListener(JInternalFrame.JDesktopIcon c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddKeyListener(JInternalFrame.JDesktopIcon c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddMouseListener(JInternalFrame.JDesktopIcon c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddMouseMotionListener(JInternalFrame.JDesktopIcon c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddMouseWheelListener(JInternalFrame.JDesktopIcon c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddInputMethodListener(JInternalFrame.JDesktopIcon c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JInternalFrame.JDesktopIcon withAddFocusListener(JInternalFrame.JDesktopIcon c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JLabel withAddVetoableChangeListener(JLabel c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JLabel withAddAncestorListener(JLabel c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JLabel withAddContainerListener(JLabel c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JLabel withAddPropertyChangeListener(JLabel c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JLabel withAddComponentListener(JLabel c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JLabel withAddHierarchyListener(JLabel c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JLabel withAddHierarchyBoundsListener(JLabel c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JLabel withAddKeyListener(JLabel c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JLabel withAddMouseListener(JLabel c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JLabel withAddMouseMotionListener(JLabel c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JLabel withAddMouseWheelListener(JLabel c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JLabel withAddInputMethodListener(JLabel c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JLabel withAddFocusListener(JLabel c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddVetoableChangeListener(JLayer<E> c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddAncestorListener(JLayer<E> c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddContainerListener(JLayer<E> c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddPropertyChangeListener(JLayer<E> c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddComponentListener(JLayer<E> c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddHierarchyListener(JLayer<E> c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddHierarchyBoundsListener(JLayer<E> c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddKeyListener(JLayer<E> c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddMouseListener(JLayer<E> c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddMouseMotionListener(JLayer<E> c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddMouseWheelListener(JLayer<E> c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddInputMethodListener(JLayer<E> c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static <E extends Component> JLayer<E> withAddFocusListener(JLayer<E> c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JLayeredPane withAddVetoableChangeListener(JLayeredPane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JLayeredPane withAddAncestorListener(JLayeredPane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JLayeredPane withAddContainerListener(JLayeredPane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JLayeredPane withAddPropertyChangeListener(JLayeredPane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JLayeredPane withAddComponentListener(JLayeredPane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JLayeredPane withAddHierarchyListener(JLayeredPane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JLayeredPane withAddHierarchyBoundsListener(JLayeredPane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JLayeredPane withAddKeyListener(JLayeredPane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JLayeredPane withAddMouseListener(JLayeredPane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JLayeredPane withAddMouseMotionListener(JLayeredPane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JLayeredPane withAddMouseWheelListener(JLayeredPane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JLayeredPane withAddInputMethodListener(JLayeredPane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JLayeredPane withAddFocusListener(JLayeredPane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static <E> JList<E> withAddVetoableChangeListener(JList<E> c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static <E> JList<E> withAddAncestorListener(JList<E> c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static <E> JList<E> withAddContainerListener(JList<E> c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static <E> JList<E> withAddPropertyChangeListener(JList<E> c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static <E> JList<E> withAddComponentListener(JList<E> c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static <E> JList<E> withAddHierarchyListener(JList<E> c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static <E> JList<E> withAddHierarchyBoundsListener(JList<E> c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static <E> JList<E> withAddKeyListener(JList<E> c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static <E> JList<E> withAddMouseListener(JList<E> c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static <E> JList<E> withAddMouseMotionListener(JList<E> c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static <E> JList<E> withAddMouseWheelListener(JList<E> c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static <E> JList<E> withAddInputMethodListener(JList<E> c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static <E> JList<E> withAddFocusListener(JList<E> c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JMenuBar withAddVetoableChangeListener(JMenuBar c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JMenuBar withAddAncestorListener(JMenuBar c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JMenuBar withAddContainerListener(JMenuBar c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JMenuBar withAddPropertyChangeListener(JMenuBar c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JMenuBar withAddComponentListener(JMenuBar c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JMenuBar withAddHierarchyListener(JMenuBar c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JMenuBar withAddHierarchyBoundsListener(JMenuBar c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JMenuBar withAddKeyListener(JMenuBar c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JMenuBar withAddMouseListener(JMenuBar c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JMenuBar withAddMouseMotionListener(JMenuBar c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JMenuBar withAddMouseWheelListener(JMenuBar c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JMenuBar withAddInputMethodListener(JMenuBar c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JMenuBar withAddFocusListener(JMenuBar c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JOptionPane withAddVetoableChangeListener(JOptionPane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JOptionPane withAddAncestorListener(JOptionPane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JOptionPane withAddContainerListener(JOptionPane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JOptionPane withAddPropertyChangeListener(JOptionPane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JOptionPane withAddComponentListener(JOptionPane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JOptionPane withAddHierarchyListener(JOptionPane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JOptionPane withAddHierarchyBoundsListener(JOptionPane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JOptionPane withAddKeyListener(JOptionPane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JOptionPane withAddMouseListener(JOptionPane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JOptionPane withAddMouseMotionListener(JOptionPane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JOptionPane withAddMouseWheelListener(JOptionPane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JOptionPane withAddInputMethodListener(JOptionPane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JOptionPane withAddFocusListener(JOptionPane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JPanel withAddVetoableChangeListener(JPanel c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JPanel withAddAncestorListener(JPanel c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JPanel withAddContainerListener(JPanel c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JPanel withAddPropertyChangeListener(JPanel c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JPanel withAddComponentListener(JPanel c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JPanel withAddHierarchyListener(JPanel c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JPanel withAddHierarchyBoundsListener(JPanel c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JPanel withAddKeyListener(JPanel c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JPanel withAddMouseListener(JPanel c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JPanel withAddMouseMotionListener(JPanel c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JPanel withAddMouseWheelListener(JPanel c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JPanel withAddInputMethodListener(JPanel c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JPanel withAddFocusListener(JPanel c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JPopupMenu withAddVetoableChangeListener(JPopupMenu c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JPopupMenu withAddAncestorListener(JPopupMenu c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JPopupMenu withAddContainerListener(JPopupMenu c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JPopupMenu withAddPropertyChangeListener(JPopupMenu c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JPopupMenu withAddComponentListener(JPopupMenu c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JPopupMenu withAddHierarchyListener(JPopupMenu c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JPopupMenu withAddHierarchyBoundsListener(JPopupMenu c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JPopupMenu withAddKeyListener(JPopupMenu c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JPopupMenu withAddMouseListener(JPopupMenu c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JPopupMenu withAddMouseMotionListener(JPopupMenu c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JPopupMenu withAddMouseWheelListener(JPopupMenu c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JPopupMenu withAddInputMethodListener(JPopupMenu c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JPopupMenu withAddFocusListener(JPopupMenu c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JProgressBar withAddVetoableChangeListener(JProgressBar c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JProgressBar withAddAncestorListener(JProgressBar c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JProgressBar withAddContainerListener(JProgressBar c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JProgressBar withAddPropertyChangeListener(JProgressBar c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JProgressBar withAddComponentListener(JProgressBar c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JProgressBar withAddHierarchyListener(JProgressBar c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JProgressBar withAddHierarchyBoundsListener(JProgressBar c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JProgressBar withAddKeyListener(JProgressBar c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JProgressBar withAddMouseListener(JProgressBar c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JProgressBar withAddMouseMotionListener(JProgressBar c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JProgressBar withAddMouseWheelListener(JProgressBar c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JProgressBar withAddInputMethodListener(JProgressBar c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JProgressBar withAddFocusListener(JProgressBar c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JRootPane withAddVetoableChangeListener(JRootPane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JRootPane withAddAncestorListener(JRootPane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JRootPane withAddContainerListener(JRootPane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JRootPane withAddPropertyChangeListener(JRootPane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JRootPane withAddComponentListener(JRootPane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JRootPane withAddHierarchyListener(JRootPane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JRootPane withAddHierarchyBoundsListener(JRootPane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JRootPane withAddKeyListener(JRootPane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JRootPane withAddMouseListener(JRootPane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JRootPane withAddMouseMotionListener(JRootPane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JRootPane withAddMouseWheelListener(JRootPane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JRootPane withAddInputMethodListener(JRootPane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JRootPane withAddFocusListener(JRootPane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JScrollBar withAddVetoableChangeListener(JScrollBar c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JScrollBar withAddAncestorListener(JScrollBar c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JScrollBar withAddContainerListener(JScrollBar c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JScrollBar withAddPropertyChangeListener(JScrollBar c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JScrollBar withAddComponentListener(JScrollBar c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JScrollBar withAddHierarchyListener(JScrollBar c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JScrollBar withAddHierarchyBoundsListener(JScrollBar c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JScrollBar withAddKeyListener(JScrollBar c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JScrollBar withAddMouseListener(JScrollBar c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JScrollBar withAddMouseMotionListener(JScrollBar c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JScrollBar withAddMouseWheelListener(JScrollBar c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JScrollBar withAddInputMethodListener(JScrollBar c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JScrollBar withAddFocusListener(JScrollBar c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JScrollPane withAddVetoableChangeListener(JScrollPane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JScrollPane withAddAncestorListener(JScrollPane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JScrollPane withAddContainerListener(JScrollPane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JScrollPane withAddPropertyChangeListener(JScrollPane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JScrollPane withAddComponentListener(JScrollPane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JScrollPane withAddHierarchyListener(JScrollPane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JScrollPane withAddHierarchyBoundsListener(JScrollPane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JScrollPane withAddKeyListener(JScrollPane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JScrollPane withAddMouseListener(JScrollPane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JScrollPane withAddMouseMotionListener(JScrollPane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JScrollPane withAddMouseWheelListener(JScrollPane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JScrollPane withAddInputMethodListener(JScrollPane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JScrollPane withAddFocusListener(JScrollPane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JSeparator withAddVetoableChangeListener(JSeparator c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JSeparator withAddAncestorListener(JSeparator c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JSeparator withAddContainerListener(JSeparator c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JSeparator withAddPropertyChangeListener(JSeparator c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JSeparator withAddComponentListener(JSeparator c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JSeparator withAddHierarchyListener(JSeparator c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JSeparator withAddHierarchyBoundsListener(JSeparator c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JSeparator withAddKeyListener(JSeparator c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JSeparator withAddMouseListener(JSeparator c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JSeparator withAddMouseMotionListener(JSeparator c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JSeparator withAddMouseWheelListener(JSeparator c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JSeparator withAddInputMethodListener(JSeparator c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JSeparator withAddFocusListener(JSeparator c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JSlider withAddVetoableChangeListener(JSlider c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JSlider withAddAncestorListener(JSlider c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JSlider withAddContainerListener(JSlider c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JSlider withAddPropertyChangeListener(JSlider c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JSlider withAddComponentListener(JSlider c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JSlider withAddHierarchyListener(JSlider c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JSlider withAddHierarchyBoundsListener(JSlider c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JSlider withAddKeyListener(JSlider c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JSlider withAddMouseListener(JSlider c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JSlider withAddMouseMotionListener(JSlider c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JSlider withAddMouseWheelListener(JSlider c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JSlider withAddInputMethodListener(JSlider c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JSlider withAddFocusListener(JSlider c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JSpinner withAddVetoableChangeListener(JSpinner c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JSpinner withAddAncestorListener(JSpinner c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JSpinner withAddContainerListener(JSpinner c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JSpinner withAddPropertyChangeListener(JSpinner c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JSpinner withAddComponentListener(JSpinner c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JSpinner withAddHierarchyListener(JSpinner c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JSpinner withAddHierarchyBoundsListener(JSpinner c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JSpinner withAddKeyListener(JSpinner c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JSpinner withAddMouseListener(JSpinner c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JSpinner withAddMouseMotionListener(JSpinner c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JSpinner withAddMouseWheelListener(JSpinner c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JSpinner withAddInputMethodListener(JSpinner c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JSpinner withAddFocusListener(JSpinner c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JSplitPane withAddVetoableChangeListener(JSplitPane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JSplitPane withAddAncestorListener(JSplitPane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JSplitPane withAddContainerListener(JSplitPane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JSplitPane withAddPropertyChangeListener(JSplitPane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JSplitPane withAddComponentListener(JSplitPane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JSplitPane withAddHierarchyListener(JSplitPane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JSplitPane withAddHierarchyBoundsListener(JSplitPane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JSplitPane withAddKeyListener(JSplitPane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JSplitPane withAddMouseListener(JSplitPane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JSplitPane withAddMouseMotionListener(JSplitPane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JSplitPane withAddMouseWheelListener(JSplitPane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JSplitPane withAddInputMethodListener(JSplitPane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JSplitPane withAddFocusListener(JSplitPane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JTabbedPane withAddVetoableChangeListener(JTabbedPane c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JTabbedPane withAddAncestorListener(JTabbedPane c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JTabbedPane withAddContainerListener(JTabbedPane c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JTabbedPane withAddPropertyChangeListener(JTabbedPane c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JTabbedPane withAddComponentListener(JTabbedPane c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JTabbedPane withAddHierarchyListener(JTabbedPane c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JTabbedPane withAddHierarchyBoundsListener(JTabbedPane c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JTabbedPane withAddKeyListener(JTabbedPane c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JTabbedPane withAddMouseListener(JTabbedPane c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JTabbedPane withAddMouseMotionListener(JTabbedPane c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JTabbedPane withAddMouseWheelListener(JTabbedPane c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JTabbedPane withAddInputMethodListener(JTabbedPane c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JTabbedPane withAddFocusListener(JTabbedPane c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JTable withAddVetoableChangeListener(JTable c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JTable withAddAncestorListener(JTable c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JTable withAddContainerListener(JTable c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JTable withAddPropertyChangeListener(JTable c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JTable withAddComponentListener(JTable c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JTable withAddHierarchyListener(JTable c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JTable withAddHierarchyBoundsListener(JTable c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JTable withAddKeyListener(JTable c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JTable withAddMouseListener(JTable c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JTable withAddMouseMotionListener(JTable c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JTable withAddMouseWheelListener(JTable c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JTable withAddInputMethodListener(JTable c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JTable withAddFocusListener(JTable c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JTableHeader withAddVetoableChangeListener(JTableHeader c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JTableHeader withAddAncestorListener(JTableHeader c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JTableHeader withAddContainerListener(JTableHeader c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JTableHeader withAddPropertyChangeListener(JTableHeader c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JTableHeader withAddComponentListener(JTableHeader c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JTableHeader withAddHierarchyListener(JTableHeader c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JTableHeader withAddHierarchyBoundsListener(JTableHeader c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JTableHeader withAddKeyListener(JTableHeader c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JTableHeader withAddMouseListener(JTableHeader c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JTableHeader withAddMouseMotionListener(JTableHeader c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JTableHeader withAddMouseWheelListener(JTableHeader c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JTableHeader withAddInputMethodListener(JTableHeader c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JTableHeader withAddFocusListener(JTableHeader c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JTextComponent withAddVetoableChangeListener(JTextComponent c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JTextComponent withAddAncestorListener(JTextComponent c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JTextComponent withAddContainerListener(JTextComponent c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JTextComponent withAddPropertyChangeListener(JTextComponent c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JTextComponent withAddComponentListener(JTextComponent c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JTextComponent withAddHierarchyListener(JTextComponent c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JTextComponent withAddHierarchyBoundsListener(JTextComponent c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JTextComponent withAddKeyListener(JTextComponent c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JTextComponent withAddMouseListener(JTextComponent c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JTextComponent withAddMouseMotionListener(JTextComponent c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JTextComponent withAddMouseWheelListener(JTextComponent c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JTextComponent withAddInputMethodListener(JTextComponent c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JTextComponent withAddFocusListener(JTextComponent c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JToolBar withAddVetoableChangeListener(JToolBar c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JToolBar withAddAncestorListener(JToolBar c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JToolBar withAddContainerListener(JToolBar c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JToolBar withAddPropertyChangeListener(JToolBar c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JToolBar withAddComponentListener(JToolBar c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JToolBar withAddHierarchyListener(JToolBar c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JToolBar withAddHierarchyBoundsListener(JToolBar c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JToolBar withAddKeyListener(JToolBar c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JToolBar withAddMouseListener(JToolBar c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JToolBar withAddMouseMotionListener(JToolBar c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JToolBar withAddMouseWheelListener(JToolBar c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JToolBar withAddInputMethodListener(JToolBar c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JToolBar withAddFocusListener(JToolBar c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JToolTip withAddVetoableChangeListener(JToolTip c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JToolTip withAddAncestorListener(JToolTip c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JToolTip withAddContainerListener(JToolTip c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JToolTip withAddPropertyChangeListener(JToolTip c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JToolTip withAddComponentListener(JToolTip c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JToolTip withAddHierarchyListener(JToolTip c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JToolTip withAddHierarchyBoundsListener(JToolTip c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JToolTip withAddKeyListener(JToolTip c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JToolTip withAddMouseListener(JToolTip c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JToolTip withAddMouseMotionListener(JToolTip c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JToolTip withAddMouseWheelListener(JToolTip c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JToolTip withAddInputMethodListener(JToolTip c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JToolTip withAddFocusListener(JToolTip c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JTree withAddVetoableChangeListener(JTree c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JTree withAddAncestorListener(JTree c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JTree withAddContainerListener(JTree c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JTree withAddPropertyChangeListener(JTree c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JTree withAddComponentListener(JTree c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JTree withAddHierarchyListener(JTree c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JTree withAddHierarchyBoundsListener(JTree c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JTree withAddKeyListener(JTree c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JTree withAddMouseListener(JTree c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JTree withAddMouseMotionListener(JTree c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JTree withAddMouseWheelListener(JTree c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JTree withAddInputMethodListener(JTree c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JTree withAddFocusListener(JTree c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JViewport withAddVetoableChangeListener(JViewport c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JViewport withAddAncestorListener(JViewport c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JViewport withAddContainerListener(JViewport c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JViewport withAddPropertyChangeListener(JViewport c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JViewport withAddComponentListener(JViewport c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JViewport withAddHierarchyListener(JViewport c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JViewport withAddHierarchyBoundsListener(JViewport c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JViewport withAddKeyListener(JViewport c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JViewport withAddMouseListener(JViewport c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JViewport withAddMouseMotionListener(JViewport c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JViewport withAddMouseWheelListener(JViewport c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JViewport withAddInputMethodListener(JViewport c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JViewport withAddFocusListener(JViewport c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }
    public static JComponent withAddVetoableChangeListener(JComponent c, VetoableChangeListener listener) {
        c.addVetoableChangeListener(listener);
        return c;
    }
    public static JComponent withAddAncestorListener(JComponent c, AncestorListener listener) {
        c.addAncestorListener(listener);
        return c;
    }
    public static JComponent withAddContainerListener(JComponent c, ContainerListener l) {
        c.addContainerListener(l);
        return c;
    }
    public static JComponent withAddPropertyChangeListener(JComponent c, PropertyChangeListener listener) {
        c.addPropertyChangeListener(listener);
        return c;
    }
    public static JComponent withAddComponentListener(JComponent c, ComponentListener l) {
        c.addComponentListener(l);
        return c;
    }
    public static JComponent withAddHierarchyListener(JComponent c, HierarchyListener l) {
        c.addHierarchyListener(l);
        return c;
    }
    public static JComponent withAddHierarchyBoundsListener(JComponent c, HierarchyBoundsListener l) {
        c.addHierarchyBoundsListener(l);
        return c;
    }
    public static JComponent withAddKeyListener(JComponent c, KeyListener l) {
        c.addKeyListener(l);
        return c;
    }
    public static JComponent withAddMouseListener(JComponent c, MouseListener l) {
        c.addMouseListener(l);
        return c;
    }
    public static JComponent withAddMouseMotionListener(JComponent c, MouseMotionListener l) {
        c.addMouseMotionListener(l);
        return c;
    }
    public static JComponent withAddMouseWheelListener(JComponent c, MouseWheelListener l) {
        c.addMouseWheelListener(l);
        return c;
    }
    public static JComponent withAddInputMethodListener(JComponent c, InputMethodListener l) {
        c.addInputMethodListener(l);
        return c;
    }
    public static JComponent withAddFocusListener(JComponent c, FocusListener l) {
        c.addFocusListener(l);
        return c;
    }

    public void setSize(Applet e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Applet e, Dimension d){e.resize(d);}
    public Dimension getSize(Applet e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Applet e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Applet e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Applet e, Dimension d){e.setMaximumSize(d);}

    public Applet withSetSize(Applet e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Applet withResize(Applet e, Dimension d){e.resize(d);  return e;}
    public Applet withSetPreferredSize(Applet e, Dimension d){e.setPreferredSize(d);return e;}
    public Applet withSetMinimumSize(Applet e, Dimension d){e.setMinimumSize(d);return e;}
    public Applet withSetMaximumSize(Applet e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Applet e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Applet e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Applet e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Applet e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Applet e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Applet e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Applet withSetSize(Applet e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Applet withResize(Applet e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Applet withSetPreferredSize(Applet e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Applet withSetMinimumSize(Applet e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Applet withSetMaximumSize(Applet e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(AbstractButton e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(AbstractButton e, Dimension d){e.resize(d);}
    public Dimension getSize(AbstractButton e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(AbstractButton e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(AbstractButton e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(AbstractButton e, Dimension d){e.setMaximumSize(d);}

    public AbstractButton withSetSize(AbstractButton e, Dimension d){e.setSize(d); return e;}
    @Deprecated public AbstractButton withResize(AbstractButton e, Dimension d){e.resize(d);  return e;}
    public AbstractButton withSetPreferredSize(AbstractButton e, Dimension d){e.setPreferredSize(d);return e;}
    public AbstractButton withSetMinimumSize(AbstractButton e, Dimension d){e.setMinimumSize(d);return e;}
    public AbstractButton withSetMaximumSize(AbstractButton e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(AbstractButton e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(AbstractButton e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(AbstractButton e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(AbstractButton e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(AbstractButton e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(AbstractButton e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public AbstractButton withSetSize(AbstractButton e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public AbstractButton withResize(AbstractButton e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public AbstractButton withSetPreferredSize(AbstractButton e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public AbstractButton withSetMinimumSize(AbstractButton e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public AbstractButton withSetMaximumSize(AbstractButton e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(BasicInternalFrameTitlePane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(BasicInternalFrameTitlePane e, Dimension d){e.resize(d);}
    public Dimension getSize(BasicInternalFrameTitlePane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(BasicInternalFrameTitlePane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(BasicInternalFrameTitlePane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(BasicInternalFrameTitlePane e, Dimension d){e.setMaximumSize(d);}

    public BasicInternalFrameTitlePane withSetSize(BasicInternalFrameTitlePane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public BasicInternalFrameTitlePane withResize(BasicInternalFrameTitlePane e, Dimension d){e.resize(d);  return e;}
    public BasicInternalFrameTitlePane withSetPreferredSize(BasicInternalFrameTitlePane e, Dimension d){e.setPreferredSize(d);return e;}
    public BasicInternalFrameTitlePane withSetMinimumSize(BasicInternalFrameTitlePane e, Dimension d){e.setMinimumSize(d);return e;}
    public BasicInternalFrameTitlePane withSetMaximumSize(BasicInternalFrameTitlePane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(BasicInternalFrameTitlePane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(BasicInternalFrameTitlePane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(BasicInternalFrameTitlePane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(BasicInternalFrameTitlePane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(BasicInternalFrameTitlePane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(BasicInternalFrameTitlePane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public BasicInternalFrameTitlePane withSetSize(BasicInternalFrameTitlePane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public BasicInternalFrameTitlePane withResize(BasicInternalFrameTitlePane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public BasicInternalFrameTitlePane withSetPreferredSize(BasicInternalFrameTitlePane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public BasicInternalFrameTitlePane withSetMinimumSize(BasicInternalFrameTitlePane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public BasicInternalFrameTitlePane withSetMaximumSize(BasicInternalFrameTitlePane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Box e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Box e, Dimension d){e.resize(d);}
    public Dimension getSize(Box e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Box e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Box e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Box e, Dimension d){e.setMaximumSize(d);}

    public Box withSetSize(Box e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Box withResize(Box e, Dimension d){e.resize(d);  return e;}
    public Box withSetPreferredSize(Box e, Dimension d){e.setPreferredSize(d);return e;}
    public Box withSetMinimumSize(Box e, Dimension d){e.setMinimumSize(d);return e;}
    public Box withSetMaximumSize(Box e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Box e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Box e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Box e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Box e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Box e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Box e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Box withSetSize(Box e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Box withResize(Box e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Box withSetPreferredSize(Box e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Box withSetMinimumSize(Box e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Box withSetMaximumSize(Box e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Box.Filler e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Box.Filler e, Dimension d){e.resize(d);}
    public Dimension getSize(Box.Filler e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Box.Filler e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Box.Filler e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Box.Filler e, Dimension d){e.setMaximumSize(d);}

    public Box.Filler withSetSize(Box.Filler e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Box.Filler withResize(Box.Filler e, Dimension d){e.resize(d);  return e;}
    public Box.Filler withSetPreferredSize(Box.Filler e, Dimension d){e.setPreferredSize(d);return e;}
    public Box.Filler withSetMinimumSize(Box.Filler e, Dimension d){e.setMinimumSize(d);return e;}
    public Box.Filler withSetMaximumSize(Box.Filler e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Box.Filler e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Box.Filler e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Box.Filler e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Box.Filler e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Box.Filler e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Box.Filler e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Box.Filler withSetSize(Box.Filler e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Box.Filler withResize(Box.Filler e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Box.Filler withSetPreferredSize(Box.Filler e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Box.Filler withSetMinimumSize(Box.Filler e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Box.Filler withSetMaximumSize(Box.Filler e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JColorChooser e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JColorChooser e, Dimension d){e.resize(d);}
    public Dimension getSize(JColorChooser e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JColorChooser e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JColorChooser e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JColorChooser e, Dimension d){e.setMaximumSize(d);}

    public JColorChooser withSetSize(JColorChooser e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JColorChooser withResize(JColorChooser e, Dimension d){e.resize(d);  return e;}
    public JColorChooser withSetPreferredSize(JColorChooser e, Dimension d){e.setPreferredSize(d);return e;}
    public JColorChooser withSetMinimumSize(JColorChooser e, Dimension d){e.setMinimumSize(d);return e;}
    public JColorChooser withSetMaximumSize(JColorChooser e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JColorChooser e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JColorChooser e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JColorChooser e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JColorChooser e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JColorChooser e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JColorChooser e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JColorChooser withSetSize(JColorChooser e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JColorChooser withResize(JColorChooser e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JColorChooser withSetPreferredSize(JColorChooser e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JColorChooser withSetMinimumSize(JColorChooser e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JColorChooser withSetMaximumSize(JColorChooser e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public <E> void setSize(JComboBox<E> e, Dimension d){e.setSize(d);}
    @Deprecated public <E> void resize(JComboBox<E> e, Dimension d){e.resize(d);}
    public <E> Dimension getSize(JComboBox<E> e, Dimension d){return e.getSize(d);}
    public <E> void setPreferredSize(JComboBox<E> e, Dimension d){e.setPreferredSize(d);}
    public <E> void setMinimumSize(JComboBox<E> e, Dimension d){e.setMinimumSize(d);}
    public <E> void setMaximumSize(JComboBox<E> e, Dimension d){e.setMaximumSize(d);}

    public <E> JComboBox<E> withSetSize(JComboBox<E> e, Dimension d){e.setSize(d); return e;}
    @Deprecated public <E> JComboBox<E> withResize(JComboBox<E> e, Dimension d){e.resize(d);  return e;}
    public <E> JComboBox<E> withSetPreferredSize(JComboBox<E> e, Dimension d){e.setPreferredSize(d);return e;}
    public <E> JComboBox<E> withSetMinimumSize(JComboBox<E> e, Dimension d){e.setMinimumSize(d);return e;}
    public <E> JComboBox<E> withSetMaximumSize(JComboBox<E> e, Dimension d){e.setMaximumSize(d);return e;}

    public <E> void setSize(JComboBox<E> e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public <E> void resize(JComboBox<E> e, int x, int y){e.resize(new Dimension(x, y));}
    public <E> Dimension getSize(JComboBox<E> e, int x, int y){return e.getSize(new Dimension(x, y));}
    public <E> void setPreferredSize(JComboBox<E> e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public <E> void setMinimumSize(JComboBox<E> e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public <E> void setMaximumSize(JComboBox<E> e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public <E> JComboBox<E> withSetSize(JComboBox<E> e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public <E> JComboBox<E> withResize(JComboBox<E> e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public <E> JComboBox<E> withSetPreferredSize(JComboBox<E> e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public <E> JComboBox<E> withSetMinimumSize(JComboBox<E> e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public <E> JComboBox<E> withSetMaximumSize(JComboBox<E> e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JFileChooser e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JFileChooser e, Dimension d){e.resize(d);}
    public Dimension getSize(JFileChooser e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JFileChooser e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JFileChooser e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JFileChooser e, Dimension d){e.setMaximumSize(d);}

    public JFileChooser withSetSize(JFileChooser e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JFileChooser withResize(JFileChooser e, Dimension d){e.resize(d);  return e;}
    public JFileChooser withSetPreferredSize(JFileChooser e, Dimension d){e.setPreferredSize(d);return e;}
    public JFileChooser withSetMinimumSize(JFileChooser e, Dimension d){e.setMinimumSize(d);return e;}
    public JFileChooser withSetMaximumSize(JFileChooser e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JFileChooser e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JFileChooser e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JFileChooser e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JFileChooser e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JFileChooser e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JFileChooser e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JFileChooser withSetSize(JFileChooser e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JFileChooser withResize(JFileChooser e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JFileChooser withSetPreferredSize(JFileChooser e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JFileChooser withSetMinimumSize(JFileChooser e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JFileChooser withSetMaximumSize(JFileChooser e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JInternalFrame e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JInternalFrame e, Dimension d){e.resize(d);}
    public Dimension getSize(JInternalFrame e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JInternalFrame e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JInternalFrame e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JInternalFrame e, Dimension d){e.setMaximumSize(d);}

    public JInternalFrame withSetSize(JInternalFrame e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JInternalFrame withResize(JInternalFrame e, Dimension d){e.resize(d);  return e;}
    public JInternalFrame withSetPreferredSize(JInternalFrame e, Dimension d){e.setPreferredSize(d);return e;}
    public JInternalFrame withSetMinimumSize(JInternalFrame e, Dimension d){e.setMinimumSize(d);return e;}
    public JInternalFrame withSetMaximumSize(JInternalFrame e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JInternalFrame e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JInternalFrame e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JInternalFrame e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JInternalFrame e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JInternalFrame e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JInternalFrame e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JInternalFrame withSetSize(JInternalFrame e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JInternalFrame withResize(JInternalFrame e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JInternalFrame withSetPreferredSize(JInternalFrame e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JInternalFrame withSetMinimumSize(JInternalFrame e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JInternalFrame withSetMaximumSize(JInternalFrame e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JInternalFrame.JDesktopIcon e, Dimension d){e.resize(d);}
    public Dimension getSize(JInternalFrame.JDesktopIcon e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setMaximumSize(d);}

    public JInternalFrame.JDesktopIcon withSetSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JInternalFrame.JDesktopIcon withResize(JInternalFrame.JDesktopIcon e, Dimension d){e.resize(d);  return e;}
    public JInternalFrame.JDesktopIcon withSetPreferredSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setPreferredSize(d);return e;}
    public JInternalFrame.JDesktopIcon withSetMinimumSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setMinimumSize(d);return e;}
    public JInternalFrame.JDesktopIcon withSetMaximumSize(JInternalFrame.JDesktopIcon e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JInternalFrame.JDesktopIcon e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JInternalFrame.JDesktopIcon e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JInternalFrame.JDesktopIcon withSetSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JInternalFrame.JDesktopIcon withResize(JInternalFrame.JDesktopIcon e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JInternalFrame.JDesktopIcon withSetPreferredSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JInternalFrame.JDesktopIcon withSetMinimumSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JInternalFrame.JDesktopIcon withSetMaximumSize(JInternalFrame.JDesktopIcon e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JLabel e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JLabel e, Dimension d){e.resize(d);}
    public Dimension getSize(JLabel e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JLabel e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JLabel e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JLabel e, Dimension d){e.setMaximumSize(d);}

    public JLabel withSetSize(JLabel e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JLabel withResize(JLabel e, Dimension d){e.resize(d);  return e;}
    public JLabel withSetPreferredSize(JLabel e, Dimension d){e.setPreferredSize(d);return e;}
    public JLabel withSetMinimumSize(JLabel e, Dimension d){e.setMinimumSize(d);return e;}
    public JLabel withSetMaximumSize(JLabel e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JLabel e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JLabel e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JLabel e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JLabel e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JLabel e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JLabel e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JLabel withSetSize(JLabel e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JLabel withResize(JLabel e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JLabel withSetPreferredSize(JLabel e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JLabel withSetMinimumSize(JLabel e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JLabel withSetMaximumSize(JLabel e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public <E extends Component> void setSize(JLayer<E> e, Dimension d){e.setSize(d);}
    @Deprecated public <E extends Component> void resize(JLayer<E> e, Dimension d){e.resize(d);}
    public <E extends Component> Dimension getSize(JLayer<E> e, Dimension d){return e.getSize(d);}
    public <E extends Component> void setPreferredSize(JLayer<E> e, Dimension d){e.setPreferredSize(d);}
    public <E extends Component> void setMinimumSize(JLayer<E> e, Dimension d){e.setMinimumSize(d);}
    public <E extends Component> void setMaximumSize(JLayer<E> e, Dimension d){e.setMaximumSize(d);}

    public <E extends Component> JLayer<E> withSetSize(JLayer<E> e, Dimension d){e.setSize(d); return e;}
    @Deprecated public <E extends Component> JLayer<E> withResize(JLayer<E> e, Dimension d){e.resize(d);  return e;}
    public <E extends Component> JLayer<E> withSetPreferredSize(JLayer<E> e, Dimension d){e.setPreferredSize(d);return e;}
    public <E extends Component> JLayer<E> withSetMinimumSize(JLayer<E> e, Dimension d){e.setMinimumSize(d);return e;}
    public <E extends Component> JLayer<E> withSetMaximumSize(JLayer<E> e, Dimension d){e.setMaximumSize(d);return e;}

    public <E extends Component> void setSize(JLayer<E> e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public <E extends Component> void resize(JLayer<E> e, int x, int y){e.resize(new Dimension(x, y));}
    public <E extends Component> Dimension getSize(JLayer<E> e, int x, int y){return e.getSize(new Dimension(x, y));}
    public <E extends Component> void setPreferredSize(JLayer<E> e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public <E extends Component> void setMinimumSize(JLayer<E> e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public <E extends Component> void setMaximumSize(JLayer<E> e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public <E extends Component> JLayer<E> withSetSize(JLayer<E> e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public <E extends Component> JLayer<E> withResize(JLayer<E> e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public <E extends Component> JLayer<E> withSetPreferredSize(JLayer<E> e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public <E extends Component> JLayer<E> withSetMinimumSize(JLayer<E> e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public <E extends Component> JLayer<E> withSetMaximumSize(JLayer<E> e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JLayeredPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JLayeredPane e, Dimension d){e.resize(d);}
    public Dimension getSize(JLayeredPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JLayeredPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JLayeredPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JLayeredPane e, Dimension d){e.setMaximumSize(d);}

    public JLayeredPane withSetSize(JLayeredPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JLayeredPane withResize(JLayeredPane e, Dimension d){e.resize(d);  return e;}
    public JLayeredPane withSetPreferredSize(JLayeredPane e, Dimension d){e.setPreferredSize(d);return e;}
    public JLayeredPane withSetMinimumSize(JLayeredPane e, Dimension d){e.setMinimumSize(d);return e;}
    public JLayeredPane withSetMaximumSize(JLayeredPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JLayeredPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JLayeredPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JLayeredPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JLayeredPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JLayeredPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JLayeredPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JLayeredPane withSetSize(JLayeredPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JLayeredPane withResize(JLayeredPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JLayeredPane withSetPreferredSize(JLayeredPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JLayeredPane withSetMinimumSize(JLayeredPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JLayeredPane withSetMaximumSize(JLayeredPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public <E> void setSize(JList<E> e, Dimension d){e.setSize(d);}
    @Deprecated public <E> void resize(JList<E> e, Dimension d){e.resize(d);}
    public <E> Dimension getSize(JList<E> e, Dimension d){return e.getSize(d);}
    public <E> void setPreferredSize(JList<E> e, Dimension d){e.setPreferredSize(d);}
    public <E> void setMinimumSize(JList<E> e, Dimension d){e.setMinimumSize(d);}
    public <E> void setMaximumSize(JList<E> e, Dimension d){e.setMaximumSize(d);}

    public <E> JList<E> withSetSize(JList<E> e, Dimension d){e.setSize(d); return e;}
    @Deprecated public <E> JList<E> withResize(JList<E> e, Dimension d){e.resize(d);  return e;}
    public <E> JList<E> withSetPreferredSize(JList<E> e, Dimension d){e.setPreferredSize(d);return e;}
    public <E> JList<E> withSetMinimumSize(JList<E> e, Dimension d){e.setMinimumSize(d);return e;}
    public <E> JList<E> withSetMaximumSize(JList<E> e, Dimension d){e.setMaximumSize(d);return e;}

    public <E> void setSize(JList<E> e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public <E> void resize(JList<E> e, int x, int y){e.resize(new Dimension(x, y));}
    public <E> Dimension getSize(JList<E> e, int x, int y){return e.getSize(new Dimension(x, y));}
    public <E> void setPreferredSize(JList<E> e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public <E> void setMinimumSize(JList<E> e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public <E> void setMaximumSize(JList<E> e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public <E> JList<E> withSetSize(JList<E> e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public <E> JList<E> withResize(JList<E> e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public <E> JList<E> withSetPreferredSize(JList<E> e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public <E> JList<E> withSetMinimumSize(JList<E> e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public <E> JList<E> withSetMaximumSize(JList<E> e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JMenuBar e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JMenuBar e, Dimension d){e.resize(d);}
    public Dimension getSize(JMenuBar e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JMenuBar e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JMenuBar e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JMenuBar e, Dimension d){e.setMaximumSize(d);}

    public JMenuBar withSetSize(JMenuBar e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JMenuBar withResize(JMenuBar e, Dimension d){e.resize(d);  return e;}
    public JMenuBar withSetPreferredSize(JMenuBar e, Dimension d){e.setPreferredSize(d);return e;}
    public JMenuBar withSetMinimumSize(JMenuBar e, Dimension d){e.setMinimumSize(d);return e;}
    public JMenuBar withSetMaximumSize(JMenuBar e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JMenuBar e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JMenuBar e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JMenuBar e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JMenuBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JMenuBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JMenuBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JMenuBar withSetSize(JMenuBar e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JMenuBar withResize(JMenuBar e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JMenuBar withSetPreferredSize(JMenuBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JMenuBar withSetMinimumSize(JMenuBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JMenuBar withSetMaximumSize(JMenuBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JOptionPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JOptionPane e, Dimension d){e.resize(d);}
    public Dimension getSize(JOptionPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JOptionPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JOptionPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JOptionPane e, Dimension d){e.setMaximumSize(d);}

    public JOptionPane withSetSize(JOptionPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JOptionPane withResize(JOptionPane e, Dimension d){e.resize(d);  return e;}
    public JOptionPane withSetPreferredSize(JOptionPane e, Dimension d){e.setPreferredSize(d);return e;}
    public JOptionPane withSetMinimumSize(JOptionPane e, Dimension d){e.setMinimumSize(d);return e;}
    public JOptionPane withSetMaximumSize(JOptionPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JOptionPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JOptionPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JOptionPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JOptionPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JOptionPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JOptionPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JOptionPane withSetSize(JOptionPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JOptionPane withResize(JOptionPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JOptionPane withSetPreferredSize(JOptionPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JOptionPane withSetMinimumSize(JOptionPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JOptionPane withSetMaximumSize(JOptionPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JPanel e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JPanel e, Dimension d){e.resize(d);}
    public Dimension getSize(JPanel e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JPanel e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JPanel e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JPanel e, Dimension d){e.setMaximumSize(d);}

    public JPanel withSetSize(JPanel e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JPanel withResize(JPanel e, Dimension d){e.resize(d);  return e;}
    public JPanel withSetPreferredSize(JPanel e, Dimension d){e.setPreferredSize(d);return e;}
    public JPanel withSetMinimumSize(JPanel e, Dimension d){e.setMinimumSize(d);return e;}
    public JPanel withSetMaximumSize(JPanel e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JPanel e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JPanel e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JPanel e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JPanel e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JPanel e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JPanel e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JPanel withSetSize(JPanel e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JPanel withResize(JPanel e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JPanel withSetPreferredSize(JPanel e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JPanel withSetMinimumSize(JPanel e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JPanel withSetMaximumSize(JPanel e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JPopupMenu e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JPopupMenu e, Dimension d){e.resize(d);}
    public Dimension getSize(JPopupMenu e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JPopupMenu e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JPopupMenu e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JPopupMenu e, Dimension d){e.setMaximumSize(d);}

    public JPopupMenu withSetSize(JPopupMenu e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JPopupMenu withResize(JPopupMenu e, Dimension d){e.resize(d);  return e;}
    public JPopupMenu withSetPreferredSize(JPopupMenu e, Dimension d){e.setPreferredSize(d);return e;}
    public JPopupMenu withSetMinimumSize(JPopupMenu e, Dimension d){e.setMinimumSize(d);return e;}
    public JPopupMenu withSetMaximumSize(JPopupMenu e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JPopupMenu e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JPopupMenu e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JPopupMenu e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JPopupMenu e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JPopupMenu e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JPopupMenu e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JPopupMenu withSetSize(JPopupMenu e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JPopupMenu withResize(JPopupMenu e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JPopupMenu withSetPreferredSize(JPopupMenu e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JPopupMenu withSetMinimumSize(JPopupMenu e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JPopupMenu withSetMaximumSize(JPopupMenu e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JProgressBar e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JProgressBar e, Dimension d){e.resize(d);}
    public Dimension getSize(JProgressBar e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JProgressBar e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JProgressBar e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JProgressBar e, Dimension d){e.setMaximumSize(d);}

    public JProgressBar withSetSize(JProgressBar e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JProgressBar withResize(JProgressBar e, Dimension d){e.resize(d);  return e;}
    public JProgressBar withSetPreferredSize(JProgressBar e, Dimension d){e.setPreferredSize(d);return e;}
    public JProgressBar withSetMinimumSize(JProgressBar e, Dimension d){e.setMinimumSize(d);return e;}
    public JProgressBar withSetMaximumSize(JProgressBar e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JProgressBar e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JProgressBar e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JProgressBar e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JProgressBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JProgressBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JProgressBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JProgressBar withSetSize(JProgressBar e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JProgressBar withResize(JProgressBar e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JProgressBar withSetPreferredSize(JProgressBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JProgressBar withSetMinimumSize(JProgressBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JProgressBar withSetMaximumSize(JProgressBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JRootPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JRootPane e, Dimension d){e.resize(d);}
    public Dimension getSize(JRootPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JRootPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JRootPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JRootPane e, Dimension d){e.setMaximumSize(d);}

    public JRootPane withSetSize(JRootPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JRootPane withResize(JRootPane e, Dimension d){e.resize(d);  return e;}
    public JRootPane withSetPreferredSize(JRootPane e, Dimension d){e.setPreferredSize(d);return e;}
    public JRootPane withSetMinimumSize(JRootPane e, Dimension d){e.setMinimumSize(d);return e;}
    public JRootPane withSetMaximumSize(JRootPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JRootPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JRootPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JRootPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JRootPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JRootPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JRootPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JRootPane withSetSize(JRootPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JRootPane withResize(JRootPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JRootPane withSetPreferredSize(JRootPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JRootPane withSetMinimumSize(JRootPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JRootPane withSetMaximumSize(JRootPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JScrollBar e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JScrollBar e, Dimension d){e.resize(d);}
    public Dimension getSize(JScrollBar e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JScrollBar e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JScrollBar e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JScrollBar e, Dimension d){e.setMaximumSize(d);}

    public JScrollBar withSetSize(JScrollBar e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JScrollBar withResize(JScrollBar e, Dimension d){e.resize(d);  return e;}
    public JScrollBar withSetPreferredSize(JScrollBar e, Dimension d){e.setPreferredSize(d);return e;}
    public JScrollBar withSetMinimumSize(JScrollBar e, Dimension d){e.setMinimumSize(d);return e;}
    public JScrollBar withSetMaximumSize(JScrollBar e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JScrollBar e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JScrollBar e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JScrollBar e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JScrollBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JScrollBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JScrollBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JScrollBar withSetSize(JScrollBar e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JScrollBar withResize(JScrollBar e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JScrollBar withSetPreferredSize(JScrollBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JScrollBar withSetMinimumSize(JScrollBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JScrollBar withSetMaximumSize(JScrollBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JScrollPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JScrollPane e, Dimension d){e.resize(d);}
    public Dimension getSize(JScrollPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JScrollPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JScrollPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JScrollPane e, Dimension d){e.setMaximumSize(d);}

    public JScrollPane withSetSize(JScrollPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JScrollPane withResize(JScrollPane e, Dimension d){e.resize(d);  return e;}
    public JScrollPane withSetPreferredSize(JScrollPane e, Dimension d){e.setPreferredSize(d);return e;}
    public JScrollPane withSetMinimumSize(JScrollPane e, Dimension d){e.setMinimumSize(d);return e;}
    public JScrollPane withSetMaximumSize(JScrollPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JScrollPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JScrollPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JScrollPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JScrollPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JScrollPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JScrollPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JScrollPane withSetSize(JScrollPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JScrollPane withResize(JScrollPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JScrollPane withSetPreferredSize(JScrollPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JScrollPane withSetMinimumSize(JScrollPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JScrollPane withSetMaximumSize(JScrollPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JSeparator e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JSeparator e, Dimension d){e.resize(d);}
    public Dimension getSize(JSeparator e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JSeparator e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JSeparator e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JSeparator e, Dimension d){e.setMaximumSize(d);}

    public JSeparator withSetSize(JSeparator e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JSeparator withResize(JSeparator e, Dimension d){e.resize(d);  return e;}
    public JSeparator withSetPreferredSize(JSeparator e, Dimension d){e.setPreferredSize(d);return e;}
    public JSeparator withSetMinimumSize(JSeparator e, Dimension d){e.setMinimumSize(d);return e;}
    public JSeparator withSetMaximumSize(JSeparator e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JSeparator e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JSeparator e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JSeparator e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JSeparator e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JSeparator e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JSeparator e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JSeparator withSetSize(JSeparator e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JSeparator withResize(JSeparator e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JSeparator withSetPreferredSize(JSeparator e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JSeparator withSetMinimumSize(JSeparator e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JSeparator withSetMaximumSize(JSeparator e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JSlider e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JSlider e, Dimension d){e.resize(d);}
    public Dimension getSize(JSlider e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JSlider e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JSlider e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JSlider e, Dimension d){e.setMaximumSize(d);}

    public JSlider withSetSize(JSlider e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JSlider withResize(JSlider e, Dimension d){e.resize(d);  return e;}
    public JSlider withSetPreferredSize(JSlider e, Dimension d){e.setPreferredSize(d);return e;}
    public JSlider withSetMinimumSize(JSlider e, Dimension d){e.setMinimumSize(d);return e;}
    public JSlider withSetMaximumSize(JSlider e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JSlider e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JSlider e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JSlider e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JSlider e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JSlider e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JSlider e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JSlider withSetSize(JSlider e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JSlider withResize(JSlider e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JSlider withSetPreferredSize(JSlider e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JSlider withSetMinimumSize(JSlider e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JSlider withSetMaximumSize(JSlider e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JSpinner e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JSpinner e, Dimension d){e.resize(d);}
    public Dimension getSize(JSpinner e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JSpinner e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JSpinner e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JSpinner e, Dimension d){e.setMaximumSize(d);}

    public JSpinner withSetSize(JSpinner e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JSpinner withResize(JSpinner e, Dimension d){e.resize(d);  return e;}
    public JSpinner withSetPreferredSize(JSpinner e, Dimension d){e.setPreferredSize(d);return e;}
    public JSpinner withSetMinimumSize(JSpinner e, Dimension d){e.setMinimumSize(d);return e;}
    public JSpinner withSetMaximumSize(JSpinner e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JSpinner e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JSpinner e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JSpinner e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JSpinner e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JSpinner e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JSpinner e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JSpinner withSetSize(JSpinner e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JSpinner withResize(JSpinner e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JSpinner withSetPreferredSize(JSpinner e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JSpinner withSetMinimumSize(JSpinner e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JSpinner withSetMaximumSize(JSpinner e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JSplitPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JSplitPane e, Dimension d){e.resize(d);}
    public Dimension getSize(JSplitPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JSplitPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JSplitPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JSplitPane e, Dimension d){e.setMaximumSize(d);}

    public JSplitPane withSetSize(JSplitPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JSplitPane withResize(JSplitPane e, Dimension d){e.resize(d);  return e;}
    public JSplitPane withSetPreferredSize(JSplitPane e, Dimension d){e.setPreferredSize(d);return e;}
    public JSplitPane withSetMinimumSize(JSplitPane e, Dimension d){e.setMinimumSize(d);return e;}
    public JSplitPane withSetMaximumSize(JSplitPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JSplitPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JSplitPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JSplitPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JSplitPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JSplitPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JSplitPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JSplitPane withSetSize(JSplitPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JSplitPane withResize(JSplitPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JSplitPane withSetPreferredSize(JSplitPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JSplitPane withSetMinimumSize(JSplitPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JSplitPane withSetMaximumSize(JSplitPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JTabbedPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JTabbedPane e, Dimension d){e.resize(d);}
    public Dimension getSize(JTabbedPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JTabbedPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JTabbedPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JTabbedPane e, Dimension d){e.setMaximumSize(d);}

    public JTabbedPane withSetSize(JTabbedPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JTabbedPane withResize(JTabbedPane e, Dimension d){e.resize(d);  return e;}
    public JTabbedPane withSetPreferredSize(JTabbedPane e, Dimension d){e.setPreferredSize(d);return e;}
    public JTabbedPane withSetMinimumSize(JTabbedPane e, Dimension d){e.setMinimumSize(d);return e;}
    public JTabbedPane withSetMaximumSize(JTabbedPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JTabbedPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JTabbedPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JTabbedPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JTabbedPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JTabbedPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JTabbedPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JTabbedPane withSetSize(JTabbedPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JTabbedPane withResize(JTabbedPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JTabbedPane withSetPreferredSize(JTabbedPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JTabbedPane withSetMinimumSize(JTabbedPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JTabbedPane withSetMaximumSize(JTabbedPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JTable e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JTable e, Dimension d){e.resize(d);}
    public Dimension getSize(JTable e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JTable e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JTable e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JTable e, Dimension d){e.setMaximumSize(d);}

    public JTable withSetSize(JTable e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JTable withResize(JTable e, Dimension d){e.resize(d);  return e;}
    public JTable withSetPreferredSize(JTable e, Dimension d){e.setPreferredSize(d);return e;}
    public JTable withSetMinimumSize(JTable e, Dimension d){e.setMinimumSize(d);return e;}
    public JTable withSetMaximumSize(JTable e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JTable e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JTable e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JTable e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JTable e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JTable e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JTable e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JTable withSetSize(JTable e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JTable withResize(JTable e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JTable withSetPreferredSize(JTable e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JTable withSetMinimumSize(JTable e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JTable withSetMaximumSize(JTable e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JTableHeader e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JTableHeader e, Dimension d){e.resize(d);}
    public Dimension getSize(JTableHeader e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JTableHeader e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JTableHeader e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JTableHeader e, Dimension d){e.setMaximumSize(d);}

    public JTableHeader withSetSize(JTableHeader e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JTableHeader withResize(JTableHeader e, Dimension d){e.resize(d);  return e;}
    public JTableHeader withSetPreferredSize(JTableHeader e, Dimension d){e.setPreferredSize(d);return e;}
    public JTableHeader withSetMinimumSize(JTableHeader e, Dimension d){e.setMinimumSize(d);return e;}
    public JTableHeader withSetMaximumSize(JTableHeader e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JTableHeader e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JTableHeader e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JTableHeader e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JTableHeader e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JTableHeader e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JTableHeader e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JTableHeader withSetSize(JTableHeader e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JTableHeader withResize(JTableHeader e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JTableHeader withSetPreferredSize(JTableHeader e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JTableHeader withSetMinimumSize(JTableHeader e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JTableHeader withSetMaximumSize(JTableHeader e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JTextComponent e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JTextComponent e, Dimension d){e.resize(d);}
    public Dimension getSize(JTextComponent e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JTextComponent e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JTextComponent e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JTextComponent e, Dimension d){e.setMaximumSize(d);}

    public JTextComponent withSetSize(JTextComponent e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JTextComponent withResize(JTextComponent e, Dimension d){e.resize(d);  return e;}
    public JTextComponent withSetPreferredSize(JTextComponent e, Dimension d){e.setPreferredSize(d);return e;}
    public JTextComponent withSetMinimumSize(JTextComponent e, Dimension d){e.setMinimumSize(d);return e;}
    public JTextComponent withSetMaximumSize(JTextComponent e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JTextComponent e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JTextComponent e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JTextComponent e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JTextComponent e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JTextComponent e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JTextComponent e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JTextComponent withSetSize(JTextComponent e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JTextComponent withResize(JTextComponent e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JTextComponent withSetPreferredSize(JTextComponent e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JTextComponent withSetMinimumSize(JTextComponent e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JTextComponent withSetMaximumSize(JTextComponent e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JToolBar e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JToolBar e, Dimension d){e.resize(d);}
    public Dimension getSize(JToolBar e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JToolBar e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JToolBar e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JToolBar e, Dimension d){e.setMaximumSize(d);}

    public JToolBar withSetSize(JToolBar e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JToolBar withResize(JToolBar e, Dimension d){e.resize(d);  return e;}
    public JToolBar withSetPreferredSize(JToolBar e, Dimension d){e.setPreferredSize(d);return e;}
    public JToolBar withSetMinimumSize(JToolBar e, Dimension d){e.setMinimumSize(d);return e;}
    public JToolBar withSetMaximumSize(JToolBar e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JToolBar e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JToolBar e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JToolBar e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JToolBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JToolBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JToolBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JToolBar withSetSize(JToolBar e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JToolBar withResize(JToolBar e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JToolBar withSetPreferredSize(JToolBar e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JToolBar withSetMinimumSize(JToolBar e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JToolBar withSetMaximumSize(JToolBar e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JToolTip e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JToolTip e, Dimension d){e.resize(d);}
    public Dimension getSize(JToolTip e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JToolTip e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JToolTip e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JToolTip e, Dimension d){e.setMaximumSize(d);}

    public JToolTip withSetSize(JToolTip e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JToolTip withResize(JToolTip e, Dimension d){e.resize(d);  return e;}
    public JToolTip withSetPreferredSize(JToolTip e, Dimension d){e.setPreferredSize(d);return e;}
    public JToolTip withSetMinimumSize(JToolTip e, Dimension d){e.setMinimumSize(d);return e;}
    public JToolTip withSetMaximumSize(JToolTip e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JToolTip e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JToolTip e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JToolTip e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JToolTip e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JToolTip e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JToolTip e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JToolTip withSetSize(JToolTip e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JToolTip withResize(JToolTip e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JToolTip withSetPreferredSize(JToolTip e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JToolTip withSetMinimumSize(JToolTip e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JToolTip withSetMaximumSize(JToolTip e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JTree e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JTree e, Dimension d){e.resize(d);}
    public Dimension getSize(JTree e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JTree e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JTree e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JTree e, Dimension d){e.setMaximumSize(d);}

    public JTree withSetSize(JTree e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JTree withResize(JTree e, Dimension d){e.resize(d);  return e;}
    public JTree withSetPreferredSize(JTree e, Dimension d){e.setPreferredSize(d);return e;}
    public JTree withSetMinimumSize(JTree e, Dimension d){e.setMinimumSize(d);return e;}
    public JTree withSetMaximumSize(JTree e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JTree e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JTree e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JTree e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JTree e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JTree e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JTree e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JTree withSetSize(JTree e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JTree withResize(JTree e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JTree withSetPreferredSize(JTree e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JTree withSetMinimumSize(JTree e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JTree withSetMaximumSize(JTree e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JViewport e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JViewport e, Dimension d){e.resize(d);}
    public Dimension getSize(JViewport e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JViewport e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JViewport e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JViewport e, Dimension d){e.setMaximumSize(d);}

    public JViewport withSetSize(JViewport e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JViewport withResize(JViewport e, Dimension d){e.resize(d);  return e;}
    public JViewport withSetPreferredSize(JViewport e, Dimension d){e.setPreferredSize(d);return e;}
    public JViewport withSetMinimumSize(JViewport e, Dimension d){e.setMinimumSize(d);return e;}
    public JViewport withSetMaximumSize(JViewport e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JViewport e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JViewport e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JViewport e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JViewport e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JViewport e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JViewport e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JViewport withSetSize(JViewport e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JViewport withResize(JViewport e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JViewport withSetPreferredSize(JViewport e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JViewport withSetMinimumSize(JViewport e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JViewport withSetMaximumSize(JViewport e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(AbstractColorChooserPanel e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(AbstractColorChooserPanel e, Dimension d){e.resize(d);}
    public Dimension getSize(AbstractColorChooserPanel e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(AbstractColorChooserPanel e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(AbstractColorChooserPanel e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(AbstractColorChooserPanel e, Dimension d){e.setMaximumSize(d);}

    public AbstractColorChooserPanel withSetSize(AbstractColorChooserPanel e, Dimension d){e.setSize(d); return e;}
    @Deprecated public AbstractColorChooserPanel withResize(AbstractColorChooserPanel e, Dimension d){e.resize(d);  return e;}
    public AbstractColorChooserPanel withSetPreferredSize(AbstractColorChooserPanel e, Dimension d){e.setPreferredSize(d);return e;}
    public AbstractColorChooserPanel withSetMinimumSize(AbstractColorChooserPanel e, Dimension d){e.setMinimumSize(d);return e;}
    public AbstractColorChooserPanel withSetMaximumSize(AbstractColorChooserPanel e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(AbstractColorChooserPanel e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(AbstractColorChooserPanel e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(AbstractColorChooserPanel e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(AbstractColorChooserPanel e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(AbstractColorChooserPanel e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(AbstractColorChooserPanel e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public AbstractColorChooserPanel withSetSize(AbstractColorChooserPanel e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public AbstractColorChooserPanel withResize(AbstractColorChooserPanel e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public AbstractColorChooserPanel withSetPreferredSize(AbstractColorChooserPanel e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public AbstractColorChooserPanel withSetMinimumSize(AbstractColorChooserPanel e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public AbstractColorChooserPanel withSetMaximumSize(AbstractColorChooserPanel e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JSpinner.DefaultEditor e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JSpinner.DefaultEditor e, Dimension d){e.resize(d);}
    public Dimension getSize(JSpinner.DefaultEditor e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JSpinner.DefaultEditor e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JSpinner.DefaultEditor e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JSpinner.DefaultEditor e, Dimension d){e.setMaximumSize(d);}

    public JSpinner.DefaultEditor withSetSize(JSpinner.DefaultEditor e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JSpinner.DefaultEditor withResize(JSpinner.DefaultEditor e, Dimension d){e.resize(d);  return e;}
    public JSpinner.DefaultEditor withSetPreferredSize(JSpinner.DefaultEditor e, Dimension d){e.setPreferredSize(d);return e;}
    public JSpinner.DefaultEditor withSetMinimumSize(JSpinner.DefaultEditor e, Dimension d){e.setMinimumSize(d);return e;}
    public JSpinner.DefaultEditor withSetMaximumSize(JSpinner.DefaultEditor e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JSpinner.DefaultEditor e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JSpinner.DefaultEditor e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JSpinner.DefaultEditor e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JSpinner.DefaultEditor e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JSpinner.DefaultEditor e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JSpinner.DefaultEditor e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JSpinner.DefaultEditor withSetSize(JSpinner.DefaultEditor e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JSpinner.DefaultEditor withResize(JSpinner.DefaultEditor e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JSpinner.DefaultEditor withSetPreferredSize(JSpinner.DefaultEditor e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JSpinner.DefaultEditor withSetMinimumSize(JSpinner.DefaultEditor e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JSpinner.DefaultEditor withSetMaximumSize(JSpinner.DefaultEditor e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(BasicSplitPaneDivider e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(BasicSplitPaneDivider e, Dimension d){e.resize(d);}
    public Dimension getSize(BasicSplitPaneDivider e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(BasicSplitPaneDivider e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(BasicSplitPaneDivider e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(BasicSplitPaneDivider e, Dimension d){e.setMaximumSize(d);}

    public BasicSplitPaneDivider withSetSize(BasicSplitPaneDivider e, Dimension d){e.setSize(d); return e;}
    @Deprecated public BasicSplitPaneDivider withResize(BasicSplitPaneDivider e, Dimension d){e.resize(d);  return e;}
    public BasicSplitPaneDivider withSetPreferredSize(BasicSplitPaneDivider e, Dimension d){e.setPreferredSize(d);return e;}
    public BasicSplitPaneDivider withSetMinimumSize(BasicSplitPaneDivider e, Dimension d){e.setMinimumSize(d);return e;}
    public BasicSplitPaneDivider withSetMaximumSize(BasicSplitPaneDivider e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(BasicSplitPaneDivider e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(BasicSplitPaneDivider e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(BasicSplitPaneDivider e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(BasicSplitPaneDivider e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(BasicSplitPaneDivider e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(BasicSplitPaneDivider e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public BasicSplitPaneDivider withSetSize(BasicSplitPaneDivider e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public BasicSplitPaneDivider withResize(BasicSplitPaneDivider e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public BasicSplitPaneDivider withSetPreferredSize(BasicSplitPaneDivider e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public BasicSplitPaneDivider withSetMinimumSize(BasicSplitPaneDivider e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public BasicSplitPaneDivider withSetMaximumSize(BasicSplitPaneDivider e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(CellRendererPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(CellRendererPane e, Dimension d){e.resize(d);}
    public Dimension getSize(CellRendererPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(CellRendererPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(CellRendererPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(CellRendererPane e, Dimension d){e.setMaximumSize(d);}

    public CellRendererPane withSetSize(CellRendererPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public CellRendererPane withResize(CellRendererPane e, Dimension d){e.resize(d);  return e;}
    public CellRendererPane withSetPreferredSize(CellRendererPane e, Dimension d){e.setPreferredSize(d);return e;}
    public CellRendererPane withSetMinimumSize(CellRendererPane e, Dimension d){e.setMinimumSize(d);return e;}
    public CellRendererPane withSetMaximumSize(CellRendererPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(CellRendererPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(CellRendererPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(CellRendererPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(CellRendererPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(CellRendererPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(CellRendererPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public CellRendererPane withSetSize(CellRendererPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public CellRendererPane withResize(CellRendererPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public CellRendererPane withSetPreferredSize(CellRendererPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public CellRendererPane withSetMinimumSize(CellRendererPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public CellRendererPane withSetMaximumSize(CellRendererPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.resize(d);}
    public Dimension getSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setMaximumSize(d);}

    public DefaultTreeCellEditor.EditorContainer withSetSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setSize(d); return e;}
    @Deprecated public DefaultTreeCellEditor.EditorContainer withResize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.resize(d);  return e;}
    public DefaultTreeCellEditor.EditorContainer withSetPreferredSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setPreferredSize(d);return e;}
    public DefaultTreeCellEditor.EditorContainer withSetMinimumSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setMinimumSize(d);return e;}
    public DefaultTreeCellEditor.EditorContainer withSetMaximumSize(DefaultTreeCellEditor.EditorContainer e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public DefaultTreeCellEditor.EditorContainer withSetSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public DefaultTreeCellEditor.EditorContainer withResize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public DefaultTreeCellEditor.EditorContainer withSetPreferredSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public DefaultTreeCellEditor.EditorContainer withSetMinimumSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public DefaultTreeCellEditor.EditorContainer withSetMaximumSize(DefaultTreeCellEditor.EditorContainer e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(JComponent e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(JComponent e, Dimension d){e.resize(d);}
    public Dimension getSize(JComponent e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(JComponent e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(JComponent e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(JComponent e, Dimension d){e.setMaximumSize(d);}

    public JComponent withSetSize(JComponent e, Dimension d){e.setSize(d); return e;}
    @Deprecated public JComponent withResize(JComponent e, Dimension d){e.resize(d);  return e;}
    public JComponent withSetPreferredSize(JComponent e, Dimension d){e.setPreferredSize(d);return e;}
    public JComponent withSetMinimumSize(JComponent e, Dimension d){e.setMinimumSize(d);return e;}
    public JComponent withSetMaximumSize(JComponent e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(JComponent e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(JComponent e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(JComponent e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(JComponent e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(JComponent e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(JComponent e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public JComponent withSetSize(JComponent e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public JComponent withResize(JComponent e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public JComponent withSetPreferredSize(JComponent e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public JComponent withSetMinimumSize(JComponent e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public JComponent withSetMaximumSize(JComponent e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Panel e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Panel e, Dimension d){e.resize(d);}
    public Dimension getSize(Panel e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Panel e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Panel e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Panel e, Dimension d){e.setMaximumSize(d);}

    public Panel withSetSize(Panel e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Panel withResize(Panel e, Dimension d){e.resize(d);  return e;}
    public Panel withSetPreferredSize(Panel e, Dimension d){e.setPreferredSize(d);return e;}
    public Panel withSetMinimumSize(Panel e, Dimension d){e.setMinimumSize(d);return e;}
    public Panel withSetMaximumSize(Panel e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Panel e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Panel e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Panel e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Panel e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Panel e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Panel e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Panel withSetSize(Panel e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Panel withResize(Panel e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Panel withSetPreferredSize(Panel e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Panel withSetMinimumSize(Panel e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Panel withSetMaximumSize(Panel e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(ScrollPane e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(ScrollPane e, Dimension d){e.resize(d);}
    public Dimension getSize(ScrollPane e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(ScrollPane e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(ScrollPane e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(ScrollPane e, Dimension d){e.setMaximumSize(d);}

    public ScrollPane withSetSize(ScrollPane e, Dimension d){e.setSize(d); return e;}
    @Deprecated public ScrollPane withResize(ScrollPane e, Dimension d){e.resize(d);  return e;}
    public ScrollPane withSetPreferredSize(ScrollPane e, Dimension d){e.setPreferredSize(d);return e;}
    public ScrollPane withSetMinimumSize(ScrollPane e, Dimension d){e.setMinimumSize(d);return e;}
    public ScrollPane withSetMaximumSize(ScrollPane e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(ScrollPane e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(ScrollPane e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(ScrollPane e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(ScrollPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(ScrollPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(ScrollPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public ScrollPane withSetSize(ScrollPane e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public ScrollPane withResize(ScrollPane e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public ScrollPane withSetPreferredSize(ScrollPane e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public ScrollPane withSetMinimumSize(ScrollPane e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public ScrollPane withSetMaximumSize(ScrollPane e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Window e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Window e, Dimension d){e.resize(d);}
    public Dimension getSize(Window e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Window e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Window e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Window e, Dimension d){e.setMaximumSize(d);}

    public Window withSetSize(Window e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Window withResize(Window e, Dimension d){e.resize(d);  return e;}
    public Window withSetPreferredSize(Window e, Dimension d){e.setPreferredSize(d);return e;}
    public Window withSetMinimumSize(Window e, Dimension d){e.setMinimumSize(d);return e;}
    public Window withSetMaximumSize(Window e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Window e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Window e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Window e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Window e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Window e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Window e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Window withSetSize(Window e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Window withResize(Window e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Window withSetPreferredSize(Window e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Window withSetMinimumSize(Window e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Window withSetMaximumSize(Window e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Button e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Button e, Dimension d){e.resize(d);}
    public Dimension getSize(Button e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Button e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Button e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Button e, Dimension d){e.setMaximumSize(d);}

    public Button withSetSize(Button e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Button withResize(Button e, Dimension d){e.resize(d);  return e;}
    public Button withSetPreferredSize(Button e, Dimension d){e.setPreferredSize(d);return e;}
    public Button withSetMinimumSize(Button e, Dimension d){e.setMinimumSize(d);return e;}
    public Button withSetMaximumSize(Button e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Button e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Button e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Button e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Button e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Button e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Button e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Button withSetSize(Button e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Button withResize(Button e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Button withSetPreferredSize(Button e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Button withSetMinimumSize(Button e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Button withSetMaximumSize(Button e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Canvas e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Canvas e, Dimension d){e.resize(d);}
    public Dimension getSize(Canvas e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Canvas e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Canvas e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Canvas e, Dimension d){e.setMaximumSize(d);}

    public Canvas withSetSize(Canvas e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Canvas withResize(Canvas e, Dimension d){e.resize(d);  return e;}
    public Canvas withSetPreferredSize(Canvas e, Dimension d){e.setPreferredSize(d);return e;}
    public Canvas withSetMinimumSize(Canvas e, Dimension d){e.setMinimumSize(d);return e;}
    public Canvas withSetMaximumSize(Canvas e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Canvas e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Canvas e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Canvas e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Canvas e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Canvas e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Canvas e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Canvas withSetSize(Canvas e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Canvas withResize(Canvas e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Canvas withSetPreferredSize(Canvas e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Canvas withSetMinimumSize(Canvas e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Canvas withSetMaximumSize(Canvas e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Checkbox e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Checkbox e, Dimension d){e.resize(d);}
    public Dimension getSize(Checkbox e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Checkbox e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Checkbox e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Checkbox e, Dimension d){e.setMaximumSize(d);}

    public Checkbox withSetSize(Checkbox e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Checkbox withResize(Checkbox e, Dimension d){e.resize(d);  return e;}
    public Checkbox withSetPreferredSize(Checkbox e, Dimension d){e.setPreferredSize(d);return e;}
    public Checkbox withSetMinimumSize(Checkbox e, Dimension d){e.setMinimumSize(d);return e;}
    public Checkbox withSetMaximumSize(Checkbox e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Checkbox e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Checkbox e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Checkbox e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Checkbox e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Checkbox e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Checkbox e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Checkbox withSetSize(Checkbox e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Checkbox withResize(Checkbox e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Checkbox withSetPreferredSize(Checkbox e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Checkbox withSetMinimumSize(Checkbox e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Checkbox withSetMaximumSize(Checkbox e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Choice e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Choice e, Dimension d){e.resize(d);}
    public Dimension getSize(Choice e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Choice e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Choice e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Choice e, Dimension d){e.setMaximumSize(d);}

    public Choice withSetSize(Choice e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Choice withResize(Choice e, Dimension d){e.resize(d);  return e;}
    public Choice withSetPreferredSize(Choice e, Dimension d){e.setPreferredSize(d);return e;}
    public Choice withSetMinimumSize(Choice e, Dimension d){e.setMinimumSize(d);return e;}
    public Choice withSetMaximumSize(Choice e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Choice e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Choice e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Choice e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Choice e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Choice e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Choice e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Choice withSetSize(Choice e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Choice withResize(Choice e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Choice withSetPreferredSize(Choice e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Choice withSetMinimumSize(Choice e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Choice withSetMaximumSize(Choice e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Container e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Container e, Dimension d){e.resize(d);}
    public Dimension getSize(Container e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Container e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Container e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Container e, Dimension d){e.setMaximumSize(d);}

    public Container withSetSize(Container e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Container withResize(Container e, Dimension d){e.resize(d);  return e;}
    public Container withSetPreferredSize(Container e, Dimension d){e.setPreferredSize(d);return e;}
    public Container withSetMinimumSize(Container e, Dimension d){e.setMinimumSize(d);return e;}
    public Container withSetMaximumSize(Container e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Container e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Container e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Container e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Container e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Container e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Container e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Container withSetSize(Container e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Container withResize(Container e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Container withSetPreferredSize(Container e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Container withSetMinimumSize(Container e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Container withSetMaximumSize(Container e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Label e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Label e, Dimension d){e.resize(d);}
    public Dimension getSize(Label e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Label e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Label e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Label e, Dimension d){e.setMaximumSize(d);}

    public Label withSetSize(Label e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Label withResize(Label e, Dimension d){e.resize(d);  return e;}
    public Label withSetPreferredSize(Label e, Dimension d){e.setPreferredSize(d);return e;}
    public Label withSetMinimumSize(Label e, Dimension d){e.setMinimumSize(d);return e;}
    public Label withSetMaximumSize(Label e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Label e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Label e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Label e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Label e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Label e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Label e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Label withSetSize(Label e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Label withResize(Label e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Label withSetPreferredSize(Label e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Label withSetMinimumSize(Label e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Label withSetMaximumSize(Label e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(java.awt.List e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(java.awt.List e, Dimension d){e.resize(d);}
    public Dimension getSize(java.awt.List e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(java.awt.List e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(java.awt.List e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(java.awt.List e, Dimension d){e.setMaximumSize(d);}

    public java.awt.List withSetSize(java.awt.List e, Dimension d){e.setSize(d); return e;}
    @Deprecated public java.awt.List withResize(java.awt.List e, Dimension d){e.resize(d);  return e;}
    public java.awt.List withSetPreferredSize(java.awt.List e, Dimension d){e.setPreferredSize(d);return e;}
    public java.awt.List withSetMinimumSize(java.awt.List e, Dimension d){e.setMinimumSize(d);return e;}
    public java.awt.List withSetMaximumSize(java.awt.List e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(java.awt.List e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(java.awt.List e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(java.awt.List e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(java.awt.List e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(java.awt.List e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(java.awt.List e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public java.awt.List withSetSize(java.awt.List e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public java.awt.List withResize(java.awt.List e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public java.awt.List withSetPreferredSize(java.awt.List e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public java.awt.List withSetMinimumSize(java.awt.List e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public java.awt.List withSetMaximumSize(java.awt.List e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(Scrollbar e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(Scrollbar e, Dimension d){e.resize(d);}
    public Dimension getSize(Scrollbar e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(Scrollbar e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(Scrollbar e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(Scrollbar e, Dimension d){e.setMaximumSize(d);}

    public Scrollbar withSetSize(Scrollbar e, Dimension d){e.setSize(d); return e;}
    @Deprecated public Scrollbar withResize(Scrollbar e, Dimension d){e.resize(d);  return e;}
    public Scrollbar withSetPreferredSize(Scrollbar e, Dimension d){e.setPreferredSize(d);return e;}
    public Scrollbar withSetMinimumSize(Scrollbar e, Dimension d){e.setMinimumSize(d);return e;}
    public Scrollbar withSetMaximumSize(Scrollbar e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(Scrollbar e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(Scrollbar e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(Scrollbar e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(Scrollbar e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(Scrollbar e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(Scrollbar e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public Scrollbar withSetSize(Scrollbar e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public Scrollbar withResize(Scrollbar e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public Scrollbar withSetPreferredSize(Scrollbar e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public Scrollbar withSetMinimumSize(Scrollbar e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public Scrollbar withSetMaximumSize(Scrollbar e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public void setSize(TextComponent e, Dimension d){e.setSize(d);}
    @Deprecated public void resize(TextComponent e, Dimension d){e.resize(d);}
    public Dimension getSize(TextComponent e, Dimension d){return e.getSize(d);}
    public void setPreferredSize(TextComponent e, Dimension d){e.setPreferredSize(d);}
    public void setMinimumSize(TextComponent e, Dimension d){e.setMinimumSize(d);}
    public void setMaximumSize(TextComponent e, Dimension d){e.setMaximumSize(d);}

    public TextComponent withSetSize(TextComponent e, Dimension d){e.setSize(d); return e;}
    @Deprecated public TextComponent withResize(TextComponent e, Dimension d){e.resize(d);  return e;}
    public TextComponent withSetPreferredSize(TextComponent e, Dimension d){e.setPreferredSize(d);return e;}
    public TextComponent withSetMinimumSize(TextComponent e, Dimension d){e.setMinimumSize(d);return e;}
    public TextComponent withSetMaximumSize(TextComponent e, Dimension d){e.setMaximumSize(d);return e;}

    public void setSize(TextComponent e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public void resize(TextComponent e, int x, int y){e.resize(new Dimension(x, y));}
    public Dimension getSize(TextComponent e, int x, int y){return e.getSize(new Dimension(x, y));}
    public void setPreferredSize(TextComponent e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public void setMinimumSize(TextComponent e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public void setMaximumSize(TextComponent e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public TextComponent withSetSize(TextComponent e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public TextComponent withResize(TextComponent e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public TextComponent withSetPreferredSize(TextComponent e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public TextComponent withSetMinimumSize(TextComponent e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public TextComponent withSetMaximumSize(TextComponent e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

//    public void setSize(JFrame e, Dimension d){e.setSize(d);}
//    public void resize(JFrame e, Dimension d){e.resize(d);}
//    public Dimension getSize(JFrame e, Dimension d){return e.getSize(d);}
//    public void setPreferredSize(JFrame e, Dimension d){e.setPreferredSize(d);}
//    public void setMinimumSize(JFrame e, Dimension d){e.setMinimumSize(d);}
//    public void setMaximumSize(JFrame e, Dimension d){e.setMaximumSize(d);}
//
//    public JFrame withSetSize(JFrame e, Dimension d){e.setSize(d); return e;}
//    public JFrame withResize(JFrame e, Dimension d){e.resize(d);  return e;}
//    public JFrame withSetPreferredSize(JFrame e, Dimension d){e.setPreferredSize(d);return e;}
//    public JFrame withSetMinimumSize(JFrame e, Dimension d){e.setMinimumSize(d);return e;}
//    public JFrame withSetMaximumSize(JFrame e, Dimension d){e.setMaximumSize(d);return e;}
//
//    public void setSize(JFrame e, int x, int y){e.setSize(new Dimension(x, y));}
//    public void resize(JFrame e, int x, int y){e.resize(new Dimension(x, y));}
//    public Dimension getSize(JFrame e, int x, int y){return e.getSize(new Dimension(x, y));}
//    public void setPreferredSize(JFrame e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
//    public void setMinimumSize(JFrame e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
//    public void setMaximumSize(JFrame e, int x, int y){e.setMaximumSize(new Dimension(x, y));}
//
//    public JFrame withSetSize(JFrame e, int x, int y){e.setSize(new Dimension(x, y));return e;}
//    public JFrame withResize(JFrame e, int x, int y){e.resize(new Dimension(x, y));return e;}
//    public JFrame withSetPreferredSize(JFrame e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
//    public JFrame withSetMinimumSize(JFrame e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
//    public JFrame withSetMaximumSize(JFrame e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    public <E extends JFrame> void setSize(E e, Dimension d){e.setSize(d);}
    @Deprecated public <E extends JFrame> void resize(E e, Dimension d){e.resize(d);}
    public <E extends JFrame> Dimension getSize(E e, Dimension d){return e.getSize(d);}
    public <E extends JFrame> void setPreferredSize(E e, Dimension d){e.setPreferredSize(d);}
    public <E extends JFrame> void setMinimumSize(E e, Dimension d){e.setMinimumSize(d);}
    public <E extends JFrame> void setMaximumSize(E e, Dimension d){e.setMaximumSize(d);}

    public <E extends JFrame> E withSetSize(E e, Dimension d){e.setSize(d); return e;}
    @Deprecated public <E extends JFrame> E withResize(E e, Dimension d){e.resize(d);  return e;}
    public <E extends JFrame> E withSetPreferredSize(E e, Dimension d){e.setPreferredSize(d);return e;}
    public <E extends JFrame> E withSetMinimumSize(E e, Dimension d){e.setMinimumSize(d);return e;}
    public <E extends JFrame> E withSetMaximumSize(E e, Dimension d){e.setMaximumSize(d);return e;}

    public <E extends JFrame> void setSize(E e, int x, int y){e.setSize(new Dimension(x, y));}
    @Deprecated public <E extends JFrame> void resize(E e, int x, int y){e.resize(new Dimension(x, y));}
    public <E extends JFrame> Dimension getSize(E e, int x, int y){return e.getSize(new Dimension(x, y));}
    public <E extends JFrame> void setPreferredSize(E e, int x, int y){e.setPreferredSize(new Dimension(x, y));}
    public <E extends JFrame> void setMinimumSize(E e, int x, int y){e.setMinimumSize(new Dimension(x, y));}
    public <E extends JFrame> void setMaximumSize(E e, int x, int y){e.setMaximumSize(new Dimension(x, y));}

    public <E extends JFrame> E withSetSize(E e, int x, int y){e.setSize(new Dimension(x, y));return e;}
    @Deprecated public <E extends JFrame> E withResize(E e, int x, int y){e.resize(new Dimension(x, y));return e;}
    public <E extends JFrame> E withSetPreferredSize(E e, int x, int y){e.setPreferredSize(new Dimension(x, y));return e;}
    public <E extends JFrame> E withSetMinimumSize(E e, int x, int y){e.setMinimumSize(new Dimension(x, y));return e;}
    public <E extends JFrame> E withSetMaximumSize(E e, int x, int y){e.setMaximumSize(new Dimension(x, y));return e;}

    /*
     * 
     * 
     * 
     * GSON UTILS 
     * 
     * 
     * 
     * 
     */
//
//    public static <T> GsonBuilder registerSerializer(GsonBuilder g, Class<? extends T> type, JsonSerializer<? extends T> s) {
//        return g.registerTypeAdapter(type, s);
//    }
//    public static <T> GsonBuilder registerDeserializer(GsonBuilder g, Class<? extends T> type, JsonDeserializer<? extends T> s) {
//        return g.registerTypeAdapter(type, s);
//    }
//    public static <T> GsonBuilder registerInstanceCreator(GsonBuilder g, Class<? extends T> type, InstanceCreator<? extends T> s) {
//        return g.registerTypeAdapter(type, s);
//    }
}
