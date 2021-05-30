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
@Table(name = "tb_role"
	, indexes = {
			@Index(name = "role_idx0", unique = false, columnList = "role_cd"),
	}
)
@SequenceGenerator(name = "role_seq"
	, sequenceName = "role_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "role_cd", length = 20)
	private String roleCd;
	
	@Column(name = "desc", length = 128)
	private String desc;
	
	//@Column(name = "create_date")
	//@CreationTimestamp
	//private LocalDateTime createdDate;
	
	@Builder
	public TbRole(
			String roleCd,
			String desc
			) {
		this.roleCd = roleCd;
		this.desc = desc;
	}
}
