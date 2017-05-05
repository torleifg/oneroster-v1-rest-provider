package no.iktsenteret.pifu.service.course;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Base_;
import no.iktsenteret.pifu.domain.Course;
import no.iktsenteret.pifu.domain.Course_;

public class CourseSpecs {

	public static Specification<Course> byOrgSourcedId(String sourcedId) {

		return new Specification<Course>() {
			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Course_.org).get(Base_.sourcedId), sourcedId);
			}
		};
	}
}
