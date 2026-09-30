package inventario;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos;
    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void mostrarProductos() {

        for (Producto producto : productos) {

            System.out.println(
                    "Código: " + producto.getCodigo()
                            + " | Nombre: " + producto.getNombre()
                            + " | Precio: $" + producto.getPrecio()
                            + " | Stock: " + producto.getStock()
            );
        }
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
        if (producto != null) {
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
}
