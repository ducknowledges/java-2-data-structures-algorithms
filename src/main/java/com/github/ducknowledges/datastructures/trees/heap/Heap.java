package com.github.ducknowledges.datastructures.trees.heap;

class Heap {
  public int[] HeapArray;
  public int size;

  public Heap() {
    HeapArray = null;
  }

  public void MakeHeap(int[] a, int depth) {
    HeapArray = new int[this.calculateHeapSize(depth)];
    for (int key : a) {
      boolean isAdded = this.Add(key);
      if (!isAdded) return;
    }
  }

  public int GetMax() {
    // вернуть значение корня и перестроить кучу
    if (size == 0) return -1;
    int max = HeapArray[0];
    size = size - 1;
    HeapArray[0] = HeapArray[size];
    HeapArray[size] = 0;
    this.reorderHeap(0);
    return max;
  }

  public boolean Add(int key) {
    if (size == HeapArray.length) return false;
    HeapArray[size] = key;
    int currentIndex = size;
    while (HeapArray[currentIndex] > HeapArray[getParentIndex(currentIndex)]) {
      swapWithParent(currentIndex, getParentIndex(currentIndex));
      currentIndex = getParentIndex(currentIndex);
    }
    size = size + 1;
    return true;
  }

  private void reorderHeap(int pos) {
    if (isLeaf(pos)) return;

    if (HeapArray[pos] < HeapArray[getLeftChild(pos)]
        || HeapArray[pos] < HeapArray[getRightChild(pos)]) {

      if (HeapArray[getLeftChild(pos)] > HeapArray[getRightChild(pos)]) {
        swapWithParent(pos, getLeftChild(pos));
        reorderHeap(getLeftChild(pos));
      } else {
        swapWithParent(pos, getRightChild(pos));
        reorderHeap(getRightChild(pos));
      }
    }
  }

  private int calculateHeapSize(int depth) {
    return (int) Math.pow(2, depth + 1.) - 1;
  }

  private void swapWithParent(int currentIndex, int parentIndex) {
    int temp = HeapArray[currentIndex];
    HeapArray[currentIndex] = HeapArray[parentIndex];
    HeapArray[parentIndex] = temp;
  }

  private boolean isLeaf(int pos) {
    return pos >= (size / 2) && pos <= size;
  }

  private int getParentIndex(int i) {
    return (i - 1) / 2;
  }

  private int getLeftChild(int parentIndex) {
    return 2 * parentIndex + 1;
  }

  private int getRightChild(int parentIndex) {
    return 2 * parentIndex + 2;
  }
}
