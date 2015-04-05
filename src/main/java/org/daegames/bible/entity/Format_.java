package org.daegames.bible.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Format.class)
public class Format_ {
    public static volatile SingularAttribute<Format, Long> formatId;
    public static volatile SingularAttribute<Format, String> version;
    public static volatile SingularAttribute<Format, String> book;
    public static volatile SingularAttribute<Format, Long> chapter;
    public static volatile SingularAttribute<Format, String> start;
    public static volatile SingularAttribute<Format, String> end;
    public static volatile SingularAttribute<Format, String> properties;
}
