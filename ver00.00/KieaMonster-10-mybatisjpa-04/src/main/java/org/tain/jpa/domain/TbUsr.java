package org.tain.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usr")
@SequenceGenerator(name = "usr_seq"
	, sequenceName = "usr_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbUsr {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_Id", length = 20)
	private String userId;
	
	@Column(name = "pass_wd", length = 20)
	private String passWd;
	
	@Column(name = "desc", length = 128)
	private String desc;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbUsr(
			String userId,
			String passWd,
			String desc
			) {
		this.userId = userId;
		this.passWd = passWd;
		this.desc = desc;
	}
}