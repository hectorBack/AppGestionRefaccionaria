package com.example.Refaccionaria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Refaccionaria.Entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
