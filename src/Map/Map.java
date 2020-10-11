package Map;

/**
 * @Author: ZhengCheng
 * @Date: created in 21:47  2020/2/29
 * @Annotation:
 */
public interface Map<K,V> {

    void add(K key,V value); //添加元素
    V remove(K key);    //移除元素
    boolean contains(K key);    //查询映射中是否包含元素
    V get(K key);   //获取元素
    void set(K key,V value);    //设置映射中的元素的值
    int getSize();  //获取映射中键值对的数量
    boolean isEmpty(); //查询映射是否为空
}
