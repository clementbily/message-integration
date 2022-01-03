package com.clementbily.service;

import com.clementbily.dao.entity.ClientEntity;
import com.clementbily.dao.entity.ClientRepository;
import com.clementbily.data.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultClientService implements ClientService {

    private final ClientRepository clientRepository;
    private final MessageChannel messageChannel;

    public DefaultClientService(ClientRepository clientRepository, @Qualifier(value = "client") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {

        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(client, clientEntity);
        clientEntity = clientRepository.save(clientEntity);
        Client clientData = new Client();
        BeanUtils.copyProperties(clientEntity, clientData);
        messageChannel.send(new GenericMessage<>(clientData));
        return clientData;
    }

    @Override
    public List<Client> getClients() {
        List<ClientEntity> clients = new LinkedList<>();
        clientRepository.findAll().forEach(clients::add);
        List<Client> clientList = new ArrayList<>();
        for (ClientEntity ClientEntity : clients) {
            Client client = new Client();
            BeanUtils.copyProperties(ClientEntity, client);
            clientList.add(client);
        }
        return clientList;
    }

    @Override
    public Client getClient(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        Client clientData = new Client();
        BeanUtils.copyProperties(clientEntity.get(), clientData);
        return clientData;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
