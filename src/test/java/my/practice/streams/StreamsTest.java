package my.practice.streams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StreamsTest {

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

    // Alphabetical sorting tests
    @Test
    void sortAlphabetical_emptyListAscending_noError() {
        List<String> list = new ArrayList<>();
        Streams.sortAlphabetical(list, true);
        assertTrue(list.isEmpty());
    }

    @Test
    void sortAlphabetical_emptyListDescending_noError() {
        List<String> list = new ArrayList<>();
        Streams.sortAlphabetical(list, false);
        assertTrue(list.isEmpty());
    }

    @Test
    void sortAlphabetical_unsortedToAscending_ascending() {
        List<String> list = Arrays.asList(new String[] { "daikon", "banana", "apple", "cantaloupe" });
        Streams.sortAlphabetical(list, true);
        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("cantaloupe", list.get(2));
        assertEquals("daikon", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    void sortAlphabetical_unsortedToDescending_ascending() {
        List<String> list = Arrays.asList(new String[] { "daikon", "banana", "apple", "cantaloupe" });
        Streams.sortAlphabetical(list, false);
        assertEquals("apple", list.get(3));
        assertEquals("banana", list.get(2));
        assertEquals("cantaloupe", list.get(1));
        assertEquals("daikon", list.get(0));
        assertEquals(4, list.size());
    }

    @Test
    void sortAlphabetical_ascendingToAscending_ascending() {
        List<String> list = Arrays.asList(new String[] { "apple", "banana", "cantaloupe", "daikon" });
        Streams.sortAlphabetical(list, true);
        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals("cantaloupe", list.get(2));
        assertEquals("daikon", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    void sortAlphabetical_ascendingToDescending_descending() {
        List<String> list = Arrays.asList(new String[] { "apple", "banana", "cantaloupe", "daikon" });
        Streams.sortAlphabetical(list, false);
        assertEquals("apple", list.get(3));
        assertEquals("banana", list.get(2));
        assertEquals("cantaloupe", list.get(1));
        assertEquals("daikon", list.get(0));
        assertEquals(4, list.size());
    }

    // getMax tests
    @Test
    void getMax_emptyList_null() {
        List<Integer> input = new ArrayList<>();
        Integer output = Streams.getMax(input);
        assertNull(output);
    }

    @Test
    void getMax_oneElement_one() {
        List<Integer> input = Arrays.asList(new Integer[] { 1 });
        Integer output = Streams.getMax(input);
        assertEquals(1, output);
    }

    @Test
    void getMax_twoElementsAsc_two() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 2 });
        Integer output = Streams.getMax(input);
        assertEquals(2, output);
    }

    @Test
    void getMax_twoElementsDesc_two() {
        List<Integer> input = Arrays.asList(new Integer[] { 2, 1 });
        Integer output = Streams.getMax(input);
        assertEquals(2, output);
    }

    @Test
    void getMax_duplicateElement_three() {
        List<Integer> input = Arrays.asList(new Integer[] { 3, 3, 3, 3 });
        Integer output = Streams.getMax(input);
        assertEquals(3, output);
    }

    // getMin tests
    @Test
    void getMin_emptyList_null() {
        List<Integer> input = new ArrayList<>();
        Integer output = Streams.getMin(input);
        assertNull(output);
    }

    @Test
    void getMin_oneElement_one() {
        List<Integer> input = Arrays.asList(new Integer[] { 1 });
        Integer output = Streams.getMin(input);
        assertEquals(1, output);
    }

    @Test
    void getMin_twoElementsAsc_one() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 2 });
        Integer output = Streams.getMin(input);
        assertEquals(1, output);
    }

    @Test
    void getMin_twoElementsDesc_one() {
        List<Integer> input = Arrays.asList(new Integer[] { 2, 1 });
        Integer output = Streams.getMin(input);
        assertEquals(1, output);
    }

    @Test
    void getMin_duplicateElement_three() {
        List<Integer> input = Arrays.asList(new Integer[] { 3, 3, 3, 3 });
        Integer output = Streams.getMin(input);
        assertEquals(3, output);
    }

    // getSecondLargest tests
    @Test
    void getSecondLargest_emptyList_null() {
        List<Integer> input = new ArrayList<>();
        Integer output = Streams.getSecondLargest(input);
        assertNull(output);
    }

    @Test
    void getSecondLargest_oneElement_null() {
        List<Integer> input = Arrays.asList(new Integer[] { 1 });
        Integer output = Streams.getSecondLargest(input);
        assertNull(output);
    }

    @Test
    void getSecondLargest_twoElementsAsc_one() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 2 });
        Integer output = Streams.getSecondLargest(input);
        assertEquals(1, output);
    }

    @Test
    void getSecondLargest_twoElementsDesc_one() {
        List<Integer> input = Arrays.asList(new Integer[] { 2, 1 });
        Integer output = Streams.getSecondLargest(input);
        assertEquals(1, output);
    }

    @Test
    void getSecondLargest_duplicateElement_three() {
        List<Integer> input = Arrays.asList(new Integer[] { 3, 3, 3, 3 });
        Integer output = Streams.getSecondLargest(input);
        assertEquals(3, output);
    }

    // getSecondSmallest tests
    @Test
    void getSecondSmallest_emptyList_null() {
        List<Integer> input = new ArrayList<>();
        Integer output = Streams.getSecondSmallest(input);
        assertNull(output);
    }

    @Test
    void getSecondSmallest_oneElement_null() {
        List<Integer> input = Arrays.asList(new Integer[] { 1 });
        Integer output = Streams.getSecondSmallest(input);
        assertNull(output);
    }

    @Test
    void getSecondSmallest_twoElementsAsc_two() {
        List<Integer> input = Arrays.asList(new Integer[] { 1, 2 });
        Integer output = Streams.getSecondSmallest(input);
        assertEquals(2, output);
    }

    @Test
    void getSecondSmallest_twoElementsDesc_two() {
        List<Integer> input = Arrays.asList(new Integer[] { 2, 1 });
        Integer output = Streams.getSecondSmallest(input);
        assertEquals(2, output);
    }

    @Test
    void getSecondSmallest_duplicateElement_three() {
        List<Integer> input = Arrays.asList(new Integer[] { 3, 3, 3, 3 });
        Integer output = Streams.getSecondSmallest(input);
        assertEquals(3, output);
    }
}
