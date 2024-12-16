package com.example.Refaccionaria.Controllers;

import com.example.Refaccionaria.Services.ClienteService;
import com.example.Refaccionaria.Services.ProductoService;
import com.example.Refaccionaria.Services.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    private final ProductoService productoService;
    private final ClienteService clienteService;
    private final VentaService ventaService;

    public InicioController(ProductoService productoService, ClienteService clienteService, VentaService ventaService) {
        this.productoService = productoService;
        this.clienteService = clienteService;
        this.ventaService = ventaService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("ventas", ventaService.listarTodas());
        return "inicio";
    }
}
