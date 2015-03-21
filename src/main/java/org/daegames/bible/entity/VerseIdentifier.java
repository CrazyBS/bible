package org.daegames.bible.entity;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* Created by CrazyBS on 3/6/2015.
*/

@Embeddable
public class VerseIdentifier implements Serializable {

    private static final long serialVersionUID = 42L;

    @Basic
    private String version;

    @Basic
    private String book;

    @Basic
    private Long chapter;

    @Basic
    private Long verse;

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

    public Long getVerse() {
        return verse;
    }

    public void setVerse(Long verse) {
        this.verse = verse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerseIdentifier that = (VerseIdentifier) o;

        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (chapter != null ? !chapter.equals(that.chapter) : that.chapter != null) return false;
        if (verse != null ? !verse.equals(that.verse) : that.verse != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (chapter != null ? chapter.hashCode() : 0);
        result = 31 * result + (verse != null ? verse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return version + "/" + book + "/" + chapter + "." + verse;
    }
}
