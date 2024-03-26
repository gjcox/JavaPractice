package my.practice;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

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
}
