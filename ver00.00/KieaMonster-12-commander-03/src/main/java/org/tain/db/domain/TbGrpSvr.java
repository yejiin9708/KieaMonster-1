package org.tain.db.domain;

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
@Table(name = "tb_grpsvr"
	, indexes = {
			@Index(name = "grpsvr_idx0", unique = false, columnList = "grp_code"),
	}
)
@SequenceGenerator(name = "grpsvr_seq"
	, sequenceName = "grpsvr_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbGrpSvr {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grpsvr_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "grp_code", length = 16)
	private String grpCode;
	
	@Column(name = "svr_code", length = 16)
	private String svrCode;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbGrpSvr(
			String grpCode,
			String svrCode
			) {
		this.grpCode = grpCode;
		this.svrCode = svrCode;
	}
}
