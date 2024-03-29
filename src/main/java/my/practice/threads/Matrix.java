package my.practice.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {
    private static final int INT_PADDING = 2;
    private static final Object threadPoolLock = new Object();

    private static ExecutorService threadPool;
    private static int nThreads;

    public static void setThreadPool(int nThreads) {
        synchronized (threadPoolLock) {
            threadPool = Executors.newFixedThreadPool(nThreads);
            Matrix.nThreads = nThreads;
        }
    }

    public static void shutdownThreadPool() {
        synchronized (threadPoolLock) {
            threadPool.shutdown();
            Matrix.nThreads = 0;
        }
    }

    private class MultiplicationTask implements Runnable {
        private Matrix m1, m2;
        private int[][] result;
        private int startIndex, endIndex;

        public MultiplicationTask(Matrix m1, Matrix m2, int[][] result, int startIndex, int endIndex) {
            this.m1 = m1;
            this.m2 = m2;
            this.result = result;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public MultiplicationTask(Matrix m1, Matrix m2, int[][] result) {
            this.m1 = m1;
            this.m2 = m2;
            this.result = result;
            this.startIndex = 0;
            this.endIndex = m1.height * m2.width;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < endIndex; i++) {
                int row = i / m2.width;
                int col = i % m2.width;
                for (int j = 0; j < m1.width; j++) {
                    result[row][col] += m1.arr[row][j] * m2.arr[j][col];
                }
            }
            return;
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
        if (this.width != other.height) {
            throw new IllegalArgumentException("this.width must equal other.height for multiplication");
        }
        if (threadPool != null && !threadPool.isShutdown()) {
            synchronized (threadPoolLock) {
                return multithreadMult(other);
            }
        } else {
            return singlethreadMult(other);
        }
    }

    private Matrix multithreadMult(Matrix other) {
        int[][] resultArr = new int[this.height][other.width];
        int subResults = this.height * other.width;
        int taskSize = Math.max(1, subResults / nThreads);
        int nTasks = Math.min(nThreads, subResults);
        Future<?>[] futures = new Future[nTasks];
        for (int i = 0; i < nTasks; i++) {
            int start = i * taskSize;
            int end = i < (nThreads - 1) ? start + taskSize : this.height * other.width;
            futures[i] = threadPool.submit(new MultiplicationTask(this, other, resultArr, start, end));
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return new Matrix(resultArr);
    }

    private Matrix singlethreadMult(Matrix other) {
        int[][] resultArr = new int[this.height][other.width];
        Thread thread = new Thread(new MultiplicationTask(this, other, resultArr));
        thread.run();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Matrix(resultArr);
    }
}
