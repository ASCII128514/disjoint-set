import java.util.HashMap;

class node<T> {
  T data;
  node<T> p;
  int rank;
  node(T data) {
    this.data = data;
    this.p = this;
    this.rank = 0;
  }
}

public class DisjointSet<T> {
  private HashMap<T, node<T>> nodes;

  DisjointSet() {
    this.nodes = new HashMap<T, node<T>>();
  }

  T findSet(T data) {
    node<T> n = this.nodes.get(data);
    if (n == null) {
      return null;
    }
    node<T> p = n;

    while (p != p.p) {
      p.p = this.nodes.get(findSet(p.p.data));
    }
    return p.data;
  }

  boolean makeSet(T data) {
    if (this.nodes.containsKey(data)) {
      return false;
    }
    node<T> n = new node<T>(data);
    this.nodes.put(data, n);
    return true;
  }

  boolean union(T data1, T data2) {
    node<T> n1 = this.nodes.get(findSet(data1));
    node<T> n2 = this.nodes.get(findSet(data2));
    if (n1 == null || n2 == null) {
      return false;
    }
    node<T> p1 = n1;
    node<T> p2 = n2;

    if (p1 == p2) {
      return false;
    }

    if (p1.rank > p2.rank) {
      p2.p = p1;
    } else if (p1.rank < p2.rank) {
      p1.p = p2;
    } else {
      p1.p = p2;
      p2.rank++;
    }

    return true;
  }
}
