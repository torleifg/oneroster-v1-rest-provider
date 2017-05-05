package no.iktsenteret.pifu.service.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.Course;
import no.iktsenteret.pifu.service.clazz.ClazzRepository;
import no.iktsenteret.pifu.service.clazz.ClazzSpecs;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	private final ClazzRepository clazzRepository;

	public CourseServiceImpl(CourseRepository courseRepository, ClazzRepository clazzRepository) {
		this.courseRepository = courseRepository;
		this.clazzRepository = clazzRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Course> getCourses(Specification<Course> filters, Pageable page) {
		Page<Course> courses = courseRepository.findAll(filters, page);

		return courses;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Course getCourse(String sourcedId) {
		Course course = courseRepository.findOne(sourcedId);

		if (course == null)
			throw new NotFoundException("Course not found: " + sourcedId);

		return course;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Clazz> getClassesforCourse(String sourcedId, Specification<Clazz> filters, Pageable page) {
		Course course = courseRepository.findOne(sourcedId);

		if (course == null)
			throw new NotFoundException("Course not found: " + sourcedId);

		Specifications<Clazz> specs = Specifications.where(ClazzSpecs.byCourseSourcedId(sourcedId)).and(filters);

		Page<Clazz> clazzes = clazzRepository.findAll(specs, page);

		return clazzes;
	}
}