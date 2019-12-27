package com.apm.moveZerosToEnd;

import java.util.Arrays;

public class MoveZerosToEnd {

  private static void moveZerosToEnd(int[] numArray) {
    int nonZeroInsertionPoint=0;
    for (int i=0; i<numArray.length; i++) {
      if (numArray[i] == 0) {
        if (nonZeroInsertionPoint == i) {
          // found the 1st Zero, pin the nonZeroInsertionPoint
          nonZeroInsertionPoint = i;
        }
        else {
          // We have already found a Zero, this is a subsequent Zero
          // Keep the nonZeroInsertionPoint pinned.
        }
      }
      else if (numArray[i] != 0) {
        if (nonZeroInsertionPoint == i) {
          // we haven't found any Zeros yet, keep moving
          nonZeroInsertionPoint++;
        }
        else {
          // found zeros earlier, now found a non-zero, then copy-over non-Zero to the Zero position
          numArray[nonZeroInsertionPoint] = numArray[i];
          numArray[i] = 0;
          nonZeroInsertionPoint++;
        }
      }
    }
  }

  public static  void main(String[] args) {
    System.out.println("ABCD");

    int[] inputArray = { 0, 0, 10, 0, 20, 0, 0, 0, 30, 0, 0, 40, 0, 0 };
    System.out.println(Arrays.toString(inputArray));
    moveZerosToEnd(inputArray);
    System.out.println(Arrays.toString(inputArray));
  }
}
