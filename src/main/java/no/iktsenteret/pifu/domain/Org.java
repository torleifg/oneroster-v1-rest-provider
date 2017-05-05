package no.iktsenteret.pifu.domain;

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

import no.iktsenteret.pifu.domain.vocab.OrgType;
import no.iktsenteret.pifu.domain.vocab.Status;
import no.iktsenteret.pifu.web.serializers.NestedCollectionSerializer;
import no.iktsenteret.pifu.web.serializers.NestedItemSerializer;

@Entity
@Relation(value = "org", collectionRelation = "orgs")
public class Org extends Base {

	private String name, identifier;

	@Enumerated(EnumType.STRING)
	private OrgType type;

	@ManyToOne
	private Org parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Org> children = new HashSet<Org>();

	protected Org() {
	}

	public Org(Status status, String name, OrgType type) {
		super(status);
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public OrgType getType() {
		return type;
	}

	public void setType(OrgType type) {
		this.type = type;
	}

	@JsonSerialize(using = NestedItemSerializer.class)
	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	@JsonSerialize(using = NestedCollectionSerializer.class)
	public Set<Org> getChildren() {
		return children;
	}

	public void addChild(Org child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(Org child) {
		children.remove(child);
		child.setParent(null);
	}
}