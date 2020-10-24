package practice;

import java.util.Arrays;

public class MergeSort {

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
        return result;
    }


    static int[] sortArr(int[] src) {
        System.out.println(Arrays.toString(src));
        if (src.length > 1) {
            int[] first = Arrays.copyOfRange(src, 0, (src.length / 2));
            int[] second = (Arrays.copyOfRange(src, src.length / 2, src.length));

            int[] first1 = sortArr(first);
            int[] second2 = sortArr(second);
            return merge(first1, second2);
        }
        if (src.length == 1) {
            return src;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] origin = new int[]{20, 4, 2, 11, 0, 1, 5, 14, 8, 9, 7, 12, 6};
        System.out.println(Arrays.toString(sortArr(origin)));
    }
}

