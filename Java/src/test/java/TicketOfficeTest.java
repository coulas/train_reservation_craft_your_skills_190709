import data.*;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.*;
import service.BookingService;
import service.BookingServiceImpl;
import service.TrainDataService;
import service.TrainDataServiceImpl;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;

public class TicketOfficeTest {

    @Rule
    public final JUnitSoftAssertions should = new JUnitSoftAssertions();

    private BookingService bookingService = new BookingServiceImpl();
    private TrainDataService trainDataService = new TrainDataServiceImpl();
    private TicketOffice ticketOffice = new TicketOffice(bookingService, trainDataService);

    @Test
    public void reserveSeats() {
        ReservationRequest request = new ReservationRequest("express_2000", 2);
        Reservation reservation = ticketOffice.makeReservation(request);
        should.assertThat(reservation.trainId).isEqualTo(request.trainId);
        should.assertThat(reservation.bookingId).isEqualTo("75bcd15");
        should.assertThat(reservation.seats).contains(new Seat("A", 1),new Seat("A", 2));
    }

    @Test
    public void reserveAnotherTrainId() {
        Reservation reservation = ticketOffice.makeReservation(new ReservationRequest("express_2001", 2));
        assertThat(reservation).isEqualToComparingFieldByField(
                new Reservation(
                        "express_2001",
                        asList(new Seat("A", 1),new Seat("A", 2)),
                        "75bcd15"));
    }

    @Test
    public void reserveAnotherTrainWithANewBookingId() {
        final String bookingReference = "75bcd16";
        ticketOffice = new TicketOffice(new BookingService() {
            @Override
            public String getReference() {
                return bookingReference;
            }
        }, trainDataService);
        Reservation reservation = ticketOffice.makeReservation(new ReservationRequest("express_2001", 2));

        assertThat(reservation).isEqualToComparingFieldByField(
                new Reservation(
                        "express_2001",
                        asList(new Seat("A", 1),new Seat("A", 2)),
                        bookingReference));
    }

    @Test
    public void should_book_unbooked_single_seat() {
        ticketOffice = new TicketOffice(bookingService, new TrainDataService() {
            @Override
            public TrainData getTrainData(String trainId) {
                return  new TrainData(asList(new BookableSeat("toto", "A", 1),new BookableSeat("", "A", 2),new BookableSeat("", "A", 3)));
            }
        });
        Reservation reservation = ticketOffice.makeReservation(new ReservationRequest("express_2000", 1));
        assertThat(reservation).isEqualToComparingFieldByField(
                new Reservation(
                        "express_2000",
                        asList(new Seat("A", 2)),
                        "75bcd15"));

    }


}
