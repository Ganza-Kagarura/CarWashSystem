package com.wetechb.CarwashProject.service;

import com.wetechb.CarwashProject.entity.Client;
import com.wetechb.CarwashProject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public List<Client> saveClients(List<Client> clients) {
        return repository.saveAll(clients);
    }
    public List<Client> getClients(){
        return repository.findAll();
    }
    public Client getClientId(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteClient(int id) {
        repository.deleteById(id);
        return "product removed !!"+id;
    }

    public Client updateClient(Client client){
        Client existingClient = repository.findById(client.getId()).orElse(null);
        existingClient.setClientName(client.getClientName());
        existingClient.setPlateNumber(client.getPlateNumber());
        existingClient.setCarModel(client.getCarModel());
        existingClient.setClientEmail(client.getClientEmail());
        existingClient.setAppointmentDate(client.getAppointmentDate());
        existingClient.setPrice(client.getPrice());
        return repository.save(existingClient);
    }

    public Client getSearchedClient(String search) {
        return repository.findByClientName(search);
    }
}
