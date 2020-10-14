package UnionFind;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @Author Alex Zheng
 * @Date 2020/10/13 18:04
 * @Annotation 第二版并查集（并查集：奇怪的数，由孩子指向父亲）
 * 最极端情况下这个会变成链表
 */
public class UnionFind2 implements UF {

    private int[] parent;

    //每个节点都指向他自己(每个节点都独立是一颗树)
    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0;i<size;i++){
            parent[i] = i;
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
        parent[pRoot] = qRoot;
    }
}
