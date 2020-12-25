package org.hsmak.serializers.serDesrOfXml;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD) // force annotations to be on fields instead of setters/getter
@XmlType(propOrder = {"id", "name", "date"})
public class Book {

    @XmlAttribute
    private Long id;

    @XmlElement(name = "title")
    private String name;

    @XmlTransient
    private String author;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate date;

    public Long getId() {
        return id;
    }

    //    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    //    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    //    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }

    //    @XmlJavaTypeAdapter(DateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }
}