import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DQueue {
    int capacity;
    Queue<String> queue;
    ReentrantLock lock=new ReentrantLock();
    public DQueue(int capacity) {
        this.capacity = capacity;
        queue=new LinkedList<String>();
    }
    public void enqueue(String item) {
        synchronized (lock){
            while (queue.size()==capacity) {
                try {
                    System.out.println("Lets wait before enqueuing.....");
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add(item);
            System.out.println("Added a new item...");
            lock.notifyAll();
        }
    }
    public String dequeue() {
        synchronized (lock){
            while (queue.size()==0) {
                try {
                    System.out.println("Lets wait before dequeuing.....");
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String element=queue.remove();
            System.out.println("Removed an element from queue.....");
            lock.notifyAll();
            return element;
        }
    }
}
