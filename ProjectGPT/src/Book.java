import javafx.beans.property.*;


public class Book {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty category;
    private final StringProperty author;
    private final StringProperty publisher;
    private final StringProperty availability;

    
    
    public Book(int id, String name, String category, String author, String publisher,String availability) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.availability = new SimpleStringProperty(availability);
        
    }

    public Book() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
        this.author = new SimpleStringProperty("");
        this.publisher = new SimpleStringProperty("");
        this.availability = new SimpleStringProperty("");
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty IdProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public String getAvailability() {
        return availability.get();
    }

    public StringProperty AvailabilityProperty() {
        return availability;
    }
}
