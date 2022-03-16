package uk.ac.man.cs.eventlite.config.data;

import java.time.LocalDate;
import java.time.LocalTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.ac.man.cs.eventlite.dao.EventService;
import uk.ac.man.cs.eventlite.dao.VenueService;
import uk.ac.man.cs.eventlite.entities.Event;
import uk.ac.man.cs.eventlite.entities.Venue;

@Configuration
@Profile("default")
public class InitialDataLoader {

	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

	@Autowired
	private EventService eventService;

	@Autowired
	private VenueService venueService;

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			if (venueService.count() > 0) {
				log.info("Database already populated with venues. Skipping venue initialization.");
			} else {
				Venue kilburn = new Venue();
				kilburn.setCapacity(50);
				kilburn.setId(1);
				kilburn.setName("Kilburn");
				venueService.saveVenue(kilburn);
			}
			
			if (eventService.count() > 0) {
				log.info("Database already populated with events. Skipping event initialization.");
			} else {
				Venue mecd = new Venue();
				mecd.setCapacity(50);
				mecd.setId(1);
				mecd.setName("MECD");
				venueService.saveVenue(mecd);
				Event showcase = new Event();
				showcase.setId(1);
				showcase.setName("COMP23412 showcase G18");
				showcase.setTime(LocalTime.of(15, 00));
				showcase.setDate(LocalDate.of(2022, 3, 4));
				showcase.setVenue(mecd);
				eventService.saveEvent(showcase);
				Event groupProject = new Event();
				groupProject.setId(2);
				groupProject.setName("Group meeting");
				groupProject.setTime(LocalTime.of(15, 00));
				groupProject.setDate(LocalDate.of(2022, 3, 4));
				groupProject.setVenue(mecd);
				eventService.saveEvent(groupProject);
			}
		};
	}
}