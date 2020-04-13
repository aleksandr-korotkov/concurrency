import entities.BookingRequest;
import queue.CustomQueue;
import threads.Handler;
import threads.Producer;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        AtomicInteger countOfRequests = new AtomicInteger(1);
        CustomQueue queue = new CustomQueue();

        Producer producerOne = new Producer(queue, countOfRequests, "A");
        producerOne.start();
        Producer producerTwo = new Producer(queue, countOfRequests, "B");
        producerTwo.start();
        Producer producerThree = new Producer(queue, countOfRequests, "C" );
        producerThree.start();

        Handler handlerOne = new Handler("z", queue);
        handlerOne.start();
        Handler handlerTwo = new Handler("j", queue);
        handlerTwo.start();
        Handler handlerThree = new Handler("x", queue);
        handlerThree.start();
        Handler handlerFour = new Handler("s", queue);
        handlerFour.start();
        Handler handlerFive = new Handler("u", queue);
        handlerFive.start();
        Handler handlerSix = new Handler("t", queue);
        handlerSix.start();
    }

}
