package inventario;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto) {

        // Verificar si el código ya existe
        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        // Validar precio y stock
        if (producto.getPrecio() < 0 || producto.getStock() < 0) {
            return false;
        }

        productos.add(producto);

        return true;
    }

    public Producto buscarProducto(int codigo) {

        for (Producto producto : productos) {

            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }

        return null;
    }

    public boolean actualizarStock(int codigo, int nuevoStock) {

        Producto producto = buscarProducto(codigo);

        if (producto != null && nuevoStock >= 0) {

            producto.setStock(nuevoStock);

            return true;
        }

        return false;
    }

    public boolean eliminarProducto(int codigo) {

        Producto producto = buscarProducto(codigo);

        if (producto != null) {

            productos.remove(producto);

            return true;
        }

        return false;
    }

    public ArrayList<Producto> getProductos() {

        return productos;
    }
}