/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopsockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eliel
 */
public class TrabajadorTienda  extends Observable implements Runnable {

    private int port;

    public TrabajadorTienda(int port) {
        this.port = port;
    }
    
    @Override
    public void run() {
        final String HOST = "127.0.0.1";
        DataInputStream dis;
        
        try {
            Socket sc = new Socket(HOST, port);
            dis = new DataInputStream(sc.getInputStream());
            
            while(true){
                
                String values = dis.readUTF();
                
                this.setChanged();
                this.notifyObservers(values);
                this.clearChanged();
                
                int quantity = dis.readInt();
                
                this.setChanged();
                this.notifyObservers(quantity);
                this.clearChanged();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TrabajadorTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
