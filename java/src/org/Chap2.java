package org;

import java.util.ArrayList;
import java.util.HashMap;

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

    }

}

class IntNode {
    Integer value;
    IntNode next;

    public IntNode(Integer value) {
        this.value = value;
    }
    public String toString() {
        return value.toString();
    }
}

class LLInt {
    private IntNode head = null;

    public LLInt() {

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
        if (head.value == value) return head;
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