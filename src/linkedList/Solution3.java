package linkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Solution 3 ：递归
class Solution3 {

    public ListNode removeElements(ListNode head, int val) {
        //头节点若为空
        if(head==null){
            return null;
        }
        /*//从头节点的下一个节点开始，调用一个功能函数
        ListNode res = removeElements(head.next,val);
        //头节点的值若和val相等
        if(head.val==val){
            return res;
        }else{
            //否则接上res
            head.next = res;
        }
        return head;*/
        head.next = removeElements(head.next,val);
        return head.val==val ? head.next:head;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,6,2,3,9,2,5,2};

        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution3().removeElements(head,2);
        System.out.println(head);
    }
}