package no.iktsenteret.pifu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "course", collectionRelation = "courses")
public class Course extends Base {

	private String title, courseCode, grade;

	@ElementCollection
	private List<String> subjects = new ArrayList<String>();

	@ManyToOne
	private AcademicSession schoolYear;

	@ManyToOne
	private Org org;

	protected Course() {
	}

	public Course(Status status, String title, String courseCode) {
		super(status);
		this.title = title;
		this.courseCode = courseCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public AcademicSession getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(AcademicSession schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}
}