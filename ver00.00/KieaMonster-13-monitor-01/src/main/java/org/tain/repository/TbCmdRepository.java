package org.tain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.TbCmd;

@RepositoryRestResource
public interface TbCmdRepository extends JpaRepository<TbCmd, Long>{

	List<TbCmd> findTbCmdListBySvrCode(String svrCode);
}
