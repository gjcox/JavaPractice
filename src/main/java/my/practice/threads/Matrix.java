package my.practice.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {
    private static final int INT_PADDING = 2;
    private static final Object threadPoolLock = new Object();

    private static ExecutorService threadPool;

    public static void setThreadPool(int nThreads) {
        synchronized (threadPoolLock) {
            threadPool = Executors.newFixedThreadPool(nThreads);
        }
    }

    public static void shutdownThreadPool() {
        synchronized (threadPoolLock) {
            threadPool.shutdown();
        }
    }

    private final int height, width;
    private final int[][] arr;

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.arr = new int[height][width];
    }

    public Matrix(int[][] arr) {
        this.arr = arr;
        this.height = arr.length;
        this.width = arr[0].length;
        for (int[] row : arr) {
            if (row.length != this.width) {
                throw new IllegalArgumentException("Matrix rows must all be the same length");
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] row : arr) {
            builder.append(IntStream.of(row)
                    .mapToObj(i -> String.format("%" + INT_PADDING + "d", i))
                    .collect(Collectors.joining(" ", "| ", " |\n")));
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Matrix)) {
            return false;
        }
        Matrix otherMatrix = (Matrix) other;

        if (this.width != otherMatrix.width || this.height != otherMatrix.height) {
            return false;
        }
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (this.arr[row][col] != otherMatrix.arr[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix add(Matrix other) {
        if (this.width != other.width || this.height != other.height) {
            throw new IllegalArgumentException("Only matrices of the same dimensions can be added");
        }
        int[][] arr = new int[height][width];
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                arr[row][col] = this.arr[row][col] + other.arr[row][col];
            }
        }
        return new Matrix(arr);
    }

    public Matrix mult(Matrix other) {
        // TODO
        return null;
    }

}
