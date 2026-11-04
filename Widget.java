import java.util.Objects;

//clase abstracta base que es elemento visual del dashboard
// tambien implementa renderizable y comparable para el orden de consumo de memoria 

public abstract class Widget implements Renderizable, Comparable<Widget> {
    protected String id;
    protected String nombre;
    protected int consumoMemoriaKB;

    public Widget(String id, String nombre, int consumoMemoriaKB) {
        this.id = id;
        this.nombre = nombre;
        this.consumoMemoriaKB = consumoMemoriaKB;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getConsumoMemoriaKB() {
        return consumoMemoriaKB;
    }

    public void setConsumoMemoriaKB(int consumoMemoriaKB) {
        this.consumoMemoriaKB = consumoMemoriaKB;
    }
// implementacion para ordenar la lista por memoria consumida
    
    @Override
    public int compareTo(Widget otro) {
        return Integer.compare(this.consumoMemoriaKB, otro.getConsumoMemoriaKB());
    }
// metodo abstracto para tener los detalles de configuracion del widget
    
    public abstract String obtenerDetallesConfiguracion();

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " (" + consumoMemoriaKB + " KB)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Widget widget = (Widget) obj;
        return Objects.equals(id, widget.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
