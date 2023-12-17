package com.wetechb.CarwashProject.controller;

import com.wetechb.CarwashProject.entity.Client;
import com.wetechb.CarwashProject.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    private final JavaMailSender emailSender;
    @PostMapping("/addClient")
    public String addClient(@ModelAttribute Client client, @RequestParam("action") String action){
        System.out.println(action);
        if(action.equals("Update")){
            client.setId(client.getId());
            service.updateClient(client);
        }else if(action.equals("Delete")){
            service.deleteClient(client.getId());
        }else{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(client.getClientEmail());
            mailMessage.setFrom("isaacganza22@gmail.com");
            mailMessage.setSubject("Appointment Message");
            mailMessage.setText("Hello, Your appointment has been registered successfully");
            emailSender.send(mailMessage);
            service.saveClient(client);
            System.out.println(client);

        }
        return "redirect:/view-register";
    }


    @PostMapping("/addClients")
    public List<Client> addClients(@RequestBody List<Client> clients){
        return service.saveClients(clients);
    }
    @GetMapping("/client")
    public String findAllClients(Model model){
        model.addAttribute("clientModel", new Client());
        model.addAttribute("clientList", service.getClients());
        return "client";
    }
    @GetMapping("/view-register")
    public String GetAllClients(Model model){
        model.addAttribute("clientModel", new Client());
        model.addAttribute("clientList", service.getClients());
        return "view";
    }
    @GetMapping("/clients/{id}")
    public Client findClientById(@PathVariable int id){
        return service.getClientId(id);
    }

    @PutMapping("/update")
    public String updateClient(@ModelAttribute Client client){
       service.updateClient(client);
        return "update";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteClient(@PathVariable int id){
        return service.deleteClient(id);
    }
    @GetMapping("/dashboard")
    public String showHomePage() {
        return "dashboard";
    }

    @PostMapping("/searchBy")
    public String searchClient(@RequestParam("search") String search, Model model){
        model.addAttribute("clientModel", new Client());
        model.addAttribute("clientList", service.getSearchedClient(search));
        return "view";
    }

}
