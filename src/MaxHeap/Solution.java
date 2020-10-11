package MaxHeap;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @Author Alex Zheng
 * @Date 2020/9/29 14:50
 * @Annotation 347.  在1,000,000的元素中选出前100名 在N的元素中选出前M名
 * 使用排序--NlogN  <  使用优先队列--NlogM
 * 解题思路：使用优先队列维护当前看到的M个元素(即每次都把元素和优先队列中的最小元素值进行比较 比最小值大则添加进去)
 * 可使用到最小堆 但是用最大堆也可以 因为大小是相对的 具体看使用的人怎么定义
 */
public class Solution {

    //内部类
    private class Freq implements Comparable<Freq> {
        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            //在这里定义频率越低优先级越高
            if (this.freq < another.freq) {
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        //先将数据存入到map中 记录每个数据出现的频率
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for (int key : map.keySet()) {
            if (pq.getSize() < k) {
                pq.enqueue(new Freq(key, map.get(key)));
                //当获取到的值大于底层最大堆堆顶元素的值时
            } else if (map.get(key) > pq.getFront().freq) {
                pq.dequeue();//出队最小的（优先级最高）
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }

        int[] res = new int[k];
        for (int i=0;i<k;i++){
            res[i]=pq.dequeue().e;
        }
        return res;
    }
}
