/**
 * @brief PART-B Playing the Market
 * @author snishi
 * @date: 7/10/2021
 */

package part_b;

//implementation of a queue
public class Queue {

    //need my own node class for the linked list queue
    class QNode{
        int numShares;
        double price;
        QNode next;
        //constructor for node
        public QNode(int numShares, double price){
            this.numShares = numShares;
            this.price = price;
            this.next = null;
        }
    }

    private QNode mFront, mRear;

    //constructor, everything set to null
    public Queue(){
        this.mFront = this.mRear = null;
    }

    public boolean isEmpty(){
        //if front reference is null, it is empty
        return (this.mFront == null);
    }

    //enqueue
    public void enqueue(int numShares, double price){
        //create the node
        QNode node = new QNode(numShares, price);

        //check if queue if empty, append it as front and rear
        if(this.mRear == null){
            this.mFront = this.mRear = node;
        }
        else{
            //else have last node point to new node & update rear
            this.mRear.next = node;
            this.mRear = node;
        }
    }

    //dequeue
    public QNode dequeue(){
        QNode temp = null;
        //empty queue check
        if(this.mFront == null){
            System.out.println("Queue is empty");
        }
        else{
            //save current front node
            temp = this.mFront;
            //update the front
            this.mFront = this.mFront.next;

            //empty queue check
            if(this.mFront == null){
                this.mRear = null;
            }
        }
        //return the node or null, if nothing was there
        return temp;
    }

    /**
     * @about peek() allows us to see the contents of the first node in the queue
     * @return a copy of the QNode at the front of the queue, or null if queue is empty
     */
    public QNode peek(){
        if(mFront == null){
            System.out.println("Queue is empty. Cannot peek");
            return null;
        }
        else{
            return mFront;
        }
    }

    /**
     * @about if leftover shares then modify the front
     * of the queue.
     * @param numShares number of shares left to be removed from front
     * @param price the price at which the shares are left to be removed
     * @return the capitol gain/loss of the modification
     */
    public double modifyFront(int numShares, double price){

        //number of shares removed
        int removedShares = this.mFront.numShares - numShares;
        //the price difference between bought and sold
        double capPrice = price - this.mFront.price;

        //modify the front
        this.mFront.numShares -= numShares;

        return removedShares * capPrice;
    }

    //testing
    public static void main(String args[]){
        Queue q = new Queue();
        System.out.println("Enqueuing: 420.0");
        q.enqueue(30, 420.0);
        System.out.println("Enqueuing: 616.0");
        q.enqueue(25, 616.0);

        QNode o = q.dequeue();
        System.out.println("Dequeued\nShares: " + o.numShares +
                            "Price: " + o.price);
        o = q.dequeue();
        System.out.println("Dequeued\nShares: " + o.numShares +
                            "Price: " + o.price);
    }
}
