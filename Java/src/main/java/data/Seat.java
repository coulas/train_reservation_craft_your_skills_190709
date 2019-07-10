package data;

public class Seat {
    public final String coach;
    public final int seatNumber;

    public Seat(String coach, int seatNumber) {
        this.coach = coach;
        this.seatNumber = seatNumber;
    }

    public boolean equals(Object o) {
        Seat other = (Seat)o;
        return coach==other.coach && seatNumber==other.seatNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Seat{");
        sb.append("coach='").append(coach).append('\'');
        sb.append(", seatNumber=").append(seatNumber);
        sb.append('}');
        return sb.toString();
    }
}