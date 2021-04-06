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
@Table(name = "tb_svr"
	, indexes = {
			@Index(name = "svr_idx0", unique = false, columnList = "svr_code"),
	}
)
@SequenceGenerator(name = "svr_seq"
	, sequenceName = "svr_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbSvr {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "svr_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "grp_code", length = 16)
	private String grpCode;
	
	@Column(name = "svr_code", length = 16)
	private String svrCode;
	
	@Column(name = "svr_name", length = 64)
	private String svrName;
	
	@Column(name = "svr_desc", length = 1024)
	private String svrDesc;
	
	@Column(name = "svr_comment", length = 1024)
	private String svrComment;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbSvr(
			String svrCode,
			String svrName,
			String svrDesc,
			String svrComment
			) {
		this.svrCode = svrCode;
		this.svrName = svrName;
		this.svrDesc = svrDesc;
		this.svrComment = svrComment;
	}
}
