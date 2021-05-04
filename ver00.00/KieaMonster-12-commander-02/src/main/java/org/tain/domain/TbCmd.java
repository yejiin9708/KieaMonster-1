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
@Table(name = "tb_cmd"
	, indexes = {
			@Index(name = "cmd_idx0", unique = false, columnList = "cmd_code"),
	}
)
@SequenceGenerator(name = "cmd_seq"
	, sequenceName = "cmd_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbCmd {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cmd_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "svr_code", length = 16)
	private String svrCode;
	
	@Column(name = "cmd_code", length = 16)
	private String cmdCode;
	
	@Column(name = "cmd_name", length = 64)
	private String cmdName;
	
	@Column(name = "cmd_desc", length = 1024)
	private String cmdDesc;
	
	@Column(name = "cmd_period", length = 5)
	private String cmdPeriod;
	
	@Column(name = "cmd_type", length = 32)
	private String cmdType;
	
	//@JsonIgnore
	//@Column(name = "cmd_arr")
	//private String[] cmdArr;
	
	//@JsonIgnore
	@Column(name = "cmd_arr")
	private String cmdArr;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbCmd(
			String cmdCode,
			String cmdName,
			String cmdDesc,
			String cmdPeriod,
			String cmdType,
			String cmdArr
			) {
		this.cmdCode = cmdCode;
		this.cmdName = cmdName;
		this.cmdDesc = cmdDesc;
		this.cmdPeriod = cmdPeriod;
		this.cmdType = cmdType;
		this.cmdArr = cmdArr;
	}
}
