package org.daegames.bible.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

/**
 * Created by CrazyBS on 3/6/2015.
 */
@StaticMetamodel(Bible.class)
public class Bible_ {
    public static volatile SingularAttribute<Bible, VerseIdentifier> verse;
    public static volatile SingularAttribute<Bible, String> text;
}
