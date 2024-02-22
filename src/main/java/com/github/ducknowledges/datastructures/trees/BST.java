package com.github.ducknowledges.datastructures.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BSTNode<T> {
  public int NodeKey;
  public T NodeValue;
  public BSTNode<T> Parent;
  public BSTNode<T> LeftChild;
  public BSTNode<T> RightChild;

  public BSTNode(int key, T val, BSTNode<T> parent) {
    NodeKey = key;
    NodeValue = val;
    Parent = parent;
    LeftChild = null;
    RightChild = null;
  }
}

class BSTFind<T> {
  public BSTNode<T> Node;
  public boolean NodeHasKey;
  public boolean ToLeft;

  public BSTFind() {
    Node = null;
  }

  public BSTFind(BSTNode<T> node, boolean nodeHasKey) {
    Node = node;
    NodeHasKey = nodeHasKey;
  }

  public BSTFind(BSTNode<T> node, boolean nodeHasKey, boolean toLeft) {
    Node = node;
    NodeHasKey = nodeHasKey;
    ToLeft = toLeft;
  }
}

class BST<T> {

  private static boolean NODE_HAS_KEY = true;
  private static boolean NODE_HAS_NOT_KEY = false;
  private static boolean NEED_ADD_TO_LEFT_CHILD = true;
  private static boolean NEED_ADD_TO_RIGHT_CHILD = false;

  BSTNode<T> Root;

  public BST(BSTNode<T> node) {
    Root = node;
  }

  public BSTFind<T> FindNodeByKey(int key) {
    return this.recursiveFindNode(key, Root);
  }

  private BSTFind<T> recursiveFindNode(int key, BSTNode<T> node) {
    if (node == null) {
      return new BSTFind<>();
    }
    if (key == node.NodeKey) {
      return new BSTFind<>(node, NODE_HAS_KEY);
    }
    if (key < node.NodeKey) {
      if (node.LeftChild != null) {
        return recursiveFindNode(key, node.LeftChild);
      } else {
        return new BSTFind<>(node, NODE_HAS_NOT_KEY, NEED_ADD_TO_LEFT_CHILD);
      }
    } else {
      if (node.RightChild != null) {
        return recursiveFindNode(key, node.RightChild);
      } else {
        return new BSTFind<>(node, NODE_HAS_NOT_KEY, NEED_ADD_TO_RIGHT_CHILD);
      }
    }
  }

  public boolean AddKeyValue(int key, T val) {
    if (Root == null) {
      Root = new BSTNode<>(key, val, null);
      return true;
    }
    BSTFind<T> findResult = this.FindNodeByKey(key);
    if (findResult.NodeHasKey) {
      return false;
    }
    BSTNode<T> newNode = new BSTNode<>(key, val, findResult.Node);
    if (findResult.ToLeft) {
      findResult.Node.LeftChild = newNode;
    } else {
      findResult.Node.RightChild = newNode;
    }
    return true;
  }

  public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
    if (FromNode == null) {
      return null;
    }
    if (FindMax) {
      return recursiveFindMax(FromNode);
    } else {
      return recursiveFindMin(FromNode);
    }
  }

  private BSTNode<T> recursiveFindMin(BSTNode<T> node) {
    if (node.LeftChild != null) {
      return recursiveFindMin(node.LeftChild);
    }
    return node;
  }

  private BSTNode<T> recursiveFindMax(BSTNode<T> node) {
    if (node.RightChild != null) {
      return recursiveFindMax(node.RightChild);
    }
    return node;
  }

  public boolean DeleteNodeByKey(int key) {
    BSTFind<T> resultFind = this.FindNodeByKey(key);
    if (resultFind == null) return false;

    if (!resultFind.NodeHasKey) return false;

    BSTNode<T> node = resultFind.Node;
    int childrenCount = this.getChildrenCount(node);
    if (childrenCount < 2) {
      this.deleteNodeWithOneOrZeroChild(node);
    } else {
      BSTNode<T> minNode = this.recursiveFindMin(node.RightChild);
      node.NodeKey = minNode.NodeKey;
      node.NodeValue = minNode.NodeValue;
      this.deleteNodeWithOneOrZeroChild(minNode);
    }
    return true;
  }

  private void deleteNodeWithOneOrZeroChild(BSTNode<T> nodeToDelete) {
    BSTNode<T> child = this.getChild(nodeToDelete);
    if (nodeToDelete.Parent == null && child == null) {
      Root = null;
      return;
    }
    if (child == null) {
      BSTNode<T> parent = nodeToDelete.Parent;
      if (parent.LeftChild == nodeToDelete) {
        parent.LeftChild = null;
      } else {
        parent.RightChild = null;
      }
    } else {
      this.moveNode(child, nodeToDelete);
    }
  }

  private BSTNode<T> getChild(BSTNode<T> node) {
    return node.LeftChild != null ? node.LeftChild : node.RightChild;
  }

  private void moveNode(BSTNode<T> fromNode, BSTNode<T> toNode) {
    toNode.NodeKey = fromNode.NodeKey;
    toNode.NodeValue = fromNode.NodeValue;
    toNode.LeftChild = fromNode.LeftChild;
    toNode.RightChild = fromNode.RightChild;
  }

  private int getChildrenCount(BSTNode<T> node) {
    int count = 0;
    if (node.LeftChild != null) {
      count++;
    }
    if (node.RightChild != null) {
      count++;
    }
    return count;
  }

  public int Count() {
    if (Root == null) return 0;
    return this.recursiveCount(Root);
  }

  private int recursiveCount(BSTNode<T> node) {
    if (node == null) {
      return 0;
    }
    return 1 + recursiveCount(node.LeftChild) + recursiveCount(node.RightChild);
  }

  public ArrayList<BSTNode> WideAllNodes() {
    ArrayList<BSTNode> result = new ArrayList<>();
    Queue<BSTNode> nodeTraversalQueue = new LinkedList<>();
    if (Root != null) {
      nodeTraversalQueue.add(Root);
      this.breadthTraversalAdd(nodeTraversalQueue, result);
    }
    return result;
  }

  private void breadthTraversalAdd(Queue<BSTNode> nodeTraversalQueue, ArrayList<BSTNode> result) {
    if (nodeTraversalQueue.isEmpty()) {
      return;
    }
    BSTNode node = nodeTraversalQueue.poll();
    result.add(node);
    if (node.LeftChild != null) {
      nodeTraversalQueue.add(node.LeftChild);
    }
    if (node.RightChild != null) {
      nodeTraversalQueue.add(node.RightChild);
    }
    this.breadthTraversalAdd(nodeTraversalQueue, result);
  }

  public ArrayList<BSTNode> DeepAllNodes(int order) {
    ArrayList<BSTNode> result = new ArrayList<>();
    if (Root != null) {
      this.recursiveDeepTraversal(Root, result, order);
    }
    return result;
  }

  private void recursiveDeepTraversal(
      BSTNode<T> currentNode, ArrayList<BSTNode> result, int order) {
    if (currentNode == null) {
      return;
    }
    if (order == 0) {
      this.recursiveDeepTraversal(currentNode.LeftChild, result, order);
      result.add(currentNode);
      this.recursiveDeepTraversal(currentNode.RightChild, result, order);
    }
    if (order == 1) {
      this.recursiveDeepTraversal(currentNode.LeftChild, result, order);
      this.recursiveDeepTraversal(currentNode.RightChild, result, order);
      result.add(currentNode);
    }
    if (order == 2) {
      result.add(currentNode);
      this.recursiveDeepTraversal(currentNode.LeftChild, result, order);
      this.recursiveDeepTraversal(currentNode.RightChild, result, order);
    }
  }
}
