package MaxHeap;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @Author Alex Zheng
 * @Date 2020/9/30 10:35
 * @Annotation 347号问题 用Java自带PriorityQueue进行解决
 */
public class Solution2 {
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
                return -1;
            } else if (this.freq > another.freq) {
                return 1;
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

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
                //当获取到的值大于底层最大堆堆顶元素的值时
            } else if (map.get(key) > pq.peek().freq) {
                pq.remove();//出队最小的（优先级最高）
                pq.add(new Freq(key, map.get(key)));
            }
        }

        int[] res = new int[k];
        for (int i=0;i<k;i++){
            res[i]=pq.remove().e;
        }
        return res;
    }
}
