package org.daegames.bible.entity;

import org.daegames.bible.resource.BibleResource;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Bible implements Serializable {

    private static final long serialVersionUID = 42L;

    @EmbeddedId
    private VerseIdentifier verse;

    @Basic
    private String text;

    @Basic
    private String header;

    @Basic
    private String properties;

    public Bible() { }

    public Bible(BibleResource verse) {
        this.verse = new VerseIdentifier();
        this.verse.setVersion(verse.getVersion());
        this.verse.setBook(verse.getBook());
        this.verse.setChapter(verse.getChapter());
        this.verse.setVerse(verse.getVerse());
        this.text = verse.getText();
        this.header = verse.getHeader();
        this.properties = verse.getProperties();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public VerseIdentifier getVerse() {
        return verse;
    }

    public void setVerse(VerseIdentifier verse) {
        this.verse = verse;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
