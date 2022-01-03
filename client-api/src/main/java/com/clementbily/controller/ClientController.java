package com.clementbily.controller;

import com.clementbily.data.Client;
import com.clementbily.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Get all the Client available in the underlying system
     *
     * @return list of Clients
     */
    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        List<Client> Clients = clientService.getClients();
        return new ResponseEntity<>(Clients, HttpStatus.OK);
    }

    /**
     * Create a Client with the system.This end point accepts Client information in
     * the json format.It will create and send back the data to the REST Client.
     *
     * @param client
     * @return newely created Client
     */
    @PostMapping()
    public ResponseEntity<Client> createClient(@RequestBody @Valid Client client) {
        Client ClientData = clientService.createClient(client);
        return new ResponseEntity<>(ClientData, HttpStatus.CREATED);
    }

    /**
     * Deleted the Client from the system.client will pass the ID for the Client and this
     * end point will remove Client from the system if found.
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get the Client detail based on the id passed by the client API.
     *
     * @param id
     * @return Client detail
     */
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client Client = clientService.getClient(id);
        return new ResponseEntity<>(Client, HttpStatus.OK);
    }
}
