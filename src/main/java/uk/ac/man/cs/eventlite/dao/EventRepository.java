package uk.ac.man.cs.eventlite.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	public Iterable<Event> findAll(Sort sort);
	
	@Query(value = "select * from events s where s.name like %:keyword", nativeQuery = true)
	 Iterable<Event> findByKeyword(@Param("keyword") String keyword);
}
