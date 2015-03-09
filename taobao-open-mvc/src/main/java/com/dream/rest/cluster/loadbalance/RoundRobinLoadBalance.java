
package com.dream.rest.cluster.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Round robin load balance.
 *
 */
public class RoundRobinLoadBalance<T> extends AbstractLoadBalance<T> {
    public static final String NAME = "roundrobin"; 
    
    private final ConcurrentMap<String, AtomicInteger> sequences = new ConcurrentHashMap<String, AtomicInteger>();
    private final ConcurrentMap<String, AtomicInteger> weightSequences = new ConcurrentHashMap<String, AtomicInteger>();
    
    private String key;
    
    public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
    protected T doSelect(List<T> invokers) {
        int length = invokers.size(); // 总个数
        int maxWeight = 0; // 最大权重
        int minWeight = Integer.MAX_VALUE; // 最小权重
        for (int i = 0; i < length; i++) {
            int weight = getWeight(invokers.get(i));
            maxWeight = Math.max(maxWeight, weight); // 累计最大权重
            minWeight = Math.min(minWeight, weight); // 累计最小权重
        }
        if (maxWeight > 0 && minWeight < maxWeight) { // 权重不一样
        	AtomicInteger weightSequence = weightSequences.get(key);
            if (weightSequence == null) {
                weightSequences.putIfAbsent(key, new AtomicInteger());
                weightSequence = weightSequences.get(key);
            }
            int currentWeight = weightSequence.getAndIncrement() % maxWeight;
            List<T> weightInvokers = new ArrayList<T>();
            for (T invoker : invokers) { // 筛选权重大于当前权重基数的Invoker
                if (getWeight(invoker) > currentWeight) {
                    weightInvokers.add(invoker);
                }
            }
            int weightLength = weightInvokers.size();
            if (weightLength == 1) {
                return weightInvokers.get(0);
            } else if (weightLength > 1) {
                invokers = weightInvokers;
                length = invokers.size();
            }
        }
        AtomicInteger sequence = sequences.get(key);
        if (sequence == null) {
            sequences.putIfAbsent(key, new AtomicInteger());
            sequence = sequences.get(key);
        }
        // 取模轮循
        return invokers.get(sequence.getAndIncrement() % length);
    }

}