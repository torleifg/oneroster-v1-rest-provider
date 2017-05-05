package no.iktsenteret.pifu.domain;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import no.iktsenteret.pifu.domain.vocab.ResultStatus;
import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "result", collectionRelation = "results")
public class Result extends Base {

	@ManyToOne
	private LineItem lineItem;

	@ManyToOne
	private User student;

	private String comment;

	private Float score;

	@Enumerated(EnumType.STRING)
	private ResultStatus resultStatus;

	private ZonedDateTime date;

	protected Result() {
	}

	public Result(Status status, LineItem lineItem, User student, Float score, ResultStatus resultStatus,
			ZonedDateTime date) {
		super(status);
		this.lineItem = lineItem;
		this.student = student;
		this.score = score;
		this.resultStatus = resultStatus;
		this.date = date;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public LineItem getLineItem() {
		return lineItem;
	}

	public void setLineItem(LineItem lineItem) {
		this.lineItem = lineItem;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
}