package no.iktsenteret.pifu.service.org;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.Org;
import no.iktsenteret.pifu.domain.Org_;
import no.iktsenteret.pifu.domain.vocab.OrgType;

public class OrgSpecs {

	public static Specification<Org> byType(OrgType type) {

		return new Specification<Org>() {
			@Override
			public Predicate toPredicate(Root<Org> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Org_.type), type);
			}
		};
	}

	public static Specification<Org> byTypeAndSourcedId(OrgType type, String sourcedId) {

		return new Specification<Org>() {
			@Override
			public Predicate toPredicate(Root<Org> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(Org_.type), type),
						builder.equal(root.get(Base_.sourcedId), sourcedId));
			}
		};
	}

}
