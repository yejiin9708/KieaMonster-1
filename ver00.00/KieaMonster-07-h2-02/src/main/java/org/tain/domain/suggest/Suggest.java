package org.tain.domain.suggest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_suggest")
@Data
public class Suggest {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "rank")
	private Integer rank;
	
	@Column(name = "score")
	private Float score;
}
