public class TicketBooking {
    private int availableTickets;
    private int totalSoldTickets = 0;

    private boolean isClosed = false;

    public TicketBooking(int availableTickets){
        this.availableTickets = availableTickets;
    }

    public synchronized void bookTickets(String user, int reqTickets) {

        if (reqTickets <= 0 || reqTickets > 3) {
            System.out.println(user + " requested invalid ticket count: " + reqTickets);
            return;
        }

        if (isClosed) {
            System.out.println(user + " requested " + reqTickets + " tickets → Failed (Sold Out)");
            return;
        }

        if (reqTickets > availableTickets) {
            System.out.println(user + " requested " + reqTickets + " tickets → Failed (Not enough tickets)");
            return;
        }

        availableTickets -= reqTickets;
        totalSoldTickets += reqTickets;

        System.out.println(user + " requested " + reqTickets +
                " tickets → Booked → Remaining: " + availableTickets);

        if (availableTickets == 0) {
            isClosed = true;
            System.out.println("All tickets sold out → Booking closed");
        }
    }

    public int getAvailableTickets(){
        return availableTickets;
    }

    public int getTotalSoldTickets(){
        return totalSoldTickets;
    }

    public synchronized boolean isClosed() {
        return isClosed;
    }
}
