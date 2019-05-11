/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc.pkg310.hw.pkg4;
import java.util.*;
/**
 *CSC 310 HW 4
 * Queues and Linked Lists
 * @author Brendan
 * 2/20/2019
 */

class MyCircularDeque
{
    private int[] queue;
    private int ind;
    
    //Constructor, sets queue size to k
    public MyCircularDeque(int k)
    {
        queue =  new int[k];
    }
    
    //inserts element e into the start of the queue
    //returns true if succesful
    //fails if the queue is full
    public boolean insertFront(int e)
    {
        if(this.isFull()) 
        {
            System.out.println("Insertion Failed: Queue is full");
            return false;
        }
        
        for (int i = queue.length - 1; i > 0; i--) {
            queue[i] = queue[i-1];
        }
        queue[0] = e;
        ind ++;
        return true;
    }
    
    //insert element e into last place in the queue
    //returns true if succesful
    //fails if queue is full
    public boolean insertLast(int e)
    {
        if(this.isFull()) 
        {
            System.out.println("Insertion Failed: Queue is full");
            return false;
        }
        
        queue[ind] = e;
        ind ++;
        return true;
    }
    
    //deletes first element in the queue
    //returns true if succesful
    //fails if queue is empty
    public boolean deleteFront()
    {
        if(this.isEmpty())
        {
            System.out.println("Deletion Failed: Queue is already empty");
            return false;
        }
        
        for (int i = 0; i < ind - 1; i++) {
            queue[i] = queue[i+1];
        }
        queue[queue.length-1] = 0;
        ind --;
        return true;
    }
    
    //deletes last value in the queue
    //returns true if succesful
    //fails if queue is empty
    public boolean deleteLast()
    {
        if(this.isEmpty())
        {
            System.out.println("Deletion Failed: Queue is already empty");
            return false;
        }
        ind --;
        queue[ind] = 0;
        return true;
    }
    
    //Prints elements of the queue
    public void print()
    {
        for (int i = 0; i < ind- 1; i++) {
            System.out.print(queue[i] + ", ");
        }
        System.out.println(queue[ind - 1]);
    }
    
    //returns front value in the queue
    //returns -1 if queue is empty
    public int getFront()
    {
        if(this.isEmpty())
        {
            System.out.println("Return Failed: No value to return");
            return -1;
        }
        
        return queue[0];
    }
    
    //returns front value in the queue
    //returns -1 if queue is empty
    public int getRear()
    {
        if(this.isEmpty())
        {
            System.out.println("Return Failed: No value to return");
            return -1;
        }
        
        return queue[ind-1];
    }
        
    // determines if the queue is empty
    public boolean isEmpty()
    {
        if( ind == 0) return true;
        return false;
    }
    
    //determines if the queue is full
    public boolean isFull()
    {
        if(ind == queue.length) return true;
        return false;
    }
}

class Queue{
    
    LinkedList<Integer> queue = new LinkedList();
    
    //adds element e to the queue
    public void enqueue(int e)
    {
        queue.add(e);
    }
    public int dequeue()
    {
        if(queue.isEmpty())
        {
            System.out.println("Deletion Failed: Queue is already empty");
        }
        else
        {
            int r = queue.get(0);
            queue.remove();
            return r;
        }
        return -99999;
    }
    public int first()
    {
        if(this.isEmpty()) return -9999;
        return queue.get(0);
    }
    public int len()
    {
        return queue.size();
    }
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    public boolean search(int e)
    {
        for (int i = 0; i < queue.size(); i++) {
            if(queue.get(i) == e) return true;
        }
        return false;
    }
}
public class CSC310HW4 {

    public static LinkedList<Integer> merge(LinkedList<Integer> init1, LinkedList<Integer> init2)
    {
        LinkedList<Integer> result = new LinkedList();
        
        while(!(init1.isEmpty()) && init2.isEmpty())
        {
            if(init1.isEmpty())
            {
                result.add(init2.get(0));
                init2.remove();
            }
            else if(init2.isEmpty())
            {
                result.add(init1.get(0));
                init1.remove();
            }
            else
            {
                if(init2.get(0) < init1.get(0))
                {
                    result.add(init2.get(0));
                    init2.remove();
                }
                else
                {
                    result.add(init1.get(0));
                    init1.remove();
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        //Part 1 Testing
        MyCircularDeque deque = new MyCircularDeque(3);
        deque.deleteLast();
        deque.insertFront(1);
        deque.print();
        deque.insertFront(2);
        deque.print();
        deque.insertLast(3);
        deque.print();
        System.out.println("Front Value: " + deque.getFront());
        System.out.println("Rear Value: " + deque.getRear());
        deque.insertFront(4);
        deque.print();
        deque.deleteFront();
        deque.print();
        deque.deleteLast();
        deque.print();
        
        //Second Prompt Testing
        //#2 testing
        LinkedList<Integer> a = new LinkedList();
        a.add(1);
        a.add(2);
        a.add(4);
        LinkedList<Integer> b = new LinkedList();
        b.add(1);
        b.add(3);
        b.add(4);
        LinkedList<Integer> ab = merge(a, b);
        
        while(!(ab.isEmpty())){
            System.out.print(ab.get(0) + ", ");
            ab.remove();
        }
        
        //#3 testing
        Queue q = new Queue();
        q.enqueue(2);
        q.enqueue(1);
        q.len();
        q.dequeue();
        q.len();
        q.enqueue(4);
        System.out.println(q.search(4));
        System.out.println(q.search(75));
        System.out.println(q.first());
        System.out.println(q.isEmpty());
    }
    
}
