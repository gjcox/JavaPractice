package my.practice.threads;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {
    private static final int INT_PADDING = 2;

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
}
