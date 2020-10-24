package practice.jmm;

import java.util.Random;

public class TestOptimization {


    public static void main(String[] args) {

        Random rd = new Random();
        byte[] arr = new byte[8192 * 10];
        rd.nextBytes(arr);
        testFunction(arr);
    }

    private static void testFunction(byte[] arr) {

        for (int len = 8192; len <= arr.length; len += 8192) {
            long t0 = System.nanoTime();
            for (int n = 0; n < 100; n++) {
                for (int index = 0; index < len; index += 64) {
                    arr[index] = 1;
                }
            }
            long dt = System.nanoTime() - t0;

            System.out.println("len: " + len + ", dt: " + dt + ", 10*dt/len: " + (10 * dt) / len);
        }
    }
}
