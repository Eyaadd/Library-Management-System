import java.time.LocalDate;
import javafx.beans.property.*;

public class BorrowedBook {
    private final IntegerProperty userId;
    private final StringProperty userName;
    private final IntegerProperty bookId;
    private final StringProperty bookName;
    private final IntegerProperty ticketNo;
    private final ObjectProperty<LocalDate> returnDate; 

    public BorrowedBook(int userId, String userName, int bookId, String bookName, int ticketNo, LocalDate returnDate) {
        this.userId = new SimpleIntegerProperty(userId);
        this.userName = new SimpleStringProperty(userName);
        this.bookId = new SimpleIntegerProperty(bookId);
        this.bookName = new SimpleStringProperty(bookName);
        this.ticketNo = new SimpleIntegerProperty(ticketNo);
        this.returnDate = new SimpleObjectProperty<>(returnDate);
    }

    public BorrowedBook(int bookId, String bookName, int ticketNo, LocalDate returnDate) {
        this.userId = new SimpleIntegerProperty(0);
        this.userName = new SimpleStringProperty("");
        this.bookId = new SimpleIntegerProperty(bookId);
        this.bookName = new SimpleStringProperty(bookName);
        this.ticketNo = new SimpleIntegerProperty(ticketNo);
        this.returnDate = new SimpleObjectProperty<>(returnDate);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public int getBookId() {
        return bookId.get();
    }

    public IntegerProperty bookIdProperty() {
        return bookId;
    }

    public String getBookName() {
        return bookName.get();
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }

    public int getTicketNo() {
        return ticketNo.get();
    }

    public IntegerProperty TicketNoProperty() {
        return ticketNo;
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }
}


