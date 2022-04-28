package uk.ac.man.cs.eventlite.dao;

import java.util.Optional;

import uk.ac.man.cs.eventlite.entities.Venue;

public interface VenueService {

	public long count();

	public Iterable<Venue> findAll();
	
	public Venue saveVenue(Venue venue);
	
	public Venue findById(Long id);
	
	public Optional<Venue> findById(long id);
	
}
