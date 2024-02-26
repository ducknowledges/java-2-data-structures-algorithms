package com.github.ducknowledges.datastructures.trees;

class aBST {

  private static boolean NODE_HAS_KEY = true;
  private static boolean NODE_HAS_NOT_KEY = false;
  private static boolean NEED_ADD_TO_LEFT_CHILD = true;
  private static boolean NEED_ADD_TO_RIGHT_CHILD = false;

  public Integer Tree[];

  public aBST(int depth) {
    int tree_size = (int) Math.pow(2, (double) depth + 1) - 1;
    Tree = new Integer[tree_size];
    for (int i = 0; i < tree_size; i++) Tree[i] = null;
  }

  public Integer FindKeyIndex(int key) {
    if (Tree[0] == null) {
      return null;
    }
    aBSTFind result = this.recursiveFindNode(key, 0);
    if (result.NodeIndex == null) {
      return null;
    }
    if (result.NodeHasKey) {
      return result.NodeIndex;
    } else {
      return -(result.ToLeft ? 2 * result.NodeIndex + 1 : 2 * result.NodeIndex + 2);
    }
  }

  private aBSTFind recursiveFindNode(int key, int nodeIndex) {
    int leftChildIndex = 2 * nodeIndex + 1;
    int rightChildIndex = 2 * nodeIndex + 2;

    if (key == Tree[nodeIndex]) {
      return new aBSTFind(nodeIndex, NODE_HAS_KEY);
    }
    if (key < Tree[nodeIndex]) {
      return this.handleChildNode(key, leftChildIndex, nodeIndex, NEED_ADD_TO_LEFT_CHILD);
    } else {
      return this.handleChildNode(key, rightChildIndex, nodeIndex, NEED_ADD_TO_RIGHT_CHILD);
    }
  }

  private aBSTFind handleChildNode(int key, int childIndex, int nodeIndex, boolean addToLeftChild) {
    if (childIndex >= Tree.length) {
      return new aBSTFind(null, NODE_HAS_NOT_KEY);
    }
    if (Tree[childIndex] != null) {
      return recursiveFindNode(key, childIndex);
    } else {
      return new aBSTFind(nodeIndex, NODE_HAS_NOT_KEY, addToLeftChild);
    }
  }

  public int AddKey(int key) {
    if (Tree[0] == null) {
      Tree[0] = key;
      return 0;
    }
    Integer foundedIndex = this.FindKeyIndex(key);
    if (foundedIndex == null) {
      return -1;
    }
    if (foundedIndex >= 0) {
      return foundedIndex;
    } else {
      Tree[-foundedIndex] = key;
      return -foundedIndex;
    }
  }
}

class aBSTFind {
  public Integer NodeIndex;
  public boolean NodeHasKey;
  public boolean ToLeft;

  public aBSTFind(Integer nodeIndex, boolean nodeHasKey) {
    NodeIndex = nodeIndex;
    NodeHasKey = nodeHasKey;
  }

  public aBSTFind(Integer nodeIndex, boolean nodeHasKey, boolean toLeft) {
    NodeIndex = nodeIndex;
    NodeHasKey = nodeHasKey;
    ToLeft = toLeft;
  }
}
