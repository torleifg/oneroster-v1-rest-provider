package no.iktsenteret.pifu.service.academicsession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.domain.vocab.AcademicSessionType;
import no.iktsenteret.pifu.service.clazz.ClazzRepository;
import no.iktsenteret.pifu.service.clazz.ClazzSpecs;
import no.iktsenteret.pifu.web.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class AcademicSessionServiceImpl implements AcademicSessionService {

	private final AcademicSessionRepository academicSessionRepository;

	private final ClazzRepository clazzRepository;

	public AcademicSessionServiceImpl(AcademicSessionRepository academicSessionRepository,
			ClazzRepository clazzRepository) {
		this.academicSessionRepository = academicSessionRepository;
		this.clazzRepository = clazzRepository;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<AcademicSession> getAcademicSessions(Specification<AcademicSession> filters, Pageable page) {
		Page<AcademicSession> academicSessions = academicSessionRepository.findAll(filters, page);

		return academicSessions;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public AcademicSession getAcademicSession(String sourcedId) {
		AcademicSession academicSession = academicSessionRepository.findOne(sourcedId);

		if (academicSession == null)
			throw new NotFoundException("AcademicSession not found: " + sourcedId);

		return academicSession;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<AcademicSession> getTerms(Specification<AcademicSession> filters, Pageable page) {
		Specifications<AcademicSession> specs = Specifications
				.where(AcademicSessionSpecs.byType(AcademicSessionType.TERM)).and(filters);

		Page<AcademicSession> terms = academicSessionRepository.findAll(specs, page);

		return terms;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public AcademicSession getTerm(String sourcedId) {
		AcademicSession term = academicSessionRepository
				.findOne(AcademicSessionSpecs.byTypeAndSourcedId(AcademicSessionType.TERM, sourcedId));

		if (term == null)
			throw new NotFoundException("Term not found: " + sourcedId);

		return term;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<AcademicSession> getGradingPeriods(Specification<AcademicSession> filters, Pageable page) {
		Specifications<AcademicSession> specs = Specifications
				.where(AcademicSessionSpecs.byType(AcademicSessionType.GRADINGPERIOD)).and(filters);

		Page<AcademicSession> gradingPeriods = academicSessionRepository.findAll(specs, page);

		return gradingPeriods;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public AcademicSession getGradingPeriod(String sourcedId) {
		AcademicSession gradingPeriod = academicSessionRepository
				.findOne(AcademicSessionSpecs.byTypeAndSourcedId(AcademicSessionType.GRADINGPERIOD, sourcedId));

		if (gradingPeriod == null)
			throw new NotFoundException("Grading period not found: " + sourcedId);

		return gradingPeriod;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<Clazz> getClassesForTerm(String sourcedId, Specification<Clazz> filters, Pageable page) {
		this.validateTerm(sourcedId);

		Specifications<Clazz> specs = Specifications.where(ClazzSpecs.byTermsSourcedId(sourcedId)).and(filters);

		Page<Clazz> clazzes = clazzRepository.findAll(specs, page);

		return clazzes;
	}

	@Override
	@PreAuthorize("hasAuthority('READ')")
	public Page<AcademicSession> getGradingPeriodsForTerm(String sourcedId, Specification<AcademicSession> filters,
			Pageable page) {
		this.validateTerm(sourcedId);

		Specifications<AcademicSession> specs = Specifications
				.where(AcademicSessionSpecs.byTypeAndParentSourcedId(AcademicSessionType.GRADINGPERIOD, sourcedId))
				.and(filters);

		Page<AcademicSession> gradingPeriods = academicSessionRepository.findAll(specs, page);

		return gradingPeriods;
	}

	public void validateTerm(String sourcedId) {
		AcademicSession term = academicSessionRepository
				.findOne(AcademicSessionSpecs.byTypeAndSourcedId(AcademicSessionType.TERM, sourcedId));

		if (term == null)
			throw new NotFoundException("Term not found: " + sourcedId);
	}
}
