import java.util.concurrent.locks.ReentrantLock;

public class TicketBooking {
    public static void main(String[] args) {
        Passenger[] passengers = {
            new Passenger("VIP1", Thread.MAX_PRIORITY),
            new Passenger("VIP2", Thread.MAX_PRIORITY),
            new Passenger("Regular1", Thread.NORM_PRIORITY),
            new Passenger("Regular2", Thread.NORM_PRIORITY),
            new Passenger("VIP3", Thread.MAX_PRIORITY),
            new Passenger("Regular3", Thread.NORM_PRIORITY),
            new Passenger("Regular4", Thread.NORM_PRIORITY),
            new Passenger("Regular5", Thread.NORM_PRIORITY),
            new Passenger("VIP4", Thread.MAX_PRIORITY),
            new Passenger("Regular6", Thread.NORM_PRIORITY),
            new Passenger("Regular7", Thread.NORM_PRIORITY)
        };
        
        for (Passenger p : passengers) {
            p.start();
        }
    }
}

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private static int availableSeats = TOTAL_SEATS;
    private static final ReentrantLock lock = new ReentrantLock();

    public static void bookTicket(String name, int priority) {
        Thread.currentThread().setPriority(priority);
        synchronized (lock) {
            if (availableSeats > 0) {
                System.out.println(name + " booked a seat. Seats left: " + (--availableSeats));
            } else {
                System.out.println(name + " failed to book. No seats available.");
            }
        }
    }
}

class Passenger extends Thread {
    private final String name;
    private final int priority;

    public Passenger(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public void run() {
        TicketBookingSystem.bookTicket(name, priority);
    }
}


