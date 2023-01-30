package domain;

public class Log {

    private String userId;
    private String bookId;

    private int num;

    private String status;

    public Log() {
    }

    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return this.bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }



    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

