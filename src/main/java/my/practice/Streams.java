package my.practice;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Solutions to these tasks https://www.w3resource.com/java-exercises/stream/
 * [accessed 26/03/2024] to get familiar with using Streams.
 */
public final class Streams {
    private Streams() {
    }

    /**
     * Calculates the average of an array of integers using an IntStreams
     * 
     * @param nums an array of integers.
     * @return the mean of nums as an OptionalDouble, or an empty OptionalDouble if
     *         nums is empty.
     */
    public static OptionalDouble calcMean(int[] nums) {
        return IntStream.of(nums)
                .mapToDouble((int i) -> Double.valueOf(i))
                .average();
    }

    /**
     * Converts an array of strings to uppercase or lowercase using streams. 
     * 
     * @param input array of strings
     * @param toUpper true to change to upper case; false to change to lower case 
     * @return an array of strings with consistent casing
     */
    public static String[] batchChangeCase(String[] input, boolean toUpper) {
        return Stream.of(input)
                .map(str -> toUpper ? str.toUpperCase() : str.toLowerCase())
                .toArray(String[]::new);
    }

    public static int sumAllEvenOrOdd(List<Integer> nums, boolean sumEvens) {
        return 0; 
    }
}
