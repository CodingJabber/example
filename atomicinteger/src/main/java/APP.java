import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 蟹老板 on 2017/3/13.
 */
public class APP {
    private static int intCounter = 0;
    private static volatile int volatileCounter = 0;
    private static AtomicInteger aiCounter = new AtomicInteger(0);

    public static void main(String args[]) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    intCounter++;
                    volatileCounter++;
                    aiCounter.incrementAndGet();
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        System.out.printf("intCounter:%s%nvolatileCounter:%s%naiCounter:%s%n", intCounter, volatileCounter, aiCounter.get());
    }
}
