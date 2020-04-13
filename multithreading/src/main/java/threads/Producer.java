package threads;

import entities.BookingRequest;
import org.apache.log4j.Logger;
import queue.CustomQueue;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread {
    private final int LIMIT_OF_REQUESTS = 15;
    private final static Logger logger = Logger.getLogger(Producer.class);
    private final String SOURCE_DATA_FILE = "src/main/resources/dataForRequests.txt";
    private final String DELIMITER = "@";

    private CustomQueue requests;
    private AtomicInteger countNumberOfRequest;
    private String id;
    private BookingRequest bookingRequest;
    private List<String> dataForRequests;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Producer(CustomQueue requests, AtomicInteger count, String id) {
        readFileAndCreateListLines();
        this.requests = requests;
        this.countNumberOfRequest = count;
        this.id = id;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (countNumberOfRequest) {
                if (countNumberOfRequest.get() <= LIMIT_OF_REQUESTS) {
                    try {
                        bookingRequest = createRequest();
                        readFileAndCreateListLines();
                        if (requests.put(bookingRequest)) {
                            logger.info("Producer " + id + ". Отправляю запрос на объявление " + bookingRequest.getAd() +
                                    ". Дата бронирования " + bookingRequest.getDate() + ". Название отеля: " + bookingRequest.getHotel() + ". Запрос № " + countNumberOfRequest.get());
                            countNumberOfRequest.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else return;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private BookingRequest createRequest(){
        String[] data = dataForRequests.get(countNumberOfRequest.get() -1).split(DELIMITER);
        return new BookingRequest(data[0],LocalDate.parse(data[1],formatter),data[2]);
    }

    private String readFileAndCreateListLines() {
        dataForRequests = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(SOURCE_DATA_FILE)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataForRequests.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "s";
    }
}
