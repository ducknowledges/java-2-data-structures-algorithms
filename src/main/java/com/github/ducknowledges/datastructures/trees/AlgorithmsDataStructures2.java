package com.github.ducknowledges.datastructures.trees;

import java.util.*;

public class AlgorithmsDataStructures2 {
  public static int[] GenerateBBSTArray(int[] a) {
    if (a.length < 1) return a;
    int bstSize = calculateBSTSize(a);
    int[] balancedBSTArray = new int[bstSize];
    int[] sortedArray = Arrays.copyOf(a, a.length);
    Arrays.sort(sortedArray);
    fillBalancedBSTArray(sortedArray, balancedBSTArray, 0, a.length - 1, 0);
    return balancedBSTArray;
  }

  private static int calculateBSTSize(int[] a) {
    int bstHeight = calculateBSTHeight(a);
    return (int) Math.pow(2, bstHeight + 1.) - 1;
  }

  private static int calculateBSTHeight(int[] a) {
    return (int) Math.floor(Math.log(a.length) / Math.log(2));
  }

  private static void fillBalancedBSTArray(
      int[] sortedArray, int[] balancedBSTArray, int start, int end, int currentIndex) {
    if (start > end) {
      return;
    }
    int middleIndex = (start + end) / 2;
    balancedBSTArray[currentIndex] = sortedArray[middleIndex];
    fillBalancedBSTArray(
        sortedArray, balancedBSTArray, start, middleIndex - 1, 2 * currentIndex + 1);
    fillBalancedBSTArray(sortedArray, balancedBSTArray, middleIndex + 1, end, 2 * currentIndex + 2);
  }
}
