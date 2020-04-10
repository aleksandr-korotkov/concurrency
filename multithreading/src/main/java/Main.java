import queue.CustomQueue;
import threads.Consumer;
import threads.Producer;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        AtomicInteger countOfRequests = new AtomicInteger(1);
        CustomQueue queue = new CustomQueue(5);

        Producer producerOne = new Producer(queue, countOfRequests, "A");
        producerOne.start();
        Producer producerTwo = new Producer(queue, countOfRequests, "B");
        producerTwo.start();
        Producer producerThree = new Producer(queue, countOfRequests, "C" );
        producerThree.start();

        Consumer consumerOne = new Consumer("z", queue);
        consumerOne.start();
        Consumer consumerTwo = new Consumer("j", queue);
        consumerTwo.start();
        Consumer consumerThree = new Consumer("x", queue);
        consumerThree.start();
        Consumer consumerFour = new Consumer("s", queue);
        consumerFour.start();
        Consumer consumerFive = new Consumer("u", queue);
        consumerFive.start();
        Consumer consumerSix = new Consumer("t", queue);
        consumerSix.start();
    }

}
