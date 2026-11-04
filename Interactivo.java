//Interfaz para elementos visuales dinamicos

public interface Interactivo {
    /**
     * Procesa la accion desencadenada por el clic del usuario
     * @return Mensaje con la respuesta a la interaccion
     */
    String manejarClic();

    /**
     * Ejecuta una actualizacion dinamica de los datos del widget
     * @return Mensaje con el estado actualizado
     */
    String actualizarTiempoReal();
}