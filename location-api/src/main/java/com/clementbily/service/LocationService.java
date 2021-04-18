package com.clementbily.service;

import com.clementbily.data.Location;
import com.clementbily.data.NewLocation;

import java.util.List;

public interface LocationService {


        /**
         * add a new location
         * @param location the location to create
         * @return the newly created location
         */
        public Location addLocation(final NewLocation location);

        /**
         * get all the locations default sorted by id
         * @return all the locations
         */
        public List<Location> getLocations();

        /**
         * get one location
         * @param id the id to find the location
         * @return the location
         * @throws Exception when an error occur
         */
        public Location getLocation(Long id) throws Exception;

        public void deleteLocation(Long id);


}
