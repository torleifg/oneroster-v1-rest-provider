package no.iktsenteret.pifu.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.Role;
import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "enrollment", collectionRelation = "enrollments")
public class Enrollment extends Base {

	@ManyToOne
	private User user;

	@ManyToOne
	private Clazz clazz;

	@ManyToOne
	private Org school;

	@Enumerated(EnumType.STRING)
	private Role role;

	private Boolean pr1ma4ry;

	protected Enrollment() {
	}

	public Enrollment(Status status, User user, Clazz clazz, Org school, Role role) {
		super(status);
		this.user = user;
		this.clazz = clazz;
		this.school = school;
		this.role = role;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty("class")
	@JsonSerialize(using = NestedItemSerializer.class)
	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public Org getSchool() {
		return school;
	}

	public void setSchool(Org school) {
		this.school = school;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonProperty("primary")
	public Boolean getPr1ma4ry() {
		return pr1ma4ry;
	}

	public void setPr1ma4ry(Boolean pr1ma4ry) {
		this.pr1ma4ry = pr1ma4ry;
	}
}