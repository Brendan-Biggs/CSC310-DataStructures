/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc310hw3;

import java.util.*;
/**
 *
 * @author Brendan
 * Data Structures Homework 3
 */

class MinStack {
    private ArrayList<Integer> stack;
    private ArrayList<Integer> mins;
    
    public MinStack (ArrayList<Integer> list)
    {
        stack = list;
    }
    public MinStack ()
    {
        stack = new ArrayList();
        mins = new ArrayList();
        //stack.trimToSize();
    }
    
    public void push(int e)
    {
        
        if(mins.isEmpty() || mins.get(mins.size() - 1) > e)
        {
            mins.add(e);
        }
        
        
        stack.add(e);
    }
    
    public int pop()
    {
        if (stack.isEmpty())
        {
            throw new java.lang.RuntimeException("Array is empty");
        }
        
        
        if(mins.get(mins.size() - 1) == stack.get(stack.size() - 1))
        {
            mins.remove(mins.size() - 1);
        }
        
        return stack.remove(stack.size() - 1);
    }
    
    public int top()
    {
        if (stack.isEmpty())
        {
            throw new java.lang.RuntimeException("Array is empty");
        }
        
        return (stack.get(stack.size() - 1));
    }
    
    public int getMin()
    {
        return mins.get(mins.size() - 1);
    }
    
}

class ValStack
{
    private ArrayList<Integer> stack;
    public ValStack()
    {
        stack = new ArrayList();
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
}

class OpStack
{
    private ArrayList<String> stack;
    public OpStack()
    {
        stack = new ArrayList();
    }
    
    public void push(String e)
    {
        stack.add(e);
    }
    
    public String pop()
    {
        if(stack.isEmpty())
        {
            throw new java.lang.RuntimeException("Array is empty");
        }
        
        return stack.remove(stack.size() - 1);
    }
    
    public String top()
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
}

public class CSC310HW3 {
    /**
     * @param args the command line arguments
     */
    
    static ValStack valstack = new ValStack();
    static OpStack opstack = new OpStack();
    static MinStack minstack =new MinStack();
    
    //does the first operation taken from the operators stack, with the first 2 values from values stack
    static public void doOp()
    {
        //get values and operator that are to be worked with
        int x = valstack.pop();
        int y = valstack.pop();
        String op = opstack.pop();
        
        //chek operator to determine what needs to be done
        switch(op){
            case "+":
                valstack.push(y+x);
                break;
            case "-":
                valstack.push(y-x);
                break;
            case "/":
                valstack.push(y/x);
                break;
            case "*":
                valstack.push(y*x);
                break;
            default:
                System.out.println("Something is wrong with that");
        }
    }
    
    //returns numerical representation of order of operations precedence
    static public int prec(String op)
    {
        switch(op){
            case "+":
                return 1;
            case "-":
                return 1;
            case "/":
                return 2;
            case "*":
                return 2;
            default:
                System.out.println("Something is wrong with that");
        }
        return 0;
    }
    
    //controls when ops are done, based on order of operations (with help from prec() method)
    static public void repOp(String refOp)
    {
        while(valstack.size() > 1 && prec(refOp) <= prec(opstack.top()))
        {
            doOp();
        }
    }
    
    //determines if the passed token is a number or an operator
    static public boolean isNumber(String x)
    {
        if(x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) return false;
        return true;
    }
    
    //takes a string expression and returns an integer result, using other methods within program
    static public int evalExpression(String ex)
    {
        //Tokenize
        StringTokenizer e = new StringTokenizer(ex);
        
        //continually add to stacks until need to do operation
        while(e.hasMoreTokens())
        {
            String tok = e.nextToken();
            if(isNumber(tok))
            {
                valstack.push(Integer.parseInt(tok));
            }
            else
            {
                repOp(tok);
                opstack.push(tok);
            }
        }
        //end of loop, means nothing is left in the expression
        
        //essentially ends repOp
        repOp("$");
        return valstack.top();
    }
    
    static public int postfix(String ex)
    {
        //Tokenize
        StringTokenizer e = new StringTokenizer(ex);
        
        //continually add to stacks until need to do operation
        while(e.hasMoreTokens())
        {
            String tok = e.nextToken();
            if(isNumber(tok))
            {
                valstack.push(Integer.parseInt(tok));
            }
            else
            {
                opstack.push(tok);
                doOp();
            }
        }
        //end of loop, means nothing is left in the expression
        return valstack.top();
    }
    public static void main(String[] args) {
        
        MinStack minstack = new MinStack();
        minstack.push(1);
        minstack.push(-1);
        
        System.out.println(minstack.top());
        System.out.println(minstack.getMin());   //should return -1
        minstack.pop();
        
        System.out.println(minstack.top());
        System.out.println(minstack.getMin());   // should return 1
        minstack.pop();
        
        //System.out.println(minstack.top());    //should throw error "Array is Empty"
        
        System.out.println(evalExpression("23 + 1 + 3 * 8 + 0 / 5"));   //should return 48
        System.out.println(postfix("23 1 + 3 8 * + 0 5 / +"));
    }
    
}
