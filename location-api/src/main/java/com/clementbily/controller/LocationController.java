package com.clementbily.controller;

import com.clementbily.data.Location;
import com.clementbily.data.NewLocation;
import com.clementbily.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    /**
     * the service
     */
    private final LocationService locationService;


    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Get all the Location available in the underlying system
     *
     * @return list of Clients
     */
    @GetMapping
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locations = locationService.getLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    /**
     * Create a Location with the system.This end point accepts Client information in
     * the json format.It will create and send back the data to the REST Client.
     *
     * @param location the locqtion to create
     * @return newely created Location
     */
    @PostMapping()
    public ResponseEntity<Location> addLocation(@RequestBody @Valid NewLocation location) {
        Location locationData = locationService.addLocation(location);
        return new ResponseEntity<>(locationData, HttpStatus.CREATED);
    }

    /**
     * Deleted the location from the system.location will pass the ID for the Client and this
     * end point will remove Client from the system if found.
     *
     * @param id
     */
    @DeleteMapping(value = "/location/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get the Location detail based on the id passed by the location API.
     *
     * @param id the location's id to get
     * @return Location detail
     */
    @GetMapping(value = "/location/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) throws Exception {
        Location location = locationService.getLocation(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}
