package org.daegames.bible.specification;

import org.daegames.bible.entity.Bible;
import org.daegames.bible.entity.Bible_;
import org.daegames.bible.entity.VerseIdentifier;
import org.daegames.bible.entity.VerseIdentifier_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BibleSpecification {
    public static Specification<Bible> isVersion(final String version) {
        return new Specification<Bible>() {
            @Override
            public Predicate toPredicate(Root<Bible> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Bible_.verse).get(VerseIdentifier_.version), version);
            }
        };
    }

    public static Specification<Bible> isBook(final String book) {
        return new Specification<Bible>() {
            @Override
            public Predicate toPredicate(Root<Bible> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Bible_.verse).get(VerseIdentifier_.book), book);
            }
        };
    }

    public static Specification<Bible> isChapter(final Long chapter) {
        return new Specification<Bible>() {
            @Override
            public Predicate toPredicate(Root<Bible> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Bible_.verse).get(VerseIdentifier_.chapter), chapter);
            }
        };
    }

    public static Specification<Bible> isVerse(final Long verse) {
        return new Specification<Bible>() {
            @Override
            public Predicate toPredicate(Root<Bible> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Bible_.verse).get(VerseIdentifier_.verse), verse);
            }
        };
    }
}
