package itq.dist;

import java.io.Serializable;
import java.util.UUID;

public class Request implements Serializable{

    public UUID ID;
    public int liters;
    public int tank;
    
    public Request(int liters, int tank) {
    	this.tank = tank;
    	this.liters = liters;
    	this.ID = UUID.randomUUID();
    }
    
}
