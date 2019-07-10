package data;

import java.util.ArrayList;
import java.util.List;

public class TrainData {
    List<BookableSeat> seats = new ArrayList<>();


    public TrainData(List<BookableSeat> seats) {
        this.seats = seats;
    }
}
