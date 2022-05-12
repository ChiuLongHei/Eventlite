package uk.ac.man.cs.eventlite.dao;


import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

public interface EventRepository extends CrudRepository<Event, Long>{
	public Iterable<Event> findAll(Sort sort);
	
	public Iterable<Event> findAllByNameContainingOrderByDateAscNameAsc(String keyword);
	
	public Iterable<Event> findAllByDateAfterAndNameContainingOrderByDateAscNameAsc(LocalDate date, String keyword);
	
	public Iterable<Event> findAllByDateBeforeAndNameContainingOrderByDateAscNameAsc(LocalDate date, String keyword);
	
	public Iterable<Event> findAllByDateAfter(LocalDate date);

	public Iterable<Event> findAllByDateBefore(LocalDate date);
	
	public Iterable<Event> findAllByVenue(Venue venue);
}
