package my.practice;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamsTest {

    // Mean calculation tests
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

    // Batch case change tests
    @Test
    void batchChangeCase_lowerToLower_lower() {
        String[] input = new String[] { "lower" };
        String[] output = Streams.batchChangeCase(input, false);
        for (String string : output) {
            assertFalse(string.matches("[A-Z]+"));
        }
    }

    @Test
    void batchChangeCase_upperToLower_lower() {
        String[] input = new String[] { "UPPER" };
        String[] output = Streams.batchChangeCase(input, false);
        for (String string : output) {
            assertFalse(string.matches("[A-Z]+"));
        }
    }

    @Test
    void batchChangeCase_upperToLower_upper() {
        String[] input = new String[] { "UPPER" };
        String[] output = Streams.batchChangeCase(input, true);
        for (String string : output) {
            assertFalse(string.matches("[a-z]+"));
        }
    }

    @Test
    void batchChangeCase_upperToUpper_upper() {
        String[] input = new String[] { "UPPER" };
        String[] output = Streams.batchChangeCase(input, true);
        for (String string : output) {
            assertFalse(string.matches("[a-z]+"));
        }
    }

    @Test
    void batchChangeCase_mixedToLower_lower() {
        String[] input = new String[] { "lower", "UPPER" };
        String[] output = Streams.batchChangeCase(input, false);
        for (String string : output) {
            assertFalse(string.matches("[A-Z]+"));
        }
    }

    @Test
    void batchChangeCase_mixedToUpper_upper() {
        String[] input = new String[] { "lower", "UPPER" };
        String[] output = Streams.batchChangeCase(input, true);
        for (String string : output) {
            assertFalse(string.matches("[a-z]+"));
        }
    }

    @Test
    void batchChangeCase_numbersToUpper_upper() {
        String[] input = new String[] { "1234" };
        String[] output = Streams.batchChangeCase(input, true);
        for (String string : output) {
            assertFalse(string.matches("[a-z]+"));
        }
    }

    @Test
    void batchChangeCase_numbersAndLettersToUpper_upper() {
        String[] input = new String[] { "1 one", "2 TWO" };
        String[] output = Streams.batchChangeCase(input, true);
        for (String string : output) {
            assertFalse(string.matches("[a-z]+"));
        }
    }

    // Even or odd summation tests
    @Test
    void sumAllEvenOrOdd_sumEvenEmptyList_zero() {
        List<Integer> input = new ArrayList<>();
        int output = Streams.sumAllEvenOrOdd(input, true);
        assertEquals(0, output);
    }

    @Test
    void sumAllEvenOrOdd_sumOddEmptyList_zero() {
        List<Integer> input = new ArrayList<>();
        int output = Streams.sumAllEvenOrOdd(input, false);
        assertEquals(0, output);
    }

    @Test
    void sumAllEvenOrOdd_sumEvenAllOdd_zero() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 3, 5, 7 });
        double output = Streams.sumAllEvenOrOdd(input, true);
        assertEquals(0, output);
    }

    @Test
    void sumAllEvenOrOdd_sumOddAllEven_zero() {
        List<Integer> input = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
        double output = Streams.sumAllEvenOrOdd(input, false);
        assertEquals(0, output);
    }

    @Test
    void sumAllEvenOrOdd_sumEvenAllEven_twenty() {
        List<Integer> input = Arrays.asList(new Integer[] { 2, 4, 6, 8 });
        double output = Streams.sumAllEvenOrOdd(input, true);
        assertEquals(20, output);
    }

    @Test
    void sumAllEvenOrOdd_sumOddAllOdd_sixteen() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 3, 5, 7 });
        double output = Streams.sumAllEvenOrOdd(input, false);
        assertEquals(16, output);
    }

    @Test
    void sumAllEvenOrOdd_sumEvenMixed_twenty() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 3, 5, 7, 2, 4, 6, 8 });
        double output = Streams.sumAllEvenOrOdd(input, true);
        assertEquals(20, output);
    }

    @Test
    void sumAllEvenOrOdd_sumOddMixed_sixteen() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 3, 5, 7, 2, 4, 6, 8 });
        double output = Streams.sumAllEvenOrOdd(input, false);
        assertEquals(16, output);
    }

    // Duplicate removal tests
    @Test
    void removeDuplicates_emptyList_emptyList() {
        List<Number> input = new ArrayList<>();
        List<Number> output = Streams.removeDuplicates(input);
        assertTrue(output.isEmpty());
    }

    @Test
    void removeDuplicates_duplicateDoubles_oneElement() {
        List<Number> input = Arrays.asList(new Double[] { 1.1, 1.1 });
        List<Number> output = Streams.removeDuplicates(input);
        Number[] expected = new Number[] { 1.1 };
        assertArrayEquals(expected, output.toArray(new Double[0]));
    }

    @Test
    void removeDuplicates_twoIntDuplicates_twoElements() {
        List<Number> input = Arrays.asList(new Integer[] { 1, 2, 1, 2 });
        List<Number> output = Streams.removeDuplicates(input);
        Number[] expected = new Number[] { 1, 2 };
        assertArrayEquals(expected, output.toArray(new Number[0]));
    }

    @Test
    void removeDuplicates_mixedNumbers_fourElements() {
        List<Number> input = Arrays.asList(new Number[] { 1, 2.0, 1.0, 2 });
        List<Number> output = Streams.removeDuplicates(input);
        Number[] expected = new Number[] { 1, 2.0, 1.0, 2 };
        assertArrayEquals(expected, output.toArray(new Number[0]));
    }

    @Test
    void removeDuplicates_intsNoDuplicates_fourElements() {
        List<Number> input = Arrays.asList(new Integer[] { 1, 2, 3, 4 });
        List<Number> output = Streams.removeDuplicates(input);
        Number[] expected = new Number[] { 1, 2, 3, 4 };
        assertArrayEquals(expected, output.toArray(new Number[0]));
    }

    // countBeginsWith tests
    @Test
    void countBeginsWith_emptyList_zero() {
        List<String> input = new ArrayList<>();
        char letter = 'a';
        int output = Streams.countBeginsWith(input, letter);
        assertEquals(0, output);
    }

    @Test
    void countBeginsWith_oneBeginsWith_one() {
        List<String> input = Arrays.asList(new String[] { "apple", "banana", "cantaloupe" });
        char letter = 'a';
        int output = Streams.countBeginsWith(input, letter);
        assertEquals(1, output);
    }

    @Test
    void countBeginsWith_oneBeginsWith_none() {
        List<String> input = Arrays.asList(new String[] { "banana", "cantaloupe", "daikon" });
        char letter = 'a';
        int output = Streams.countBeginsWith(input, letter);
        assertEquals(0, output);
    }

    @Test
    void countBeginsWith_allBeginWith_three() {
        List<String> input = Arrays.asList(new String[] { "apple", "accordion", "antelope" });
        char letter = 'a';
        int output = Streams.countBeginsWith(input, letter);
        assertEquals(3, output);
    }
}
