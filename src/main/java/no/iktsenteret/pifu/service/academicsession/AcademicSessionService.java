package no.iktsenteret.pifu.service.academicsession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.Clazz;

public interface AcademicSessionService {

	Page<AcademicSession> getAcademicSessions(Specification<AcademicSession> filters, Pageable page);

	AcademicSession getAcademicSession(String sourcedId);

	Page<AcademicSession> getTerms(Specification<AcademicSession> filters, Pageable page);

	AcademicSession getTerm(String sourcedId);

	Page<AcademicSession> getGradingPeriods(Specification<AcademicSession> filters, Pageable page);

	AcademicSession getGradingPeriod(String sourcedId);

	Page<Clazz> getClassesForTerm(String sourcedId, Specification<Clazz> filters, Pageable page);

	Page<AcademicSession> getGradingPeriodsForTerm(String sourcedId, Specification<AcademicSession> filters,
			Pageable page);
}
