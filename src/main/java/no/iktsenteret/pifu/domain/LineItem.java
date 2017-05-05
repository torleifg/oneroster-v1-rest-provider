package no.iktsenteret.pifu.domain;

import java.time.ZonedDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "lineItem", collectionRelation = "lineItems")
public class LineItem extends Base {

	private String title, description;

	private ZonedDateTime assignDate, dueDate;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Clazz clazz;

	@ManyToOne
	private AcademicSession gradingPeriod;

	@Embedded
	private ResultValue resultValue;

	protected LineItem() {
	}

	public LineItem(Status status, String title, ZonedDateTime assignDate, ZonedDateTime dueDate, Clazz clazz,
			Category category, AcademicSession gradingPeriod, ResultValue resultValue) {
		super(status);
		this.title = title;
		this.assignDate = assignDate;
		this.dueDate = dueDate;
		this.clazz = clazz;
		this.category = category;
		this.gradingPeriod = gradingPeriod;
		this.resultValue = resultValue;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(ZonedDateTime assignDate) {
		this.assignDate = assignDate;
	}

	public ZonedDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(ZonedDateTime dueDate) {
		this.dueDate = dueDate;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
	public AcademicSession getGradingPeriod() {
		return gradingPeriod;
	}

	public void setGradingPeriod(AcademicSession gradingPeriod) {
		this.gradingPeriod = gradingPeriod;
	}

	public ResultValue getResultValue() {
		return resultValue;
	}

	public void setResultValue(ResultValue resultValue) {
		this.resultValue = resultValue;
	}
}