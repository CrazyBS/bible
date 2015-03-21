package org.daegames.bible.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by CrazyBS on 3/7/2015.
 */
@StaticMetamodel(VerseIdentifier.class)
public class VerseIdentifier_ {
    public static volatile SingularAttribute<VerseIdentifier, String> version;
    public static volatile SingularAttribute<VerseIdentifier, String> book;
    public static volatile SingularAttribute<VerseIdentifier, Long> chapter;
    public static volatile SingularAttribute<VerseIdentifier, Long> verse;
}
