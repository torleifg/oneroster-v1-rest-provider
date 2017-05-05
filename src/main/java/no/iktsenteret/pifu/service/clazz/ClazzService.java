package no.iktsenteret.pifu.service.clazz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.LineItem;
import no.iktsenteret.pifu.domain.Result;
import no.iktsenteret.pifu.domain.User;

public interface ClazzService {

	Page<Clazz> getClasses(Specification<Clazz> filters, Pageable page);

	Clazz getClass(String sourcedId);

	Page<User> getStudentsForClass(String sourcedId, Specification<User> filters, Pageable page);

	Page<User> getTeachersForClass(String sourcedId, Specification<User> filters, Pageable page);

	Page<Result> getResultsForClass(String sourcedId, Specification<Result> filters, Pageable page);

	Page<LineItem> getLineItemsForClass(String sourcedId, Specification<LineItem> filters, Pageable page);

	Page<Result> getResultsForLineItemForClass(String clazzSourcedId, String lineItemSourcedId,
			Specification<Result> filters, Pageable page);

	Page<Result> getResultsForStudentsForClass(String clazzSourcedId, String studentSourcedId,
			Specification<Result> filters, Pageable page);
}
