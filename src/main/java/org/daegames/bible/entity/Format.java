package org.daegames.bible.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Format implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @Column(name = "FORMAT_ID")
    private Long formatId;

    @Basic
    private String version;

    @Basic
    private String book;

    @Basic
    private Long chapter;

    @Basic
    @Column(name = "VERSE_START")
    private String start;

    @Basic
    @Column(name = "VERSE_END")
    private String end;

    @Basic
    private String properties;

    public Long getFormatId() {
        return formatId;
    }

    public void setFormatId(Long formatId) {
        this.formatId = formatId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Long getChapter() {
        return chapter;
    }

    public void setChapter(Long chapter) {
        this.chapter = chapter;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
