package my.practice.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
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

    @Test
    void getHeight_m1_3() {
        assertEquals(3, m1.getHeight());
    }

    @Test
    void getWidth_m1_2() {
        assertEquals(2, m1.getWidth());
    }

    @Test
    void toString_m1_multiline() {
        String expected = "|  1  2 |\n|  3  4 |\n|  5  6 |\n";
        assertEquals(expected, m1.toString());
    }

    @Test
    void equals_m1AndM1_true() {
        assertTrue(m1.equals(m1));
    }

    @Test
    void equals_m1AndM1Copy_true() {
        assertTrue(m1.equals(m1Copy));
    }

    @Test
    void equals_m1CopyAndM1_true() {
        assertTrue(m1Copy.equals(m1));
    }

    @Test
    void equals_m1AndM2_false() {
        assertFalse(m1.equals(m2));
    }

    @Test
    void add_m1AndM1_m1Unchanged() {
        m1.add(m1);
        assertTrue(m1.equals(m1Copy));
    }

    @Test
    void add_m1AndM1_doubleM1() {
        Matrix expected = new Matrix(new int[][] {
                new int[] { 2, 4 },
                new int[] { 6, 8 },
                new int[] { 10, 12 },
        });
        Matrix sum = m1.add(m1);
        assertTrue(expected.equals(sum));
    }

    @Test
    void add_mismatchedDimenions_illegalArgumentsException() {
        assertThrows(IllegalArgumentException.class, () -> m1.add(m3));
    }

    @Test
    void mult_mismatchedDimensions_illegalArgumentsExceptions() {
        assertThrows(IllegalArgumentException.class, () -> m1.mult(m1));
    }

    @Test
    void mult_m1_unchanged() {
        m1.mult(m3);
        assertEquals(m1Copy, m1);
    }

    @Test
    void mult_m1AndIdentityMatrix_m1() {
        Matrix dot = m1.mult(identity2x2);
        assertEquals(m1Copy, dot);
    }

    @Test
    void mult_m1Andm3_3by2() {
        Matrix expected = new Matrix(new int[][] {
                new int[] { 3, 6 },
                new int[] { 9, 16 },
                new int[] { 15, 26 },
        });
        Matrix dot = m1.mult(m3);
        assertEquals(expected, dot);
    }

    @Test
    void mult_m1Andm3_3by3() {
        Matrix expected = new Matrix(new int[][] {
                new int[] { -9, -12, -15 },
                new int[] { -19, -26, -33 },
                new int[] { -29, -40, -51 },
        });
        Matrix dot = m1.mult(m4);
        assertEquals(expected, dot);
    }

}
