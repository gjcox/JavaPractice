package my.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StreamsTest {
  @Test
    void calcMean_emptyInput_isEmpty() {
        int[] nums = new int[] {};
        assertTrue(Streams.calcMean(nums).isEmpty());
    }

    @Test
    void calcMean_nonEmptyInput_notEmpty() {
        int[] nums = new int[] { 5 };
        assertFalse(Streams.calcMean(nums).isEmpty());
    }

    @Test
    void calcMean_five_five() {
        int[] nums = new int[] { 5 };
        assertEquals(5.0, Streams.calcMean(nums).getAsDouble());
    }

    @Test
    void calcMean_fiveAndSeven_six() {
        int[] nums = new int[] { 5, 7 };
        assertEquals(6.0, Streams.calcMean(nums).getAsDouble());
    }

    @Test
    void calcMean_fourAndSix_five() {
        int[] nums = new int[] { 4, 6 };
        assertEquals(5.0, Streams.calcMean(nums).getAsDouble());
    }

    @Test
    void calcMean_fourAndSeven_fivePointFive() {
        int[] nums = new int[] { 4, 7 };
        assertEquals(5.5, Streams.calcMean(nums).getAsDouble());
    }

    @Test
    void calcMean_tenAndTenAndOne_seven() {
        int[] nums = new int[] { 10, 10, 1 };
        assertEquals(7, Streams.calcMean(nums).getAsDouble());
    }
}
