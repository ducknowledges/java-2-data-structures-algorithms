package com.github.ducknowledges.datastructures.graphs.simplegraph;

class Vertex {
  public int Value;

  public Vertex(int val) {
    Value = val;
  }
}

class SimpleGraph {
  Vertex[] vertex;
  int[][] m_adjacency;
  int max_vertex;
  int vertexCount;

  public SimpleGraph(int size) {
    max_vertex = size;
    m_adjacency = new int[size][size];
    vertex = new Vertex[size];
    vertexCount = 0;
  }

  public void AddVertex(int value) {
    if (vertexCount < max_vertex) {
      vertex[vertexCount] = new Vertex(value);
      vertexCount++;
    }
  }

  public void RemoveVertex(int v) {
    if (v >= 0 && v < vertexCount) {
      // Удаление вершины из массива вершин
      for (int i = v; i < vertexCount - 1; i++) {
        vertex[i] = vertex[i + 1];
      }
      vertex[vertexCount - 1] = null;

      // Удаление связей удаляемой вершины с другими вершинами
      for (int i = 0; i < vertexCount; i++) {
        RemoveEdge(i, v);
        RemoveEdge(v, i);
      }

      // Смещение связей в матрице смежности
      for (int i = v; i < vertexCount - 1; i++) {
        for (int j = 0; j < vertexCount; j++) {
          m_adjacency[i][j] = m_adjacency[i + 1][j];
        }
      }

      for (int i = 0; i < vertexCount; i++) {
        for (int j = v; j < vertexCount - 1; j++) {
          m_adjacency[i][j] = m_adjacency[i][j + 1];
        }
      }

      for (int i = 0; i < vertexCount - 1; i++) {
        RemoveEdge(i, vertexCount - 1);
        RemoveEdge(vertexCount - 1, i);
      }

      // Уменьшение счетчика вершин
      vertexCount--;
    }
  }

  public boolean IsEdge(int v1, int v2) {
    if (v1 >= 0 && v1 < vertexCount && v2 >= 0 && v2 < vertexCount) {
      return m_adjacency[v1][v2] == 1;
    }
    return false;
  }

  public void AddEdge(int v1, int v2) {
    if (v1 >= 0 && v1 < vertexCount && v2 >= 0 && v2 < vertexCount) {
      m_adjacency[v1][v2] = 1;
      m_adjacency[v2][v1] = 1;
    }
  }

  public void RemoveEdge(int v1, int v2) {
    if (v1 >= 0 && v1 < vertexCount && v2 >= 0 && v2 < vertexCount) {
      m_adjacency[v1][v2] = 0;
      m_adjacency[v2][v1] = 0;
    }
  }
}
