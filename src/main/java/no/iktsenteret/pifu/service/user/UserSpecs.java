package no.iktsenteret.pifu.service.user;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.User;
import no.iktsenteret.pifu.domain.User_;
import no.iktsenteret.pifu.domain.vocab.Role;

public class UserSpecs {

	public static Specification<User> byRole(Role role) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(User_.role), role);
			}
		};
	}

	public static Specification<User> byRoleAndSourcedId(Role role, String sourcedId) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(User_.role), role),
						builder.equal(root.get(Base_.sourcedId), sourcedId));
			}
		};
	}

	public static Specification<User> byRoleAndSchoolSourcedId(Role role, String sourcedId) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(User_.role), role),
						builder.equal(root.join(User_.orgs).get(Base_.sourcedId), sourcedId));
			}
		};
	}

	public static Specification<User> byUserSourcedIds(List<String> userSourcedIds) {

		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.get(Base_.sourcedId).in(userSourcedIds);
			}
		};
	}
}
