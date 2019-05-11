/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc310hw5;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brendan
 */

//Custom Stack class, needed for the output
class Tower
{
    public String title;
    
    private ArrayList<Integer> stack;
    public Tower(String name)
    {
        stack = new ArrayList();
        title = name;
    }
    
    public void push(int e)
    {
        stack.add(e);
    }
    
    public int pop()
    {
        if(stack.isEmpty())
        {
            throw new java.lang.RuntimeException("Array is empty");
        }
        
        return stack.remove(stack.size() - 1);
    }
    
    public int top()
    {
        if(stack.isEmpty())
        {
            throw new java.lang.RuntimeException("Array is empty");
        }
        
        return stack.get(stack.size()-1);
    }
    public int size()
    {
        return stack.size();
    }
    public void print()
    {
        for(int i = this.size() - 1; i >= 0; i--)
        {
            System.out.println(this.stack.get(i));
        }
    }
}

class TowersOfHanoi {
    //count variable used to find number of steps to complete tower of hanoi
   public static int count = 0;
   public static void solve(int n, Tower start, Tower auxiliary, Tower end) {
       if (n == 1) {
           System.out.println("Move disc " + start.top() + " from peg " + start.title + " to peg " + end.title);
           end.push(start.pop());
           count++;
           
       } else {
           solve(n - 1, start, end, auxiliary);
           System.out.println("Move disc " + start.top() + " from peg " + start.title + " to peg " + end.title);
           end.push(start.pop());
           count++;
           solve(n - 1, auxiliary, start, end);
       }
   }
}
   
//class for tree noedfs which each have a left child, right child, and value
class TreeNode
    {
        public TreeNode leftChild;
        public TreeNode rightChild;
        public int val;
        
        public String toString()
        {
            if(this == null) return "";
            if(this.leftChild == null && this.rightChild == null)
            {
                return "This node has no children and has a value of " + this.val;
            }
            else if(this.leftChild == null)
            {
                return ("This node's right child is " + this.rightChild.val + ", and it's value is " + this.val);
            }
            else if(this.rightChild == null)
            {
                return ("This node's left child is " + this.leftChild.val + ", and it's value is " + this.val);
            }
            else
                 return ("This node's right child is " + this.rightChild.val + ", it's left child is " + this.leftChild.val + ", and it's value is " + this.val);
        }
        public TreeNode(int x)
        {
            val = x;
            leftChild = null;
            rightChild = null;
        }
        
        public void setLeft(TreeNode left)
        {
            this.leftChild = left;
        }
        public void setRight(TreeNode right)
        {
            this.rightChild = right;
        }
        
        //inorder: Leftchild, self, rightchild
        //prints the numbers stored in the tree, in a left this right order
        public void inorder()
        {
            if(this.leftChild != null)
            {
                this.leftChild.inorder();
            }
            System.out.print(this.val + " ");
            if(this.rightChild != null)
            {
                this.rightChild.inorder();
            }
        }
        //preorder: self, leftchild, rightchild
        public void preorder()
        {
            System.out.print(this.val + " ");
            if(this.leftChild != null)
            {
                this.leftChild.preorder();
            }
            if(this.rightChild != null)
            {
                this.rightChild.preorder();
            }
        }
        
    }
public class CSC310HW5 {
    
    
    
    public static void main(String[] args) {
        Tower pegA = new Tower("A");
        Tower pegB = new Tower("B");
        Tower pegC = new Tower("C");
        
        TowersOfHanoi toh = new TowersOfHanoi();
        System.out.println("This program assumes that the standard goal of the Towers of Hanoi problem, moving a stack of discs from peg A to C, is desired.");
        System.out.println("As a result, All discs will be initialized on peg A.");
        System.out.println();
        System.out.print("Enter number of discs: ");
        Scanner scanner = new Scanner(System.in);
        int discs = scanner.nextInt();
        
        for (int i = discs; i >0; i--) 
        {
            pegA.push(i);
        }
        
        //System.out.println("PegA: ");
        //pegA.print();
        //System.out.println();
        toh.solve(discs, pegA, pegB, pegC);
        //System.out.println();
        //System.out.println("PegC: ");
        //pegC.print();
        
        //System.out.println("That took " + toh.count + " operations");
        System.out.println("//////////////////////End of part 1////////////////");
        System.out.println();
        /////////////////////////End of Part one////////////////////
        
        //create the nodes
        Scanner input = new Scanner(System.in);
        System.out.println("NOTE*");
        System.out.println("This program only allows for a tree that is 3 layers deep");
        System.out.println();
        ArrayList<TreeNode> tree = new ArrayList();
        for (int i = 0; i < 6; i++)
        {
            try{
                System.out.println("Enter the value of the next node in the array representation of your tree, or enter null if appropriate");
                int x = input.nextInt();
                tree.add(new TreeNode(x));
            }catch(Exception e){
                String bad_input = input.nextLine();        //get past bad input value for next iteration
                tree.add(null);
                //continue;
            }
        }
        
        //System.out.println("TEST");
        
        tree.add(null);             //Bounds issues with setting child nodes later in program, adding one null node to the end of the list fixed it
        
        int n = tree.size();
        //System.out.println("N: " + n);
        //System.out.println("TEST 1.5");
        
        //set children
        for (int i = 0; i < n; i++) 
        {
            //System.out.println("TEST2");
            if(tree.get(i) != null && i*2+2 < n)
            {
                System.out.println(i);
                tree.get(i).setLeft(tree.get(i*2+1));
                tree.get(i).setRight(tree.get(i*2+2));
            }
            
        }
        /*
        //System.out.println("TEST3");
        for (int i = 0; i < n; i++) {
            System.out.println(tree.get(i));
        }
        //*/
        
        System.out.println("INORDER:");
        tree.get(0).inorder();
        System.out.println();
        System.out.println("PREORDER");
        tree.get(0).preorder();
    }
    
}
