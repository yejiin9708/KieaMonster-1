package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tain.model.Wife;
import org.tain.projection.RemodellingWifeInfo;
import org.tain.projection.WifeInfo;

public interface WifeRepository extends JpaRepository<Wife, Integer>{

	WifeInfo findWifeByFirstName(String firstName);
	
	@Query("select w from Wife w where w.firstName = ?1")
	RemodellingWifeInfo findRemodelWifeByFisrtName(String firstName);
}
