/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerosa_simone
 */
public class Chat extends Thread {

    static Chat c;
    ArrayList<Messaggio> Messaggi;
    ArrayList<Messaggio> QueueMessaggi;
    String Persona;
    Form2 f;

    public Chat() throws SocketException {
        f = new Form2();
        Messaggi = new ArrayList();
        QueueMessaggi = new ArrayList();
        Persona=null;
    }

    public Chat(Form2 f) {
        this.f = f;
        Messaggi = new ArrayList();
        QueueMessaggi = new ArrayList();
        Persona=null;
    }

    public static Chat getInstance() throws SocketException {
        // Crea l'oggetto solo se NON esiste:
        if (c == null) {
            c = new Chat();
        }
        return c;
    }

    public void AggiungiMessaggio(Messaggio m) {
        QueueMessaggi.add(m);
    }

    public void azzera() {
        Messaggi = new ArrayList();
        QueueMessaggi = new ArrayList();
        Persona=null;
    }

    @Override
    public void run() {
        boolean set=false;
        while (true) {
            
            if(Persona!=null&&!set)
            {
                //imposto persona in alto
                
                set = true;
            }else
                set=false;
            
            
            if(!QueueMessaggi.isEmpty()){
                 //stampa messaggio
                 Messaggio m=QueueMessaggi.remove(0);
                 
                 if(m.Type==Messaggio.ARRIVATO){
                     //stampo m.message a sinistra
                 }
                 if(m.Type==Messaggio.INVIATO){
                     //stampo m.message a destra
                 }
                 
                 Messaggi.add(m);
            }
        }
        
    }
    
    
    

}
