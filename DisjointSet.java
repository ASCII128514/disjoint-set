/* Academic Honesty Certification
* Written sources used:
* (Include textbook(s), complete citations for web or other written sources.
* Note that you are not allowed to use the web for this assignment.
* Write "none" if no sources used.)
* Textbook
*
*
* Help obtained:
* (Include names of anyone other than the instructor.)
* NA
*
*
* My written or typed signature below confirms that the above list
* of sources is complete.
* Signature: Garry Gao, Paul Lu
*/

import java.util.HashMap;


/*
node class. The basic unit of our disjointSet. 
Contains data, parent node p, and a integer rank. 
*/
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

/*
DisjointSet class. Uses hashmap to store nodes. 
*/
public class DisjointSet<T> {
  private HashMap<T, node<T>> nodes;

  /*
  DisjointSet() - initialization
  Does not take parameter, does not have return value
  Creats a HashMap to store nodes. 
  */
  DisjointSet() {
    this.nodes = new HashMap<T, node<T>>();
  }

  /*
  findSet(T data) -> T p.data
  returns a pointer to the representative of the set containning data.
  */
  T findSet(T data) {
    node<T> n = this.nodes.get(data);
    // if data not in set, return null
    if (n == null) {
      return null;
    }
    node<T> p = n;
    // recursion to find the representative
    while (p != p.p) {
      //path compression so that each node on the find path point to the root.
      p.p = this.nodes.get(findSet(p.p.data));
    }
    return p.data;
  }

  /*
  makeSet(T data) -> boolean
  creates a new set whose only member is data and return true if data is not already in the set,
  otherwise return false and do nothing else.
  */
  boolean makeSet(T data) {
    // if already contains this data, return false
    if (this.nodes.containsKey(data)) {
      return false;
    }
    // otherwise create a node to store the data
    node<T> n = new node<T>(data);
    this.nodes.put(data, n);
    return true;
  }

  /*
  union(T data1, T data2) -> boolean
  it takes two parameters. If the nodes containing these two parameters are in the same set, returns false.,
  if not, then the unique set with a lower rank will be unioned to the set with a high rank.
  */
  boolean union(T data1, T data2) {
    //find the representative of two parameters
    node<T> n1 = this.nodes.get(findSet(data1));
    node<T> n2 = this.nodes.get(findSet(data2));

    // if data1 or data2 is not found in the set, function is invalid
    if (n1 == null || n2 == null) {
      return false;
    }
    node<T> p1 = n1;
    node<T> p2 = n2;

    // if two representatives are the same then already in the same set, return false
    if (p1 == p2) {
      return false;
    }

    // otherwise put lower rank set into the higher rank set. 
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
