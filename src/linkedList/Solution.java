package linkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 */
//Solution 1 ：普通解法
class Solution {

    public ListNode removeElements(ListNode head, int val) {

        //判断表头元素是否符合删除条件
        while(head!=null&&head.val==val){
            ListNode delNode = head;
            head = delNode.next;
            delNode.next = null;
//            head = head.next;  //不考虑对象游离时，可以直接将head指向head的下一个结点
        }

        if(head==null){
            return null;
        }

        ListNode prev = head;
        while(prev.next!=null){
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
//                prev.next = prev.next.next;  //不考虑对象游离时，可以直接将prev.next指向其的下一个结点
            }else{
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,6,2,3,9,2,5,2};

        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution().removeElements(head,2);
        System.out.println(head);
    }

}