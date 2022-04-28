package uk.ac.man.cs.eventlite.dao;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import uk.ac.man.cs.eventlite.entities.Event;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository; 

	private final static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	private final static String DATA = "data/events.json";

	@Override
	public long count() {
		return eventRepository.count();
	}

    @Override
	public Iterable<Event> findAll() {
		Iterable<Event> listEvents = eventRepository.findAll(Sort.by("date").ascending().and(Sort.by("time").ascending()));
		return listEvents;
	}
	
	@Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
	
	@Override
	public Iterable<Event> searchAfter(LocalDate date, String keyword){
		return eventRepository.findAllByDateAfterAndNameContainingOrderByDateAscNameAsc(date, keyword);	
	}
	
	@Override
	public Iterable<Event> searchBefore(LocalDate date, String keyword){
		return eventRepository.findAllByDateBeforeAndNameContainingOrderByDateAscNameAsc(date, keyword);	
	}
	
	public Iterable<Event> findAllByDateAfter(LocalDate date){
		return eventRepository.findAllByDateAfter(date);
	}
	
	public Iterable<Event> findAllByDateBefore(LocalDate date){
		return eventRepository.findAllByDateBefore(date);
	}
	
	public void deleteAll() {
		eventRepository.deleteAll();
		
	}
}