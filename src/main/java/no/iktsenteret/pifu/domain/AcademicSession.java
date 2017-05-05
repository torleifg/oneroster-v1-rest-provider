package no.iktsenteret.pifu.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.AcademicSessionType;
import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedCollectionSerializer;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "academicSession", collectionRelation = "academicSessions")
public class AcademicSession extends Base {

	private String title;

	private LocalDate startDate, endDate;

	@Enumerated(EnumType.STRING)
	private AcademicSessionType type;

	@ManyToOne
	private AcademicSession parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<AcademicSession> children = new HashSet<>();

	protected AcademicSession() {
	}

	public AcademicSession(Status status, String title, LocalDate startDate, LocalDate endDate,
			AcademicSessionType type) {
		super(status);
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public AcademicSessionType getType() {
		return type;
	}

	public void setType(AcademicSessionType type) {
		this.type = type;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public AcademicSession getParent() {
		return parent;
	}

	public void setParent(AcademicSession parent) {
		this.parent = parent;
	}

	@JsonSerialize(using = NestedCollectionSerializer.class)
	public Set<AcademicSession> getChildren() {
		return children;
	}

	public void addChild(AcademicSession child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(AcademicSession child) {
		children.remove(child);
		child.setParent(null);
	}
}