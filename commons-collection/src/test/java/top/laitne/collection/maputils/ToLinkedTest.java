package top.laitne.collection.maputils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import top.laitne.collection.MapUtils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: zhangjl
 * @Date: 21-6-8
 * @Description:
 */
public class ToLinkedTest {
    private static final SecureRandom RANDOM = new SecureRandom();

    private int dataSize;
    private Map<Integer, Object> byComparable;
    private Map<String, Object> usingComparator;

    @Before
    public void init() {
        this.byComparable = new HashMap<>();
        this.usingComparator = new HashMap<>();
        this.dataSize = 100;

        for (int i = 0; i < dataSize; i++) {
            byComparable.put(RANDOM.nextInt(), null);
            usingComparator.put(String.valueOf(RANDOM.nextInt()), null);
        }
    }

    @Test
    public void testToLinkedByComparable() {
        LinkedHashMap<Integer, Object> linkedHashMap = MapUtils.toLinked(byComparable);
        Integer[] keys = linkedHashMap.keySet().toArray(new Integer[]{});

        Assert.assertEquals(dataSize, keys.length);

        Integer prev = Integer.MIN_VALUE;
        for (Integer key : keys) {
            Assert.assertTrue(prev < key);

            prev = key;
        }
    }

    @Test
    public void testToLinkedUsingComparator() {
        LinkedHashMap<String, Object> linkedHashMap = MapUtils.toLinked(usingComparator, String.CASE_INSENSITIVE_ORDER);
        String[] keys = linkedHashMap.keySet().toArray(new String[]{});

        Assert.assertEquals(dataSize, keys.length);

        String prev = "\0";
        for (String key : keys) {
            Assert.assertTrue(String.CASE_INSENSITIVE_ORDER.compare(prev, key) <= 0);

            prev = key;
        }
    }
}
