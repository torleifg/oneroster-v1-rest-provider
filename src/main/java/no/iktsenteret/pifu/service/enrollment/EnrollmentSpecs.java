package no.iktsenteret.pifu.service.enrollment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.Enrollment;
import no.iktsenteret.pifu.domain.Enrollment_;
import no.iktsenteret.pifu.domain.vocab.Role;

public class EnrollmentSpecs {

	public static Specification<Enrollment> bySchoolSourcedId(String sourcedId) {

		return new Specification<Enrollment>() {
			@Override
			public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Enrollment_.school).get(Base_.sourcedId), sourcedId);
			}
		};
	}

	public static Specification<Enrollment> byUserSourcedId(String sourcedId) {

		return new Specification<Enrollment>() {
			@Override
			public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Enrollment_.user).get(Base_.sourcedId), sourcedId);
			}
		};
	}

	public static Specification<Enrollment> findByRoleAndClazzSourcedId(Role role, String sourcedId) {

		return new Specification<Enrollment>() {
			@Override
			public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(Enrollment_.role), role),
						builder.equal(root.get(Enrollment_.clazz).get(Base_.sourcedId), sourcedId));
			}
		};
	}

	public static Specification<Enrollment> bySchoolSourcedIdAndClazzSourcedId(String schoolSourcedId,
			String clazzSourcedId) {

		return new Specification<Enrollment>() {
			@Override
			public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(Enrollment_.school).get(Base_.sourcedId), schoolSourcedId),
						builder.equal(root.get(Enrollment_.clazz).get(Base_.sourcedId), clazzSourcedId));
			}
		};
	}

	public static Specification<Enrollment> byRoleAndSchoolSourcedIdAndClazzSourcedId(Role role, String schoolSourcedId,
			String clazzSourcedId) {

		return new Specification<Enrollment>() {
			@Override
			public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(Enrollment_.role), role),
						builder.equal(root.get(Enrollment_.school).get(Base_.sourcedId), schoolSourcedId),
						builder.equal(root.get(Enrollment_.clazz).get(Base_.sourcedId), clazzSourcedId));
			}
		};
	}
}