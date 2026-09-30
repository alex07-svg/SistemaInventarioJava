package inventario;

public class Main {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        Producto teclado = new Producto(
                101,
                "Teclado",
                25000,
                20
        );

        Producto mouse = new Producto(
                102,
                "Mouse",
                15000,
                35
        );

        Producto monitor = new Producto(
                103,
                "Monitor",
                150000,
                10
        );

        inventario.agregarProducto(teclado);
        inventario.agregarProducto(mouse);
        inventario.agregarProducto(monitor);

        System.out.println("===== INVENTARIO INICIAL =====");

        inventario.mostrarProductos();

        System.out.println("\n===== BUSCAR PRODUCTO =====");

        Producto encontrado = inventario.buscarProducto(102);

        if (encontrado != null) {

            System.out.println(
                    "Producto encontrado: "
                            + encontrado.getNombre()
            );

        } else {

            System.out.println("Producto no encontrado.");
        }

        System.out.println("\n===== ACTUALIZAR STOCK =====");

        inventario.actualizarStock(102, 50);

        inventario.mostrarProductos();

        System.out.println("\n===== ELIMINAR PRODUCTO =====");

        inventario.eliminarProducto(103);

        inventario.mostrarProductos();
    }
}