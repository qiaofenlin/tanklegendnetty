package handler.service;

import dao.UserPlayInfo;


import java.util.Map;
import java.util.concurrent.*;


/**
 * @Created by  qiao
 * @date 18-3-10 下午7:28
 */

public class TradeUserInfoService {
    private UserPlayInfo userPlayInfo;

    private final ConcurrentMap<Integer, Future<UserPlayInfo>> cache = (ConcurrentMap)new ConcurrentHashMap<>();

    public UserPlayInfo get(final Integer key) {
        Future<UserPlayInfo> future = cache.get(key);
        if (future == null) {
            Callable<UserPlayInfo> callable = new Callable<UserPlayInfo>() {
                @Override
                public UserPlayInfo call() throws Exception {
                    return new UserPlayInfo(key);
                }
            };
            FutureTask<UserPlayInfo> task = new FutureTask<>(callable);

            future = cache.putIfAbsent(key, task);
            if (future == null) {
                future = task;
                task.run();
            }
        }

        try {
            return future.get();
        } catch (Exception e) {
            cache.remove(key);
            throw new RuntimeException(e);
        }
    }

//    public long increase(String word) {
//        AtomicLong number = wordCounts.get(word);
//        if (number == null) {
//            AtomicLong newNumber = new AtomicLong(0);
//            number = wordCounts.putIfAbsent(word, newNumber);
//            if (number == null) {
//                number = newNumber;
//            }
//        }
//        return number.incrementAndGet();
//    }
}
