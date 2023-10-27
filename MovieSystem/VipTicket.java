package MovieSystem;

class VipTicket extends Ticket {
    private String specialAccess;

    public VipTicket(Movie movie, String showTime, String specialAccess) {
        super(movie, showTime);
        this.specialAccess = specialAccess;
    }

    public String getSpecialAccess() {
        return specialAccess;
    }
}