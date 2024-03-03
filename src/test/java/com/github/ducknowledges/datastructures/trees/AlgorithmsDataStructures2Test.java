package com.github.ducknowledges.datastructures.trees;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AlgorithmsDataStructures2")
class AlgorithmsDataStructures2Test {

  @Test
  @DisplayName("should genrate empty balanced bst array")
  void shouldGenerateEmptyBalancedBSTArray() {
    int[] a = {};
    int[] expected = {};
    int[] actual = AlgorithmsDataStructures2.GenerateBBSTArray(a);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("should generate balanced bst array fron one element")
  void shouldGenerateBalancedBSTArrayOneElement() {
    int[] a = {1};
    int[] expected = {1};
    int[] actual = AlgorithmsDataStructures2.GenerateBBSTArray(a);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("should generate balanced bst array from odd number of elements")
  void shouldGenerateBalancedBSTArray() {
    int[] a = {3, 2, 1, 6, 7, 4, 5};
    int[] expected = {4, 2, 6, 1, 3, 5, 7, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] actual = AlgorithmsDataStructures2.GenerateBBSTArray(a);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("should generate balanced bst array from even number of elements")
  void shouldGenerateBalancedBSTArrayEven() {
    int[] a = {3, 2, 1, 6, 7, 4, 5, 8};
    int[] expected = {4, 2, 6, 1, 3, 5, 7, 0, 0, 0, 0, 0, 0, 0, 8};
    int[] actual = AlgorithmsDataStructures2.GenerateBBSTArray(a);
    assertThat(actual).isEqualTo(expected);
  }
}
