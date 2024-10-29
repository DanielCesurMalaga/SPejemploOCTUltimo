import java.util.concurrent.locks.ReentrantLock;

public class Producto extends Thread{
    static PrecioProducto producto[];
    static ReentrantLock cerrojo;

    public Producto(){
        cerrojo = new ReentrantLock();
        producto = new PrecioProducto[10];
    }
    public static void mostrarProducto(){
        cerrojo.lock();
        for (int i = 0; i < producto.length; i++) {
            if (producto[i]!=null) 
            System.out.println(producto[i].getProveedor()+" precio: "+producto[i].getPrecio());
        }
        cerrojo.unlock();
    }

    public void insertarProducto(PrecioProducto precioProducto, int posicion){
        cerrojo.lock();
        if (posicion<10){
            producto[posicion]=precioProducto;
        }
        cerrojo.unlock();
    }

    public void run(){
        for (int i = 0; i < 10; i++) {
            producto[i]= new PrecioProducto(getName()+i, i*10+1);
        }
    }
}

class PrecioProducto {
    private String proveedor;
    private float precio;
    /* Getters y Setters precios */
    public PrecioProducto(String proveedor, float precio) {
        this.proveedor = proveedor;
        this.precio = precio;
    }
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

