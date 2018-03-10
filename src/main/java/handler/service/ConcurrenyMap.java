package handler.service;

import java.util.Map;

/**
 * @Created by  qiao
 * @date 18-3-10 下午7:35
 */
/*oncurrentMap接口定义了几个基于 CAS（Compare and Set）操作，很简单，但非常有用*/
interface ConcurrentMap<K, V> extends Map<K, V> {
    V putIfAbsent(K key, V value);
    boolean remove(Object key, Object value);
    boolean replace(K key, V oldValue, V newValue);
    V replace(K key, V value);
}

