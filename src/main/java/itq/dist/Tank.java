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

    public void dispatch(int liters) throws Exception {
        if (liters >= MIN_DISPATCH && liters <= MAX_DISPATCH) {
            this.capacity -= liters;
        } else {
            throw new Exception("Couldn't dispatch\nMin capacity: " + MIN_DISPATCH + "\nMax capacity: " + MAX_DISPATCH);
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
