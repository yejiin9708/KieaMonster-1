package org.tain.repository.campaign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.tain.domain.campaign.Campaign;

@RepositoryRestResource
@Transactional
public interface CampaignRepository extends JpaRepository<Campaign, Long>{

}
