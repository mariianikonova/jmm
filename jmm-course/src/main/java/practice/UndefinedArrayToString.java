package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class UndefinedArrayToString extends RecursiveTask<List<Integer>> {
    static List<Integer> res = new ArrayList();

    Object[] recursiveArr;
    static List<Integer> recursiveRes = new ArrayList();

    public UndefinedArrayToString(Object[] recursiveRes) {
        this.recursiveArr = recursiveRes;
    }

    public static void main(String[] args) {
        Object[] origin = new Object[]{20,
                new Object[]{100, 101, 3}, 2, 11,
                new Object[]{120, 121, 4, 9,
                        new Object[]{110, 111, 48, 2, 11, 12}}, 0, 1, 5,
                new Object[]{1, 3,
                        new Object[]{130, 131, 48, 2, 11,
                                new Object[]{140,
                                        new Object[]{150, 151}, 3,
                                        new Object[]{160, 161, 9, 7,}}}}, 14, 8, 9, 7, 12, 6}; //40

        System.out.println(" ==================== recursive");
        List<Integer> resultRecursive = arrsToOneDem(origin, new ArrayList<>());
        Arrays.asList(resultRecursive).forEach(System.out::println);
        System.out.println("size: " + resultRecursive.size());

        System.out.println(" ==================== with field");
        recursiveArrsToOneDem(origin);
        res.forEach(System.out::println);
        System.out.println("size: " + res.size());

        System.out.println(" ==================== with FJP");
        ForkJoinPool pool = new ForkJoinPool();
        UndefinedArrayToString task = new UndefinedArrayToString(origin);
        pool.invoke(task);
        Arrays.asList(task.recursiveArr).forEach(System.out::println);
        System.out.println("size: " + Arrays.asList(task.recursiveArr).size());

    }

    private static List<Integer> arrsToOneDem(Object[] origin, List<Integer> result) {
        Arrays.asList(origin).forEach(System.out::println);
        System.out.println("size: " + origin.length);
        for (int i = 0; i < origin.length; i++) {
            if (origin[i] instanceof Object[]) {
                System.out.println("elem index: " + i + " elem:" + origin[i]);
                arrsToOneDem((Object[]) origin[i], result);
            } else {
                result.add((Integer) origin[i]);
            }
        }
        return result;
    }

    private static void recursiveArrsToOneDem(Object[] origin) {
        for (int i = 0; i < origin.length; i++) {
            if (origin[i] instanceof Object[]) {
                recursiveArrsToOneDem((Object[]) origin[i]);
            } else {
                res.add((Integer) origin[i]);
            }
        }
    }

    @Override
    protected List<Integer> compute() {
        for (int i = 0; i < recursiveArr.length; i++) {
            if (recursiveArr[i] instanceof Object[]) {
                RecursiveTask task = new UndefinedArrayToString((Object[]) recursiveArr[i]);
                task.invoke();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.join();
            } else {
                recursiveRes.add((Integer) recursiveArr[i]);
            }
        }
        return recursiveRes;
    }
}


/*        ForkJoinPool pool = new ForkJoinPool();
        int[] res = pool.invoke(new practice.MergeSortMultiThreadList(origin));*/
//Integer[] res = (Integer[]) recursiveArrsToOneDem(origin);
//        arrsToOneDem(origin);
//        res.forEach(System.out::println);