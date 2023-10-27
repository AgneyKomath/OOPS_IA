package MovieSystem;

import java.util.Vector;

class Movie {
    private String title;
    private String genre;
    private double ticketPrice;
    private int totalSeats;
    private Vector<String> showTimes;
    private Vector<Ticket> bookedTickets;
    private Vector<VipTicket> bookedVipTickets; // Add a Vector for VIP tickets

    public Movie(String title, String genre, double ticketPrice, int totalSeats) {
        this.title = title;
        this.genre = genre;
        this.ticketPrice = ticketPrice;
        this.totalSeats = totalSeats;
        this.showTimes = new Vector<>();
        this.bookedTickets = new Vector<>();
        this.bookedVipTickets = new Vector<>(); // Initialize the VIP ticket Vector
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public Vector<String> getShowTimes() {
        return showTimes;
    }

    public void addShowTime(String time) {
        showTimes.add(time);
    }

    public boolean bookTickets(String showTime, int numSeats) {
        if (totalSeats >= numSeats) {
            for (int i = 0; i < numSeats; i++) {
                bookedTickets.add(new Ticket(this, showTime));
            }
            totalSeats -= numSeats;
            return true;
        }
        return false;
    }

    public boolean bookVipTickets(String showTime, int numSeats) {
        if (totalSeats >= numSeats) {
            for (int i = 0; i < numSeats; i++) {
                bookedVipTickets.add(new VipTicket(this, showTime, "VIP Access")); // Create VIP tickets
            }
            totalSeats -= numSeats;
            return true;
        }
        return false;
    }

    public Vector<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public Vector<VipTicket> getBookedVipTickets() {
        return bookedVipTickets;
    }

    public boolean cancelTicket(int numSeatsToCancel) {
        if (numSeatsToCancel > 0 && numSeatsToCancel <= bookedTickets.size()) {
            for (int i = 0; i < numSeatsToCancel; i++) {
                bookedTickets.remove(bookedTickets.size() - 1);
                totalSeats++;
            }
            return true;
        }
        return false;
    }
}
