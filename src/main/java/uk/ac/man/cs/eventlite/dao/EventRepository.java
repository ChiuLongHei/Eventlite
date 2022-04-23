package uk.ac.man.cs.eventlite.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	public Iterable<Event> findAll(Sort sort);
	
	public Iterable<Event> findAllByNameContainingOrderByDateAscNameAsc(String keyword);
}
