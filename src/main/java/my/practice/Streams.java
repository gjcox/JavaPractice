package my.practice;

import java.util.Comparator;
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
     * @param input   array of strings
     * @param toUpper true to change to upper case; false to change to lower case
     * @return an array of strings with consistent casing
     */
    public static String[] batchChangeCase(String[] input, boolean toUpper) {
        return Stream.of(input)
                .map(str -> toUpper ? str.toUpperCase() : str.toLowerCase())
                .toArray(String[]::new);
    }

    /**
     * Calculates the sum of all even or all odd numbers in a list using streams.
     * 
     * @param nums     integers to sum
     * @param sumEvens true to sum evens; false to sum odds
     * @return the sum of either all even or all odd numbers in nums
     */
    public static int sumAllEvenOrOdd(List<Integer> nums, boolean sumEvens) {
        return nums.stream()
                .filter(i -> (i % 2 == 0) == sumEvens)
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * Removes duplicates from a list of numbers. Does not affect the original list.
     * 
     * @param <T>  a type that extends Number
     * @param list from which to remove duplicates
     * @return a new list that has no duplicates
     */
    public static <T extends Number> List<T> removeDuplicates(List<T> list) {
        return list.stream().distinct().toList();
    }

    /**
     * Counts the number of strings in a list that start with a specific letter.
     * 
     * @param strings a list of strings
     * @param letter  the specific letter to base counting off of
     * @return the number of strings in the list that start with the given letter
     */
    public static int countBeginsWith(List<String> strings, char letter) {
        return (int) strings.stream()
                .filter(s -> s.startsWith(String.valueOf(letter)))
                .count();
    }

    /**
     * Sorts a list of Strings inplace in ascending or descending alphabetical
     * order.
     * 
     * @param toSort    the list to sort
     * @param ascending the order to sort in
     */
    public static void sortAlphabetical(List<String> toSort, boolean ascending) {
        List<String> sorted = toSort.stream()
                .sorted(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .toList();
        for (int i = 0; i < sorted.size(); i++) {
            toSort.set(i, sorted.get(i));
        }
    }

    /**
     * Returns the largest value in a list of integers, or null if the list
     * is empty.
     * 
     * @param ints the list of integers
     * @return the largest integer in the list
     */
    public static Integer getMax(List<Integer> ints) {
        return ints.stream()
                .max(Integer::compare)
                .orElse(null);
    }

    /**
     * Returns the smallest value in a list of integers, or null if the list is
     * empty.
     * 
     * @param ints the list of integers
     * @return the smallest integer in the list
     */
    public static Integer getMin(List<Integer> ints) {
        return ints.stream()
                .min(Integer::compare)
                .orElse(null);
    }
}
