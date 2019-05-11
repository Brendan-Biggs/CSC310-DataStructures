CSC 310
HW 5 Documentation

**NOTE**
Prior knowledge as to the formal representation of a binary tree as an array must be known to correctly enter a tree. For the example given in the Assignment sheet, and accounting for inputs used to continue or stop input, the user must enter '1 y null y 2 y null y null y 3 n

STEP BY STEP:
	Upon running the program it will prompt you for the number of discs you would like to be in your Tower of Hanoi puzzle, which the program will solve for you. Following that input, it will return directions for what the solution is.
	Then, you will be prompted to enter the root value for your tree. After you do, you will be asked if there is another node, and if you answer yes you will be prompted for another value, or null if needed. An important note, any word starting in a y will result in another value being requested. Any input that does not start in a y will stop input requests and will continue on with the program. Once all values have been entered and you have signaled to the program that input must stop, by entering any input that does not start with a y, it will go through the list of your inputs and assign each node to have a left or right child, where appropriate. Finally, it will return the Preorder traversal and then the Inorder traversal of the tree you have built.