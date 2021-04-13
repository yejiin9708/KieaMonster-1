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
@Table(name = "tb_grp"
	, indexes = {
			@Index(name = "grp_idx0", unique = false, columnList = "grp_code"),
	}
)
@SequenceGenerator(name = "grp_seq"
	, sequenceName = "grp_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbGrp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grp_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_code", length = 16)
	private String orgCode;
	
	@Column(name = "grp_code", length = 16)
	private String grpCode;
	
	@Column(name = "grp_name", length = 64)
	private String grpName;
	
	@Column(name = "grp_desc", length = 1024)
	private String grpDesc;
	
	@Column(name = "grp_comment", length = 1024)
	private String grpComment;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbGrp(
			String grpCode,
			String grpName,
			String grpDesc,
			String grpComment
			) {
		this.grpCode = grpCode;
		this.grpName = grpName;
		this.grpDesc = grpDesc;
		this.grpComment = grpComment;
	}
}
