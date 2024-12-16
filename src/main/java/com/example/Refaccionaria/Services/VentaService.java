package com.example.Refaccionaria.Services;

import com.example.Refaccionaria.Entity.Producto;
import com.example.Refaccionaria.Entity.Venta;
import com.example.Refaccionaria.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoService productoService;

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta guardar(Venta venta) {
        // Obtén el producto asociado para verificar el precio y el stock
        Producto producto = productoService.obtenerPorId(venta.getProducto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        // Verifica si hay suficiente stock
        if (producto.getCantidadEnStock() < venta.getCantidad()) {
            throw new IllegalArgumentException("No hay suficiente stock disponible para el producto.");
        }

        // Calcula el total
        BigDecimal total = producto.getPrecio().multiply(BigDecimal.valueOf(venta.getCantidad()));

        // Asigna el total a la venta
        venta.setTotal(total);

        // Resta la cantidad vendida del stock del producto
        producto.setCantidadEnStock(producto.getCantidadEnStock() - venta.getCantidad());

        // Guarda los cambios en el producto
        productoService.guardarProducto(producto);

        // Guarda la venta
        return ventaRepository.save(venta);
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }
}
