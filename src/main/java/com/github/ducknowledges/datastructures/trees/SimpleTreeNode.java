package com.github.ducknowledges.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public class SimpleTreeNode<T> {
  public T NodeValue;
  public SimpleTreeNode<T> Parent;
  public List<SimpleTreeNode<T>> Children;
  public int Level;

  public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
    NodeValue = val;
    Parent = parent;
    Children = null;
    Level = 0;
  }
}

class SimpleTree<T> {
  public SimpleTreeNode<T> Root;

  public SimpleTree(SimpleTreeNode<T> root) {
    Root = root;
  }

  public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
    if (Root == null) {
      Root = ParentNode;
    }
    if (ParentNode.Children == null) {
      ParentNode.Children = new ArrayList<>();
    }
    ParentNode.Children.add(NewChild);
    NewChild.Parent = ParentNode;
  }

  public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
    if (NodeToDelete == null) return;
    List<SimpleTreeNode<T>> childrenCopy = new ArrayList<>();
    if (NodeToDelete.Children != null) {
      childrenCopy.addAll(NodeToDelete.Children);
    }
    for (SimpleTreeNode<T> child : childrenCopy) {
      this.DeleteNode(child);
    }
    NodeToDelete.Children = null;
    if (NodeToDelete.Parent != null && NodeToDelete.Parent.Children != null) {
      NodeToDelete.Parent.Children.remove(NodeToDelete);
      NodeToDelete.Parent = null;
    }
  }

  public List<SimpleTreeNode<T>> GetAllNodes() {
    List<SimpleTreeNode<T>> allNodes = new ArrayList<>();
    if (Root != null) {
      this.addNode(Root, allNodes);
    }
    return allNodes;
  }

  private void addNode(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> allNodes) {
    allNodes.add(node);
    if (node.Children != null) {
      for (SimpleTreeNode<T> child : node.Children) {
        this.addNode(child, allNodes);
      }
    }
  }

  public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
    List<SimpleTreeNode<T>> foundedNodes = new ArrayList<>();
    if (Root != null) {
      this.findNodesByValue(Root, val, foundedNodes);
    }
    return foundedNodes;
  }

  private void findNodesByValue(
      SimpleTreeNode<T> node, T val, List<SimpleTreeNode<T>> resultNodes) {
    if (node.NodeValue.equals(val)) {
      resultNodes.add(node);
    }
    if (node.Children != null) {
      for (SimpleTreeNode<T> child : node.Children) {
        this.findNodesByValue(child, val, resultNodes);
      }
    }
  }

  public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
    if (OriginalNode == null || NewParent == null) return;
    if (OriginalNode.Parent != null && OriginalNode.Parent.Children != null) {
      OriginalNode.Parent.Children.remove(OriginalNode);
    }
    AddChild(NewParent, OriginalNode);
  }

  public int Count() {
    return this.countNodes(Root);
  }

  private int countNodes(SimpleTreeNode<T> node) {
    if (node == null) return 0;

    int count = 1;
    if (node.Children != null) {
      for (SimpleTreeNode<T> child : node.Children) {
        count += this.countNodes(child);
      }
    }
    return count;
  }

  public int LeafCount() {
    return this.countLeafNodes(Root);
  }

  private int countLeafNodes(SimpleTreeNode<T> node) {
    if (node == null) return 0;

    if (node.Children == null || node.Children.isEmpty()) {
      return 1;
    }

    int leafCount = 0;
    for (SimpleTreeNode<T> child : node.Children) {
      leafCount += this.countLeafNodes(child);
    }
    return leafCount;
  }

  public void AssignLevels() {
    if (Root != null) {
      this.assignLevels(Root, 0);
    }
  }

  private void assignLevels(SimpleTreeNode<T> node, int level) {
    node.Level = level;

    if (node.Children != null) {
      for (SimpleTreeNode<T> child : node.Children) {
        this.assignLevels(child, level + 1);
      }
    }
  }
}

