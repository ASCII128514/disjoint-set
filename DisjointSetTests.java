import java.util.Random;

/* Academic Honesty Certification
* Written sources used:
* (Include textbook(s), complete citations for web or other written sources.
* Note that you are not allowed to use the web for this assignment.
* Write "none" if no sources used.)
* ProblemSet5.pdf
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

public class DisjointSetTests {
  static void makeSetTest() {

    // test for integer
    System.out.println("Test for integer makeset");
    DisjointSet<Integer> dsi = new DisjointSet<Integer>();


    System.out.println("Test for integer repetition");
    int[] arr = new int[16];
    for (int i = 0 ; i < 16; i++) {
      Random rand = new Random();
      arr[i] = rand.nextInt(100);
      dsi.makeSet(arr[i]);

      // test for repetition
      if(dsi.makeSet(arr[i])){
        System.out.println("makeSet fail to avoid duplicate values");
      }
    }




    // test for string
    System.out.println("Test for string makeset");
    DisjointSet<String> dss = new DisjointSet<String>();

    String a = "a";

    dss.makeSet(a);

    System.out.println("Test for String repetition");
    if(dss.makeSet(a)){
      System.out.println("makeSet fail to avoid duplicate values");
    }
  }

  static void findSetTest() {
    // test for integer
    DisjointSet<Integer> dsi = new DisjointSet<Integer>();

    for (int i = 0 ; i < 16; i++) {
      dsi.makeSet(i);
    }

    for(int i = 0; i < 15; i+=1) {
      if(!dsi.union(i, i+1)){
        System.out.println("union failed");
      };
    }

    int root = dsi.findSet(0);
    for(int i = 0; i < 16; i+=1) {
      if (root != dsi.findSet(i)) {
        System.out.println(dsi.findSet(i));
        System.out.println("findSet fail to union");
      }
    }
  }

  static void unionTest() {

    // test for union
    DisjointSet<Integer> dsi = new DisjointSet<Integer>();

    for (int i = 0; i < 16; i++) {
      dsi.makeSet(i);
    }

    for (int i = 0; i < 16; i += 2) {
      dsi.union(i, i + 1);
      if (dsi.findSet(i) != dsi.findSet(i + 1)) {
        System.out.println("makeSet fail to union");
      }
    }

    for(int i = 0; i < 16; i+=4) {
      dsi.union(i, i+2);
      if (dsi.findSet(i) != dsi.findSet(i+2)) {
        System.out.println("makeSet fail to union");
      }
    }

  }

  public static void main(String[] args) {

    System.out.println("MakeSet Test");
    makeSetTest();
    System.out.println("MakeSet Test Succeeded");

    System.out.println("FindSet Test");
    findSetTest();
    System.out.println("FindSet Test Succeeded");

    System.out.println("Union Test");
    unionTest();
    System.out.println("Union Test Succeeded");
  }
}
