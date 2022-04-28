package uk.ac.man.cs.eventlite.dao;

import java.time.LocalDate;
import java.util.List;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventService {

	public long count();

	public Iterable<Event> findAll();
	
	public Event saveEvent(Event event);
	
	public Iterable<Event> searchAfter(LocalDate date, String keyword);

	public Iterable<Event> searchBefore(LocalDate date, String keyword);
	
	public Iterable<Event> findAllByDateAfter(LocalDate date);

	public Iterable<Event> findAllByDateBefore(LocalDate date);
	
	public void deleteAll();
}