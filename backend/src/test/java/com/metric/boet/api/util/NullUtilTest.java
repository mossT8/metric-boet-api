package com.metric.boet.api.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class NullUtilTest {

    /**
     * Test case for the {@link NullUtil#isNull(Object)} method with a null object.
     */
    @Test
    public void testIsNullWithNullObject() {
        Object obj = null;
        boolean result = NullUtil.isNull(obj);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNull(Object)} method with a non-null object.
     */
    @Test
    public void testIsNullWithNonNullObject() {
        Object obj = new Object();
        boolean result = NullUtil.isNull(obj);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(String)} method with a null string.
     */
    @Test
    public void testIsNullOrEmptyWithNullString() {
        String str = null;
        boolean result = NullUtil.isNullOrEmpty(str);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(String)} method with an empty string.
     */
    @Test
    public void testIsNullOrEmptyWithEmptyString() {
        String str = "";
        boolean result = NullUtil.isNullOrEmpty(str);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(String)} method with a non-empty string.
     */
    @Test
    public void testIsNullOrEmptyWithNonEmptyString() {
        String str = "Hello";
        boolean result = NullUtil.isNullOrEmpty(str);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Collection)} method with a null collection.
     */
    @Test
    public void testIsNullOrEmptyWithNullCollection() {
        Collection<?> col = null;
        boolean result = NullUtil.isNullOrEmpty(col);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Collection)} method with an empty collection.
     */
    @Test
    public void testIsNullOrEmptyWithEmptyCollection() {
        Collection<?> col = new ArrayList<>();
        boolean result = NullUtil.isNullOrEmpty(col);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Collection)} method with a non-empty collection.
     */
    @Test
    public void testIsNullOrEmptyWithNonEmptyCollection() {
        Collection<?> col = Arrays.asList(1, 2, 3);
        boolean result = NullUtil.isNullOrEmpty(col);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Object[])} method with a null array.
     */
    @Test
    public void testIsNullOrEmptyWithNullArray() {
        Object[] arr = null;
        boolean result = NullUtil.isNullOrEmpty(arr);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Object[])} method with an empty array.
     */
    @Test
    public void testIsNullOrEmptyWithEmptyArray() {
        Object[] arr = new Object[0];
        boolean result = NullUtil.isNullOrEmpty(arr);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Object[])} method with a non-empty array.
     */
    @Test
    public void testIsNullOrEmptyWithNonEmptyArray() {
        Object[] arr = new Object[]{1, 2, 3};
        boolean result = NullUtil.isNullOrEmpty(arr);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Map)} method with a null map.
     */
    @Test
    public void testIsNullOrEmptyWithNullMap() {
        Map<?, ?> map = null;
        boolean result = NullUtil.isNullOrEmpty(map);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Map)} method with an empty map.
     */
    @Test
    public void testIsNullOrEmptyWithEmptyMap() {
        Map<?, ?> map = new HashMap<>();
        boolean result = NullUtil.isNullOrEmpty(map);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrEmpty(Map)} method with a non-empty map.
     */
    @Test
    public void testIsNullOrEmptyWithNonEmptyMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        boolean result = NullUtil.isNullOrEmpty(map);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrZero(Integer)} method with a null integer.
     */
    @Test
    public void testIsNullOrZeroWithNullInteger() {
        Integer num = null;
        boolean result = NullUtil.isNullOrZero(num);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrZero(Integer)} method with zero as the integer.
     */
    @Test
    public void testIsNullOrZeroWithZeroInteger() {
        Integer num = 0;
        boolean result = NullUtil.isNullOrZero(num);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNullOrZero(Integer)} method with a non-zero integer.
     */
    @Test
    public void testIsNullOrZeroWithNonZeroInteger() {
        Integer num = 5;
        boolean result = NullUtil.isNullOrZero(num);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNull(Object)} method with a null object.
     */
    @Test
    public void testIsNotNullWithNullObject() {
        Object obj = null;
        boolean result = NullUtil.isNotNull(obj);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNull(Object)} method with a non-null object.
     */
    @Test
    public void testIsNotNullWithNonNullObject() {
        Object obj = new Object();
        boolean result = NullUtil.isNotNull(obj);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(String)} method with a null string.
     */
    @Test
    public void testIsNotNullOrEmptyWithNullString() {
        String str = null;
        boolean result = NullUtil.isNotNullOrEmpty(str);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(String)} method with an empty string.
     */
    @Test
    public void testIsNotNullOrEmptyWithEmptyString() {
        String str = "";
        boolean result = NullUtil.isNotNullOrEmpty(str);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(String)} method with a non-empty string.
     */
    @Test
    public void testIsNotNullOrEmptyWithNonEmptyString() {
        String str = "Hello";
        boolean result = NullUtil.isNotNullOrEmpty(str);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Collection)} method with a null collection.
     */
    @Test
    public void testIsNotNullOrEmptyWithNullCollection() {
        Collection<?> col = null;
        boolean result = NullUtil.isNotNullOrEmpty(col);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Collection)} method with an empty collection.
     */
    @Test
    public void testIsNotNullOrEmptyWithEmptyCollection() {
        Collection<?> col = new ArrayList<>();
        boolean result = NullUtil.isNotNullOrEmpty(col);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Collection)} method with a non-empty collection.
     */
    @Test
    public void testIsNotNullOrEmptyWithNonEmptyCollection() {
        Collection<?> col = Arrays.asList(1, 2, 3);
        boolean result = NullUtil.isNotNullOrEmpty(col);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Object[])} method with a null array.
     */
    @Test
    public void testIsNotNullOrEmptyWithNullArray() {
        Object[] arr = null;
        boolean result = NullUtil.isNotNullOrEmpty(arr);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Object[])} method with an empty array.
     */
    @Test
    public void testIsNotNullOrEmptyWithEmptyArray() {
        Object[] arr = new Object[0];
        boolean result = NullUtil.isNotNullOrEmpty(arr);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Object[])} method with a non-empty array.
     */
    @Test
    public void testIsNotNullOrEmptyWithNonEmptyArray() {
        Object[] arr = new Object[]{1, 2, 3};
        boolean result = NullUtil.isNotNullOrEmpty(arr);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Map)} method with a null map.
     */
    @Test
    public void testIsNotNullOrEmptyWithNullMap() {
        Map<?, ?> map = null;
        boolean result = NullUtil.isNotNullOrEmpty(map);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Map)} method with an empty map.
     */
    @Test
    public void testIsNotNullOrEmptyWithEmptyMap() {
        Map<?, ?> map = new HashMap<>();
        boolean result = NullUtil.isNotNullOrEmpty(map);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrEmpty(Map)} method with a non-empty map.
     */
    @Test
    public void testIsNotNullOrEmptyWithNonEmptyMap() {
        Map<Object, Object> map = new HashMap<>();
        map.put("key", "value");
        boolean result = NullUtil.isNotNullOrEmpty(map);
        assert (result == true);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrZero(Integer)} method with a null integer.
     */
    @Test
    public void testIsNotNullOrZeroWithNullInteger() {
        Integer num = null;
        boolean result = NullUtil.isNotNullOrZero(num);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrZero(Integer)} method with zero as the integer.
     */
    @Test
    public void testIsNotNullOrZeroWithZeroInteger() {
        Integer num = 0;
        boolean result = NullUtil.isNotNullOrZero(num);
        assert (result == false);
    }

    /**
     * Test case for the {@link NullUtil#isNotNullOrZero(Integer)} method with a non-zero integer.
     */
    @Test
    public void testIsNotNullOrZeroWithNonZeroInteger() {
        Integer num = 5;
        boolean result = NullUtil.isNotNullOrZero(num);
        assert (result == true);
    }
}

