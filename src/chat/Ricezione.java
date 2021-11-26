/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gero
 */
public class Ricezione extends Thread {

    DatagramSocket socket;
    InetAddress indirizzoMittente;
    Comunicazione c1;
    static InetAddress vecchio;

    public Ricezione(String Nome) throws SocketException {
        this.socket = new DatagramSocket(1235);
        indirizzoMittente = null;
        c1 = Comunicazione.getComunicazione();
        vecchio = null;
        Comunicazione.getComunicazione().NomeMittente=Nome;

    }

    @Override
    public void run() {

        while (true) {

            byte[] buffer = new byte[1500];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("ricevuto: "+packet.getData());
            if (vecchio != null) {
                indirizzoMittente = packet.getAddress();
                if (vecchio.equals(indirizzoMittente)) {

                    try {
                        c1.setInvia(indirizzoMittente);
                        setoperazioneEdato(new String(packet.getData(), 0, packet.getLength()));
                        c1.creaMessaggioEInvia();
                    } catch (SocketException ex) {
                        Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                    //scarta il pacchetto
                }

            } else {
                vecchio = packet.getAddress();
                try {
                    //invia y al mittente

                    c1.setInvia(vecchio);
                    setoperazioneEdato(new String(packet.getData(), 0, packet.getLength()));
                    c1.creaMessaggioEInvia();
                } catch (SocketException ex) {
                    Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public void setoperazioneEdato(String mess) {
        String[] dati = mess.split(";");

        c1.setDato(dati[1]);
        c1.setOperazione(dati[0]);
    }

}
