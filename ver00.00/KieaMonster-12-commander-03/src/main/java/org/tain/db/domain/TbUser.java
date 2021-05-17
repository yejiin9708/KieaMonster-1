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
@Table(name = "tb_user"
	, indexes = {
			@Index(name = "user_idx0", unique = true, columnList = "user_id"),
	}
)
@SequenceGenerator(name = "user_seq"
	, sequenceName = "user_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_Id", length = 20)
	private String userId;
	
	@Column(name = "pass_wd", length = 20)
	private String passWd;
	
	@Column(name = "group_cd", length = 20)
	private String groupCd;
	
	@Column(name = "roll_cd", length = 50)
	private String rollCd;
	

	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbUser(
			String userId,
			String passWd,
			String groupCd,
			String rollCd
			) {
		this.userId = userId;
		this.passWd = passWd;
		this.groupCd = groupCd;
		this.rollCd = rollCd;
	}
}
