package org.isegodin.gameOfLife.core;

import java.lang.reflect.Array;

/**
 * @author isegodin
 */
public class Array2DUtil {

    public static int getIndex(int x, int y, int width) {
        if (x > width - 1) {
            throw new IllegalArgumentException("X should be less than width of a two dimensional Array");
        }
        return width * y + x;
    }

    public static <T> T set(T value, int x, int y, T[] array, int width) {
        int idx = getIndex(x, y, width);
//        if (idx > array.length - 1) {
//            return null;
//        }
        T oldValue = array[idx];
        array[idx] = value;
        return oldValue;
    }

    public static <T> T get(int x, int y, T[] array, int width) {
        return get(x, y, (Object)array, width);
    }

    public static <T> T get(int x, int y, Object array, int width) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Specified object is not an Array, but " + array.getClass());
        }
        int idx = getIndex(x, y, width);
//        if (idx > Array.getLength(array) - 1) {
//            throw new IndexOutOfBoundsException();
//        }
        return (T) Array.get(array, idx);
    }
}
