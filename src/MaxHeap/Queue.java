package MaxHeap;

/**
 * @Author: ZhengCheng
 * @Date: created in 23:52  2020/2/10
 * @Annotation: 复用队列接口 本章用于实现优先队列(基于最大堆)
 */
public interface Queue<E> {
    void enqueue(E e); //进队操作
    E dequeue();  //出队操作
    E getFront();  //获取队首元素
    int getSize();  //获取队列元素总数
    boolean isEmpty();  //判断队列是否为空
}
