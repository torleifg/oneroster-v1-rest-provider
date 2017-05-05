package no.iktsenteret.pifu.domain;

import javax.persistence.Entity;

import org.springframework.hateoas.core.Relation;

import no.iktsenteret.pifu.domain.vocab.Status;

@Entity
@Relation(value = "category", collectionRelation = "categories")
public class Category extends Base {

	private String title;

	protected Category() {
	}

	public Category(Status status, String title) {
		super(status);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
