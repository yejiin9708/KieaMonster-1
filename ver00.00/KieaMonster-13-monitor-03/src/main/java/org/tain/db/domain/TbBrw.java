package org.tain.db.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_brw"
	, indexes = {
			@Index(name = "brw_idx0", unique = false, columnList = "ip_addr"),
	}
)
@SequenceGenerator(name = "brw_seq"
	, sequenceName = "brw_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class TbBrw {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brw_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ip_addr", length = 16)
	private String ipAddr;
	
	@Column(name = "user_id", length = 64)
	private String userId;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Builder
	public TbBrw(
			String ipAddr,
			String userId
			) {
		this.ipAddr = ipAddr;
		this.userId = userId;
	}
}
