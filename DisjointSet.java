class DisjointSet {
    private int[] parent;
    private int[] rank;
    private int[] size;
    private int count;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
        } else {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
            rank[xRoot]++;
        }
        count--;
    }

    public int getCount() {
        return count;
    }

    public int getSize(int x) {
        return size[find(x)];
    }
}
