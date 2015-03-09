
package com.dream.rest.cluster.loadbalance;

import java.util.Arrays;
import java.util.List;

import com.dream.rest.cluster.LoadBalance;


public abstract class AbstractLoadBalance<T> implements LoadBalance<T> {
	
	
	public T select(T[] invokers){
		List<T> list = Arrays.asList(invokers);
		return select(list);
	}

    public T select(List<T> invokers) {
        if (invokers == null || invokers.size() == 0)
            return null;
        if (invokers.size() == 1)
            return invokers.get(0);
        return doSelect(invokers);
    }

    protected abstract T doSelect(List<T> invokers);

    protected int getWeight(Object obj) {
    	int weight = 0;
        if (weight > 0) {
        	long timestamp = System.currentTimeMillis();
	    	if (timestamp > 0L) {
	    		int uptime = (int) (System.currentTimeMillis() - timestamp);
	    		int warmup = 0;
	    		if (uptime > 0 && uptime < warmup) {
	    			weight = calculateWarmupWeight(uptime, warmup, weight);
	    		}
	    	}
        }
    	return weight;
    }
    
    static int calculateWarmupWeight(int uptime, int warmup, int weight) {
    	int ww = (int) ( (float) uptime / ( (float) warmup / (float) weight ) );
    	return ww < 1 ? 1 : (ww > weight ? weight : ww);
    }

}