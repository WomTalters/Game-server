
package Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;



/**
 *
 * @author Tom
 */
public class Manager implements Runnable{
    private DatagramSocket socket;
    private ArrayList<Connection> connections = new ArrayList();
    private LinkedList<DatagramPacket> packetQueue = new LinkedList();
    
    boolean running;
    
    
    
    public void Manager(){
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            System.out.println("Error: "+ ex.getCause());
        }
        
        Thread netThread = new Thread(this);
        netThread.setName("Network");
        netThread.start();
        
    }

    @Override
    public void run() {        
        DatagramPacket packet;
        byte[] buf;
        
        running = true;
        
        while(running){
            
            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getCause());
            }
        }        
    }
    
    
    
}
