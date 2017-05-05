package no.iktsenteret.pifu.service.academicsession;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.AcademicSession_;
import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.vocab.AcademicSessionType;

public class AcademicSessionSpecs {

	public static Specification<AcademicSession> byType(AcademicSessionType type) {

		return new Specification<AcademicSession>() {
			@Override
			public Predicate toPredicate(Root<AcademicSession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(AcademicSession_.type), type);
			}
		};
	}

	public static Specification<AcademicSession> byTypeAndSourcedId(AcademicSessionType type, String sourcedId) {

		return new Specification<AcademicSession>() {
			@Override
			public Predicate toPredicate(Root<AcademicSession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(AcademicSession_.type), type),
						builder.equal(root.get(Base_.sourcedId), sourcedId));
			}
		};
	}

	public static Specification<AcademicSession> byTermSourcedIds(List<String> termSourcedIds) {

		return new Specification<AcademicSession>() {
			@Override
			public Predicate toPredicate(Root<AcademicSession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return root.get(Base_.sourcedId).in(termSourcedIds);
			}
		};
	}

	public static Specification<AcademicSession> byTypeAndParentSourcedId(AcademicSessionType type, String sourcedId) {

		return new Specification<AcademicSession>() {
			@Override
			public Predicate toPredicate(Root<AcademicSession> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.and(builder.equal(root.get(AcademicSession_.type), type),
						builder.equal(root.join(AcademicSession_.parent).get(Base_.sourcedId), sourcedId));
			}
		};
	}
}