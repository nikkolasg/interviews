package org;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by nikko on 7/17/15.
 */
public class Chap2 {

    public static void main(String[] args) {
        System.out.println("[+] Chapter 3 Linked Lists");
        LLInt list = new LLInt();
        list.Add(10);
        list.Add(5);
        list.Add(12);
        list.Add(8);
        System.out.println("[+] Linked list : " + list.toString());
        list.Delete(8);
        System.out.println("[+] Deleted 8 : " + list.toString());
        list.Add(5);
        System.out.println("[+] List : " + list.toString());
        list.DeleteDuplicateInPlace();
        System.out.println("[+] Delete Duplicates INPLACE: " + list.toString());
        list.Add(5);
        list.DeleteDuplicate();
        System.out.println("[+] Delete Duplicates BUFFER : " + list.toString());
        list.Add(4);
        list.Add(3);
        list.Add(10);
        System.out.println("[+] List : " + list.toString());
        int pvalue = 6;
        list.Partition2(pvalue);
        System.out.println("[+] List Partitioned around " + pvalue + " : " + list.toString());
        LLInt empty = new LLInt();
        System.out.println("[+] List empty : " + empty.toString());
        empty.Partition2(pvalue);
        System.out.println("[+] Partitioned ? : " + empty.toString());
        empty.Add(2);
        empty.Partition2(pvalue);
        System.out.println("[+] List +2 Partitioned : " + empty.toString());
        list.Add(55);
        System.out.println("[+] List current : " + list.toString());
        list.SwapByTwo();
        System.out.println("[+] List swapped : " + list.toString());

        LLInt pal = new LLInt();
        pal.Add(1);
        pal.Add(2);
        pal.Add(3);
        pal.Add(4);
        pal.Add(2);
        pal.Add(1);
        System.out.println("[+] List : " + pal.toString() + " => palindrome ? " + pal.isPalindrome());
        System.out.println("[+] List : " + empty.toString() + " => palindrome ? " + empty.isPalindrome());
        empty.Delete(2);
        System.out.println("[+] List : " + empty.toString() + " => palindrome ? " + empty.isPalindrome());


    }

}

class IntNode implements Comparator<IntNode> {
    Integer value;
    IntNode next;

    public IntNode(Integer value) {
        this.value = value;
    }
    public String toString() {
        return value.toString();
    }

    @Override
    public int compare(IntNode intNode, IntNode t1) {
        return intNode.value.compareTo(t1.value);
    }

    public boolean equals(IntNode n) {
        return n.value.equals(value);
    }
}

class LLInt {
    private IntNode head = null;

    public LLInt() {

    }

    public String FastRunner() {
        IntNode slow = head;
        IntNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // ODD NUMBER
            slow = slow.next;

        }
        return "";
    }
    public boolean isPalindrome() {
        if(head == null) return true;
        Stack<IntNode> s = new Stack<>();
        IntNode slow = head;
        IntNode fast = head;
        int length = 1;
        while (fast.next != null) {
            s.push(slow);
            slow = slow.next;
            fast = fast.next.next;
            length += 1;
            if (fast == null) {
                break;
            }
            length += 1;
        }
        if (length % 2 != 0 ) slow = slow.next;

        IntNode tmp;
        while ( !s.empty() && slow != null) {
            tmp = s.pop();
            if (!tmp.equals(slow))
                return false;
            slow = slow.next;
        }
        return true;
    }

    public void SwapByTwo() {
        if (head == null) return;

        IntNode current = new IntNode(0);
        current.next = head;
        IntNode first = current;
        while (current.next != null) {
            IntNode n1 = current.next;
            IntNode n2 = current.next.next;
            if(n2 == null) break; // we are at the end

            IntNode last = n2.next;
            current.next = n2;
            n2.next = n1;
            n1.next = last;

            current = n1;
        }

        head = first.next;
    }

    public void Partition2(Integer value) {
        if (head == null ) return;
        // fake first node
        IntNode superior = new IntNode(0);
        IntNode current = new IntNode(0);
        current.next = head;
        IntNode tempHead = current;
        IntNode tempSup = superior;

        while (current.next != null) {

            // test if it must be in the second list (superior > value)
            if (current.next.value > value) {
                // add it to the superior list
                superior.next = current.next;
                superior = superior.next;
                // remove it from the current list
                current.next = current.next.next;
                // set to null the next node of superior.next
                // so it ENDS the linked list
                superior.next = null;
            } else {
                current = current.next;
            }
        }
        head = tempHead.next;
        current.next = tempSup.next;

    }
    public void Partition(Integer value) {
        if(head == null) return;
        IntNode before = null;
        IntNode first = null;
        IntNode after = null;
        IntNode firstAfter = null;
        IntNode current = head;
        if (current.value <= value) {
            before = current;
            first = before;
        } else {
            after = current;
            firstAfter = after;
        }

        while (current.next != null) {
            if (current.next.value <= value){
                // add the node to the before
                if (before == null) {
                    before = current.next;
                    first = before;
                } else {
                    before.next = current.next;
                    before = before.next;
                }
            } else {
                if (after == null) {
                    after = current.next;
                    firstAfter = after;
                } else {
                    after.next = current.next;
                    after = after.next;
                }
                after.next = null;
            }
            // pass to the next unknown node
            current = current.next;
        }

        before.next = firstAfter;
        head = first;
    }

    public void Add(Integer value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
            return;
        }
        IntNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public IntNode Delete(Integer value) {
        if (head == null) return null;
        if (head.value == value) {
            IntNode tmp = head;
            head = null;
            return tmp;
        }
        IntNode current = head;
        IntNode next = head.next;
        while (current != null && next != null) {
            if (next.value.equals(value)) {
                // changes
                current.next = next.next;
                return next;
            }
            current = next;
            next = current.next;
        }
        return null;
    }

    public void DeleteDuplicate() {
        if (head == null) return;
        HashMap<Integer,Integer> map = new HashMap<>(  );
        IntNode current = head;
        map.put(current.value,1);
        while(current.next != null) {
            int count = map.getOrDefault(current.next.value,0);
            //duplicate
            if (count > 0) {
                current.next = current.next.next;
            } else {
                map.put(current.next.value,1);
                current = current.next;
            }
        }
    }

    public void DeleteDuplicateInPlace() {
        IntNode current = head;
        IntNode fast = current;
        while(current != null) {
            while(fast.next != null) {
                // duplicate
                if (fast.next.value.equals(current.value)) {
                     fast.next = fast.next.next;
                } else {
                    fast = fast.next;
                }
            }
            current = current.next;
            fast = current;
        }
    }

    public String toString() {
        StringBuffer buff = new StringBuffer();
        IntNode n = head;
        while (n != null) {
            buff.append(n.value.toString() + " -> ");
            n = n.next;
        }
        return buff.toString();
    }
}