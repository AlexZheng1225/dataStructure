package stack;

/**
 * @Author: ZhengCheng
 * @Date: created in 21:25  2020/2/9
 * @Annotation:
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
