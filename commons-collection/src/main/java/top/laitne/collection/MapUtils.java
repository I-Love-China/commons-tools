package top.laitne.collection;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: zhangjl
 * @Date: 21-6-8
 * @Description:
 */
public final class MapUtils {
    public static boolean isEmpty(Map map) {
        return null == map || map.size() == 0;
    }

    public static <K extends Comparable<K>, V> LinkedHashMap<K, V> toLinked(Map<K, V> map) {
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>();
        if (isEmpty(map)) {
            return linkedHashMap;
        }

        List<Map.Entry<K, V>> entries = map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toList());

        for (Map.Entry<K, V> entry : entries) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }

        return linkedHashMap;
    }

    public static <K, V> LinkedHashMap<K, V> toLinked(Map<K, V> map, Comparator<K> comparator) {
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>();
        if (isEmpty(map)) {
            return linkedHashMap;
        }

        List<Map.Entry<K, V>> entries = map.entrySet().stream()
                .sorted((a, b) -> comparator.compare(a.getKey(), b.getKey()))
                .collect(Collectors.toList());

        for (Map.Entry<K, V> entry : entries) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }

        return linkedHashMap;
    }

    private MapUtils() {
    }
}
