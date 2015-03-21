package org.daegames.bible.resource;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BibleResource extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 42L;

    @Size(min = 1, max = 50)
    @NotNull
    private String version;

    @Size(min = 1, max = 30)
    @NotNull
    private String book;

    @Min(1)
    @NotNull
    private Long chapter;

    @Min(1)
    @NotNull
    private Long verse;

    @Size(min = 1, max = 300)
    @NotNull
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
