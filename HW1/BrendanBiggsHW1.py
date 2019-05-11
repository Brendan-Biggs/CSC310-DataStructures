# -*- coding: utf-8 -*-
"""
Created on Thu Jan 24 16:20:39 2019

CSC 310 - HW 1
Professor Wooky Kim
@author: Brendan

"""

def main():
    cls()
    while True:
        print()
        print("What would you like to do? Enter the number corresponding to your choice.")
        print("1: Reverse a string")
        print("2: Find an odd pair within a list of values")
        print("3: Find all permutations of a list of values")
        print("4: Find the hamming distance between two values")
        print("5: Quit")
        
        choice = int(input())
        if choice == 1:
            cls()
            reverseString()
        elif choice == 2:
            cls()
            oddProduct()
        elif choice == 3:
            templist = []
            cls()
            
            #build the list to be used
            n = int(input("How many items in your list? "))
            for i in range(n):
                templist.append(int(input("Enter a number: ")))
                
            #find the permutations of the newly built list    
            permutations(templist, 0)
        elif choice == 4:
            cls()
            hamming()
        elif choice == 5:
            break
        else:
            cls()
            print("That is not a valid input, try again")

    print("MAIN - WIP")

#asks for a single line of user input and reverses it
def reverseString():
    string = []
        
    try:
        while True:
            string = [input("Please enter a string: ")] + string
        
    except EOFError:
        print()
        for line in string:
            print(line)

#asks for a list of 'x' numbers and determines if any can multiply to return an odd number
#returns first found instance of such a pair
#if no such pair is found, returns a string saying as much    
def oddProduct():
    list = []
    x = int(input("How many numbers are in your list? \n"))
    for i in range(x):
        list.append(int(input("Enter a number: ")))
    print(list)
    
    #loop to find first number in the pair
    for i in range(len(list)):
        #loop to find second number in the pair
        #all values before the current value are already known to not work with the current value
        #current value cannot be paired with itself
        for j in range(i+1, len(list)):
            if(((list[i] * list[j]) % 2 == 1)):
                print("True")
                return True
    
 
#takes a list and a start index(i) to find the permutations
def permutations(list, ind):
    if ind == len(list) - 1:
        print(list)
    else:
        #place all values in the list at the front of the list, then finds all permutations of that list, excluding the new first value
        for j in range(ind, len(list)):
            list[ind], list[j] = list[j], list[ind]
            permutations(list, ind + 1)
            list[ind], list[j] = list[j], list[ind] # swap back, for the next loop
        
    
#asks for two numbers and finds the hamming distance between them
def hamming():
    count = 0
    #Enter values between 0 and 255
    user1 = int(input("Enter an integer between 0 and 255: "))
    user2 = int(input("Enter another integer between 0 and 255: "))
    #convert each number to binary
    bin1 = toBin(user1)
    bin2 = toBin(user2)
    #compare those strings and return the number of places that are different
    for i in range(len(bin1)):
        if(bin1[i] != bin2[i]):
            count += 1
    print("The hamming distance between " + str(user1) + " and " + str(user2) + " is " + str(count))

#helper method to convert an integer into an 8-bit binary string
def toBin(n):
    s = ""
    base = 7 
    
    while base >= 0:
        #print("running")
        if(2**base <= n):
            s += "1"
            n -= (2**base)
        else:
            s += "0"
            
        base -= 1
    return s
    
#clears the console screen, to aid in readability
def cls():
    print('\n'*100)

  
main()