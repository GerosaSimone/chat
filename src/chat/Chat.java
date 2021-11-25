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
    Integer Ys;
    Integer Yd;
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
                f.setPersona(Persona);
                set = true;
            }else
                set=false;
            
            
            if(!QueueMessaggi.isEmpty()){
                 //stampa messaggio
                 Messaggio m=QueueMessaggi.remove(0);
                 
                 if(m.Type==Messaggio.ARRIVATO){
                     //stampo m.message a sinistra
                     f.AggiungiSinistra(dividiMessaggio(m.message), Ys, calcolaAltezza(m.message));
                 }
                 if(m.Type==Messaggio.INVIATO){
                     //stampo m.message a destra
                     f.AggiungiDestra(dividiMessaggio(m.message), Ys, calcolaAltezza(m.message));
                 }
                 
                 Messaggi.add(m);
            }
        }
        
    }
    
    public String dividiMessaggio(String s){
        String S="";
        Integer count=0;
        for (int i = 0; i < s.length()/30; i++) {
            while(count<30){
                if(s.charAt(count)==' '&&count>=30)
                {
                    char[] c=s.toCharArray();
                    c[count]='\n';
                    S+=c;
                }
                count++;
            }
            count=0;
        }
        return S;
    }
    
    public Integer calcolaAltezza(String s){
        return (s.length()/30)*10; 
    }
    
    
    

}
