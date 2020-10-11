package Set;

/**
 * @Author: ZhengCheng
 * @Date: created in 15:21  2020/2/29
 * @Annotation: 不能盛放重复元素（可用二分搜索树实现） 这里就是基于二分搜索树实现
 */
public interface Set<E> {

    void add(E e); //添加元素(不能添加重复元素)
    void remove(E e); //删除元素
    boolean contains(E e);  //查询是否包含元素
    int getSize();  //获取元素数量
    boolean isEmpty();  //集合是否为空


}
