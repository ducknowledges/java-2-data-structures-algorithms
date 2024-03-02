package com.github.ducknowledges.datastructures.trees.balancedbst;

import java.util.Arrays;

class BSTNode {
  public int NodeKey;
  public BSTNode Parent;
  public BSTNode LeftChild;
  public BSTNode RightChild;
  public int Level;

  public BSTNode(int key, BSTNode parent) {
    NodeKey = key;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}

class BalancedBST {
  public BSTNode Root;

  public BalancedBST() {
    Root = null;
  }

  public void GenerateTree(int[] a) {
    if (a.length == 0) {
      return;
    }
    int[] sortedArray = Arrays.stream(a).sorted().toArray();
    Root = generateBalancedTree(sortedArray, 0, sortedArray.length - 1, null);
  }

  private BSTNode generateBalancedTree(int[] sortedArray, int start, int end, BSTNode parent) {
    if (start > end) {
      return null;
    }
    int middleIndex = (start + end) / 2;
    BSTNode node = new BSTNode(sortedArray[middleIndex], parent);
    node.Level = parent == null ? 0 : node.Parent.Level + 1;
    node.LeftChild = generateBalancedTree(sortedArray, start, middleIndex - 1, node);
    node.RightChild = generateBalancedTree(sortedArray, middleIndex + 1, end, node);
    return node;
  }

  public boolean IsBalanced(BSTNode root_node) {
    if (root_node == null) {
      return true;
    }
    int leftHeight = getNodeHeight(root_node.LeftChild);
    int rightHeight = getNodeHeight(root_node.RightChild);
    if (Math.abs(leftHeight - rightHeight) > 1) {
      return false;
    }
    if (!IsBalanced(root_node.LeftChild)) {
      return false;
    }
    return IsBalanced(root_node.RightChild);
  }

  private int getNodeHeight(BSTNode node) {
    if (node == null) {
      return 0;
    }
    int leftHeight = getNodeHeight(node.LeftChild);
    int rightHeight = getNodeHeight(node.RightChild);
    return Math.max(leftHeight, rightHeight) + 1;
  }
}
