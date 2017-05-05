package no.iktsenteret.pifu.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.ClazzType;
import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedCollectionSerializer;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "class", collectionRelation = "classes")
public class Clazz extends Base {

	private String title, clazzCode, location, grade;

	@Enumerated(EnumType.STRING)
	private ClazzType clazzType;

	@ElementCollection
	private List<String> subjects = new ArrayList<String>();

	@ManyToOne
	private Course course;

	@ManyToOne
	private Org school;

	@ManyToMany
	private Set<AcademicSession> terms = new HashSet<>();

	protected Clazz() {
	}

	public Clazz(Status status, String title, Org school) {
		super(status);
		this.title = title;
		this.school = school;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("classCode")
	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@JsonProperty("classType")
	public ClazzType getClazzType() {
		return clazzType;
	}

	public void setClazzType(ClazzType clazzType) {
		this.clazzType = clazzType;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public Org getSchool() {
		return school;
	}

	public void setSchool(Org school) {
		this.school = school;
	}

	@JsonSerialize(using = NestedCollectionSerializer.class)
	public Set<AcademicSession> getTerms() {
		return terms;
	}
}