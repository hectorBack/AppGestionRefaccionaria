package com.example.Refaccionaria.Controllers;

import com.example.Refaccionaria.Entity.Venta;
import com.example.Refaccionaria.Services.ClienteService;
import com.example.Refaccionaria.Services.ProductoService;
import com.example.Refaccionaria.Services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarVentas(Model model) {
        List<Venta> ventas = ventaService.listarTodas();
        model.addAttribute("ventas", ventas);
        return "ventas/lista"; // Vista para listar ventas
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaVenta(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "ventas/formulario"; // Vista para agregar una nueva venta
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") Venta venta, Model model) {
        try {
            ventaService.guardar(venta);
            return "redirect:/ventas";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("venta", venta);
            model.addAttribute("productos", productoService.listarProductos());
            model.addAttribute("clientes", clienteService.listarTodos());
            return "ventas/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVenta(@PathVariable Long id, Model model) {
        Venta venta = ventaService.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada: " + id));
        model.addAttribute("venta", venta);
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "ventas/formulario"; // Reutilizamos la vista de agregar/editar venta
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }
}
