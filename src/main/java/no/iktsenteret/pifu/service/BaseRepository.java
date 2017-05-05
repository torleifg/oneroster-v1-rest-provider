package no.iktsenteret.pifu.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID>, JpaSpecificationExecutor<T> {

	Page<T> findAll(Pageable page);

	T findOne(ID sourcedId);

	/*
	 * T save(T entity);
	 * 
	 * Iterable<T> save(Iterable<T> entities);
	 */
}
