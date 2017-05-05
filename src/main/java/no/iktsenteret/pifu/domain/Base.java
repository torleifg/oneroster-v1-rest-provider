package no.iktsenteret.pifu.domain;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import no.iktsenteret.pifu.domain.vocab.Status;

@MappedSuperclass
@JsonFilter("fields")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "sourcedId")
@JsonInclude(Include.NON_DEFAULT)
public class Base {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String sourcedId;

	@Enumerated(EnumType.STRING)
	private Status status;

	@LastModifiedDate
	private ZonedDateTime dateLastModified;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	protected Base() {
	}

	public Base(Status status) {
		this.status = status;
	}

	public String getSourcedId() {
		return sourcedId;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public ZonedDateTime getDateLastModified() {
		return dateLastModified;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}
}