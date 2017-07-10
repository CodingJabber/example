import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 蟹老板 on 2017/3/23.
 */
public class APP {
    public static void main(String args[]) throws Exception {
        Set<Integer> cowHashSet = new CopyOnWriteHashSet();
        Set<Integer> hashSet = new HashSet<>();
        execute("CopyOnWriteHashSet", cowHashSet);
        execute("HashSet", hashSet);
    }

    private static void execute(String name, Set s) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        AtomicInteger counter = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 10000; j++)
            threadPool.execute(() -> {
                for (int i = 0; i < 1000; i++) {
                    if (s.contains(i)) {
                        //System.out.print(i + "\t");
                        ReentrantLock lock = new ReentrantLock();
                        s.remove(i);
                    }
                    if (i % 1000 == 0) {
                        s.add(counter.getAndIncrement());
                    }
                }
            });
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.printf("%n%s:%sms%n", name, end - start);
    }
}
