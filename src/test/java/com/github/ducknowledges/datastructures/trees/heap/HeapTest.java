package com.github.ducknowledges.datastructures.trees.heap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Heap")
class HeapTest {

  @Test
  @DisplayName("should be null new heap")
  void shouldBeNullNewHeap() {
    Heap heap = new Heap();
    assertThat(heap.HeapArray).isNull();
  }

  @Test
  @DisplayName("should make heap from empty array")
  void shouldMakeHeapFromEmptyArray() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {}, 0);
    assertThat(heap.HeapArray).contains(0);
  }

  @Test
  @DisplayName("should make heap from odd array")
  void shouldMakeHeapFromOddArray() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {4, 2, 6, 1, 3, 5, 7}, 2);
    assertThat(heap.HeapArray).isEqualTo(new int[] {7, 3, 6, 1, 2, 4, 5});
  }

  @Test
  @DisplayName("should make heap from even array")
  void shouldMakeHeapFromEvenArray() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {3, 2, 1, 6, 7, 4, 5, 8}, 3);
    assertThat(heap.HeapArray).isEqualTo(new int[] {8, 7, 5, 6, 3, 1, 4, 2, 0, 0, 0, 0, 0, 0, 0});
  }

  @Test
  @DisplayName("should get max from empty heap")
  void shouldGetMaxFromEmptyHeap() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {}, 0);
    int expected = heap.GetMax();
    assertThat(expected).isEqualTo(-1);
  }

  @Test
  @DisplayName("should get max from heap with odd size")
  void shouldGetMaxFromOddHeap() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {4, 2, 6, 1, 3, 5, 7}, 2);
    int expected = heap.GetMax();
    assertThat(expected).isEqualTo(7);
    assertThat(heap.HeapArray).isEqualTo(new int[] {6, 3, 5, 1, 2, 4, 0});
  }

  @Test
  @DisplayName("should get max from heap with even size")
  void shouldGetMaxFromEvenHeap() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {3, 2, 1, 6, 7, 4, 5, 8}, 3);
    int expected = heap.GetMax();
    assertThat(expected).isEqualTo(8);
    assertThat(heap.HeapArray).isEqualTo(new int[] {7, 6, 5, 2, 3, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0});
  }

  @Test
  @DisplayName("should add to empty heap")
  void shouldAddToEmptyHeap() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {}, 0);
    boolean expected = heap.Add(1);
    assertThat(expected).isTrue();
    assertThat(heap.HeapArray).isEqualTo(new int[] {1});
  }

  @Test
  @DisplayName("should return false when adding to full heap")
  void shouldReturnFalseWhenAddingToFullHeap() {
    Heap heap = new Heap();
    heap.MakeHeap(new int[] {4, 2, 6, 1, 3, 5, 7}, 2);
    boolean expected = heap.Add(9);
    assertThat(expected).isFalse();
  }
}
