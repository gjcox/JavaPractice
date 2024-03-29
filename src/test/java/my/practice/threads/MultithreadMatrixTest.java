package my.practice.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultithreadMatrixTest {
    Matrix m1, m1Copy, m2, m3, m4, identity2x2;

    @BeforeEach
    void initMatrix() {
        m1 = new Matrix(new int[][] {
                new int[] { 1, 2 },
                new int[] { 3, 4 },
                new int[] { 5, 6 },
        });
        m1Copy = new Matrix(new int[][] {
                new int[] { 1, 2 },
                new int[] { 3, 4 },
                new int[] { 5, 6 },
        });
        m2 = new Matrix(new int[][] {
                new int[] { 7, 8 },
                new int[] { 9, 10 },
                new int[] { 11, 12 },
        });
        m3 = new Matrix(new int[][] {
                new int[] { 3, 4 },
                new int[] { 0, 1 },
        });
        m4 = new Matrix(new int[][] {
                new int[] { -1, -2, -3 },
                new int[] { -4, -5, -6 },
        });
        identity2x2 = new Matrix(new int[][] {
                new int[] { 1, 0 },
                new int[] { 0, 1 },
        });
    }

    @BeforeAll
    static void setThreadPool() {
        Matrix.setThreadPool(15);
    }

    @AfterAll
    static void shutdownThreadPool() {
        Matrix.shutdownThreadPool();
    }

    @Test
    void mult_multithreaded_m1_unchanged() {
        m1.mult(m3);
        assertEquals(m1Copy, m1);
    }

    @Test
    void mult_multithreaded_m1AndIdentityMatrix_m1() {
        Matrix dot = m1.mult(identity2x2);
        assertEquals(m1Copy, dot);
    }

    @Test
    void mult_multithreaded_m1Andm3_3by2() {
        Matrix expected = new Matrix(new int[][] {
                new int[] { 3, 6 },
                new int[] { 9, 16 },
                new int[] { 15, 26 },
        });
        Matrix dot = m1.mult(m3);
        assertEquals(expected, dot);
    }

    @Test
    void mult_multithreaded_m1Andm3_3by3() {
        Matrix expected = new Matrix(new int[][] {
                new int[] { -9, -12, -15 },
                new int[] { -19, -26, -33 },
                new int[] { -29, -40, -51 },
        });
        Matrix dot = m1.mult(m4);
        assertEquals(expected, dot);
    }
}
