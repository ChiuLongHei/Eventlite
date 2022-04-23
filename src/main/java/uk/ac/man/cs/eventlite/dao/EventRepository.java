package uk.ac.man.cs.eventlite.dao;


import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	public Iterable<Event> findAll(Sort sort);
	
	public Iterable<Event> findAllByDateAfter(LocalDate date);

	public Iterable<Event> findAllByDateBefore(LocalDate date);
}
