package data;

import service.BookingService;
import service.TrainDataService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TicketOffice {


    private BookingService bookingService;
    private TrainDataService trainDataService;

    public TicketOffice(BookingService bookingService, TrainDataService trainDataService) {
        this.bookingService = bookingService;
        this.trainDataService = trainDataService;
    }

    public Reservation makeReservation(ReservationRequest request) {
        List<Seat> seats = getAvailableSeats(request);
        return new Reservation(
                request.trainId,
                seats,
                bookingService.getReference());
    }

    private List<Seat> getAvailableSeats(ReservationRequest request) {
        TrainData trainData = trainDataService.getTrainData(request.trainId);
        return trainData.seats.stream()
                .filter(b -> b.getBookingId().isEmpty())
                .map(bookableSeat -> new Seat(bookableSeat.getCoach(), bookableSeat.getSeatNumber()))
                .limit(request.seatCount)
                .collect(Collectors.toList());
    }

}