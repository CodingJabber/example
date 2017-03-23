/**
 * Created by 蟹老板 on 2017/3/13.
 * <p>
 * volatile修辞的作用是让被修辞者在被多线程读写时保持可见性
 */
public class APP {
    private static boolean shutdown = false;
    //private static volatile boolean shutdown = false;

    public static void main(String args[]) throws Exception {
        new Thread() {
            public void run() {
                System.out.println("start\t" + System.currentTimeMillis());
                while (!shutdown) { //如果变量shutdown没有用volatile修饰，可能永远无法跳出此循环
                }
                System.out.println("shutdowned\t" + System.currentTimeMillis());
            }
        }.start();
        new Thread() {
            public void run() {
                shutdown = true;
                System.out.println("shutdown...\t" + System.currentTimeMillis());
            }
        }.start();
    }
}
