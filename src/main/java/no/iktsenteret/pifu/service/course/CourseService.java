package no.iktsenteret.pifu.service.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Course;

public interface CourseService {

	Page<Course> getCourses(Specification<Course> filters, Pageable page);

	Course getCourse(String sourcedId);

	Page<Clazz> getClassesforCourse(String sourcedId, Specification<Clazz> filters, Pageable page);
}
