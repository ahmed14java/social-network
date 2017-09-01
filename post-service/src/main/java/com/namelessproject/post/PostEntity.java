package com.namelessproject.post;

import java.text.Normalizer;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "POST")
@Access(AccessType.FIELD)
public class PostEntity {

	@Id
	@TableGenerator(name = "POST_ID_GEN", 	
					table = "ID_GENERATOR", 
					pkColumnName = "SEQ_NAME", 
					pkColumnValue = "POST", 
					valueColumnName = "SEQ_VALUE")
	@GeneratedValue(generator = "POST_ID_GEN", 
					strategy = GenerationType.TABLE)
	private Long id;
	@Length(max=200, message="post.title.longer.than.200.chars")
	private String title;
	@Column
	@NotBlank(message = "post.content.cannot.be.empty")
	@Length(min=5, message="post.content.cannot.be.shorter.than.5.chars")
	private String content;
	@NotBlank(message = "post.key.cannot.be.empty")
	private String key;
	@NotBlank(message = "post.username.cannot.be.empty")
	private String username;
	@Column(nullable=false)
	private Date creationDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@PrePersist
	protected void onCreate() {
	    updateCreationDate();
	    generateKey();
	}
	
	@PreUpdate
	protected void onUpdate() {
	    generateKey();
	}
	
	protected void generateKey() {
		try {
			final StringBuilder sb = new StringBuilder();
			sb.append(getId());

			if (!StringUtils.isEmpty(getTitle())) {
				sb.append("_").append(normalizeTitle());
			}
				
			setKey(sb.toString());			
		} catch (Exception e) {
			throw new RuntimeException("[Unexpected] Cannot encode this title", e);
		}
	}

	protected void updateCreationDate() {
		setCreationDate(new Date());
	}
	
	private String normalizeTitle() {
		String normalizedTitle = Normalizer.normalize(getTitle(), Normalizer.Form.NFD);
		normalizedTitle = normalizedTitle.replaceAll("\\s+", "_");
		return normalizedTitle;
	}

}