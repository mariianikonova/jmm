package practice.jmm;


//https://youtu.be/tCZ6jKgXAUI?list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&t=4554
public class CacheLineL164b {
    final static int Array_SIZE = 64 * 1024;

    public static void main(String[] args) {

        byte[] arr = new byte[Array_SIZE];

        for (int index = 0; index < 10; index++) {
            testFunction(arr);
            System.out.println("----------");
        }
    }

    //идея экперемента: https://youtu.be/tCZ6jKgXAUI?list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&t=4640
    //https://youtu.be/tCZ6jKgXAUI?list=PLoij6udfBncgyV-7Y0lEh0EwfkpcAoBeK&t=4576
    private static void testFunction(byte[] arr) {

        for (int step8Kb = 8192; step8Kb <= arr.length; step8Kb += 8192) { //step8Kb < arr.length (64kb)
            long t0 = System.nanoTime();
            for (int n = 0; n < 100; n++) {
                for (int step64b = 0; step64b < step8Kb; step64b += 64) {
                    arr[step64b] = 1;
                }
            }
            long dt = System.nanoTime() - t0;

            System.out.println("len: " + step8Kb + ", dt: " + dt + ", 10*dt/len: " + (10 * dt) / step8Kb);
        }
    }
}



