package queue;

/**
 * @Author: ZhengCheng
 * @Date: created in 21:29  2020/2/8
 * @Annotation:数组
 */
public class Array<E> {

    private E[] data; //定义数组
    private int size;  //定义数组容量

    /**
     * 构造参数，传入数组的容量capacity构造Array
     *
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity]; //不能直接使用new E[capacity]，编译不通过，应使用new Object[capacity]后再进行强制转换。
        size = 0;
    }

    /**
     * 无参构造函数，默认数组容量capacity为10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组中元素的个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 往数组末尾添加一个元素
     *
     * @param e
     */
    public void addLast(E e) {
        /*if(size==data.length){
            throw new IllegalArgumentException("Addlast failed. array.array is full");
        }
        data[size] = e;
        size++;*/
        add(size, e);
    }

    /**
     * 在数组头部添加一个元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在数组任意位置添加元素 需传入所要添加的值e及对应的索引index
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Addlast failed. Require index >=0. array.array is full");
        }
        if (size == data.length) {
            //throw new IllegalArgumentException("Addlast failed. array.array is full");
            resize(data.length * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index > size) {
            throw new IllegalArgumentException("Get is faild. Index is illegal.");
        }
        return data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    /**
     * 修改index索引位置的元素为e
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set is faild. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     *
     * @param e
     * @return
     */
    public boolean contanis(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素e的索引，查找到则返回索引值index 否则返回-1
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        E temp = data[index];
        //判断合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get is faild. Index is illegal.");
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; //将该位置的数据设置为空，便于GC自动回收
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return temp;
    }

    /**
     * 删除数组的第一个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除数组的最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e （并不保证删除所有元素e 因为元素e可能不止一个 find函数同理）
     *
     * @param e
     * @return
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("array.array:size = %d,capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 自动扩容/缩容
     *
     * @param newCapicity
     */
    private void resize(int newCapicity) {
        E[] newData = (E[]) new Object[newCapicity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

}