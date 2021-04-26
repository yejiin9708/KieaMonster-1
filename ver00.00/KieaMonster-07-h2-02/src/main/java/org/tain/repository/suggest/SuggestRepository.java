package org.tain.repository.suggest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.suggest.Suggest;

@RepositoryRestResource
public interface SuggestRepository extends JpaRepository<Suggest, Long> {

}
