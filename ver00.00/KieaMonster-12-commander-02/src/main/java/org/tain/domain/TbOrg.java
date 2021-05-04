package org.tain.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_org"
	, indexes = {
			@Index(name = "org_idx0", unique = false, columnList = "org_code"),
	}
)
@SequenceGenerator(name = "org_seq"
	, sequenceName = "org_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbOrg {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_code", length = 16)
	private String orgCode;
	
	@Column(name = "org_name", length = 64)
	private String orgName;
	
	@Column(name = "org_desc", length = 1024)
	private String orgDesc;
	
	@Column(name = "org_comment", length = 1024)
	private String orgComment;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbOrg(
			String orgCode,
			String orgName,
			String orgDesc,
			String orgComment
			) {
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgDesc = orgDesc;
		this.orgComment = orgComment;
	}
}
