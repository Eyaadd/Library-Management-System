import java.sql.*;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class User {
    public static User user;
    private int userId;
    private String phonenumber;
    private String username;
    private String password;
    private String email;
    private static Connection conn;
    private static final String url = "jdbc:mysql://127.0.0.1/eyaddb";
    private static final String usernamee = "root";
    private static final String passwordd = "eyad07";

    public User() throws SQLException {
        conn = DriverManager.getConnection(url, usernamee, passwordd);
    }

    public User(String username, String password, String email, String phonenumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
    }

        public User(String username) {
        this.username = username;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean signUp(String username, String password, String email, String phonenumber) {
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)) {
            int nextUserId = generateNextUserId(conn);

            String sql = "INSERT INTO user (UserId, Username, Password, Email, Phonenumber) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, nextUserId);
                statement.setString(2, username);
                statement.setString(3, password);
                statement.setString(4, email);
                statement.setString(5, phonenumber);
                setUserId(nextUserId);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean userlogin(String username, String password) throws SQLException {
        String name;
        Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
        String sqlcheckLogin = "SELECT * FROM user WHERE BINARY Username=? AND BINARY Password=?";
        PreparedStatement statement = conn.prepareStatement(sqlcheckLogin);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet checkLoginResult = statement.executeQuery();

        if (checkLoginResult.next()) {
            name = checkLoginResult.getString("Username");

            return true;
        } else {
            return false;
        }
    }

    private int generateNextUserId(Connection conn) throws SQLException {
        String sqlGetMaxUserId = "SELECT MAX(UserId) AS max_id FROM user";
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlGetMaxUserId);
            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                return maxId + 1; // Increment the maximum UserID
            }
        }
        // If no existing users, start with ID 1
        return 1;
    }

    public static String getUserUsername(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(url, usernamee, passwordd);

        String query = "SELECT Username FROM user WHERE Username = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                } else {
                    return null;
                }
            }
        }
    }

    public static String getUserEmail(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(url, usernamee, passwordd);

        String query = "SELECT Email FROM user WHERE Username = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                } else {
                    return null;
                }
            }
        }
    }

    public static int getUserID(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(url, usernamee, passwordd);

        String query = "SELECT UserId FROM user WHERE Username = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("userId");
                } else {
                    return 0;
                }
            }
        }
    }

    public static boolean ForgotPassword_EmailCheck(String email) throws SQLException {
            String name;
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            String sqlcheckLogin = "SELECT * FROM user WHERE BINARY Email=?";
            PreparedStatement statement = conn.prepareStatement(sqlcheckLogin);
            statement.setString(1, email);
            ResultSet checkLoginResult = statement.executeQuery();

            if (checkLoginResult.next()) {
                name = checkLoginResult.getString("Email");
                return true;
            } else {
                return false;
            }
        }

    public static void ForgotPassword_NewPaswword(String newPassword, String email) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            String sqlUpdateUser = "UPDATE user SET Password = ? WHERE Email = ? " ;
            PreparedStatement updateUser = conn.prepareStatement(sqlUpdateUser);
            updateUser.setString(1, newPassword);
            updateUser.setString(2, email);
            updateUser.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public ObservableList<Book> User_getBooksFromDatabase() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
    
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            Statement stmt = conn.createStatement();
            String sql = "SELECT BookID, BookName, BookCategory, BookAuthor, Publisher , Availability FROM book";
    
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int bookId = rs.getInt("BookID");
                String bookName = rs.getString("BookName");
                String category = rs.getString("BookCategory");
                String author = rs.getString("BookAuthor");
                String publisher = rs.getString("Publisher");
                String availability = rs.getString("Availability");

    
                Book book = new Book(bookId, bookName, category, author, publisher, availability);
                bookList.add(book);
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return bookList;
    }

    public ObservableList<BorrowedBook> getUserBorrowedBooks(String user_username) {
        ObservableList<BorrowedBook> borrowedBooksList = FXCollections.observableArrayList();
    
        String sql = "SELECT book_id, book_name, booking_id, return_date FROM booking WHERE user_username = ?";
        
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, user_username);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int bookId = rs.getInt("book_id");
                    String bookName = rs.getString("book_name");
                    int bookingId = rs.getInt("booking_id");
                    LocalDate returnDate = rs.getDate("return_date").toLocalDate(); 
    
                    BorrowedBook userBorrowedBook = new BorrowedBook(bookId, bookName, bookingId, returnDate);
                    borrowedBooksList.add(userBorrowedBook);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions here
            e.printStackTrace();
        }
    
        return borrowedBooksList;
    }

    private static String getBookNameByID(Connection conn, int bookId) throws SQLException {
        String sql = "SELECT * FROM book WHERE BookID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, bookId);
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("BookName");
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    private static boolean isBookExists(Connection conn, int bookId, String bookName) throws SQLException {
        String sql = "SELECT * FROM book WHERE BookID = ? AND BINARY BookName = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setString(2, bookName);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static boolean isBookAvailable(Connection conn, int bookId) throws SQLException {
        String sql = "SELECT Availability FROM book WHERE BookID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                return resultSet.next() && resultSet.getString("Availability").equals("Available");
            }
        }
    }

    private static int generateBookingId(Connection conn) throws SQLException {
        String sqlGetMaxBookingId = "SELECT MAX(booking_id) AS max_id FROM booking";
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlGetMaxBookingId);
            if (resultSet.next()) {
                int maxBookingId = resultSet.getInt("max_id");
                return maxBookingId + 1; 
            }
        }
        return 1;
    }

    public static void performBooking(String username, int BookId, String bookName, LocalDate returnDate) throws SQLException {
        java.sql.Date bookingDate = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date sqlDate = java.sql.Date.valueOf(returnDate);


        try (Connection conn = DriverManager.getConnection(url,  usernamee, passwordd)) {
            int userId = getUserID(username);
            int bookingId = generateBookingId(conn);

            if (userId == -1 || !isBookExists(conn, BookId, bookName)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("# Invalid username or book ID. \n# Booking Failed.");
                alert.showAndWait();
                return;
            }

            if (!isBookAvailable(conn, BookId)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("# Book is not available for booking.\n# Booking failed.");
                alert.showAndWait();
                return;
            }

            if (isBookAvailable(conn, BookId)) {
                String sqlInsertBooking = "INSERT INTO booking (booking_id, user_id, book_id, booking_date, user_username, book_name, return_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertBooking)) {
                    pstmt.setInt(1, bookingId);
                    pstmt.setInt(2, userId);
                    pstmt.setInt(3, BookId);
                    pstmt.setDate(4, new java.sql.Date(bookingDate.getTime()));
                    pstmt.setString(5, username);
                    pstmt.setString(6, getBookNameByID(conn, BookId));
                    pstmt.setDate(7,sqlDate);
                    pstmt.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# '"+ getBookNameByID(conn, BookId) +"' Borrowed Successfully !\n# Ticket No. : " + bookingId);
                    alert.showAndWait();                
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("# Book is not available for booking.\n# Booking failed.");
                alert.showAndWait();
            }

            String sqlUpdateBook = "UPDATE book SET availability = 'Borrowed' WHERE BookID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdateBook)) {
                pstmt.setInt(1, BookId);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isUserBooked(int userId, int bookId) throws SQLException {
        String sql = "SELECT * FROM Booking WHERE user_id = ? AND Book_id = ?";

        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)){
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, bookId);
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isBookBooked(int bookId) throws SQLException {
        String sql = "SELECT * FROM Booking WHERE Book_id = ?";

        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)){
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, bookId);
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("# Book is Already Booked By a User. \n# Removing Book Failed.");
            alert.showAndWait();
            return false;
        }
    }

    public static void cancelBooking(String username, int bookId, String bookName) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)) {
            int userId = getUserID(username);

            if (userId == -1 || !isBookExists(conn, bookId, bookName)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("# Invalid username or book ID. \n# Return failed.");
                alert.showAndWait();
                return;
            }

            if (!isUserBooked(userId, bookId)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("# User has not booked the specified book.  \n# Return failed.");
                alert.showAndWait();
                return;
            }

            String sqlDeleteBooking = "DELETE FROM booking WHERE user_id = ? AND book_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlDeleteBooking)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, bookId);
                int deletedRows = pstmt.executeUpdate();

                if (deletedRows > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# Book has been successfully Returned.\n # Retruned Book ID: " + bookId);
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("# Return Failed.\n # No Books Found For ID: " + bookId);
                    alert.showAndWait();
                }
            }

            String sqlUpdateBook = "UPDATE book SET availability = 'Available' WHERE BookID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdateBook)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
