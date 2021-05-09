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
	
	@Column(name = "msg_code", length = 16)
	private String msgCode;
	
	@Column(name = "cmd_code", length = 16)
	private String cmdCode;
	
	@Column(name = "cmd_name", length = 64)
	private String cmdName;
	
	@Column(name = "cmd_desc", length = 1024)
	private String cmdDesc;
	
	@Column(name = "cmd_period", length = 8)
	private String cmdPeriod;
	
	@Column(name = "cmd_type", length = 32)
	private String cmdType;
	
	@Column(name = "cmd_arr", length = 128)
	private String cmdArr;
	
	@Column(name = "cmd_result", length = 10240000)  // 10 MB
	private String cmdResult;
	
	@Builder
	public TbResult(
			String svrCode,
			String msgCode,
			String cmdCode,
			String cmdName,
			String cmdDesc,
			String cmdPeriod,
			String cmdType,
			String cmdArr,
			String cmdResult
			) {
		this.svrCode = svrCode;
		this.msgCode = msgCode;
		this.cmdCode = cmdCode;
		this.cmdName = cmdName;
		this.cmdDesc = cmdDesc;
		this.cmdPeriod = cmdPeriod;
		this.cmdType = cmdType;
		this.cmdArr = cmdArr;
		this.cmdResult = cmdResult;
	}
}
