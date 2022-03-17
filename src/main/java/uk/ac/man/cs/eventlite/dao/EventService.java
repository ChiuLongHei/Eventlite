package uk.ac.man.cs.eventlite.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

public interface EventService {

	public long count();

	public Iterable<Event> findAll();
	
	public Event saveEvent(Event event);
	
	public Event updateEventName(long id, String name);
	
	public Event updateEventDate(long id, LocalDate date);
	
	public Event updateEventTime(long id, LocalTime time);

	public Event updateEventVenue(long id, Venue venue);
}