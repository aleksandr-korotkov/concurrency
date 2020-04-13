package queue;

import entities.BookingRequest;

import java.util.LinkedList;
import java.util.List;

public class CustomQueue {
        private List<BookingRequest> queue = new LinkedList();
        private final int LIMIT = 5;

        public synchronized boolean put(BookingRequest request) throws InterruptedException  {
            while (queue.size() == this.LIMIT) {
                wait();
            }
            if (queue.size() == 0) {
                notifyAll();
            }
            queue.add(request);
            if(queue.contains(request)) {
                return true;
            }
            return false;
        }

        public synchronized BookingRequest take() throws InterruptedException{
            while (queue.size() == 0){
                wait();
            }
            if (queue.size() == this.LIMIT){
                notifyAll();
            }

            return queue.remove(0);
        }

        public synchronized int size(){
            return queue.size();
        }
}
