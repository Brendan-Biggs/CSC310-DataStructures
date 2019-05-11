/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc310hw6;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Brendan
 */
class PriorityQueue
{
    LinkedList<Integer> Queue = new LinkedList<>();
    
    public void add(int x)
    {
        if (Queue.isEmpty())
        {
            Queue.add(x);
        }
        else
        {
            for (int i = 0; i < Queue.size(); i++) {
                if(x <= Queue.get(i))
                {
                    Queue.add(i, x);
                    break;
                }
                else if(i == Queue.size()-1)
                {
                    Queue.add(x);
                    break;
                }
            }
        }
    }
    
    public int getMin()
    {
        return Queue.get(0);
    }
    
    public int remove_min()
    {
        return Queue.pop();
    }
    
    public void print()
    {
        while(Queue.size() > 0)
        {
            System.out.print(Queue.pop() + " ");
        }
        System.out.println();
    }
}

 
class HeapSort 
{ 
    public void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>=0; i--) 
        { 
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int arr[], int n, int i) 
    { 
        int max = i; // Initialize the max value as root 
        int left = 2*i + 1;
        int right = 2*i + 2;
  
        // If leftChild child is larger than root 
        if (left < n && arr[left] > arr[max]) 
            max = left; 
  
        // If rightChild child is larger than max so far 
        if (right < n && arr[right] > arr[max]) 
            max = right; 
  
        // If max is not root 
        if (max != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[max]; 
            arr[max] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, max); 
        } 
    } 
  
    //print an array of size n
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 
  
} 

class BinHeap{
    private ArrayList<Integer> heap = new ArrayList<>();
    
    void flip(int x, int y){
        Integer helper = heap.get(y);
        heap.set(y, heap.get(x));
        heap.set(x, helper);
    }
    
    int parent(int x){
        return (x-1)/2;
    }
    
    int leftChild(int x){
        return 2*x+1;
    }
    
    int rightChild(int x){
        return 2*x+2;
    }
    
    boolean hasLeftChild(int x){
        return 2*x+1 < heap.size() && heap.get(2*x+1) != null;
    }
    
    boolean hasRightChild(int x){
        return 2*x+2 < heap.size() && heap.get(2*x+2) != null;
    }
    
    void insert(int x){
        heap.add(x);
        upheap(heap.size()-1);
    }
    
    public int findMin()
    {
        return heap.get(0);
    }
    
    public int delMin()
    {
        int ans = heap.get(0);
        flip(0, heap.size()-1);
        heap.remove(heap.size()-1);
        downheap(0);
        return ans;
    }
    
    public boolean isEmpty()
    {
        return heap.isEmpty();
    }
    
    public int size()
    {
        return heap.size();
    }
    
    //maintains heap conditions after adding a new element - WIP
    public void upheap(int x)
    {
        if(x != 0)
        {
            if(heap.get(x) < heap.get(parent(x)))
            {
                flip(x, parent(x));
                upheap(parent(x));
            }
        }
    }
    
    //maintains heap conditions after removing an item - WIP
    public void downheap(int x)
    {
        Integer LChild = null;
        Integer RChild = null;
        
        //set above values to be accurate, if they exist within the heap
        if(hasLeftChild(x)) LChild = leftChild(x);
        if(hasRightChild(x)) RChild = rightChild(x);
        
        int min = -1;
        if(LChild != null && RChild!= null ) 
        {
            if(heap.get(LChild) < heap.get(RChild)) min = LChild;
            else min = RChild;
        }
        
        else if(LChild != null ) min = LChild;
        else if(RChild != null ) min = RChild;
        else return;
        
        if(heap.get(min) < heap.get(x))
        {
            flip(x, min);
            downheap(min);
        }
    }
}
public class CSC310HW6 {
    
    public static void main(String[] args) {
        /////////////Part 1//////////
        System.out.println("START: Part 1");
        PriorityQueue p = new PriorityQueue();
        int[] a = {1,6,3,7,5,9};
        for (int i = 0; i < a.length; i++) p.add(a[i]);
        
        p.print();
        System.out.println();
        ////////////Part 2//////////
        System.out.println("START: Part 2");
        int arr[] = {12, 11, 13, 5, 6, 7}; 
  
        HeapSort ob = new HeapSort();
        System.out.println("Unsorted Array is");
        ob.printArray(arr);
        ob.sort(arr); 
  
        System.out.println("Sorted array is"); 
        ob.printArray(arr);
        System.out.println();
        //////////////Part 3//////////////
        System.out.println("START: Part 3");
        
        BinHeap min = new BinHeap();
        min.insert(5);
        min.insert(7);
        min.insert(3);
        min.insert(11);
        
        System.out.println(min.delMin());
        System.out.println(min.delMin());
        System.out.println(min.delMin());
        System.out.println(min.delMin());
    }
    
}
