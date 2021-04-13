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
@Table(name = "tb_result"
	, indexes = {
			@Index(name = "result_idx0", unique = false, columnList = "svr_code"),
			@Index(name = "result_idx1", unique = false, columnList = "cmd_code"),
	}
)
@SequenceGenerator(name = "result_seq"
	, sequenceName = "result_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbResult {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "svr_code", length = 16)
	private String svrCode;
	
	@Column(name = "cmd_code", length = 16)
	private String cmdCode;
	
	@Column(name = "cmd_result", length = 10240)
	private String cmdResult;
	
	@Builder
	public TbResult(
			String svrCode,
			String cmdCode,
			String cmdResult
			) {
		this.svrCode = svrCode;
		this.cmdCode = cmdCode;
		this.cmdResult = cmdResult;
	}
}
