package org.tain.domain.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.tain.domain.serializer.LocalDateTimeDeserializer;
import org.tain.domain.serializer.LocalDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_board"
	, indexes = {
			@Index(name = "board_idx0", unique = false, columnList = "title"),
			@Index(name = "board_idx1", unique = false, columnList = "user_id"),
	}
)
/*
@SequenceGenerator(name = "board_seq"
	, sequenceName = "board_seq"
	, initialValue = 1
	, allocationSize = 1
)
*/
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class Board {

	@Id
	/* @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq") */
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", length = 256)
	private String title;
	
	@Column(name = "sub_title", length = 256)
	private String subTitle;
	
	@Lob
	@Column(name = "content")
	private String content;
	
	@Column(name = "user_id", length = 16)
	private String userId;
	
	
	@Column(name = "create_date")
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	//@CreationTimestamp
	private LocalDateTime createdDate;
	
	/*
	//@JsonIgnore  // ignore when select
	@JsonProperty(access = Access.WRITE_ONLY)  // ignore when all, only write
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")  // jackson
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")                                               // spring framework
	@Column(name = "update_date")
	@UpdateTimestamp
	private Timestamp updatedDate;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "job_date")
	@UpdateTimestamp
	private Date jobDate;
	*/
	
	@Builder
	public Board(
			Long id,
			String title,
			String subTitle,
			String content,
			String userId,
			LocalDateTime createdDate
			) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.content = content;
		this.userId = userId;
		this.createdDate = createdDate;
	}
}
