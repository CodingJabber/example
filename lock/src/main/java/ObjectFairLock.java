import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 蟹老板 on 2017/3/24.
 */
public class ObjectFairLock<T> {
    private Map<T, List<Object>> waitingThreadMap = new HashMap();
    private Map<T, Boolean> lockedMap = new HashMap();

    public void lock(T lockObject) throws InterruptedException {
        Object queueObject = new Object();
        List<Object> waitingThreads;
        //java.util.concurrent.CountDownLatch
        synchronized (lockObject) {
            if (!waitingThreadMap.containsKey(lockObject)) {
                waitingThreadMap.put(lockObject, new ArrayList());
            }
            waitingThreads = waitingThreadMap.get(lockObject);
            waitingThreads.add(queueObject);
        }
        while (true) {
            synchronized (lockObject) {
                if (waitingThreads.get(0) != queueObject) {
                    waitingThreads.remove(queueObject);
                    return;
                }
            }
            try {
                queueObject.wait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }



}
