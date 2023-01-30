package domain;

public class Book {
    private String bookId;
    private String bookName;
    private String author;
    private String ISBN;
    private String press;
    private int total;
    private int remain;

    public Book(){
    }

    public String getbookId(){
        return this.bookId;
    }
    public void setbookId(String bookId) {
        this.bookId = bookId;
    }

    public String getbookName(){
        return this.bookName;
    }
    public void setbookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor(){
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN(){
        return this.ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPress(){
        return this.press;
    }
    public void setPress(String press) {
        this.press = press;
    }

    public int getTotal(){
        return this.total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemain(){
        return this.remain;
    }
    public void setRemain(int remain) {
        this.remain = remain;
    }

}
