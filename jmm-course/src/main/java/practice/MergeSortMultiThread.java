package practice;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MergeSortMultiThread extends RecursiveTask<int[]> {

    private final int[] src;

    public MergeSortMultiThread(int[] src) {
        this.src = src;
    }

    static int[] merge(int[] src1, int[] src2) {

        int[] result = new int[src1.length + src2.length];
        int counter = 0;

        int kn = 0;
        int in = 0;
        while (counter < (src1.length + src2.length)) {

            if (in > (src1.length - 1)) {
                for (int e : Arrays.copyOfRange(src2, kn, src2.length)) {
                    result[counter] = e;
                    counter++;
                }
                break;
            }
            if (kn > (src2.length - 1)) {
                for (int e : Arrays.copyOfRange(src1, in, src1.length)) {
                    result[counter] = e;
                    counter++;
                }
                break;
            }

            if (src1[in] < src2[kn]) {
                result[counter] = src1[in];
                counter++;
                in++;
            } else {
                result[counter] = src2[kn];
                counter++;
                kn++;
            }
        }
        System.out.println("res: " + Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        int[] origin = new int[]{20, 4, 2, 11, 0, 1, 5, 14, 8, 9, 7, 12, 6};
        ForkJoinPool pool = new ForkJoinPool();
        int[] res = pool.invoke(new MergeSortMultiThread(origin));
        System.out.println(Arrays.toString(res));
    }

    @Override
    protected int[] compute() {
        System.out.println(Arrays.toString(src));
        if (src.length > 1) {
            MergeSortMultiThread left = new MergeSortMultiThread(Arrays.copyOfRange(src, 0, (src.length / 2)));
            MergeSortMultiThread right = new MergeSortMultiThread(
                    Arrays.copyOfRange(src, src.length / 2, src.length));
            invokeAll(left, right);
            return merge(left.join(), right.join());
        }
        if (src.length == 1) {
            return src;
        }
        return new int[]{};
    }
}
