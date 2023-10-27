package MovieSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Movie> movies = new Vector<>();

        Movie movie1 = new Movie("Baahubali", "Comedy", 250.0, 100);
        movie1.addShowTime("10:00 AM");
        movie1.addShowTime("2:00 PM");
        movie1.addShowTime("7:00 PM");

        Movie movie2 = new Movie("Oppenheimer", "Drama", 300.0, 80);
        movie2.addShowTime("11:00 AM");
        movie2.addShowTime("4:00 PM");
        movie2.addShowTime("8:00 PM");

        Movie movie3 = new Movie("Barbie", "Action", 150.0, 100);
        movie3.addShowTime("7:00 AM");
        movie3.addShowTime("6:00 PM");
        movie3.addShowTime("11:00 PM");

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        while (true) {
            System.out.println("\nMovie Ticket Booking System");
            System.out.println("1. List Movies");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View Purchased Tickets");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Movies:");
                    for (int i = 0; i < movies.size(); i++) {
                        System.out.println((i + 1) + ". " + movies.get(i).getTitle() + "\tPrice: "
                                + movies.get(i).getTicketPrice() + "\tShowTimes: " + movies.get(i).getShowTimes());
                    }
                    break;

                case 2:
                    System.out.print("\nEnter the movie number: ");
                    int movieNumber = sc.nextInt();
                    sc.nextLine();

                    if (movieNumber >= 1 && movieNumber <= movies.size()) {
                        Movie selectedMovie = movies.get(movieNumber - 1);
                        System.out.println("\nShow Times for " + selectedMovie.getTitle() + ":");

                        for (int i = 0; i < selectedMovie.getShowTimes().size(); i++) {
                            System.out.println((i + 1) + ". " + selectedMovie.getShowTimes().get(i));
                        }
                        System.out.print("\nEnter the show time number: ");
                        int showTimeNumber = sc.nextInt();
                        sc.nextLine();

                        if (showTimeNumber >= 1 && showTimeNumber <= selectedMovie.getShowTimes().size()) {
                            String selectedShowTime = selectedMovie.getShowTimes().get(showTimeNumber - 1);
                            System.out.print("\nDo you want to book VIP tickets? (Y/N): ");
                            String vipChoice = sc.nextLine();

                            if (vipChoice.equalsIgnoreCase("Y")) {
                                System.out.print("\nEnter the number of VIP seats you want to buy: ");
                                int numVipSeats = sc.nextInt();
                                sc.nextLine();

                                if (selectedMovie.bookVipTickets(selectedShowTime, numVipSeats)) {
                                    double vipTicketPrice = selectedMovie.getTicketPrice() * 1.5; // 50% extra price for
                                                                                                  // VIP
                                    double totalPrice = numVipSeats * vipTicketPrice;
                                    System.out
                                            .println("\nVIP Tickets booked successfully for " + selectedMovie.getTitle()
                                                    + " at " + selectedShowTime);
                                    System.out.println("Total Price (VIP): ₹" + totalPrice);
                                } else {
                                    System.out.println("\nSorry, not enough VIP seats available for this show.");
                                }
                            } else {
                                System.out.print("\nEnter the number of regular seats you want to buy: ");
                                int numSeats = sc.nextInt();
                                sc.nextLine();

                                if (selectedMovie.bookTickets(selectedShowTime, numSeats)) {
                                    double totalPrice = numSeats * selectedMovie.getTicketPrice();
                                    System.out.println(
                                            "\nRegular Tickets booked successfully for " + selectedMovie.getTitle()
                                                    + " at " + selectedShowTime);
                                    System.out.println("Total Price (Regular): ₹" + totalPrice);
                                } else {
                                    System.out.println("\nSorry, not enough regular seats available for this show.");
                                }
                            }
                        } else {
                            System.out.println("\nInvalid show time number.");
                        }
                    } else {
                        System.out.println("\nInvalid movie number.");
                    }
                    break;
                case 3:
                    System.out.println("\nPurchased Tickets:");

                    for (Movie movie : movies) {
                        Vector<Ticket> bookedTickets = movie.getBookedTickets();
                        Vector<VipTicket> bookedVipTickets = movie.getBookedVipTickets(); // Add this line

                        if (!bookedTickets.isEmpty() || !bookedVipTickets.isEmpty()) { // Modify this condition
                            System.out.println("\nTickets for " + movie.getTitle() + ":");

                            // Create a Map to group regular tickets by showtime
                            Map<String, Integer> showtimeSeatCount = new HashMap<>();
                            for (Ticket ticket : bookedTickets) {
                                String showTime = ticket.getShowTime();
                                showtimeSeatCount.put(showTime, showtimeSeatCount.getOrDefault(showTime, 0) + 1);
                            }

                            // Create a Map to group VIP tickets by showtime
                            Map<String, Integer> showtimeSeatCountVip = new HashMap<>();
                            for (VipTicket vipTicket : bookedVipTickets) { // Iterate through VIP tickets
                                String showTime = vipTicket.getShowTime();
                                showtimeSeatCountVip.put(showTime, showtimeSeatCountVip.getOrDefault(showTime, 0) + 1);
                            }

                            // Display regular tickets by showtime
                            for (Map.Entry<String, Integer> entry : showtimeSeatCount.entrySet()) {
                                System.out.println("\nShow Time: " + entry.getKey());
                                System.out.println(
                                        "Total Regular Seats Purchased for this showtime: " + entry.getValue());
                            }

                            // Display VIP tickets by showtime
                            for (Map.Entry<String, Integer> entry : showtimeSeatCountVip.entrySet()) {
                                System.out.println("\nShow Time: " + entry.getKey());
                                System.out.println("Total ****VIP**** Seats Purchased for this showtime: " + entry.getValue());
                            }

                            // Calculate and display the total seats booked for the movie
                            int totalSeatsPurchasedForMovie = bookedTickets.size() + bookedVipTickets.size(); // Modify
                                                                                                              // this
                                                                                                              // line
                            System.out.println("\nTotal Seats Purchased for " + movie.getTitle() + ": "
                                    + totalSeatsPurchasedForMovie);
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nSelect the movie to cancel a ticket for:");

                    for (int i = 0; i < movies.size(); i++) {
                        System.out.println((i + 1) + ". " + movies.get(i).getTitle());
                    }

                    System.out.print("\nEnter the movie number: ");
                    int cancelMovieNumber = sc.nextInt();
                    sc.nextLine();

                    if (cancelMovieNumber >= 1 && cancelMovieNumber <= movies.size()) {
                        Movie selectedMovie = movies.get(cancelMovieNumber - 1);
                        Vector<Ticket> bookedTickets = selectedMovie.getBookedTickets();

                        if (!bookedTickets.isEmpty()) {
                            System.out.println("\nSelect the showtime to cancel a ticket for:");

                            for (int i = 0; i < selectedMovie.getShowTimes().size(); i++) {
                                System.out.println((i + 1) + ". " + selectedMovie.getShowTimes().get(i));
                            }

                            System.out.print("\nEnter the showtime number: ");
                            int cancelShowtimeNumber = sc.nextInt();
                            sc.nextLine();

                            if (cancelShowtimeNumber >= 1
                                    && cancelShowtimeNumber <= selectedMovie.getShowTimes().size()) {
                                String selectedShowtime = selectedMovie.getShowTimes().get(cancelShowtimeNumber - 1);
                                System.out.print("\nEnter the number of seats to cancel:");
                                int numSeatsToCancel = sc.nextInt();
                                sc.nextLine();

                                if (selectedMovie.cancelTicket(numSeatsToCancel)) {
                                    System.out.println("\n" + numSeatsToCancel + " seat(s) canceled successfully for "
                                            + selectedMovie.getTitle() + " at " + selectedShowtime);
                                } else {
                                    System.out.println("\nTicket cancellation failed. Invalid number of seats.");
                                }
                            } else {
                                System.out.println("\nInvalid showtime number.");
                            }
                        } else {
                            System.out.println("\nNo purchased tickets for this movie.");
                        }
                    } else {
                        System.out.println("\nInvalid movie number.");
                    }
                    break;

                case 5:
                    System.out.println("\nThank you for using the Movie Ticket Booking System.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("\nInvalid choice. Please enter a valid option.");
            }
        }
    }
}
