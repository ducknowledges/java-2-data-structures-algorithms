package com.github.ducknowledges.datastructures.trees.simpletree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SimpleTreeNode")
class SimpleTreeNodeTest {

  @Test
  @DisplayName("should set parent to empty tree and set child node")
  void shouldSetParentToEmptyTreeAndSetChildNode() {
    SimpleTreeNode<Integer> parentNode = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, null);
    SimpleTree<Integer> tree = new SimpleTree<>(null);
    tree.AddChild(parentNode, childNode);

    assertThat(tree.Root).isEqualTo(parentNode);
    assertThat(parentNode.Children).hasSize(1).contains(childNode);
    assertThat(parentNode).isEqualTo(childNode.Parent);
  }

  @Test
  @DisplayName("should add child to root")
  void shouldAddChildToRoot() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, null);
    tree.AddChild(rootNode, childNode);

    assertThat(tree.Root).isEqualTo(rootNode);
    assertThat(rootNode.Children).hasSize(1).contains(childNode);
    assertThat(rootNode).isEqualTo(childNode.Parent);
  }

  @Test
  @DisplayName("should add child to existing node")
  void testAddChildToExistingNode() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, null);
    tree.AddChild(rootNode, childNode);

    SimpleTreeNode<Integer> grandChildNode = new SimpleTreeNode<>(3, null);
    tree.AddChild(childNode, grandChildNode);

    assertThat(rootNode.Children).hasSize(1).contains(childNode);
    assertThat(childNode.Children).hasSize(1).contains(grandChildNode);
    assertThat(childNode).isEqualTo(grandChildNode.Parent);
  }

  @Test
  @DisplayName("should add multiple children to exiting node")
  void testAddMultipleChildren() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> childNode = new SimpleTreeNode<>(2, null);
    tree.AddChild(rootNode, childNode);

    SimpleTreeNode<Integer> grandChildNode1 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> grandChildNode2 = new SimpleTreeNode<>(4, null);
    tree.AddChild(childNode, grandChildNode1);
    tree.AddChild(childNode, grandChildNode2);

    assertThat(rootNode.Children).hasSize(1).contains(childNode);
    assertThat(childNode.Children).hasSize(2).contains(grandChildNode1, grandChildNode2);
    assertThat(childNode).isEqualTo(grandChildNode1.Parent).isEqualTo(grandChildNode2.Parent);
  }

  @Test
  void testDeleteNonRootNode() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    tree.DeleteNode(node3);

    assertThat(node3.Parent).isNull();
    assertThat(node3.Children).isNull();
    assertThat(node5.Parent).isNull();
    assertThat(node5.Children).isNull();
    assertThat(rootNode.Children).hasSize(2).contains(node2, node4);
  }

  @Test
  @DisplayName("should get empty list of all nodes with empty tree")
  void shouldGetAllNodesWithEmptyTree() {
    SimpleTree<Integer> tree = new SimpleTree<>(null);

    List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();

    assertThat(allNodes).isEmpty();
  }

  @Test
  @DisplayName("should get all nodes from single node tree")
  void shouldGetAllNodesFromSingleNodeTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();

    assertThat(allNodes).hasSize(1).contains(rootNode);
  }

  @Test
  @DisplayName("should get all nodes from multiple nodes tree")
  void shouldGetAllNodesFromMultipleNodesTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);

    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();

    assertThat(allNodes).hasSize(5).contains(rootNode, node2, node3, node4, node5);
  }

  @Test
  @DisplayName("should get empty list of nodes with value from empty tree")
  void shouldNotFindNodesByValueWithEmptyTree() {
    SimpleTree<Integer> tree = new SimpleTree<>(null);

    List<SimpleTreeNode<Integer>> resultNodes = tree.FindNodesByValue(1);

    assertThat(resultNodes).isEmpty();
  }

  @Test
  @DisplayName("should find nodes by value from single node tree")
  void shouldFindNodesByValueFromSingleNodeTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    List<SimpleTreeNode<Integer>> resultNodes = tree.FindNodesByValue(1);

    assertThat(resultNodes).hasSize(1).contains(rootNode);
  }

  @Test
  @DisplayName("should not find nodes by value from single node tree")
  void shouldNotFindNodesByValueFromSingleNodeTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    List<SimpleTreeNode<Integer>> resultNodes = tree.FindNodesByValue(2);

    assertThat(resultNodes).isEmpty();
  }

  @Test
  @DisplayName("should find nodes by value from multiple nodes tree")
  void shouldFindNodesByValueFromMultipleNodesTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(1, null);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    List<SimpleTreeNode<Integer>> foundedNodes = tree.FindNodesByValue(1);

    assertThat(foundedNodes).hasSize(3).contains(rootNode, node3, node5);
  }

  @Test
  @DisplayName("should not find nodes by value from multiple nodes tree")
  void shouldNotFindNodesByValueWithMultipleNodes() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(1, null);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    List<SimpleTreeNode<Integer>> foundedNodes = tree.FindNodesByValue(5);

    assertThat(foundedNodes).isEmpty();
  }

  @Test
  void testMoveNode() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node4, node5);
    tree.AddChild(node4, node6);

    tree.MoveNode(node4, node2);

    assertThat(node2.Children).hasSize(1).contains(node4);
    assertThat(node4.Parent).isEqualTo(node2);
    assertThat(node4.Children).hasSize(2).contains(node5, node6);
    assertThat(node4).isEqualTo(node5.Parent).isEqualTo(node6.Parent);
    assertThat(rootNode.Children).hasSize(2).contains(node2, node3);
  }

  @Test
  @DisplayName("should count nodes in empty tree")
  void shouldCountNodeInEmptyTree() {
    SimpleTree<Integer> tree = new SimpleTree<>(null);

    assertThat(tree.Count()).isZero();
  }

  @Test
  @DisplayName("should count nodes in single node tree")
  void shouldCountNodesInSingleNodeTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    assertThat(tree.Count()).isEqualTo(1);
  }

  @Test
  @DisplayName("should count nodes in multiple nodes tree")
  void shouldCountNodesInMultipleNodesTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);

    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    assertThat(tree.Count()).isEqualTo(5);
  }

  @Test
  @DisplayName("should count leaf nodes in empty tree")
  void shouldLeafCountNodesInEmptyTree() {
    SimpleTree<Integer> tree = new SimpleTree<>(null);

    assertThat(tree.LeafCount()).isZero();
  }

  @Test
  @DisplayName("should count leaf nodes in single node tree")
  void shouldLeafCountNodesInSingleNodeTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    assertThat(tree.LeafCount()).isEqualTo(1);
  }

  @Test
  void testLeafCountWithMultipleNodes() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);

    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    assertThat(tree.LeafCount()).isEqualTo(3);
  }

  @Test
  @DisplayName("should assign levels to empty tree")
  void shouldAssignLevelsInEmptyTree() {
    SimpleTree<Integer> tree = new SimpleTree<>(null);

    tree.AssignLevels();

    assertThat(tree.Root).isNull();
  }

  @Test
  @DisplayName("should assign levels in single node tree")
  void shouldAssignLevelsInSingleNodeTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);

    tree.AssignLevels();
    assertThat(rootNode.Level).isZero();
  }

  @Test
  @DisplayName("should assign levels in multiple nodes tree")
  void shouldAssignLevelsInMultipleNodesTree() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node4);
    tree.AddChild(node3, node5);

    tree.AssignLevels();

    assertThat(rootNode.Level).isZero();
    assertThat(node2.Level).isEqualTo(1);
    assertThat(node3.Level).isEqualTo(1);
    assertThat(node4.Level).isEqualTo(1);
    assertThat(node5.Level).isEqualTo(2);
  }

  @Test
  @DisplayName("should get edge node values of even trees")
  void shouldGetEdgeNodeValues1() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);

    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node6);
    tree.AddChild(node2, node5);
    tree.AddChild(node2, node7);
    tree.AddChild(node3, node4);
    tree.AddChild(node6, node8);
    tree.AddChild(node8, node9);
    tree.AddChild(node8, node10);

    List<Integer> actualEdges = tree.EvenTrees();

    assertThat(actualEdges).containsExactly(1, 3, 1, 6);
  }

  @Test
  @DisplayName("should get edge node values of even trees")
  void shouldGetEdgeNodeValues2() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);
    SimpleTreeNode<Integer> node11 = new SimpleTreeNode<>(11, null);
    SimpleTreeNode<Integer> node12 = new SimpleTreeNode<>(12, null);

    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    tree.AddChild(rootNode, node2);
    tree.AddChild(rootNode, node3);
    tree.AddChild(rootNode, node6);
    tree.AddChild(node2, node5);
    tree.AddChild(node2, node7);
    tree.AddChild(node3, node4);
    tree.AddChild(node6, node8);
    tree.AddChild(node5, node11);
    tree.AddChild(node7, node12);
    tree.AddChild(node8, node9);
    tree.AddChild(node8, node10);

    List<Integer> actualEdges = tree.EvenTrees();

    assertThat(actualEdges).containsExactly(1, 3, 1, 6, 2, 5, 2, 7);
  }

  @Test
  @DisplayName("should get edge node values of even trees")
  void shouldGetEdgeNodeValues3() {
    SimpleTreeNode<Integer> rootNode = new SimpleTreeNode<>(1, null);
    SimpleTreeNode<Integer> node2 = new SimpleTreeNode<>(2, null);
    SimpleTreeNode<Integer> node3 = new SimpleTreeNode<>(3, null);
    SimpleTreeNode<Integer> node4 = new SimpleTreeNode<>(4, null);
    SimpleTreeNode<Integer> node5 = new SimpleTreeNode<>(5, null);
    SimpleTreeNode<Integer> node6 = new SimpleTreeNode<>(6, null);
    SimpleTreeNode<Integer> node7 = new SimpleTreeNode<>(7, null);
    SimpleTreeNode<Integer> node8 = new SimpleTreeNode<>(8, null);
    SimpleTreeNode<Integer> node9 = new SimpleTreeNode<>(9, null);
    SimpleTreeNode<Integer> node10 = new SimpleTreeNode<>(10, null);

    SimpleTree<Integer> tree = new SimpleTree<>(rootNode);
    tree.AddChild(rootNode, node2);
    tree.AddChild(node2, node3);
    tree.AddChild(node3, node4);
    tree.AddChild(node4, node5);
    tree.AddChild(node5, node6);
    tree.AddChild(node6, node7);
    tree.AddChild(node7, node8);
    tree.AddChild(node8, node9);
    tree.AddChild(node9, node10);

    List<Integer> actualEdges = tree.EvenTrees();

    assertThat(actualEdges).containsExactly(2, 3, 4, 5, 6, 7, 8, 9);
  }
}
