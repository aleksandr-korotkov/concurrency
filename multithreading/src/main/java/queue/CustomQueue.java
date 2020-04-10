package queue;

import entities.BookingRequest;

import java.util.LinkedList;
import java.util.List;

public class CustomQueue {
        private List<BookingRequest> queue = new LinkedList();
        private int limit;

    public CustomQueue(int limit) {
        this.limit = limit;
    }

    public synchronized boolean put(BookingRequest request) throws InterruptedException  {
            while (queue.size() == this.limit) {
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
            if (queue.size() == this.limit){
                notifyAll();
            }

            return queue.remove(0);
        }

        public synchronized int size(){
            return queue.size();
        }
}
