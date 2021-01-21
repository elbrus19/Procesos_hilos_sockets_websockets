/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopsockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eliel
 */
public class ServidorTienda implements Runnable {

    private ArrayList<Socket> employees;

    private int port;

    public ServidorTienda(int port) {
        this.port = port;
        this.employees = new ArrayList();
    }

    @Override
    public void run() {
        ServerSocket server;
        Socket sc;
        DataInputStream in;

        try {
            server = new ServerSocket(port);
            System.out.println("Servidor Iniciado");

            while (true) {
                sc = server.accept();
                System.out.println("Trabajador Conectado");
                employees.add(sc);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendInfo(String[] values, int[] quantity) {

        for (Socket socket : employees) {

            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                for (int i = 0; i < values.length; i++) {
                    dos.writeUTF(values[i]);
                    dos.writeInt(quantity[i]);
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorTienda.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
