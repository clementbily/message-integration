package com.clementbily.service;

import com.clementbily.data.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);

    List<Client> getClients();

    Client getClient(Long id);

    void deleteClient(Long id);
}
