public class TicketBooking {
    private int availableTickets;
    private int totalSoldTickets = 0;

    public TicketBooking(int availableTickets){
        this.availableTickets = availableTickets;
    }

    public synchronized void bookTickets(String user, int reqTickets) throws InterruptedException {
        while (reqTickets > availableTickets) {

            System.out.println(user + " requested " + reqTickets + " tickets → Waiting (Not enough tickets)");

            try {
                wait();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        availableTickets -= reqTickets;
        totalSoldTickets += reqTickets;
        System.out.println(user + " requested " + reqTickets + " tickets → Booked → Remaining: " + availableTickets);

        notifyAll();
    }

    public int getAvailableTickets(){
        return availableTickets;
    }

    public int getTotalSoldTickets(){
        return totalSoldTickets;
    }
}
