/**
 * Created by 蟹老板 on 2017/3/13.
 */
public class APP {
    private static volatile boolean shutdown = false;

    public static void main(String args[]) throws Exception {
        new Thread() {
            public void run() {
                while (!shutdown) {
                    System.out.println("run.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        Thread.sleep(1000);
        new Thread() {
            public void run() {
                shutdown = true;
            }
        }.start();
    }
}
