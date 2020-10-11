package MaxHeap;

import java.util.Random;

/**
 * @Author: ZhengCheng
 * @Date: created in 20:30  2020/3/14
 * @Annotation:
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<E>();
    }



    //heapify:将任意树组整理成堆的形状
    public MaxHeap(E[] arr) {
        data = new Array<E>(arr);
        //从最后一个叶子节点的父节点开始进行siftDown
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    //返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    //返回一个布尔值，表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent.");
        }
        return (index - 1) / 2;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的左节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中，一个索引所表示的元素的右节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    //Sift Up
    //向堆中添加元素(堆中元素的上浮过程)---Sift Up
    public void add(E e) {
        data.addLast(e);
        //输入要上浮元素的索引 也就是数组最后一个元素的索引
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            //看父亲节点的父亲节点是否满足条件 满足则继续交换位置
            k = parent(k);
        }
    }

    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    // 提取最大元素
    public E extractMax() {
        //取走堆中最大元素
        E temp = findMax();
        data.swap(0, data.getSize() - 1);
        //交换元素后删除末尾元素
        data.removeLast();
        siftDown(0);
        //返回最大值
        return temp;
    }

    //    Sift Down 数据下沉
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) >= 0) {
                j = rightChild(k);
                //data[j]是leftChild和rightChild中的最大值
            }

            //父节点的值和孩子节点中最大的值进行比较
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            //交换两个元素
            data.swap(k, j);
            k = j;
        }
    }

    //Replace：取出堆中最大元素 并 替换成新元素e
    public E replace(E e) {
        E temp = findMax();
        data.set(0, e);
        siftDown(0);
        return temp;
    }

    private static double testHeap(Integer[] testData,boolean isHeapify){
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if(isHeapify){
            maxHeap = new MaxHeap<Integer>(testData);
        }else{
            maxHeap = new MaxHeap<Integer>();
            for(int num:testData){
                maxHeap.add(num);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        //判断是否异常
        for (int a = 1; a < testData.length; a++) {
            if (arr[a - 1] < arr[a]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test Maxheap is completed");

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {

        int n = 1000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        //Onlogn
        double time1 = testHeap(testData,false);
        //On
        double time2 = testHeap(testData,true);
        System.out.println("time1:"+time1+"s ,time2:"+time2+"s");

//        System.out.println("maxHeap size:"+maxHeap.size());

//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = maxHeap.extractMax();
//        }
//
//        //判断是否异常
//        for (int a = 1; a < n; a++) {
//            if (arr[a - 1] < arr[a]) {
//                throw new IllegalArgumentException("Error");
//            }
//        }
//        System.out.println("Test Maxheap is completed");
    }


}
