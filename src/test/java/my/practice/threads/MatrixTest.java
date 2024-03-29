package my.practice.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    Matrix m;

    @BeforeEach
    void initMatrix() {
        m = new Matrix(new int[][] {
                new int[] { 1, 2 },
                new int[] { 3, 4 },
                new int[] { 5, 6 },
        });
    }

    @Test
    void getHeight_m_3() {
        assertEquals(3, m.getHeight());
    }

    @Test
    void getWidth_m_2() {
        assertEquals(2, m.getWidth());
    }

    @Test
    void toString_m_multiline() {
        String expected = "|  1  2 |\n|  3  4 |\n|  5  6 |\n";
        assertEquals(expected, m.toString());
    }

}
