package MovieSystem;

class Ticket {
    private Movie movie;
    private String showTime;

    public Ticket(Movie movie, String showTime) {
        this.movie = movie;
        this.showTime = showTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowTime() {
        return showTime;
    }
}

// class VipTicket extends Ticket {
//     private String specialAccess;

//     public VipTicket(Movie movie, String showTime, String specialAccess) {
//         super(movie, showTime);
//         this.specialAccess = specialAccess;
//     }

//     public String getSpecialAccess() {
//         return specialAccess;
//     }
// }