package org.tain.repository.campPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.tain.domain.campPage.CampPage;

@RepositoryRestResource
@Transactional
public interface CampPageRepository extends JpaRepository<CampPage, Long>{

}
