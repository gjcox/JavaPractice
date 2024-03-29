package my.practice.threads;

/**
 * Solutions to these tasks
 * https://www.w3resource.com/java-exercises/thread/index.php
 * [accessed 26/03/2024] to get familiar with using Streams.
 */
public class Threads {
    /**
     * A basic Java runnable that prints "Hello, World!" when executed.
     */
    public static class HelloWorldRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello, World!");
        }
    }

    /**
     * Prints all of the odd or even integers from 0 up to a specified limit.
     */
    public static class ParityRunnable implements Runnable {
        private int max;
        private boolean even;

        /**
         * 
         * @param max  the value at which to stop printing (inclusive)
         * @param even true to print even numbers; false to print odd numbers
         */
        public ParityRunnable(int max, boolean even) {
            this.max = max;
            this.even = even;
        }

        @Override
        public void run() {
            for (int i = even ? 0 : 1; i <= max; i += 2)
                System.out.println((even ? "even" : "odd") + ": " + i);
        }
    }

    public static void main(String[] args) {
        Thread helloThread = new Thread(new HelloWorldRunnable());
        helloThread.start();

        Thread evenThread = new Thread(new ParityRunnable(20, true));
        Thread oddThread = new Thread(new ParityRunnable(20, false));
        evenThread.start();
        oddThread.start();
    }
}
