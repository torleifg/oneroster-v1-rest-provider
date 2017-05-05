package no.iktsenteret.pifu.web;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RestController;

import no.iktsenteret.pifu.domain.LineItem;

@RestController
@ExposesResourceFor(LineItem.class)
public class LineItemController {
}
