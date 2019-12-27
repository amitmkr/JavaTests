package com.apm.completableFuture;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class CompletableFutureTest {

  private static void printMultiplicationSync(int num1, int num2) {
    getNumberAfterDelay(num1)
      .thenCombine(getNumberAfterDelay(num2), CompletableFutureTest::multiply)
      .thenAccept(multResult -> System.out.println("The result is " + multResult));
  }

  private static void printMultiplicationSync(int num1, int num2, int num3) {
    getNumberAfterDelay(num1)
      .thenCombine(getNumberAfterDelay(num2), CompletableFutureTest::multiply)
      .thenCombine(getNumberAfterDelay(num3), CompletableFutureTest::multiply)
      .thenAccept(multResult -> System.out.println("The result is " + multResult));
  }

  private static void printMultiplicationAsync(int num1, int num2) {
    getNumberAfterDelay(num1)
      .thenCombineAsync(getNumberAfterDelay(num2), CompletableFutureTest::multiply)
      .thenAccept(multResult -> System.out.println("The result is " + multResult));
  }

  private static CompletableFuture<Integer> getNumberAfterDelay(int num) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("Getting number in " + num + " seconds");
      try { sleep(num * 1000); } catch (Exception e) { System.out.println("Caught Exception"); e.printStackTrace();}
      System.out.println("Now returning number after " + num + " seconds");
      return Integer.valueOf(num);
    }
    );
  }

  private static Integer multiply(Integer number1, Integer number2) {
    return number1 * number2;
  }

  public static void main(String[] args) {

    System.out.println("Trying sync with 2 numbers ...");
    printMultiplicationSync(5, 10);

    System.out.println("Trying sync with 3 numbers ...");
    printMultiplicationSync(6, 18, 12);

    //System.out.println("Trying async ...");
    //printMultiplicationAsync(5, 10);

  }
}

/*
Trying sync with 2 numbers ...
Trying sync with 3 numbers ...
Getting number in 18 seconds
Getting number in 10 seconds
Getting number in 6 seconds
Getting number in 12 seconds
Getting number in 5 seconds
Now returning number after 5 seconds
Now returning number after 6 seconds
Now returning number after 10 seconds
The result is 50
Now returning number after 12 seconds
Now returning number after 18 seconds
The result is 1296

Process finished with exit code 0
 */

