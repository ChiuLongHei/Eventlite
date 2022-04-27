package uk.ac.man.cs.eventlite.dao;

import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.man.cs.eventlite.entities.Venue;

@Service
public class VenueServiceImpl implements VenueService {

	private final static Logger log = LoggerFactory.getLogger(VenueServiceImpl.class);

	private final static String DATA = "data/venues.json";
	
	@Autowired
	private venueRepository venueRepository;

	@Override
	public long count() {

		return venueRepository.count();
	}

	@Override
	public Iterable<Venue> findAll() {
		
		return venueRepository.findAll();
	}
	
	@Override
	public Venue saveVenue(Venue venue) {
		return venueRepository.save(venue);
	}
	
	public Venue findById(Long id) {
		return venueRepository.findById(id).get();
		
	}
	
	public void deleteAll() {
		venueRepository.deleteAll();
		
	}

}

