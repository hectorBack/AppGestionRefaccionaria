package com.example.Refaccionaria.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Refaccionaria.Entity.Proveedor;
import com.example.Refaccionaria.Repository.ProveedorRepository;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository proveedorRepository;

	//Obtener todos los Proveedores
	public List<Proveedor> listarProveedores(){
		return proveedorRepository.findAll();
	}
	
	//Guardar Proveedores 
	public Proveedor guardarProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	
	//Eliminar Proveedor por id
	public void eliminarProveedor(Long id) {
		proveedorRepository.deleteById(id);
	}

	//obtener por Id
	public Optional<Proveedor> obtenerPorId(Long id){
		return proveedorRepository.findById(id);
	}

}
