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
@Table(name = "tb_roleusr"
	, indexes = {
			@Index(name = "roleusr_idx0", unique = false, columnList = "role_cd"),
	}
)
@SequenceGenerator(name = "roleusr_seq"
	, sequenceName = "roleusr_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbRoleUsr {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleusr_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "role_cd", length = 20)
	private String roleCd;
	
	@Column(name = "user_id", length = 20)
	private String userId;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbRoleUsr(
			String roleCd,
			String userId
			) {
		this.roleCd = roleCd;
		this.userId = userId;
	}
}
