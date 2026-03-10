import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcertTicketBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int intialTickets = sc.nextInt();
        int totalUsers = sc.nextInt();

        TicketBooking ticket = new TicketBooking(intialTickets);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= totalUsers; i++) {
            executor.submit(new UserBooking(ticket, "User-" + i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n----------------------------------");
        System.out.println("Summary");
        System.out.println("----------------------------------");
        System.out.println("Initial Tickets: " + intialTickets);
        System.out.println("Total Tickets Sold: " + ticket.getTotalSoldTickets());
        System.out.println("Remaining Tickets: " + ticket.getAvailableTickets());


    }
}
