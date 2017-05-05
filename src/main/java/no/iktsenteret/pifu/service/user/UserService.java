package no.iktsenteret.pifu.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.User;

public interface UserService {

	Page<User> getUsers(Specification<User> filters, Pageable page);

	User getUser(String sourcedId);

	Page<User> getTeachers(Specification<User> filters, Pageable page);

	User getTeacher(String sourcedId);

	Page<User> getStudents(Specification<User> filters, Pageable page);

	User getStudent(String sourcedId);

	Page<Clazz> getClassesForStudent(String sourcedId, Specification<Clazz> filters, Pageable page);

	Page<Clazz> getClassesForTeacher(String sourcedId, Specification<Clazz> filters, Pageable page);
}
