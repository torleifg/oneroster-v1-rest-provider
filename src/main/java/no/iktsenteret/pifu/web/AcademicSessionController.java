package no.iktsenteret.pifu.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;

import no.iktsenteret.pifu.domain.AcademicSession;
import no.iktsenteret.pifu.domain.Clazz;
import no.iktsenteret.pifu.service.academicsession.AcademicSessionService;
import no.iktsenteret.pifu.web.response.ApiResponse;

@RestController
@ExposesResourceFor(AcademicSession.class)
public class AcademicSessionController {

	private final AcademicSessionService academicSessionService;

	private final ApiResponse apiResponse;

	public AcademicSessionController(AcademicSessionService academicSessionService, ApiResponse apiResponse) {
		this.academicSessionService = academicSessionService;
		this.apiResponse = apiResponse;
	}

	@GetMapping(value = "/academicSessions")
	public ResponseEntity<?> getAcademicSessions(Specification<AcademicSession> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<AcademicSession> academicSessions = academicSessionService.getAcademicSessions(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, academicSessions, page))
				.body(apiResponse.collection(AcademicSession.class, academicSessions, fields));
	}

	@GetMapping(value = "/academicSessions/{id}")
	public ResponseEntity<?> getAcademicSession(@PathVariable("id") String sourcedId, FilterProvider fields) {
		AcademicSession academicSession = academicSessionService.getAcademicSession(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(AcademicSession.class, academicSession, fields));
	}

	@GetMapping(value = "/terms")
	public ResponseEntity<?> getTerms(Specification<AcademicSession> filters, Pageable page, FilterProvider fields,
			HttpServletRequest request) {
		Page<AcademicSession> terms = academicSessionService.getTerms(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, terms, page))
				.body(apiResponse.collection(AcademicSession.class, terms, fields));
	}

	@GetMapping(value = "/terms/{id}")
	public ResponseEntity<?> getTerm(@PathVariable("id") String sourcedId, FilterProvider fields) {
		AcademicSession term = academicSessionService.getTerm(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(AcademicSession.class, term, fields));
	}

	@GetMapping(value = "/gradingPeriods")
	public ResponseEntity<?> getGradingPeriods(Specification<AcademicSession> filters, Pageable page,
			FilterProvider fields, HttpServletRequest request) {
		Page<AcademicSession> gradingPeriods = academicSessionService.getGradingPeriods(filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, gradingPeriods, page))
				.body(apiResponse.collection(AcademicSession.class, gradingPeriods, fields));
	}

	@GetMapping(value = "/gradingPeriods/{id}")
	public ResponseEntity<?> getGradingPeriod(@PathVariable("id") String sourcedId, FilterProvider fields) {
		AcademicSession gradingPeriod = academicSessionService.getGradingPeriod(sourcedId);

		return ResponseEntity.ok().body(apiResponse.item(AcademicSession.class, gradingPeriod, fields));
	}

	@GetMapping(value = "/terms/{id}/classes")
	public ResponseEntity<?> getClassesForTerm(@PathVariable("id") String sourcedId, Specification<Clazz> filters,
			Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<Clazz> clazzes = academicSessionService.getClassesForTerm(sourcedId, filters, page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, clazzes, page))
				.body(apiResponse.collection(Clazz.class, clazzes, fields));
	}

	@GetMapping(value = "/terms/{id}/gradingPeriods")
	public ResponseEntity<?> getGradingsPeriodsForTerm(@PathVariable("id") String sourcedId,
			Specification<AcademicSession> filters, Pageable page, FilterProvider fields, HttpServletRequest request) {
		Page<AcademicSession> gradingPeriods = academicSessionService.getGradingPeriodsForTerm(sourcedId, filters,
				page);

		return ResponseEntity.ok().headers(apiResponse.httpHeaders(request, gradingPeriods, page))
				.body(apiResponse.collection(AcademicSession.class, gradingPeriods, fields));
	}
}