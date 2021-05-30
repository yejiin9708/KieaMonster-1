package org.tain.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.db.domain.TbUsr;

@RepositoryRestResource
public interface TbUsrRepository extends JpaRepository<TbUsr, Long>{
}
