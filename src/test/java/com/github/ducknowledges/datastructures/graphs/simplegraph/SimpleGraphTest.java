package com.github.ducknowledges.datastructures.graphs.simplegraph;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SimpleGraph")
class SimpleGraphTest {

  @Test
  @DisplayName("should create graph with correct size")
  void shouldCreateGraph() {
    int size = 5;
    SimpleGraph graph = new SimpleGraph(size);
    assertThat(graph.m_adjacency)
        .isEqualTo(
            new int[][] {
              // 0  1  2  3  4
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0}
            });
    assertThat(graph.max_vertex).isEqualTo(size);
    assertThat(graph.vertex).hasSize(size);
  }

  @Test
  @DisplayName("should add vertex to graph")
  void shouldAddVertex() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(1);
    assertThat(graph.vertex[0].Value).isEqualTo(1);
    assertThat(graph.vertexCount).isEqualTo(1);
  }

  @Test
  @DisplayName("should not add vertex when graph is full")
  void shouldNotAddVertexWhenGraphIsFull() {
    SimpleGraph graph = new SimpleGraph(2);
    graph.AddVertex(1);
    graph.AddVertex(2);
    graph.AddVertex(3);
    assertThat(graph.vertex).hasSize(2);
    assertThat(graph.vertexCount).isEqualTo(2);
  }

  @Test
  @DisplayName("should add vertex with no edges")
  void shouldAddVertexWithNoEdges() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(1);
    int vertexIndex = graph.vertexCount - 1;

    for (int i = 0; i < graph.vertexCount; i++) {
      if (i != vertexIndex) {
        assertThat(graph.IsEdge(vertexIndex, i)).isFalse();
        assertThat(graph.IsEdge(i, vertexIndex)).isFalse();
      }
    }
  }

  @Test
  @DisplayName("should return False if edge does not exist")
  void shouldReturnFlaseIsEdge() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(1);
    graph.AddVertex(2);
    int vertex1 = 0;
    int vertex2 = 1;

    assertThat(graph.IsEdge(vertex1, vertex2)).isFalse();
    assertThat(graph.IsEdge(vertex2, vertex1)).isFalse();
  }

  @Test
  @DisplayName("should return True if edge exists")
  void shouldReturnTrueIsEdge() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(1);
    graph.AddVertex(2);
    int vertex1 = 0;
    int vertex2 = 1;

    assertThat(graph.IsEdge(vertex1, vertex2)).isFalse();
    assertThat(graph.IsEdge(vertex2, vertex1)).isFalse();

    graph.AddEdge(vertex1, vertex2);

    assertThat(graph.IsEdge(vertex1, vertex2)).isTrue();
    assertThat(graph.IsEdge(vertex2, vertex1)).isTrue();
  }

  @Test
  @DisplayName("should add edge")
  void shouldAddEdge() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(1);
    graph.AddVertex(2);
    int vertex1 = 0;
    int vertex2 = 1;

    assertThat(graph.IsEdge(vertex1, vertex2)).isFalse();
    assertThat(graph.IsEdge(vertex2, vertex1)).isFalse();

    graph.AddEdge(vertex1, vertex2);

    assertThat(graph.IsEdge(vertex1, vertex2)).isTrue();
    assertThat(graph.IsEdge(vertex2, vertex1)).isTrue();
  }

  @Test
  @DisplayName("should remove edge")
  void shouldRemoveEdge() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(1);
    graph.AddVertex(2);
    int vertex1 = 0;
    int vertex2 = 1;

    graph.AddEdge(vertex1, vertex2);

    assertThat(graph.IsEdge(vertex1, vertex2)).isTrue();

    graph.RemoveEdge(vertex1, vertex2);

    assertThat(graph.IsEdge(vertex1, vertex2)).isFalse();
  }

  @Test
  @DisplayName("should remove vertex")
  void shouldRemoveVertex1() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(0);
    graph.AddVertex(1);
    graph.AddVertex(2);
    graph.AddEdge(0, 1);
    graph.AddEdge(0, 2);
    graph.AddEdge(1, 2);

    assertThat(graph.m_adjacency)
        .isEqualTo(
            new int[][] {
              // 0  1  2  3  4
              {0, 1, 1, 0, 0},
              {1, 0, 1, 0, 0},
              {1, 1, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0}
            });

    graph.RemoveVertex(1);

    assertThat(graph.vertex[0].Value).isEqualTo(0);
    assertThat(graph.vertex[1].Value).isEqualTo(2);
    assertThat(graph.vertex[2]).isEqualTo(null);

    assertThat(graph.m_adjacency)
        .isEqualTo(
            new int[][] {
              // 0  1  2  3  4
              {0, 1, 0, 0, 0},
              {1, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0}
            });

    assertThat(graph.IsEdge(0, 1)).isTrue();
    assertThat(graph.vertexCount).isEqualTo(2);
  }

  @Test
  @DisplayName("should remove vertex")
  void shouldRemoveVertex2() {
    SimpleGraph graph = new SimpleGraph(5);
    graph.AddVertex(0);
    graph.AddVertex(1);
    graph.AddVertex(2);
    graph.AddVertex(3);
    graph.AddEdge(0, 1);
    graph.AddEdge(0, 2);
    graph.AddEdge(1, 2);
    graph.AddEdge(2, 3);

    assertThat(graph.m_adjacency)
        .isEqualTo(
            new int[][] {
              // 0  1  2  3  4
              {0, 1, 1, 0, 0},
              {1, 0, 1, 0, 0},
              {1, 1, 0, 1, 0},
              {0, 0, 1, 0, 0},
              {0, 0, 0, 0, 0}
            });

    graph.RemoveVertex(1);

    assertThat(graph.vertex[0].Value).isEqualTo(0);
    assertThat(graph.vertex[1].Value).isEqualTo(2);
    assertThat(graph.vertex[2].Value).isEqualTo(3);
    assertThat(graph.vertex[3]).isNull();

    assertThat(graph.m_adjacency)
        .isEqualTo(
            new int[][] {
              // 0  1  2  3  4
              {0, 1, 0, 0, 0},
              {1, 0, 1, 0, 0},
              {0, 1, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0}
            });

    assertThat(graph.IsEdge(0, 1)).isTrue();
    assertThat(graph.vertexCount).isEqualTo(3);
  }
}
