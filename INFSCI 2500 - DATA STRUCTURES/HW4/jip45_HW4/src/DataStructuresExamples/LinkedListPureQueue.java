package DataStructuresExamples;

import java.util.LinkedList;


public class LinkedListPureQueue<E> implements PureQueue<E> {
    //The linkedlist will maintain size, front, and back references for us.
    protected LinkedList<E> list;


    //Default constructor.
    public LinkedListPureQueue(){
        list = new LinkedList();    //an empty linked list.
    }

    //Copy constructor
    public LinkedListPureQueue(LinkedListPureQueue<E> otherQueue){
        //Makes a shallow copy of the other list.
        //We create a new linkedlist object with an input paramater of the
        //other queue's underlying linkedlist and assign it to our underlying list.
        list= new LinkedList<E>(otherQueue.list);

        //We access the otherQueue.list field directly but does not violate
        //OOP principles because we are within a LinkedListPureQueue method.
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void enqueue(E element){
        //When an element is added to a queue, it goes in at the end.
        //Like joining a waiting line.
        list.addLast(element);
    }

    public E dequeue(){
        //When an element is removed from a queue, it comes from the front.
        //Like your turn after watiting in line.
        return list.removeFirst();
    }

    public E front(){
        //Return the element at the front of the queue without removing it.
        return list.getFirst();
    }

    public static void main(String[] args){
        LinkedListPureQueue<String> myQueue= new LinkedListPureQueue<String>();

        myQueue.enqueue("Brian");
        myQueue.enqueue("Jane");
        myQueue.enqueue("Karen");
        myQueue.enqueue("Bob");
        myQueue.enqueue("Kim");
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
    }
}
