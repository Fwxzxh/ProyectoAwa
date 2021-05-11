package itq.dist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tank extends Thread {
    final static Logger logger = LogManager.getLogger(Tank.class);

    // Cantidad en litros
    int MIN_DISPATCH = 1;
    int MAX_DISPATCH = 1000;

    public int fullCapacity;
    public int capacity; // Litros
    public int fillTime; // Minutos

    public Tank(int capacity, int fillTime) {
        this.fullCapacity = capacity;
        this.capacity = capacity;
        this.fillTime = fillTime;
    }
  
    public synchronized void dispatch(int liters) throws Exception {
        if (liters >= MIN_DISPATCH && liters <= MAX_DISPATCH) {
        	if (liters > capacity && capacity == 0) {
        		throw new Exception("El tanque se encuentra vacío, intente el día de mañana");
        	} else if (liters > capacity) {
        		throw new Exception("El tanque no dispone de esa cantidad, por favor indique una cantidad menor de agua");
        	} else {
            this.capacity -= liters;
            }
        } else {
            throw new Exception("Los litros especificados estan fuera de la capacidad para despachar \nCapacidad minima: " + MIN_DISPATCH + "\nCapacidad máxima: " + MAX_DISPATCH);
        }
    }

    public void fill() {
        try {
            Thread.sleep(this.fillTime * 1000 * 60);
        } catch (InterruptedException e) {
            logger.error("El hilo actual fue detenido: [" + this.getId() + "]");
            logger.error(e.getMessage());
        }

        this.capacity = this.fullCapacity;
        logger.info("Tanque llenado");
    }
}
