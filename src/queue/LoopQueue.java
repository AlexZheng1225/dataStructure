package queue;

/**
 * @Author: ZhengCheng
 * @Date: created in 23:23  2020/2/11
 * @Annotation: LoopQueue实现
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    //队列的队首和队尾
    private int front, tail;
    //后期可以尝试去掉size变量
    private int size;

    public LoopQueue(int capacity) {
        //有意识的浪费一个空间
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        //之前有意识的浪费了一个空间
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        //判断队列是否已满，是则扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        //把队列看成是头尾相接的环状结构
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Connot dequeue from an empty queue.");
        }
        E temp = data[front];
        //便于GC回收，防止对象游离
        data[front] = null;
        front++;
        size--;
        //防止复杂度震荡
        if(getCapacity()/4==size && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return temp;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d,capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front;i!=tail;i = (i+1)%data.length){
            res.append(data[i]);
            if((i+1)%data.length!=tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    //测试
    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 11; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
