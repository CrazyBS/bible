package org.daegames.bible.resource;

import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by CrazyBS on 4/4/2015.
 */
public class FormatResource extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 42L;

    @NotNull
    @Min(1)
    private Long formatId;

    @Size(min = 1, max = 50)
    @NotNull
    private String version;

    @Size(min = 1, max = 30)
    @NotNull
    private String book;

    @NotNull
    @Min(1)
    private Long chapter;

    @NotNull
    @Pattern(regexp = "\\d+(\\.\\d+)?")
    private String start;

    @NotNull
    @Pattern(regexp = "\\d+(\\.\\d+)?")
    private String end;

    @NotNull
    @Size(max=100)
    private String properties;

    public FormatResource() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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
