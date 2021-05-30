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
@Table(name = "tb_cditm"
	, indexes = {
			@Index(name = "cditm_idx0", unique = false, columnList = "cd_mst"),
	}
)
@SequenceGenerator(name = "cditm_seq"
	, sequenceName = "cditm_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbCdItm {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cditm_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "cd_mst", length = 20)
	private String cdMst;
	
	@Column(name = "cd_itm", length = 20)
	private String cdItm;
	
	@Column(name = "cd_val", length = 20)
	private String cdVal;
	
	@Column(name = "desc", length = 128)
	private String desc;

	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbCdItm(
			String cdMst,
			String cdItm,
			String cdVal,
			String desc
			) {
		this.cdMst = cdMst;
		this.cdItm = cdItm;
		this.cdVal = cdVal;
		this.desc = desc;
	}
}
