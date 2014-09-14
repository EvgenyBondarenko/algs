import java.util.concurrent.CountDownLatch;

/**
 * Created by jbon on 5/12/14.
 */
public class ThreadDeliriumImproved {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch firstLatch = new CountDownLatch(1);
        CountDownLatch secondLatch = new CountDownLatch(1);
        Thread01 t1 = new Thread01(firstLatch, secondLatch);
        Thread02 t2 = new Thread02(firstLatch, secondLatch, t1);
        t1.setThread02(t2);

        t1.start();
        t2.start();
//        TimeUnit.SECONDS.sleep(10);
//        t1.start();

        t1.join();
        t2.join();
    }

    private static class Thread01 extends Thread {
        private final CountDownLatch firstLatch;
        private final CountDownLatch secondLatch;
        public int foo = 0;
        private Thread02 thread02;

        private Thread01(CountDownLatch firstLatch, CountDownLatch secondLatch) {
            this.firstLatch = firstLatch;
            this.secondLatch = secondLatch;
        }

        public void setThread02(Thread02 thread02) {
            this.thread02 = thread02;
        }

        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    foo += i;
                }
                firstLatch.countDown(); // first thread is done
                secondLatch.await(); // let the second thread do its job
                System.out.println("Foo: " + thread02.foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Thread02 extends Thread {
        private final CountDownLatch firstLatch;
        private final CountDownLatch secondLatch;
        private final Thread01 thread01;
        public int foo = 0;

        public Thread02(CountDownLatch firstLatch, CountDownLatch secondLatch, Thread01 thread01) {
            this.firstLatch = firstLatch;
            this.secondLatch = secondLatch;
            this.thread01 = thread01;
        }

        public void run() {
            try {
                firstLatch.await(); // let the first thread do its job
                foo = thread01.foo;
                for (int i = 0; i < 10; i++) {
                    foo += i;
                }
                secondLatch.countDown(); // second thread is done
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
