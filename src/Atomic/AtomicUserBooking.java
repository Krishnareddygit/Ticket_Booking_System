package Atomic;

import java.util.concurrent.ThreadLocalRandom;

public class AtomicUserBooking implements Runnable {

    private final AtomicTicketBooking ticketBooking;
    private final String userName;

    public AtomicUserBooking(AtomicTicketBooking ticketBooking, String userName) {
        this.ticketBooking = ticketBooking;
        this.userName = userName;
    }

    @Override
    public void run() {

        int reqTickets = ThreadLocalRandom.current().nextInt(1, 4);

        ticketBooking.bookTickets(userName, reqTickets);
    }
}
