package practice.jmm;


public class InstructionLevelParallelism2 {

    public static void main(String[] args) {
        long t0 = System.nanoTime();
        double d0 = 0;
        double d1 = 0;
        for (int k = 0; k < 100_000_000; k++) {
            d0 = d0 * d0;
            d1 = d1 * d1;
        }
        long t1 = System.nanoTime();
        System.out.println((t1 - t0) / 1000000 + " ms");
    }
}
