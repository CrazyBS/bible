package org.daegames.bible.repository;

import org.daegames.bible.entity.Bible;
import org.daegames.bible.entity.VerseIdentifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BibleRepository extends PagingAndSortingRepository<Bible,VerseIdentifier>, JpaSpecificationExecutor<Bible> {

    @Query("select distinct verse.book from Bible where verse.version = :version")
    List<String> getAllBooks(@Param("version") String version);
}
