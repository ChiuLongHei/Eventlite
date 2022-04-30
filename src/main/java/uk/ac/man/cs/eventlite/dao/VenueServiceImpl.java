package uk.ac.man.cs.eventlite.dao;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import com.mapbox.geojson.Point;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.ac.man.cs.eventlite.entities.Event;
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
		
		Iterable<Venue> listVenues = venueRepository.findAll(Sort.by("name").ascending());
		return listVenues;

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
	
	@Override
	public Optional<Venue> findById(long id){
		return venueRepository.findById(id);
	}

	@Override
	public Iterable<Venue> searchVenues(String keyword){
		return venueRepository.findAllByNameContainingOrderByNameAsc(keyword);	
	}
	
	@Override
	public Venue setLocation(Venue venue) {
		String address = venue.getAddress();
		String postcode = venue.getPostalCode();
		MapboxGeocoding mapboxGeocoding = MapboxGeocoding.builder()
				.accessToken("pk.eyJ1IjoiZDNubmlzdXR1IiwiYSI6ImNsMm0xYmpyYTBpcnAzYm11N3JuY2k0c3MifQ.xKR2OruW0ljKvKjBnusWvg")
				.query(postcode)
				.build();
		mapboxGeocoding.enqueueCall(new Callback<GeocodingResponse>() {
			@Override
			public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
		 
				List<CarmenFeature> results = response.body().features();
		 
				if (results.size() > 0) {
				  // Log the first results Point.
				  Point firstResultPoint = results.get(0).center();
				  venue.setLongitude(firstResultPoint.longitude());
				  venue.setLatitude(firstResultPoint.latitude());
				  log.info("Longitude: " + firstResultPoint.longitude());
				  log.info("Latitude: " + firstResultPoint.latitude());
		 
				} else {
				  // No result for your request were found.
				  venue.setLongitude(0.0);
				  venue.setLatitude(0.0);
				  log.info("onResponse: No result found");
				}
			}
		 
			@Override
			public void onFailure(Call<GeocodingResponse> call, Throwable throwable) {
				throwable.printStackTrace();
			}
		});
		try {
		    Thread.sleep(1000L);
		} catch(InterruptedException e) {
		    System.out.println("got interrupted!");
		}
		return venue;
	}
}

