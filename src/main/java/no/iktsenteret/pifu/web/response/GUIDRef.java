package no.iktsenteret.pifu.web.response;

import org.springframework.hateoas.Link;

import no.iktsenteret.pifu.domain.Base;

public class GUIDRef {

	private final Base entity;

	private final Link link;

	public GUIDRef(Base entity, Link link) {
		this.entity = entity;
		this.link = link;
	}

	public String getSourcedId() {
		return entity.getSourcedId();
	}

	public String getHref() {
		return link.getHref();
	}

	public String getType() {
		return link.getRel();
	}
}
