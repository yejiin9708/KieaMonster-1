package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tain.model.Husband;

public interface HusbandRepository extends JpaRepository<Husband, Integer>{

}
