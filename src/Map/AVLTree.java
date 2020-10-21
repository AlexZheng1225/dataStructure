package Map;

import Set.FileOperation;

import java.util.ArrayList;

/**
 * @Author: ZhengCheng
 * @Date: created in 14:56  2020/3/1
 * @Annotation: AVLTree 相比BST实现的Map 要多添加一个自平衡的机制
 */
//必须是可以比较的
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    class Node {
        public K key;
        public V value;
        public Node left, right;
        //高度值
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            //从根节点出发，去找到待添加的位置，位置肯定是叶子节点的位置
            height = 1;
        }
    }

    private int size;
    private Node root;

    public AVLTree() {
        root = null;
        size = 0;
    }

    //判断该二叉树是否是平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            //对于空来说 并无打破平衡二叉树的性质
            return true;
        }
        //获取该node的平衡因子
        int balanceFactor = getBalanceFactor(node);
        //求绝对值
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        //看node的左子树和右子树是不是都是平衡二叉树
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //判断该树是否为二叉树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        //中序遍历
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            //看数字是不是升序数组
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //获取节点node的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获得node节点的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { //key==node.key
            //当添加的key映射中已经有时
            node.value = value;
        }
        //更新node.height(节点高度)
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //若平衡因子大于1 abs是绝对值
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced: " + balanceFactor);
//        }

        //平衡维护--维护后满足二分搜素树和平衡二叉树的性质
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //使用右旋转
            return rightRotate(node);
        }

        //右子树比左子树高 整棵树向右倾斜
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //左右旋转后，即是平衡二叉树也是二分搜索树
    //对节点进行向左旋转操作，返回旋转后新的根节点
    //LL
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;

        //向左旋转过程
        x.left = y;
        y.right = T3;

        //完成做旋转之后要更新节点的Height值(只需要更新x和y节点的高度值，更新先更新y的高度值)  看图
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //左右旋转后，即是平衡二叉树也是二分搜索树
    //对节点进行向右旋转操作，返回旋转后新的根节点
    //RR
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //向右旋转过程
        x.right = y;
        y.left = T3;

        //完成右旋转之后要更新节点的Height值(只需要更新x和y节点的高度值，更新先更新y的高度值)  看图
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }
        root = remove(root, key);
        return node.value;
    }

    //已经平衡维护
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        //临时存储
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else { //key==node.key

            //下面三种情况是互斥的
            //待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) { //待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                //待删除节点左右子树都不为空
                //找到比待删除节点要大的最小的节点，即待删除右子树最小的节点
                //用此节点顶替待删除结点
                Node successor = minimum(node.right);  //找到最小值
                //removeMin两种解决方法，一种是在removeMin中添加相应的维护平衡的代码
                //或者 递归调用remove
                //successor.right = removeMin(node.right);  //将右子树的最小值删除并返回删除后的结果
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                //在此处不用维护size，因为在调用removeMin方法的时候已经对size进行了处理
                node.left = node.right = null;
                retNode = successor;
            }
        }

        //可能删除retNode之后变为空(叶子节点会遇到这种情况) 防止空指针异常
        //如果retNode为null
        if (retNode == null) {
            return null;
        }
        //更新height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            //使用右旋转
            return rightRotate(retNode);
        }

        //右子树比左子树高 整棵树向右倾斜
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

    }

    //返回node为根的二分搜索树的最小值所在的结点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除结点后二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);  //右节点移动到了被删元素的位置上
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }


    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "does't exist!");
        }
        node.value = newValue;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");
        ArrayList<String> word1 = new ArrayList<>();
        if (FileOperation.readFile("E:\\JavaWeb\\Algorithms\\dataStructure\\src\\pride-and-prejudice.txt", word1)) {
            System.out.println("Total words:" + word1.size());
            AVLTree<String, Integer> map = new AVLTree<String, Integer>();
            for (String word : word1) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words:" + map.getSize());
            System.out.println("Frequency of pride:" + map.get("pride"));
            System.out.println("is BST: " + map.isBST());
            System.out.println("is Balanced: " + map.isBalanced());

            for (String word : word1) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced()) {
                    throw new RuntimeException("Error");
                }
            }
            System.out.println();
        }


    }

}
