package com.wetechb.CarwashProject.repository;

import com.wetechb.CarwashProject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByClientName(String name);
}
