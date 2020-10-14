package UnionFind;

/**
 * @Author Alex Zheng
 * @Date 2020/10/13 18:04
 * @Annotation 第四版并查集 基于rank优化 让深度低的树向深度高的树合并
 * 通常实现并查集是使用rank这种优化的
 */
public class UnionFind4 implements UF {

    private int[] parent;
    //rank[i]表示以i为根的集合所表示的树的层数
    private int[] rank;

    //每个节点都指向他自己(每个节点都独立是一颗树)
    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //找根节点
    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        //p==parent[t]时，则p为根节点
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    //查看元素p和元素q是否所属一个集合
    //时间复杂度为O(h) h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素p和元素q所属的集合
    //时间复杂度为O(h) h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int qRoot = find(q);
        int pRoot = find(p);
        if (pRoot == qRoot) {
            //则证明这两个元素本身同属一个集合
            return;
        }
        //根据两个元素所在书的rank不同判断合并方向
        //将rank低的集合合并到rank高的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else { //rank[qRoot] == rank[pRoot]
            parent[qRoot] = pRoot; //谁指向谁不重要
            rank[pRoot] += 1;
        }

    }
}
