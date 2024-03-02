package com.github.ducknowledges.datastructures.trees.balancedbst;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BalancedBST")
class BalancedBSTTest {

  @Test
  @DisplayName("should create a balanced tree from empty array")
  void shouldCreateBalancedTreeEmptyArray() {
    int[] a = {};
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.GenerateTree(a);
    assertThat(balancedBST.Root).isNull();
  }

  @Test
  @DisplayName("should create a balanced tree from one element")
  void shouldCreateBalancedTreeOneElement() {
    int[] a = {3};
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.GenerateTree(a);
    assertThat(balancedBST.Root.NodeKey).isEqualTo(3);
  }

  @Test
  @DisplayName("should create a balanced tree from odd number of elements")
  void shouldCreateBalancedTree() {
    int[] a = {3, 2, 1, 6, 7, 4, 5};
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.GenerateTree(a);
    assertThat(balancedBST.Root.NodeKey).isEqualTo(4);
    assertThat(balancedBST.Root.LeftChild.NodeKey).isEqualTo(2);
    assertThat(balancedBST.Root.RightChild.NodeKey).isEqualTo(6);
    assertThat(balancedBST.Root.LeftChild.LeftChild.NodeKey).isEqualTo(1);
    assertThat(balancedBST.Root.LeftChild.RightChild.NodeKey).isEqualTo(3);
    assertThat(balancedBST.Root.RightChild.LeftChild.NodeKey).isEqualTo(5);
    assertThat(balancedBST.Root.RightChild.RightChild.NodeKey).isEqualTo(7);
  }

  @Test
  @DisplayName("should create a balanced tree from even number of elements")
  void shouldCreateBalancedTreeEven() {
    int[] a = {3, 2, 1, 6, 7, 4};
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.GenerateTree(a);
    assertThat(balancedBST.Root.NodeKey).isEqualTo(3);
    assertThat(balancedBST.Root.LeftChild.NodeKey).isEqualTo(1);
    assertThat(balancedBST.Root.RightChild.NodeKey).isEqualTo(6);
    assertThat(balancedBST.Root.LeftChild.RightChild.NodeKey).isEqualTo(2);
    assertThat(balancedBST.Root.RightChild.RightChild.NodeKey).isEqualTo(7);
  }

  @Test
  @DisplayName("should get TRUE if tree is empty")
  void shouldGetTrueIfTreeIsEmpty() {
    BalancedBST balancedBST = new BalancedBST();
    assertThat(balancedBST.IsBalanced(balancedBST.Root)).isTrue();
  }

  @Test
  @DisplayName("should get TRUE if tree has only one node")
  void shouldGetTrueIfTreeHasOnlyOneNode() {
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.Root = new BSTNode(1, null);
    assertThat(balancedBST.IsBalanced(balancedBST.Root)).isTrue();
  }

  @Test
  @DisplayName("should get TRUE if tree is balanced by generating tree")
  void shouldCreateBalancedTreeFromSortedArray() {
    int[] a = {3, 2, 1, 6, 7, 4};
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.GenerateTree(a);
    assertThat(balancedBST.IsBalanced(balancedBST.Root)).isTrue();
  }

  @Test
  @DisplayName("should get TRUE if tree is balanced")
  void shouldGetTrueIfTreeIsBalanced() {
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.Root = getRootOfBalancedTree();
    assertThat(balancedBST.IsBalanced(balancedBST.Root)).isTrue();
  }

  @Test
  @DisplayName("should get FALSE if tree is balanced")
  void shouldGetFalseIfTreeIsNotBalanced() {
    BalancedBST balancedBST = new BalancedBST();
    balancedBST.Root = getRootOfNotBalancedTree();
    assertThat(balancedBST.IsBalanced(balancedBST.Root)).isFalse();
  }

  private BSTNode getRootOfBalancedTree() {
    BSTNode root = createNode(4, null);

    BSTNode node2 = createNode(2, root);
    BSTNode node6 = createNode(6, root);

    BSTNode node1 = createNode(1, node2);
    BSTNode node3 = createNode(3, node2);
    BSTNode node5 = createNode(5, node6);
    BSTNode node7 = createNode(7, node6);

    root.LeftChild = node2;
    root.RightChild = node6;

    node2.LeftChild = node1;
    node2.RightChild = node3;
    node6.LeftChild = node5;
    node6.RightChild = node7;

    return root;
  }

  private BSTNode getRootOfNotBalancedTree() {
    BSTNode root = createNode(5, null);

    BSTNode node4 = createNode(4, root);
    BSTNode node9 = createNode(9, root);

    BSTNode node3 = createNode(3, node4);
    BSTNode node7 = createNode(7, node9);
    BSTNode node10 = createNode(10, node9);

    BSTNode node2 = createNode(2, node3);
    BSTNode node6 = createNode(6, node7);

    root.LeftChild = node4;
    root.RightChild = node9;

    node4.LeftChild = node3;
    node9.LeftChild = node7;
    node9.RightChild = node10;

    node3.LeftChild = node2;
    node7.LeftChild = node6;

    return root;
  }

  private BSTNode createNode(int key, BSTNode parent) {
    BSTNode node = new BSTNode(key, parent);
    node.Level = parent == null ? 0 : parent.Level + 1;
    return node;
  }
}
