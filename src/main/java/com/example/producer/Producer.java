package com.example.producer;

import java.io.Closeable;

public interface Producer<K,V> extends Closeable{

	public boolean send(String topic, K key, V value);
}
