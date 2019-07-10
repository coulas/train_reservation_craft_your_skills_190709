package service;

import data.BookableSeat;
import data.Seat;
import data.TrainData;

import static java.util.Arrays.asList;

public class TrainDataServiceImpl implements TrainDataService {
    @Override
    public TrainData getTrainData(String trainId) {
        return  new TrainData(asList(new BookableSeat("", "A", 1),new BookableSeat("", "A", 2),new BookableSeat("","A", 3)));
    }
}
