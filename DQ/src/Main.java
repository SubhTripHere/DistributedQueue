import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ExecutorService producerExecutor=new ThreadPoolExecutor(3,5,10,TimeUnit.MINUTES,new ArrayBlockingQueue<>(10));
        ExecutorService consumerExecutor  = new ThreadPoolExecutor(3,5,10,TimeUnit.MINUTES,new ArrayBlockingQueue<>(10));
        DQueue dQueue=new DQueue(3);
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=10;i++) {
                    try {
                        Thread.sleep(2000);
                        dQueue.enqueue("Item"+i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=10;i++) {
                    try {
                        Thread.sleep(3000);
                        System.out.println(dQueue.dequeue());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.start();
        thread2.start();


//        for (int i = 0; i < 3; i++) {
//            producerExecutor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    for(int j=0;j<10;j++){
//                        dQueue.enqueue("Inserting :"+(j));
//                    }
//                }
//            });
//        }
//        for (int i = 0; i < 2; i++) {
//            consumerExecutor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    for(int j=0;j<10;j++){
//                        System.out.println(dQueue.dequeue());
//                    }
//                }
//            });
//        }
    }
}