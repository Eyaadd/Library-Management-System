public class Booking {
    private int bookingId;
    private int userId;
    private int BookId;
    private String bookingDate;

    public Booking(int bookingId, int userId, int BookId, String bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.BookId = BookId;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }


}
