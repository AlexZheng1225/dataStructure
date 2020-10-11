package linkedList;

/**
 * @Author: ZhengCheng
 * @Date: created in 21:47  2020/2/15
 * @Annotation: 使用链表实现linkedList
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    //链表节点的构造函数
    //使用arr为参数，创建一个链表，当前的ListNode为链表头节点
    ListNode(int[] arr){
        if(arr==null||arr.length==0){
            throw new IllegalArgumentException("arr can not be empty.");
        }
        this.val = arr[0];
        ListNode current = this;
        for(int i=1;i<arr.length;i++){
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }

    //以当前节点为头节点的链表信息字符串
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while(cur!=null){
            res.append(cur.val+"-->");
            cur = cur.next;
        }
        res.append("NULL"); //链表尾部
        return res.toString();
    }

}
