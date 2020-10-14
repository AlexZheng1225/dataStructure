package UnionFind;

/**
 * @Author Alex Zheng
 * @Date 2020/10/13 18:04
 * @Annotation 第三版并查集 基于size优化 尽量缩短树的深度
 */
public class UnionFind3 implements UF {

    private int[] parent;
    //sz[i]表示以i为根的集合中元素个数
    private int[] sz;

    //每个节点都指向他自己(每个节点都独立是一颗树)
    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0;i<size;i++){
            parent[i] = i;
            sz[i]=1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //找根节点
    private int find(int p){
        if (p<0&&p>=parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        //p==parent[t]时，则p为根节点
        while(p!=parent[p]){
            p = parent[p];
        }
        return p;
    }

    //查看元素p和元素q是否所属一个集合
    //时间复杂度为O(h) h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    //合并元素p和元素q所属的集合
    //时间复杂度为O(h) h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int qRoot = find(q);
        int pRoot = find(p);
        if (pRoot==qRoot){
            //则证明这两个元素本身同属一个集合
            return;
        }
        //元素个数比较少的根节点指向元素个数比较多的根节点
        //这样做比较大概率最终树的深度不会发生太大改变
        if (sz[pRoot]<sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
