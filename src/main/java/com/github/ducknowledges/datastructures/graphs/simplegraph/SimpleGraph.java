package com.github.ducknowledges.datastructures.graphs.simplegraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Vertex {
  public int Value;
  public boolean visited;

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

  public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
    this.prepareSearchStructures();
    Deque<Vertex> route =
        this.searchRoute(VFrom, vertex[VTo], new ArrayDeque<>(), new ArrayDeque<>());
    return this.convertRouteToList(route);
  }

  private Deque<Vertex> searchRoute(
      int currentVertexIndex, Vertex searchedVertex, Deque<Vertex> stack, Deque<Integer> visited) {
    Vertex currentVertex = vertex[currentVertexIndex];
    if (!currentVertex.visited) {
      stack.push(currentVertex);
      visited.push(currentVertexIndex);
    }
    for (int adjacentVertexIndex = 0;
        adjacentVertexIndex < m_adjacency[currentVertexIndex].length;
        adjacentVertexIndex++) {
      Vertex adjacent = vertex[adjacentVertexIndex];
      if (m_adjacency[currentVertexIndex][adjacentVertexIndex] == 1 && !adjacent.visited) {
        if (adjacent.equals(searchedVertex)) {
          stack.push(adjacent);
          return stack;
        } else {
          currentVertex.visited = true;
          return this.searchRoute(adjacentVertexIndex, searchedVertex, stack, visited);
        }
      }
    }
    stack.pop();
    visited.pop();
    currentVertex.visited = true;
    if (!stack.isEmpty()) {
      int prevIndex = visited.getFirst();
      vertex[prevIndex].visited = true;
      return this.searchRoute(prevIndex, searchedVertex, stack, visited);
    } else {
      return stack;
    }
  }

  private void prepareSearchStructures() {
    for (Vertex v : vertex) {
      v.visited = false;
    }
  }

  private ArrayList<Vertex> convertRouteToList(Deque<Vertex> route) {
    ArrayList<Vertex> list = new ArrayList<>();
    while (!route.isEmpty()) {
      list.add(route.removeLast());
    }
    return list;
  }
}
