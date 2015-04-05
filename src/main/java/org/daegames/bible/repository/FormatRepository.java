package org.daegames.bible.repository;

import org.daegames.bible.entity.Format;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface FormatRepository extends PagingAndSortingRepository<Format,Long>, JpaSpecificationExecutor<Format> {
}
