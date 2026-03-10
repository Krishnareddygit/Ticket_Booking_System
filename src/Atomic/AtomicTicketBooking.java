package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTicketBooking {

    private final int initialTickets;
    private final AtomicInteger availableTickets;
    private final AtomicInteger totalSoldTickets = new AtomicInteger(0);

    public AtomicTicketBooking(int initialTickets) {
        this.initialTickets = initialTickets;
        this.availableTickets = new AtomicInteger(initialTickets);
    }

    public void bookTickets(String user, int reqTickets) {

        if (reqTickets <= 0 || reqTickets > 3) {
            System.out.println(user + " requested invalid ticket count: " + reqTickets);
            return;
        }

        while (true) {

            int currentAvailable = availableTickets.get();

            if (currentAvailable == 0) {
                System.out.println(user + " requested " + reqTickets + " tickets → Failed (Sold Out)");
                return;
            }

            if (reqTickets > currentAvailable) {
                System.out.println(user + " requested " + reqTickets + " tickets → Failed (Not enough tickets)");
                return;
            }

            if (availableTickets.compareAndSet(currentAvailable, currentAvailable - reqTickets)) {

                totalSoldTickets.addAndGet(reqTickets);

                System.out.println(user + " requested " + reqTickets +
                        " tickets → Booked → Remaining: " + availableTickets.get());

                return;
            }
        }
    }

    public int getInitialTickets() {
        return initialTickets;
    }

    public int getAvailableTickets() {
        return availableTickets.get();
    }

    public int getTotalSoldTickets() {
        return totalSoldTickets.get();
    }
}
