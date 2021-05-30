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
@Table(name = "tb_cdmst"
	, indexes = {
			@Index(name = "cdmst_idx0", unique = false, columnList = "cd_mst"),
	}
)
@SequenceGenerator(name = "cdmst_seq"
	, sequenceName = "cdmst_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbCdMst {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cdmst_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "cd_mst", length = 20)
	private String cdMst;
	
	@Column(name = "desc", length = 128)
	private String desc;

	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbCdMst(
			String cdMst,
			String desc
			) {
		this.cdMst = cdMst;
		this.desc = desc;
	}
}
