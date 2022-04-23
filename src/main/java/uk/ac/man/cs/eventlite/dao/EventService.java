package uk.ac.man.cs.eventlite.dao;

import java.time.LocalDate;

import uk.ac.man.cs.eventlite.entities.Event;

public interface EventService {

	public long count();

	public Iterable<Event> findAll();
	
	public Event saveEvent(Event event);
	
	public Iterable<Event> findAllByDateAfter(LocalDate date);

	public Iterable<Event> findAllByDateBefore(LocalDate date);
}