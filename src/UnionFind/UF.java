package UnionFind;

/**
 * @Author Alex Zheng
 * @Date 2020/10/13 16:42
 * @Annotation 并查集
 */
public interface UF {

    int getSize();

    boolean isConnected(int p,int q);

    void unionElements(int p,int q);

}
