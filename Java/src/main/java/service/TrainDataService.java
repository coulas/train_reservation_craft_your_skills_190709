package service;

import data.TrainData;

public interface TrainDataService {
    //{"seats": {"1A": {"booking_reference": "", "seat_number": "1", "coach": "A"},
    // "2A": {"booking_reference": "", "seat_number": "2", "coach": "A"}}}
TrainData getTrainData(String trainId);
}
