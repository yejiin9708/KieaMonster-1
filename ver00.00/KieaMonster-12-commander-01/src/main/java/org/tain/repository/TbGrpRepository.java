package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.TbGrp;

@RepositoryRestResource
public interface TbGrpRepository extends JpaRepository<TbGrp, Long>{

}
