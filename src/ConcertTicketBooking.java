import java.util.Scanner;
import java.util.concurrent.*;

public class ConcertTicketBooking {
    public static void main(String[] args) {

        int intialTickets = 50;
        int totalUsers = 100;

        TicketBooking ticket = new TicketBooking(intialTickets);

        ExecutorService executor =
                new ThreadPoolExecutor(
                        5,
                        10,
                        60,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(30),
                        new ThreadPoolExecutor.CallerRunsPolicy()
                );

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= totalUsers; i++) {
            executor.submit(new UserBooking(ticket, "User-" + i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\n----------------------------------");
        System.out.println("Summary");
        System.out.println("----------------------------------");
        System.out.println("Initial Tickets: " + intialTickets);
        System.out.println("Total Tickets Sold: " + ticket.getTotalSoldTickets());
        System.out.println("Remaining Tickets: " + ticket.getAvailableTickets());
        System.out.println("Total time of Execution:" + (endTime - startTime) + " ms");
    }
}
