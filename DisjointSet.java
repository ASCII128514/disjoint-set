import java.util.HashMap;

class node {
  int data;
  node p;
  int rank;
  node(int data) {
    this.data = data;
    this.p = this;
    this.rank = 0;
  }
}

public class DisjointSet {
  private HashMap<Integer, node> nodes;

  DisjointSet() {
    this.nodes = new HashMap<Integer, node>();
  }

  int findSet(int data) {
    node n = this.nodes.get(data);
    if (n == null) {
      return -1;
    }
    node p = n;

    while (p != p.p) {
      p.p = this.nodes.get(findSet(p.p.data));
    }
    return p.data;
  }

  boolean makeSet(int data) {
    if (this.nodes.containsKey(data)) {
      return false;
    }
    node n = new node(data);
    this.nodes.put(data, n);
    return true;
  }

  boolean union(int data1, int data2) {
    node n1 = this.nodes.get(findSet(data1));
    node n2 = this.nodes.get(findSet(data2));
    if (n1 == null || n2 == null) {
      return false;
    }
    node p1 = n1;
    node p2 = n2;

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
