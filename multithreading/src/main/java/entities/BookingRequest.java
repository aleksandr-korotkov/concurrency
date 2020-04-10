package entities;

import java.time.LocalDate;

public class BookingRequest {
    private String ad;
    private LocalDate date;
    private String hotel;

    public BookingRequest(String ad, LocalDate date, String hotel) {
        this.ad = ad;
        this.date = date;
        this.hotel = hotel;
    }

    public String getAd() {
        return ad;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getHotel() {
        return hotel;
    }
}
