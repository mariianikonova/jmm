package practice.jmm;


import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadAccessInOneCacheLine {

    volatile static long value0 = 0l; //value0 и value8 никогда не попадут в одну кэш-линию, тк кэщ-линия 64b
    //64 * 8 = 512бит.
    volatile static long value1 = 0l;
    volatile static long value2 = 0l;
    volatile static long value3 = 0l;
    volatile static long value4 = 0l;
    volatile static long value5 = 0l;
    volatile static long value6 = 0l;
    volatile static long value7 = 0l;
    volatile static long value8 = 0l;


    public static void main(String[] args) {
        falseSharingDetector();
    }

    //Instruction level parallelism
    private static void falseSharingDetector() {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        final CountDownLatch latch0 = new CountDownLatch(2);
        final CountDownLatch latch1 = new CountDownLatch(2);
        pool.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                latch0.countDown();
                latch0.await();
                long t0 = System.nanoTime();
                for (int k = 0; k < 100_000_000; k++) {
                    value0 = value0 * k;
                }
                long t1 = System.nanoTime();
                System.out.println((t1 - t0) / 1000000 + " ms");
                latch1.countDown(); //#Thread 1 finished
                return null;
            }
        });

        pool.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                latch0.countDown();
                latch0.await();
                long t0 = System.nanoTime();
                for (int k = 0; k < 100_000_000; k++) {
                    value0 = value0 * k;
                }
                long t1 = System.nanoTime();
                System.out.println((t1 - t0) / 1000000 + " ms");
                latch1.countDown(); //#Thread 1 finished
                return null;
            }
        });
        try {
            latch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
