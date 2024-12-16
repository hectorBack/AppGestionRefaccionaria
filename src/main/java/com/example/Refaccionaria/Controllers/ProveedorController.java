package com.example.Refaccionaria.Controllers;

import com.example.Refaccionaria.Entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Refaccionaria.Entity.Proveedor;
import com.example.Refaccionaria.Services.ProveedorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("proveedores", proveedorService.listarProveedores());
		return "proveedores/lista";
	}
	
	@GetMapping("/nuevo")
	public String nuevoProveedorForm(Model model) {
		model.addAttribute("proveedores", new Proveedor());
		return "proveedores/formulario";
	}
	
	@PostMapping("guardar")
	public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
		proveedorService.guardarProveedor(proveedor);
		return "redirect:/proveedores";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarProveedor(@PathVariable Long id) {
		proveedorService.eliminarProveedor(id);
		return "redirect:/proveedores";
	}

	// Mostrar formulario de edición de un producto
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Proveedor proveedor = proveedorService.obtenerPorId(id)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
		model.addAttribute("proveedores", proveedor);
		 		return "proveedores/formulario";
	}
	
}
