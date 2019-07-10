package data;

public class BookableSeat {
    private final String bookingId;
    private final String coach;
    private final int seatNumber;

    public BookableSeat(String bookingId, String coach, int seatNumber) {
        this.bookingId = bookingId;
        this.coach = coach;
        this.seatNumber = seatNumber;
    }

    public String getCoach() {
        return coach;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("data.BookableSeat{");
        sb.append("bookingId='").append(bookingId).append('\'');
        sb.append(", coach='").append(coach).append('\'');
        sb.append(", seatNumber=").append(seatNumber);
        sb.append('}');
        return sb.toString();
    }
}
