package inventario;

public class Main
{
    public static void main(String[] args) {
        Producto teclado = new Producto(101, "Teclado", 29.99, 10);
        System.out.println(teclado.getNombre());
        System.out.println(teclado.getCodigo());
        System.out.println(teclado.getPrecio());
        System.out.println(teclado.getStock());
    }
}
