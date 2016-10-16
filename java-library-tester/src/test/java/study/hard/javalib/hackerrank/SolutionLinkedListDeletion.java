package study.hard.javalib.hackerrank;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by iandmyhand on 10/16/16.
 */

class Node {
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}
public class SolutionLinkedListDeletion {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Node head = null;
        int T = scan.nextInt();
        while(T-->0) {
            head = insert(head, scan.nextInt());
        }
        head = removeDuplicates(head);
        display(head);
    }

    static Node insert(Node head, int data) {
        if(null == head) return new Node(data);
        else {
            Node cur = head;
            while(true) {
                if(null == cur.next) {
                    cur.next = new Node(data);
                    return head;
                }
                cur = cur.next;
            }
        }
    }

    static Node removeDuplicates(Node head) {
        Set<Integer> set = new HashSet();
        Node cur = head;
        while(null != cur) {
            set.add(cur.data);
            cur = cur.next;
        }
        Integer[] sorted = set.toArray(new Integer[0]);
        quickSort(sorted, 0, sorted.length - 1);
        return toLinkedList(sorted);
    }

    private static void quickSort(Integer[] arr, Integer l, Integer r) {
        Integer i = partition(arr, l, r);
        if(l < i - 1) quickSort(arr, l, i - 1);
        if(r > i) quickSort(arr, i, r);
    }

    private static Integer partition(Integer[] arr, int l, int r) {
        Integer i = l, j = r;
        Integer pivot = arr[(l + r) / 2];
        while(i <= j) {
            while(pivot > arr[i]) i++;
            while(pivot < arr[j]) j--;
            if(i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private static void swap(Integer[] array, int l, int r) {
        int t = array[l];
        array[l] = array[r];
        array[r] = t;
    }

    private static Node toLinkedList(Integer[] arr) {
        Node head = new Node(arr[0]);
        Node cur = head;
        for(int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    static void display(Node cur) {
        if(null == cur) return;
        else {
            System.out.print(String.format("%d ", cur.data));
            display(cur.next);
        }
    }
}
