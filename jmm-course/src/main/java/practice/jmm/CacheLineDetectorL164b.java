package practice.jmm;


//https://youtu.be/tCZ6jKgXAUI?list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&t=4420
public class CacheLineDetectorL164b {
    final static int Array_SIZE = 2 * 1024 * 1024;

    public static void main(String[] args) {

        byte[] arr = new byte[Array_SIZE];

        for (int index = 0; index < 10; index++) {
            testFunction(arr);
            System.out.println("----------");
        }
    }

    private static long testFunction(byte[] arr) {
        long summ = 0;
        for (int stepSize = 1; stepSize <= 64; stepSize *= 2) {

            long t0 = System.nanoTime();

            for (int n = 0; n < 10; n++) {
                for (int k = 0; k < arr.length; k += stepSize) {
                    summ += arr[k];
                }
            }
            long dt = System.nanoTime() - t0;
            // время затраченное на 1 ячейку памяти:
            System.out.println("len: " + arr.length + ", dt: " + dt + ", 10*dt/len: " + (10 * dt) / arr.length);

        }
        return summ;
    }
}



