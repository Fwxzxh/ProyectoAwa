package itq.dist;
import itq.dist.Tank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.time.LocalTime;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private static final int PORT = 2000;

    public static void main(String[] args) {
        ServerSocket serverSocket;
        boolean alive = true;
      
        Tank tank1 = new Tank(10000, 10); // Tanque 1: 10000 litros de capacidad, 10 minutos tiempo de llenado
        Tank tank2 = new Tank(20000, 20); // Tanque 2: 20000 litros de capacidad, 20 minutos tiempo de llenado
        Tank tank3 = new Tank(3000, 3); //Tanque 3: 3000 litros de capacidad, 3 minutos tiempo de llenado

        try {
            serverSocket = new ServerSocket(PORT);
            logger.info("Servidor iniciado exitosamente en el puerto [" + PORT + "]");
            try {
                while(alive) {
                    Socket socket = serverSocket.accept();
                  
                  // A las 00:00 hrs los tanques se llenan
                    if (java.time.LocalTime.now().toString() == "00:00:0.0") {
                        tank1.fill();
                        tank2.fill();
                        tank3.fill();
                    }

                    InputStream inStream = socket.getInputStream();
                    DataInputStream dataIn = new DataInputStream(inStream);
                    int input = dataIn.readInt(); // Solicitud de n litros (Provicional)

                    logger.debug("Datos recibidos: [" + input + "]");

                    OutputStream outStream = socket.getOutputStream();
                    DataOutputStream flowOut = new DataOutputStream(outStream);
                  
                  // Provicionalmente en el tanque 1 se despachan n litros
                    try {
                        tank1.dispatch(input);
                    } catch(Exception e) {
                        flowOut.writeUTF(e.getMessage());
                    }

                    flowOut.writeUTF("Tank 1 capacity: " + tank1.capacity);
                }

                serverSocket.close();
            } catch(Exception e) {
                e.printStackTrace();
                logger.error("Ocurrio un error en el server: ");
                logger.error(e.getMessage());
            }
        } catch(IOException e) {
            e.printStackTrace();
            logger.error("Puerto ocupado: [" + PORT + "]");
            logger.error(e.getMessage());
        }
    }
}
