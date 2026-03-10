package Atomic;

import java.util.concurrent.*;

public class AtomicConcertBooking {

    public static void main(String[] args) {

        int initialTickets = 50;
        int totalUsers = 100;

        AtomicTicketBooking ticket = new AtomicTicketBooking(initialTickets);

        ExecutorService executor =
                new ThreadPoolExecutor(
                        5,
                        10,
                        60,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(50),
                        new ThreadPoolExecutor.CallerRunsPolicy()
                );

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= totalUsers; i++) {
            executor.submit(new AtomicUserBooking(ticket, "User-" + i));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\n----------------------------------");
        System.out.println("Summary");
        System.out.println("----------------------------------");
        System.out.println("Initial Tickets: " + ticket.getInitialTickets());
        System.out.println("Total Tickets Sold: " + ticket.getTotalSoldTickets());
        System.out.println("Remaining Tickets: " + ticket.getAvailableTickets());
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }
}
