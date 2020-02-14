package queue;

/**
 * @Author: ZhengCheng
 * @Date: created in 23:52  2020/2/10
 * @Annotation:
 */
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
