package HashTable;

import sun.reflect.generics.tree.Tree;

import java.util.TreeMap;

/**
 * @Author Alex Zheng
 * @Date 2020/10/26 11:39
 * @Annotation
 */
public class HashTable<K, V> {

    //素数表格
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;

    private int capacityIndex = 0;

    //TreeMap数组
    private TreeMap<K, V>[] hashtable;
    //数组容量
    private int M;
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }


    //计算key的hash值去绝对值再mod M
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    //添加操作
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        // hash(key)是计算出来的索引，hashtable[hash(key)].containsKey(key)指的就是
        // 查询到hashtable中TreeMap中是否已经包含了这个key
        if (map.containsKey(key)) {
            //将hash(key)中的TreeMap中的key的值进行修改
            map.put(key, value);
        } else {
            //添加数据
            map.put(key, value);
            size++;
            //不写成size/M >= upperTol，为了避免整型向浮点型转化
            //capacityIndex +1 <capacity.length 防止越界
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }


    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            //不写成size/M < upperTol，为了避免整型向浮点型转化
            //capacityIndex - 1 >= 0 避免越界
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(2 * M);
            }
        }
        return ret;
    }

    //扩容/缩容
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            //对每个TreeMap进行初始化
            newHashTable[i] = new TreeMap<>();
        }
        //将原先hashtable中的元素迁移到新的hashtable中来
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                //此处有个陷阱 hash(key)方法种的M是未扩容之前数组的M 需要注意
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("key doesn't exist!");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

}
