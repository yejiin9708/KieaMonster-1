package org.tain.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.db.domain.TbGrpSvr;

@RepositoryRestResource
public interface TbGrpSvrRepository extends JpaRepository<TbGrpSvr, Long>{

}
