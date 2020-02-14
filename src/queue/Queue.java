package queue;

/**
 * @Author: ZhengCheng
 * @Date: created in 23:52  2020/2/10
 * @Annotation:队列接口
 */
public interface Queue<E> {
    void enqueue(E e); //进队操作
    E dequeue();  //出队操作
    E getFront();  //获取队首元素
    int getSize();  //获取队列元素总数
    boolean isEmpty();  //判断队列是否为空
}
