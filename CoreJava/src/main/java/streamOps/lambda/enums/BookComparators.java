package streamOps.lambda.enums;

import java.util.Comparator;

/**
 * Created by hsmak on 11/12/16.
 */
public enum BookComparators {

    // Alternatively, a different approach
    // public enum BookComparators implements Comparator<Book>{

    // Lambda Expression
    COMPARABLE_SORT((a1, a2) -> a1.compareTo(a2)),

    //Method References
    AUTHOR_SORT(Comparator.comparing(Book::getAuthor)),
    TITLE_SORT(Comparator.comparing(Book::getTitle)),
    DATE_SORT(Comparator.comparing(Book::getDate)),

    //Reversed Order
    AUTHOR_SORT_REVERSED(Comparator.comparing(Book::getAuthor).reversed()),
    TITLE_SORT_REVERSED(Comparator.comparing(Book::getTitle).reversed()),
    DATE_SORT_REVERSED(Comparator.comparing(Book::getDate).reversed());

    private Comparator<Book> c;

    private BookComparators(Comparator<Book> c) {
        this.c = c;
    }

}
