package no.iktsenteret.pifu.service.lineitem;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.LineItem;
import no.iktsenteret.pifu.domain.LineItem_;

public class LineItemSpecs {

	public static Specification<LineItem> ByClazzSourcedId(String sourcedId) {

		return new Specification<LineItem>() {
			@Override
			public Predicate toPredicate(Root<LineItem> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(LineItem_.clazz).get(Base_.sourcedId), sourcedId);
			}
		};
	}
}
