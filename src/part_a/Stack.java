/**
 * @author Sean Nishi
 * Date: 7/10/2021
 */

package part_a;

import java.util.Scanner;

/**
 * Reverse the numbers on the array using stack and recursion.
 *
 * The user should be prompted to enter how many integer numbers want to store in an array (at lease 5 numbers).
 * They should also be prompted to enter each number that to be stored into an array. Use stack and recursion concepts for reversing each number.
 * After reversing, all the reversed numbers should be stored in the same array and display them on the output screen.
 *
 * For example:
 *
 * Size of array: 5
 *
 * Input array: 367, 289, 89, 4586, 230
 *
 * Output array: 763, 982, 98, 6854, 32
 */
public class Stack {
    ///Implementing a stack

    private int mArray[];
    private int mTop;
    private int mSize;

    //constructor
    Stack(int size){
        mTop = -1;
        mSize = size;
        mArray = new int[mSize];
    }

    /**
     * @return whether stack is empty
     */
    public boolean isEmpty(){
        return (mTop < 0);
    }

    /**
     *
     * @return whether stack is full
     */
    public boolean isFull() { return (mTop >= mSize - 1); }

    /**
     * @return int at top of stack
     */
    public int peek(){
        if(mTop < 0) {
            System.out.println("Stack underflow");
            return 0;
        }
        else
            return mArray[mTop];
    }

    /**
     * @param x, int to be pushed
     * @return whether successfully pushed
     */
    public boolean push(int x){
        if(mTop >= (mSize - 1)){
            System.out.println("Stack overflow");
            return false;
        }
        else{
            mArray[++mTop] = x;
            System.out.println("Pushed " + x + " onto the stack");
            return true;
        }
    }

    /**
     * @return the int popped
     */
    public int pop(){
        //check if stack is empty
        if(mTop < 0){
            System.out.println("Stack underflow");
            return 0;
        }
        else{
            return mArray[mTop--];
        }
    }

    //getter fcn for top of stack member var
    public int getTop() { return this.mTop; }

    public int getSize() { return this.mSize; }

    public int[] getArray() { return this.mArray; }

    public void setArray(int arr[], int size) {
        //if we are working with array with same size, can operate
        if(this.mSize == size){
            for(int i = 0; i < this.mSize; i++){
                this.mArray[i] = arr[i];
            }
        }
    }

    /**
     * @param num the original number
     * @param ans the reversed number we return
     * @return ans, the after all recursion is done
     */
    private int recursion(int num, int ans){
        //stopping condition, return the answer
        if(num == 0){
            return ans;
        }
        //can we still work on it?
        else if (num > 0){
            //save the 1s number
            int temp = num % 10;
            //shift ans by 10, then add remainder to ans
            ans = ans * 10 + temp;
            //pass it ans so it can build off it, changing current ans with new ans
            ans = recursion(num / 10, ans);//UPDATE THE ANS
        }
        return ans;
    }

    public void recursionAll(){
        int ans = 0;
        //for each member var in our stack
        for(int i = 0; i < mSize; i++){
            //preform recursion on each item
            mArray[i] = recursion(mArray[i], ans);
        }
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the size of the stack: ");
        int size = scan.nextInt();
        int temp;

        Stack s = new Stack(size);

        //push user input onto stack
        while(!s.isFull()){
            System.out.print("Enter an integer: ");
            temp = scan.nextInt();
            s.push(temp);
        }

        System.out.println("\n******Doing Recursion******");
        //do recursion for all items
        s.recursionAll();


        System.out.println("\n**********Popping**********");
        //print reversed numbers
        while(!s.isEmpty()) {
            temp = s.pop();
            System.out.println("Popping " + temp);
        }
        return;
    }
}
