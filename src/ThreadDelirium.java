/**
 * Created by jbon on 5/12/14.
 */
public class ThreadDelirium {

    public static void main(String[] args) throws InterruptedException {
        Thread01 t1 = new Thread01();
        Thread02 t2 = new Thread02(t1);
        t1.setThread02(t2);

        t1.start();
        t2.start();
//        TimeUnit.SECONDS.sleep(10);
//        t1.start();

        t1.join();
        t2.join();
    }

    private static class Thread01 extends Thread {
        public int foo = 0;
        private Thread02 thread02;

        public void setThread02(Thread02 thread02) {
            this.thread02 = thread02;
        }

        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    foo += i;
                }
                synchronized (this) {
                    this.notify();
                }
                synchronized (thread02) {
                    thread02.wait();
                }
                System.out.println("Foo: " + thread02.foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Thread02 extends Thread {
        private final Thread01 thread01;
        public int foo = 0;

        public Thread02(Thread01 thread01) {
            this.thread01 = thread01;
        }

        public void run() {
            try {
                synchronized (thread01) {
                    thread01.wait();
                }
                foo = thread01.foo;
                for (int i = 0; i < 10; i++) {
                    foo += i;
                }
                synchronized (this) {
                    this.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
