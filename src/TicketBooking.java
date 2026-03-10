public class TicketBooking {
    private int availableTickets;
    private int totalSoldTickets = 0;

    private boolean isClosed = false;

    public TicketBooking(int availableTickets){
        this.availableTickets = availableTickets;
    }

    public synchronized void bookTickets(String user, int reqTickets) throws InterruptedException {
        while (reqTickets > availableTickets && !isClosed) {

            System.out.println(user + " requested " + reqTickets + " tickets → Waiting (Not enough tickets)");

            try {
                wait();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        if (isClosed) {
            System.out.println(user + " requested " + reqTickets + " tickets → Booking closed (Sold out)");
            return;
        }

        availableTickets -= reqTickets;
        totalSoldTickets += reqTickets;
        System.out.println(user + " requested " + reqTickets + " tickets → Booked → Remaining: " + availableTickets);

        if (availableTickets == 0) {
            isClosed = true;
            System.out.println("--------------------------------------");
            System.out.println("All tickets sold out → Booking closed");
            System.out.println("----------------------------------------");
        }else{
            notifyAll();
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
