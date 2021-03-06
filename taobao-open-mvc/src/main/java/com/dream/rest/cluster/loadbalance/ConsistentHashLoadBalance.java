
package com.dream.rest.cluster.loadbalance;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * ConsistentHashLoadBalance
 * 
 * @author william.liangf
 */
public class ConsistentHashLoadBalance<String> extends AbstractLoadBalance {

	@Override
	protected Object doSelect(List invokers) {
		// TODO Auto-generated method stub
		return null;
	}

//    private final ConcurrentMap<String, ConsistentHashSelector<?>> selectors = new ConcurrentHashMap<String, ConsistentHashSelector<?>>();

//    protected String doSelect(List<String> invokers) {
////        String key = invokers.get(0).getUrl().getServiceKey() + "." + invocation.getMethodName();
////        int identityHashCode = System.identityHashCode(invokers);
////        ConsistentHashSelector<T> selector = (ConsistentHashSelector<T>) selectors.get(key);
////        if (selector == null || selector.getIdentityHashCode() != identityHashCode) {
////            selectors.put(key, new ConsistentHashSelector<T>(invokers, invocation.getMethodName(), identityHashCode));
////            selector = (ConsistentHashSelector<T>) selectors.get(key);
////        }
////        return selector.select(invocation);
//    	return null;
//    }

//    private static final class ConsistentHashSelector<T> {
//
//        private final TreeMap<Long, Invoker<T>> virtualInvokers;
//
//        private final int                       replicaNumber;
//        
//        private final int                       identityHashCode;
//        
//        private final int[]                     argumentIndex;
//
//        public ConsistentHashSelector(List<Invoker<T>> invokers, String methodName, int identityHashCode) {
//            this.virtualInvokers = new TreeMap<Long, Invoker<T>>();
//            this.identityHashCode = System.identityHashCode(invokers);
//            URL url = invokers.get(0).getUrl();
//            this.replicaNumber = url.getMethodParameter(methodName, "hash.nodes", 160);
//            String[] index = Constants.COMMA_SPLIT_PATTERN.split(url.getMethodParameter(methodName, "hash.arguments", "0"));
//            argumentIndex = new int[index.length];
//            for (int i = 0; i < index.length; i ++) {
//                argumentIndex[i] = Integer.parseInt(index[i]);
//            }
//            for (Invoker<T> invoker : invokers) {
//                for (int i = 0; i < replicaNumber / 4; i++) {
//                    byte[] digest = md5(invoker.getUrl().toFullString() + i);
//                    for (int h = 0; h < 4; h++) {
//                        long m = hash(digest, h);
//                        virtualInvokers.put(m, invoker);
//                    }
//                }
//            }
//        }
//
//        public int getIdentityHashCode() {
//            return identityHashCode;
//        }
//
//        public Invoker<T> select(Invocation invocation) {
//            String key = toKey(invocation.getArguments());
//            byte[] digest = md5(key);
//            Invoker<T> invoker = sekectForKey(hash(digest, 0));
//            return invoker;
//        }
//
//        private String toKey(Object[] args) {
//            StringBuilder buf = new StringBuilder();
//            for (int i : argumentIndex) {
//                if (i >= 0 && i < args.length) {
//                    buf.append(args[i]);
//                }
//            }
//            return buf.toString();
//        }
//
//        private Invoker<T> sekectForKey(long hash) {
//            Invoker<T> invoker;
//            Long key = hash;
//            if (!virtualInvokers.containsKey(key)) {
//                SortedMap<Long, Invoker<T>> tailMap = virtualInvokers.tailMap(key);
//                if (tailMap.isEmpty()) {
//                    key = virtualInvokers.firstKey();
//                } else {
//                    key = tailMap.firstKey();
//                }
//            }
//            invoker = virtualInvokers.get(key);
//            return invoker;
//        }
//
//        private long hash(byte[] digest, int number) {
//            return (((long) (digest[3 + number * 4] & 0xFF) << 24)
//                    | ((long) (digest[2 + number * 4] & 0xFF) << 16)
//                    | ((long) (digest[1 + number * 4] & 0xFF) << 8) 
//                    | (digest[0 + number * 4] & 0xFF)) 
//                    & 0xFFFFFFFFL;
//        }
//
//        private byte[] md5(String value) {
//            MessageDigest md5;
//            try {
//                md5 = MessageDigest.getInstance("MD5");
//            } catch (NoSuchAlgorithmException e) {
//                throw new IllegalStateException(e.getMessage(), e);
//            }
//            md5.reset();
//            byte[] bytes = null;
//            try {
//                bytes = value.getBytes("UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                throw new IllegalStateException(e.getMessage(), e);
//            }
//            md5.update(bytes);
//            return md5.digest();
//        }

//    }

}
