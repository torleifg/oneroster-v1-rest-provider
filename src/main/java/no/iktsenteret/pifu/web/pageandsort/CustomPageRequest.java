package no.iktsenteret.pifu.web.pageandsort;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class CustomPageRequest implements Pageable {

	private final Pageable page;
	private final Direction orderBy;

	public CustomPageRequest(Pageable page, Direction orderBy) {
		this.page = page;
		this.orderBy = orderBy;
	}

	public CustomPageRequest(Pageable page) {
		this(page, null);
	}

	@Override
	public int getPageNumber() {
		return page.getPageNumber() / page.getPageSize() + (page.getPageNumber() % page.getPageSize() == 0 ? 0 : 1);
	}

	@Override
	public int getPageSize() {
		return page.getPageSize();
	}

	@Override
	public int getOffset() {
		return page.getPageNumber();
	}

	@Override
	public Sort getSort() {
		if (orderBy != null && page.getSort() != null) {
			return new Sort(orderBy, page.getSort().iterator().next().getProperty());
		} else
			return page.getSort();
	}

	@Override
	public boolean hasPrevious() {
		return getOffset() > getPageSize();
	}

	@Override
	public Pageable previousOrFirst() {
		return hasPrevious() ? previous() : first();
	}

	public PageRequest previous() {
		return new PageRequest(getOffset() - getPageSize(), getPageSize());
	}

	@Override
	public Pageable next() {
		return new PageRequest(getOffset() + getPageSize(), getPageSize());
	}

	@Override
	public Pageable first() {
		return new PageRequest(0, getPageSize());
	}
}