package no.iktsenteret.pifu.service.result;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.Result;
import no.iktsenteret.pifu.domain.Result_;

public class ResultSpecs {

	public static Specification<Result> byLineItemSourcedIds(List<String> lineItemSourcedIds) {

		return new Specification<Result>() {
			@Override
			public Predicate toPredicate(Root<Result> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.get(Result_.lineItem).get(Base_.sourcedId).in(lineItemSourcedIds);
			}
		};
	}

	public static Specification<Result> byLineItemSourcedId(String sourcedId) {

		return new Specification<Result>() {
			@Override
			public Predicate toPredicate(Root<Result> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Result_.lineItem).get(Base_.sourcedId), sourcedId);
			}
		};
	}

	public static Specification<Result> byStudentSourcedId(String sourcedId) {

		return new Specification<Result>() {
			@Override
			public Predicate toPredicate(Root<Result> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Result_.student).get(Base_.sourcedId), sourcedId);
			}
		};
	}
}
