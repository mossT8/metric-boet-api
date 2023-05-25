package com.metric.boet.api.util;

import java.util.Collection;
import java.util.Map;

public class NullUtil {

    private NullUtil() {
        // private constructor to prevent instantiation
    }

    /**
     * Checks whether an object is null.
     *
     * @param obj the object to check
     * @return true if the object is null, false otherwise
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Checks whether a string is null or empty.
     *
     * @param str the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Checks whether a collection is null or empty.
     *
     * @param col the collection to check
     * @return true if the collection is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    /**
     * Checks whether an array is null or empty.
     *
     * @param arr the array to check
     * @return true if the array is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * Checks whether a map is null or empty.
     *
     * @param map the map to check
     * @return true if the map is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Checks whether an integer is null or zero.
     *
     * @param num the integer to check
     * @return true if the integer is null or zero, false otherwise
     */
    public static boolean isNullOrZero(Integer num) {
        return num == null || num == 0;
    }


    /**
     * Checks whether an object is not null.
     *
     * @param obj the object to check
     * @return true if the object is not null, false otherwise
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * Checks whether a string is not null or empty.
     *
     * @param str the string to check
     * @return true if the string is not null or empty, false otherwise
     */
    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * Checks whether a collection is not null or empty.
     *
     * @param col the collection to check
     * @return true if the collection is not null or empty, false otherwise
     */
    public static boolean isNotNullOrEmpty(Collection<?> col) {
        return !isNullOrEmpty(col);
    }

    /**
     * Checks whether an array is not null or empty.
     *
     * @param arr the array to check
     * @return true if the array is not null or empty, false otherwise
     */
    public static boolean isNotNullOrEmpty(Object[] arr) {
        return !isNullOrEmpty(arr);
    }

    /**
     * Checks whether a map is not null or empty.
     *
     * @param map the map to check
     * @return true if the map is not null or empty, false otherwise
     */
    public static boolean isNotNullOrEmpty(Map<?, ?> map) {
        return !isNullOrEmpty(map);
    }

    /**
     * Checks whether an integer is not null or zero.
     *
     * @param num the integer to check
     * @return true if the integer is not null or zero, false otherwise
     */
    public static boolean isNotNullOrZero(Integer num) {
        return !isNullOrZero(num);
    }

}
