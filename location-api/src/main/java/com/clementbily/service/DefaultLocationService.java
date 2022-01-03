package com.clementbily.service;

import com.clementbily.data.Location;
import com.clementbily.data.NewLocation;
import com.clementbily.repository.ClientRepository;
import com.clementbily.repository.LocationRepository;
import com.clementbily.repository.entity.ClientEntity;
import com.clementbily.repository.entity.LocationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultLocationService implements LocationService {

    private final LocationRepository locationRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public DefaultLocationService(LocationRepository locationRepository, ClientRepository clientRepository) {
        this.locationRepository = locationRepository;
        this.clientRepository = clientRepository;

    }

    /**
     * add a new location
     *
     * @param location the location to create
     * @return the newly created location
     */
    public Location addLocation(NewLocation location) {
        LocationEntity entity = new LocationEntity();
        ClientEntity clientEntity = clientRepository.
                findById(location.getClientId())
                .orElseThrow();
        modelMapper.map(location, entity);
        entity.setClient(clientEntity);
        entity = locationRepository.save(entity);
        return entityToLocation(entity);
    }

    /**
     * get all the locations default sorted by id
     *
     * @return all the locations
     */
    public List<Location> getLocations() {
        List<LocationEntity> locationEntities = new ArrayList<>();
        locationRepository.findAll().forEach(locationEntities::add);
        List<Location> locations = locationEntities.stream().map(this::entityToLocation)
                .collect(Collectors.toUnmodifiableList());
        return locations;
    }

    /**
     * get one location
     *
     * @param id the id to find the location
     * @return the location
     * @throws Exception when an error occur
     */
    public Location getLocation(Long id) throws Exception {
        Optional<LocationEntity> entity = locationRepository.findById(id);
        var location = entity.map(this::entityToLocation);
        return location.orElseThrow(() -> new Exception("error"));
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    /**
     * convert function between entity and domain model
     *
     * @param entity
     * @return the mapped domain model Location
     */
    private Location entityToLocation(LocationEntity entity) {
        Location location = new Location();
        modelMapper.map(entity, location);
        return location;
    }
}
