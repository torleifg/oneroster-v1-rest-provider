package no.iktsenteret.pifu.service.clazz;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Clazz_;

public class ClazzSpecs {

	public static Specification<Clazz> bySchoolSourcedId(String sourcedId) {

		return new Specification<Clazz>() {
			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Clazz_.school).get(Base_.sourcedId), sourcedId);
			}
		};
	}

	public static Specification<Clazz> byTermsSourcedId(String sourcedId) {

		return new Specification<Clazz>() {
			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.join(Clazz_.terms).get(Base_.sourcedId), sourcedId);
			}
		};
	}

	public static Specification<Clazz> byCourseSourcedId(String sourcedId) {

		return new Specification<Clazz>() {
			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Clazz_.course).get(Base_.sourcedId), sourcedId);
			}
		};
	}

	public static Specification<Clazz> byClazzSourcedIds(List<String> clazzSourcedIds) {

		return new Specification<Clazz>() {
			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.get(Base_.sourcedId).in(clazzSourcedIds);
			}
		};
	}
}
