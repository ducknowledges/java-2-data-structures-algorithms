package com.github.ducknowledges.datastructures.trees.arraybst;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("aBST")
class aBSTTest {
  @Test
  @DisplayName("should create tree with correct size")
  void shouldCreateTree() {
    int depth = 3;
    int actualSize = 15;

    aBST tree = new aBST(depth);

    assertThat(tree.Tree).hasSize(actualSize);
  }

  @Test
  @DisplayName("should add key to empty tree")
  void shouldAddKeyToEmptyTree() {
    int key = 1;
    int expectedIndex = 0;

    aBST tree = new aBST(3);
    int actualIndex = tree.AddKey(key);

    assertThat(actualIndex).isEqualTo(expectedIndex);
    assertThat(tree.Tree[expectedIndex]).isEqualTo(key);
  }

  @Test
  @DisplayName("should not add key to non-empty tree")
  void shouldNotAddKeyToNonEmptyTree1() {
    int key = 1;
    int expectedIndex = -1;

    aBST tree = create2DepthTree();
    tree.AddKey(key);
    int actualIndex = tree.AddKey(key);

    assertThat(actualIndex).isEqualTo(expectedIndex);
  }

  @Test
  @DisplayName("should not add key to non-empty tree")
  void shouldNotAddKeyToNonEmptyTree2() {
    int key = 13;
    int expectedIndex = -1;

    aBST tree = create2DepthTree();
    tree.AddKey(key);
    int actualIndex = tree.AddKey(key);

    assertThat(actualIndex).isEqualTo(expectedIndex);
  }

  @Test
  @DisplayName("should add key to non-empty tree")
  void shouldAddKeyToNonEmptyTree() {
    int key = 10;
    int expectedIndex = 5;

    aBST tree = create2DepthTree();
    int actualIndex = tree.AddKey(key);

    assertThat(actualIndex).isEqualTo(expectedIndex);
    assertThat(tree.Tree).isEqualTo(new Integer[] {9, 3, 11, 2, 7, 10, 12});
  }

  @Test
  @DisplayName("should return existed index of key in non-empty tree")
  void shouldReturnExistedIndex() {
    int key1 = 7;
    int expectedIndex1 = 4;
    int key2 = 12;
    int expectedIndex2 = 6;

    aBST tree = create2DepthTree();
    int actualIndex1 = tree.AddKey(key1);
    int actualIndex2 = tree.AddKey(key2);

    assertThat(actualIndex1).isEqualTo(expectedIndex1);
    assertThat(actualIndex2).isEqualTo(expectedIndex2);
    assertThat(tree.Tree).isEqualTo(new Integer[] {9, 3, 11, 2, 7, null, 12});
  }

  @Test
  @DisplayName("should add keys to empty tree")
  void shouldAddKeysToEmptyTree() {
    aBST tree = new aBST(2);
    tree.AddKey(9);
    tree.AddKey(3);
    tree.AddKey(11);
    tree.AddKey(12);
    tree.AddKey(7);
    tree.AddKey(2);

    assertThat(tree.Tree).isEqualTo(new Integer[] {9, 3, 11, 2, 7, null, 12});
  }

  @Test
  @DisplayName("should not find key in empty tree")
  void shouldNotFindKeyInEmptyTree() {
    int key = 1;

    aBST tree = this.create2DepthTree();
    Integer actualIndex = tree.FindKeyIndex(key);

    assertThat(actualIndex).isNull();
  }

  @Test
  @DisplayName("should find key in non-empty tree")
  void shouldFindKeyInNonEmptyTree() {
    int key = 7;
    int expectedIndex = 4;

    aBST tree = this.create2DepthTree();
    Integer actualIndex = tree.FindKeyIndex(key);

    assertThat(actualIndex).isEqualTo(expectedIndex);
    assertThat(tree.Tree).isEqualTo(new Integer[] {9, 3, 11, 2, 7, null, 12});
  }

  @Test
  @DisplayName("should not find key in non-empty tree")
  void shouldNotFindKeyInNonEmptyTree() {
    int key = 8;

    aBST tree = this.create2DepthTree();
    Integer actualIndex = tree.FindKeyIndex(key);

    assertThat(actualIndex).isNull();
    assertThat(tree.Tree).isEqualTo(new Integer[] {9, 3, 11, 2, 7, null, 12});
  }

  @Test
  @DisplayName("should return negative index of empty slot of tree")
  void shouldFindNegativeIndexOfEmptyTreeSlot1() {
    int key = 10;
    int expectedIndex = -5;

    aBST tree = this.create2DepthTree();
    Integer actualIndex = tree.FindKeyIndex(key);

    assertThat(actualIndex).isEqualTo(expectedIndex);
    assertThat(tree.Tree).isEqualTo(new Integer[] {9, 3, 11, 2, 7, null, 12});
  }

  @Test
  @DisplayName("should return negative index of empty slot of tree")
  void shouldFindNegativeIndexOfEmptyTreeSlot2() {
    int key1 = 1;
    int expectedIndex1 = -7;
    int key2 = 13;
    int expectedIndex2 = -14;

    aBST tree = this.create3DepthTree();
    Integer actualIndex1 = tree.FindKeyIndex(key1);
    Integer actualIndex2 = tree.FindKeyIndex(key2);

    assertThat(actualIndex1).isEqualTo(expectedIndex1);
    assertThat(actualIndex2).isEqualTo(expectedIndex2);
    assertThat(tree.Tree)
        .isEqualTo(
            new Integer[] {
              9, 3, 11, 2, 7, null, 12, null, null, null, null, null, null, null, null
            });
  }

  private aBST create2DepthTree() {
    Integer[] tree = new Integer[] {9, 3, 11, 2, 7, null, 12};
    aBST aBST = new aBST(2);
    aBST.Tree = tree;
    return aBST;
  }

  private aBST create3DepthTree() {
    Integer[] tree =
        new Integer[] {9, 3, 11, 2, 7, null, 12, null, null, null, null, null, null, null, null};
    aBST aBST = new aBST(3);
    aBST.Tree = tree;
    return aBST;
  }
}
