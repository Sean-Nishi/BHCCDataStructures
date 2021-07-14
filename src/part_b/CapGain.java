/**
 * @author snishi
 * @date 7/10/2021
 */

package part_b;

import java.util.Scanner;

public class CapGain {
    private double totCapGain;
    private Queue sharesHeld;

    public CapGain(){
        totCapGain = 0.0;
        sharesHeld = new Queue();
    }

    public void updateTotCapGain(double totCapGain){
        this.totCapGain += totCapGain;
    }

    public double getTotCapGain(){
        return totCapGain;
    }

    /**
     * @about prompt for main menu items
     */
    public static void mainMenuPrompt(){
        System.out.println("********Main Menu********");
        System.out.println("1. Buy");
        System.out.println("2. Sell");
        System.out.println("3. Total Capital Gain So Far");
        System.out.println("4. Quit");
    }

    /**
     * @about add numShares shares at price price to the queue
     * @param numShares number of shares bought
     * @param price price at which shares are bought
     */
    public void buy(int numShares, double price){
        sharesHeld.enqueue(numShares, price);
    }

    /**
     * @about sell numShares shares at price price, dequeue
     * @param numShares number of shares sold
     * @param price price at which shares are sold
     */
    public void sell(int numShares, double price){
        if(sharesHeld.isEmpty()){
            System.out.println("ERROR: cannot sell when you havent bought anything");
            return;
        }
        int numShares1 = numShares;
        double capGain = 0.0;
        //while we still have shares left to dequeue
        while(numShares > 0){
            Queue.QNode lookahead = sharesHeld.peek();
            //if the front node has more shares than we want to dequeue
            if(lookahead.numShares >= numShares){
                //modify the number of shares in the front & update the current capGain
                capGain += sharesHeld.modifyFront(numShares, price);
                //update the TOTAL capitol gain
                updateTotCapGain(capGain);
                //update numShares for next pass
                numShares -= lookahead.numShares;
            }
            else{//else the front node has part of the shares we want to dequeue
                //dequeue the front
                Queue.QNode temp = sharesHeld.dequeue();
                //update current capGain
                capGain += ((temp.numShares) * (price - temp.price));
                //update the number of shares we want to dequeue now
                numShares -= temp.numShares;
            }
        }
        System.out.println("Sold "+ numShares1 + " shares at " + price + " price");
    }

    //testing main program
    public static void main(String args[]){

        CapGain currentSession = new CapGain();
        Scanner scan = new Scanner(System.in);
        int input = 0;
        int shares;
        double price;

        System.out.println("Welcome to the Market App");

        do{
            //display menu
            mainMenuPrompt();
            //get input
            input = scan.nextInt();

            //deal with input
            switch (input) {
                case 1:
                    System.out.println("How many shares would you like to buy?");
                    shares = scan.nextInt();
                    System.out.println("At what price would you like to buy the shares?");
                    price = scan.nextDouble();
                    currentSession.buy(shares, price);
                    System.out.println("Bought "+ shares + " shares at " + price + " price");
                    break;
                case 2:
                    System.out.println("How many shares would you like to sell?");
                    shares = scan.nextInt();
                    System.out.println("At what price would you like to sell your shares?");
                    price = scan.nextDouble();
                    currentSession.sell(shares, price);
                    break;
                case 3:
                    System.out.println("Total Capital Gain so far: " + currentSession.getTotCapGain());
                    break;
            }
        } while(input != 4);

        System.out.println("Thank you for using my app.\nHave a good day");
    }
}
