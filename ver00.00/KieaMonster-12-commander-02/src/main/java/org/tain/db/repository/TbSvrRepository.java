package org.tain.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.db.domain.TbSvr;

@RepositoryRestResource
public interface TbSvrRepository extends JpaRepository<TbSvr, Long>{

}
