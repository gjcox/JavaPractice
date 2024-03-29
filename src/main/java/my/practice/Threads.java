package my.practice;

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

    public static void main(String[] args) {
        Thread helloThread = new Thread(new HelloWorldRunnable());
        helloThread.start();
    }
}
