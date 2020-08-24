package streamOps.lambda.enums;

import java.util.Date;

/**
 * Created by hsmak on 11/12/16.
 */
public class Book implements Comparable<Book>{

    private String author;
    private String title;
    private Date date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return getTitle().compareTo(o.getTitle());
    }
}
