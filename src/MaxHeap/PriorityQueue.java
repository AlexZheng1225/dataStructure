package MaxHeap;

/**
 * @Author Alex Zheng
 * @Date 2020/9/29 14:42
 * @Annotation 优先队列可以使用最大/小堆进行实现
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<E>();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        //不用进行校验 extractMax()方法在一开始调用findMax()的时候就进行了校验
        //拿出最大值
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        //最大堆中 指堆顶的元素
        return maxHeap.findMax();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
