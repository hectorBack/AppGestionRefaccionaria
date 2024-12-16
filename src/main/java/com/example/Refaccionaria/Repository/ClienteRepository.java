package com.example.Refaccionaria.Repository;

import com.example.Refaccionaria.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
