package no.iktsenteret.pifu.web.pageandsort;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomPageableHandlerMethodArgumentResolver extends PageableHandlerMethodArgumentResolver {

	@Override
	public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
			NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {

		Pageable page = super.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest,
				webDataBinderFactory);

		Direction orderBy = Direction.fromStringOrNull(nativeWebRequest.getParameter("orderBy"));

		return new CustomPageRequest(page, orderBy);
	}
}
