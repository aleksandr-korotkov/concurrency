package threads;

import entities.BookingRequest;
import org.apache.log4j.Logger;
import queue.CustomQueue;

public class Consumer extends Thread {
    final static Logger logger = Logger.getLogger(Consumer.class);

    private String id;
    private BookingRequest bookingRequest;
    private CustomQueue queue;

    public Consumer(String id, CustomQueue queue) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            try {
                bookingRequest = queue.take();
                logger.info("Handler " + id + ". Обрабатываю запрос на объявление " + bookingRequest.getAd() +
                        ". Дата бронирования " + bookingRequest.getDate() + ". Название отеля: " + bookingRequest.getHotel());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(queue.size()==0){
                return;
            }
        }

    }
}
