package com.github.ducknowledges.datastructures.trees.bst;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BTS")
class BSTTest {

  @Test
  @DisplayName("should return bst find with empty tree")
  void shouldReturnBSTFindWithNullNode() {
    BST<Integer> tree = new BST<>(null);

    BSTFind<Integer> findResult = tree.FindNodeByKey(2);

    assertThat(findResult).isNotNull();
    assertThat(findResult.Node).isNull();
    assertThat(findResult.NodeHasKey).isFalse();
    assertThat(findResult.ToLeft).isFalse();
  }

  @Test
  @DisplayName("should return bst find with root node")
  void shouldReturnBSTFindWithRootNode() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> findResult = tree.FindNodeByKey(9);

    assertThat(findResult).isNotNull();
    assertThat(findResult.NodeHasKey).isTrue();
    assertThat(findResult.ToLeft).isFalse();
    assertThat(findResult.Node.NodeKey).isEqualTo(9);
    assertThat(findResult.Node.NodeValue).isEqualTo(9);
    assertThat(findResult.Node.Parent).isNull();
  }

  @Test
  @DisplayName("should return bst find with exist node")
  void shouldReturnBSTFindWithExistNode() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(2);

    assertThat(finder).isNotNull();
    assertThat(finder.NodeHasKey).isTrue();
    assertThat(finder.Node.NodeKey).isEqualTo(2);
    assertThat(finder.Node.NodeValue).isEqualTo(2);
    assertThat(finder.Node.Parent.NodeKey).isEqualTo(3);
    assertThat(finder.Node.Parent.NodeValue).isEqualTo(3);
  }

  @Test
  @DisplayName("should return bst find for insert to right child")
  void shouldReturnBSTFindForRightChild() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(8);

    assertThat(finder).isNotNull();
    assertThat(finder.NodeHasKey).isFalse();
    assertThat(finder.ToLeft).isFalse();
    assertThat(finder.Node.NodeKey).isEqualTo(7);
    assertThat(finder.Node.NodeValue).isEqualTo(7);
  }

  @Test
  @DisplayName("should return bst find for insert to left child")
  void shouldReturnBSTFindForLeftChild() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(1);

    assertThat(finder).isNotNull();
    assertThat(finder.NodeHasKey).isFalse();
    assertThat(finder.ToLeft).isTrue();
    assertThat(finder.Node.NodeKey).isEqualTo(2);
    assertThat(finder.Node.NodeValue).isEqualTo(2);
  }

  @Test
  @DisplayName("should add node to empty tree")
  void shouldAddNodeToEmptyTree() {
    BST<Integer> tree = new BST<>(null);

    BSTFind<Integer> finder = tree.FindNodeByKey(1);
    assertThat(finder.NodeHasKey).isFalse();
    boolean addResult = tree.AddKeyValue(1, 1);
    BSTNode<Integer> addedNode = tree.FindNodeByKey(1).Node;

    assertThat(addResult).isTrue();
    assertThat(addedNode).isNotNull();
    assertThat(addedNode.NodeKey).isEqualTo(1);
    assertThat(addedNode.NodeValue).isEqualTo(1);
    assertThat(addedNode.Parent).isNull();
  }

  @Test
  @DisplayName("should add node to the left child")
  void shouldAddNodeToLeftChild() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(1);
    assertThat(finder.NodeHasKey).isFalse();
    boolean addResult = tree.AddKeyValue(1, 1);
    BSTNode<Integer> addedNode = tree.FindNodeByKey(1).Node;

    assertThat(addResult).isTrue();
    assertThat(addedNode).isNotNull();
    assertThat(addedNode.NodeKey).isEqualTo(1);
    assertThat(addedNode.NodeValue).isEqualTo(1);
    assertThat(addedNode.Parent.NodeKey).isEqualTo(2);
  }

  @Test
  @DisplayName("should add node to the right child")
  void shouldAddNodeToRightChild() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(8);
    assertThat(finder.NodeHasKey).isFalse();
    boolean addResult = tree.AddKeyValue(8, 8);
    BSTNode<Integer> addedNode = tree.FindNodeByKey(8).Node;

    assertThat(addResult).isTrue();
    assertThat(addedNode).isNotNull();
    assertThat(addedNode.NodeKey).isEqualTo(8);
    assertThat(addedNode.NodeValue).isEqualTo(8);
    assertThat(addedNode.Parent.NodeKey).isEqualTo(7);
  }

  @Test
  @DisplayName("should not add node if it is exist")
  void shouldNotAddNodeIfItIsExist() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(7);
    assertThat(finder.NodeHasKey).isTrue();
    boolean addResult = tree.AddKeyValue(7, 7);
    BSTNode<Integer> addedNode = tree.FindNodeByKey(7).Node;

    assertThat(addResult).isFalse();
    assertThat(addedNode).isNotNull();
    assertThat(addedNode.NodeKey).isEqualTo(7);
    assertThat(addedNode.NodeValue).isEqualTo(7);
    assertThat(addedNode.Parent.NodeKey).isEqualTo(3);
  }

  @Test
  @DisplayName("should find max from root node")
  void shouldFindMaxFromRootNode() {
    BST<Integer> tree = this.createTree();

    BSTNode<Integer> maxNode = tree.FinMinMax(tree.Root, true);

    assertThat(maxNode).isNotNull();
    assertThat(maxNode.NodeKey).isEqualTo(12);
    assertThat(maxNode.NodeValue).isEqualTo(12);
  }

  @Test
  @DisplayName("should find min from root node")
  void shouldFindMinFromRootNode() {
    BST<Integer> tree = this.createTree();

    BSTNode<Integer> maxNode = tree.FinMinMax(tree.Root, false);

    assertThat(maxNode).isNotNull();
    assertThat(maxNode.NodeKey).isEqualTo(2);
    assertThat(maxNode.NodeValue).isEqualTo(2);
  }

  @Test
  @DisplayName("should find max from node")
  void shouldFindMaxFromNode() {
    BST<Integer> tree = this.createTree();

    BSTFind<Integer> finder = tree.FindNodeByKey(3);
    BSTNode<Integer> maxNode = tree.FinMinMax(finder.Node, true);

    assertThat(maxNode).isNotNull();
    assertThat(maxNode.NodeKey).isEqualTo(7);
    assertThat(maxNode.NodeValue).isEqualTo(7);
  }

  @Test
  @DisplayName("should find min from node")
  void shouldFindMinFromNode() {
    BST<Integer> tree = this.createTree();

    tree.AddKeyValue(1, 1);
    BSTFind<Integer> finder = tree.FindNodeByKey(3);
    BSTNode<Integer> maxNode = tree.FinMinMax(finder.Node, false);

    assertThat(maxNode).isNotNull();
    assertThat(maxNode.NodeKey).isEqualTo(1);
    assertThat(maxNode.NodeValue).isEqualTo(1);
  }

  @Test
  @DisplayName("should count nodes")
  void shouldCountNodes() {
    BST<Integer> tree = this.createTree();

    assertThat(tree.Count()).isEqualTo(6);
    tree.AddKeyValue(14, 14);
    assertThat(tree.Count()).isEqualTo(7);
  }

  @Test
  @DisplayName("should delete leaf nodes")
  void shouldDeleteLeafNode() {
    BST<Integer> tree = this.createTree();

    boolean deleted1 = tree.DeleteNodeByKey(2);
    boolean deleted2 = tree.DeleteNodeByKey(7);
    boolean deleted3 = tree.DeleteNodeByKey(12);
    boolean deleted4 = tree.DeleteNodeByKey(10);
    boolean deleted5 = tree.DeleteNodeByKey(3);
    boolean deleted6 = tree.DeleteNodeByKey(9);

    assertThat(deleted1).isTrue();
    assertThat(deleted2).isTrue();
    assertThat(deleted3).isTrue();
    assertThat(deleted4).isTrue();
    assertThat(deleted5).isTrue();
    assertThat(deleted6).isTrue();
    BSTFind<Integer> finder = tree.FindNodeByKey(2);
    assertThat(finder.NodeHasKey).isFalse();
  }

  @Test
  @DisplayName("should delete node with one child")
  void shouldDeleteNodeWithOneChild() {
    BST<Integer> tree = this.createTree();

    boolean deleted = tree.DeleteNodeByKey(10);

    assertThat(deleted).isTrue();
    BSTFind<Integer> finder = tree.FindNodeByKey(10);
    assertThat(finder.NodeHasKey).isFalse();
  }

  @Test
  @DisplayName("should delete node with two children")
  void shouldDeleteNodeWithTwoChild() {
    BST<Integer> tree = createTreeWithTwoChildren();

    boolean deleted = tree.DeleteNodeByKey(10);
    assertThat(deleted).isTrue();

    BSTFind<Integer> findResult = tree.FindNodeByKey(10);
    assertThat(findResult.NodeHasKey).isFalse();
  }

  @Test
  @DisplayName("should delete root node")
  void shouldDeleteRootNode() {
    BST<Integer> tree = createTreeWithTwoChildren();

    boolean deleted = tree.DeleteNodeByKey(9);
    assertThat(deleted).isTrue();

    BSTFind<Integer> findResult = tree.FindNodeByKey(9);
    assertThat(findResult.NodeHasKey).isFalse();
  }

  @Test
  @DisplayName("should not delete node if it is not present")
  void shouldNotDeleteNodeIfNotPresent() {
    BST<Integer> tree = createTree();

    boolean deleted = tree.DeleteNodeByKey(15);
    assertThat(deleted).isFalse();
  }

  private BST<Integer> createTree() {
    BSTNode<Integer> root = new BSTNode<>(9, 9, null);
    BSTNode<Integer> rootLeftChild = new BSTNode<>(3, 3, root);
    BSTNode<Integer> rootRightChild = new BSTNode<>(10, 10, root);
    root.LeftChild = rootLeftChild;
    root.RightChild = rootRightChild;
    rootLeftChild.LeftChild = new BSTNode<>(2, 2, rootLeftChild);
    rootLeftChild.RightChild = new BSTNode<>(7, 7, rootLeftChild);
    rootRightChild.RightChild = new BSTNode<>(12, 12, rootRightChild);
    return new BST<>(root);
  }

  private BST<Integer> createTreeWithTwoChildren() {
    BSTNode<Integer> root = new BSTNode<>(9, 9, null);
    BSTNode<Integer> rootLeftChild = new BSTNode<>(3, 3, root);
    BSTNode<Integer> rootRightChild = new BSTNode<>(10, 10, root);
    root.LeftChild = rootLeftChild;
    root.RightChild = rootRightChild;
    rootLeftChild.LeftChild = new BSTNode<>(2, 2, rootLeftChild);
    rootLeftChild.RightChild = new BSTNode<>(7, 7, rootLeftChild);
    rootRightChild.LeftChild = new BSTNode<>(8, 8, rootRightChild);
    rootRightChild.RightChild = new BSTNode<>(12, 12, rootRightChild);
    return new BST<>(root);
  }

  @Test
  @DisplayName("should wide travers empty tree (BFS) and return empty nodes")
  void shouldWideTraversEmptyTree() {
    BST<Integer> tree = new BST<>(null);
    List<BSTNode> nodes = tree.WideAllNodes();
    assertThat(nodes).isEmpty();
  }

  @Test
  @DisplayName("should wide travers single tree (BFS) and return node")
  void shouldWideTraversSingleTree() {
    BST<Integer> tree = new BST<>(new BSTNode<>(1, 1, null));
    List<BSTNode> nodes = tree.WideAllNodes();
    assertThat(nodes).hasSize(1);
    assertThat(nodes.get(0).NodeKey).isEqualTo(1);
  }

  @Test
  @DisplayName("should wide travers tree (BFS) and return all nodes")
  void shouldWideTraversTree() {
    BST<Integer> tree = this.createTraversalTree();
    List<BSTNode> nodes = tree.WideAllNodes();
    assertThat(nodes).hasSize(7);
    for (int i = 0; i < 7; i++) {
      assertThat(nodes.get(i).NodeKey).isEqualTo(i + 1);
    }
  }

  @Test
  @DisplayName("should deep travers tree (DFS) IN-ORDER and return empty nodes")
  void shouldDeepTraversEmptyTreeInOrder() {
    BST<Integer> tree = new BST<>(null);
    int inOrder = 0;
    List<BSTNode> nodes = tree.DeepAllNodes(inOrder);
    assertThat(nodes).isEmpty();
  }

  @Test
  @DisplayName("should deep travers single tree (DFS) IN-ORDER and return node")
  void shouldDeepTraversSingleTreeInOrder() {
    BST<Integer> tree = new BST<>(new BSTNode<>(1, 1, null));
    int inOrder = 0;
    List<BSTNode> nodes = tree.DeepAllNodes(inOrder);
    assertThat(nodes).hasSize(1);
    assertThat(nodes.get(0).NodeKey).isEqualTo(1);
  }

  @Test
  @DisplayName("should deep travers tree (DFS) IN-ORDER and return all nodes")
  void shouldDeepTraversTreeInOrder() {
    BST<Integer> tree = this.createTraversalTree();
    int inOrder = 0;
    List<BSTNode> nodes = tree.DeepAllNodes(inOrder);
    assertThat(nodes).hasSize(7);
    assertThat(nodes.get(0).NodeKey).isEqualTo(4);
    assertThat(nodes.get(1).NodeKey).isEqualTo(2);
    assertThat(nodes.get(2).NodeKey).isEqualTo(5);
    assertThat(nodes.get(3).NodeKey).isEqualTo(7);
    assertThat(nodes.get(4).NodeKey).isEqualTo(1);
    assertThat(nodes.get(5).NodeKey).isEqualTo(3);
    assertThat(nodes.get(6).NodeKey).isEqualTo(6);
  }

  @Test
  @DisplayName("should deep travers tree (DFS) POST-ORDER and return all nodes")
  void shouldDeepTraversTreePostOrder() {
    BST<Integer> tree = this.createTraversalTree();
    int postOrder = 1;
    List<BSTNode> nodes = tree.DeepAllNodes(postOrder);
    assertThat(nodes).hasSize(7);
    assertThat(nodes.get(0).NodeKey).isEqualTo(4);
    assertThat(nodes.get(1).NodeKey).isEqualTo(7);
    assertThat(nodes.get(2).NodeKey).isEqualTo(5);
    assertThat(nodes.get(3).NodeKey).isEqualTo(2);
    assertThat(nodes.get(4).NodeKey).isEqualTo(6);
    assertThat(nodes.get(5).NodeKey).isEqualTo(3);
    assertThat(nodes.get(6).NodeKey).isEqualTo(1);
  }

  @Test
  @DisplayName("should deep travers tree (DFS) PRE-ORDER and return all nodes")
  void shouldDeepTraversTreePreOrder() {
    BST<Integer> tree = this.createTraversalTree();
    int preOrder = 2;
    List<BSTNode> nodes = tree.DeepAllNodes(preOrder);
    assertThat(nodes).hasSize(7);
    assertThat(nodes.get(0).NodeKey).isEqualTo(1);
    assertThat(nodes.get(1).NodeKey).isEqualTo(2);
    assertThat(nodes.get(2).NodeKey).isEqualTo(4);
    assertThat(nodes.get(3).NodeKey).isEqualTo(5);
    assertThat(nodes.get(4).NodeKey).isEqualTo(7);
    assertThat(nodes.get(5).NodeKey).isEqualTo(3);
    assertThat(nodes.get(6).NodeKey).isEqualTo(6);
  }

  private BST<Integer> createTraversalTree() {
    BSTNode<Integer> root = new BSTNode<>(1, 1, null);
    BSTNode<Integer> rootLeftChild = new BSTNode<>(2, 2, root);
    BSTNode<Integer> rootRightChild = new BSTNode<>(3, 3, root);
    root.LeftChild = rootLeftChild;
    root.RightChild = rootRightChild;
    rootLeftChild.LeftChild = new BSTNode<>(4, 4, rootLeftChild);
    rootLeftChild.RightChild = new BSTNode<>(5, 5, rootLeftChild);
    rootRightChild.RightChild = new BSTNode<>(6, 6, rootRightChild);
    rootLeftChild.RightChild.RightChild = new BSTNode<>(7, 7, rootLeftChild.RightChild);
    return new BST<>(root);
  }
}
