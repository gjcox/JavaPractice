package my.practice.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    Matrix m1, m1Copy, m2;

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
}
