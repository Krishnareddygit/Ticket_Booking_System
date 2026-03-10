import java.util.Random;

public class UserBooking implements Runnable {
    private final TicketBooking ticketBooking;
    private final String userName;
    private final Random random = new Random();

    public UserBooking(TicketBooking ticketBooking, String userName) {
        this.ticketBooking = ticketBooking;
        this.userName = userName;
    }

    @Override
    public void run() {
        int reqTickets = random.nextInt(3) + 1;
        ticketBooking.bookTickets(userName,reqTickets);
    }
}
