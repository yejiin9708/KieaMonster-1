package org.tain.domain.campaign;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ci_campaign")
@Data
public class Campaign {

	@Id
	@Column(name = "campaign_sk")
	private Long campaignSk;
	
	@Column(name = "campaign_nm", length = 256)
	private String campaignNm;
	
	@Column(name = "campaign_folder_txt", length = 256)
	private String campaignFolderTxt;
	
	@Column(name = "campaign_owner_nm", length = 256)
	private String campaignOwnerNm;
	
	/*
	@Column(name = "processed_dttm")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@CreationTimestamp
	private LocalDate processedDttm;
	*/
}
