package itq.dist;
import itq.dist.Tank;
import itq.dist.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.time.LocalTime;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private static final int PORT = 2000;
/**
 * 
 * @param args
 */
    public static void main(String[] args) {
        ServerSocket serverSocket;
        boolean alive = true;
        String message;
      
        Tank tank1 = new Tank(10000, 10); // Tanque 1: 10000 litros de capacidad, 10 minutos tiempo de llenado
        Tank tank2 = new Tank(20000, 20); // Tanque 2: 20000 litros de capacidad, 20 minutos tiempo de llenado
        Tank tank3 = new Tank(3000, 3); //Tanque 3: 3000 litros de capacidad, 3 minutos tiempo de llenado

        try {
            serverSocket = new ServerSocket(PORT);
            logger.info("Servidor iniciado exitosamente en el puerto [" + PORT + "]");
            try {
                while(alive) {
                    Socket socket = serverSocket.accept();
                  
                    if (java.time.LocalTime.now().toString() == "00:00:0.0") {
                        tank1.fill();
                        tank2.fill();
                        tank3.fill();
                    }

                    InputStream inStream = socket.getInputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
					Request clientRequest = (Request) objectInputStream.readObject();
					
                    logger.info("Petición de llenado de [" + clientRequest.liters + "] recibida del cliente [" + clientRequest.ID + "]" + " a la hora: " + java.time.LocalTime.now().toString());
                    
                    OutputStream outStream = socket.getOutputStream();
                    DataOutputStream flowOut = new DataOutputStream(outStream);

                    try {
                        switch(clientRequest.tank) {
                            case 1:
                                tank1.dispatch(clientRequest.liters);
                                logger.info("El Tanque " + clientRequest.tank + " ha despachado " + clientRequest.liters + "L correctamente, su contenido actual es de: " + tank1.capacity + " L \n ");
                                message = getMessage(tank1,clientRequest);
                                break;
                            case 2:
                                tank2.dispatch(clientRequest.liters);
                                logger.info("El Tanque " + clientRequest.tank + " ha despachado " + clientRequest.liters + "L  correctamente, su contenido actual es de: " + tank2.capacity + " L \n ");
                                message = getMessage(tank2,clientRequest);
                                break;
                            case 3:
                                tank3.dispatch(clientRequest.liters);
                                logger.info("El Tanque " + clientRequest.tank + " ha despachado " + clientRequest.liters + "L  correctamente, su contenido actual es de: " + tank3.capacity + " L \n ");
                                message = getMessage(tank3,clientRequest);
                                break;
                            default:
                                message = "El número de tanque para despachar no coincidio con los tanques registrados";
                        }
                        flowOut.writeUTF(message);
                        logger.info(message)
                    } catch(Exception e) {
                        flowOut.writeUTF(e.getMessage());
                    }						                
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
/**
 * 
 * @param tank
 * @param request
 * @return
 */
    private static String getMessage(Tank tank, Request request) {
		return "Se han despachado " + request.liters + "L correctamente";
	}
}
