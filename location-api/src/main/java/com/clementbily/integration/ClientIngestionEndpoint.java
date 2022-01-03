package com.clementbily.integration;

import com.clementbily.data.Client;
import com.clementbily.repository.ClientRepository;
import com.clementbily.repository.entity.ClientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.integration.annotation.EndpointId;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ClientIngestionEndpoint {

    private final ClientRepository clientRepository;
    private final  ModelMapper mapper = new ModelMapper();
    public ClientIngestionEndpoint(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @EndpointId("clientIngestor")
    @ServiceActivator(inputChannel = "client")
    public void addClient(Message<Client> message) {
        Client client = message.getPayload();
        var clientEntity = new ClientEntity();
        mapper.map(client, clientEntity);
        clientRepository.save(clientEntity);
    }
}


