package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.TbOrg;

@RepositoryRestResource
public interface TbOrgRepository extends JpaRepository<TbOrg, Long>{

}
