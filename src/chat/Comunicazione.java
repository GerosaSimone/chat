/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author gero
 */
//oggetto comunicazione creare nel main
public class Comunicazione {
    Form1 f;
    static Comunicazione c;
    Invia invia;
    String NomeDestinatario;
    String NomeMittente;
    String operazione;
    String dato;
    
    public Comunicazione() throws SocketException {
        
        this.invia = new Invia();
        this.operazione = "";
        this.dato = "";
        NomeDestinatario = null;
        NomeMittente = null;
        c = null;
    }
    
    public void azzera() throws SocketException {
        this.invia.azzera();
        this.operazione = "";
        this.dato = "";
        NomeDestinatario = null;
        c = null;
    }
    
    public Comunicazione(String nome) throws SocketException {
        
        this.invia = new Invia();
        this.operazione = "";
        this.dato = "";
        NomeDestinatario = null;
        NomeMittente = nome;
        c = null;
    }
    
    public void setInvia(InetAddress invia) throws SocketException {
        this.invia.setIndirizzoDestinatario(invia);
        ;
    }
    
    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }
    
    public void setDato(String dato) {
        this.dato = dato;
    }
    
    public static synchronized Comunicazione getComunicazione() throws SocketException {
        if (c == null) {
            c = new Comunicazione();
        }
        return c;
    }
    
    public void creaMessaggioEInvia() throws IOException {
        if (operazione.equals("a")) {
            if (NomeDestinatario == null) {
                int option = JOptionPane.showConfirmDialog(null, "accettare la comunicazione con:" + this.dato, "richiesta", JOptionPane.YES_NO_OPTION);
                
                if (option == 0) { //The ISSUE is here
                    NomeDestinatario = this.dato;
                    invia.send("y;" + NomeMittente);
                    Chat.getInstance().Persona=NomeDestinatario;
                    
                } else {
                    invia.send("n;;");
                    azzera();
                    invia.azzera();
                    //imposto vecchio della ricezione a null
                    Ricezione.vecchio = null;
                    Chat.getInstance().azzera();
                }
            } else {
                invia.send("n;;");
                Ricezione.vecchio = null;
                Chat.getInstance().azzera();
                //imposto vecchio della ricezione a null
            }
            
        } else if (operazione.equals("m")) {
            Chat.getInstance().AggiungiMessaggio(new Messaggio(dato,0));
            //visualizziamo a schermo il messaggio
            //metodo aggiungi messaggio statico nel form 
        } else if (operazione.equals("c")) {
            //settodestinatario a null
            //messagebox connessione chiusa
            azzera();
            invia.azzera();
            Ricezione.vecchio = null;
            Chat.getInstance().azzera();
        } else if (operazione.equals("y")) {
            
            Chat.getInstance().Persona = dato;
            Form2 f=new Form2();
            f.setVisible(true);
            f.setRicezione(c);
            this.f.setVisible(false);
            
            //metodo statico che mette il destinatario in alto al form
            //inizia conversazione
        } else if (operazione.equals("n")) {
            azzera();
            invia.azzera();
            Ricezione.vecchio = null;
            Chat.getInstance().azzera();
            //imposto vecchio della ricezione a null
        } else {
            //arrivata un operazione nulla
        }
    }

    //invia mess da form
    public void InviaMessaggio(String m) throws IOException {
        this.invia.send("m;" + m);
        Chat.getInstance().AggiungiMessaggio(new Messaggio(dato,1));
    }

    //invia chiusura da form
    public void chiudicomunicazione() throws IOException {
        this.invia.send("c;;");
        azzera();
        invia.azzera();
        Chat.getInstance().azzera();
    }

    //invia comunicazione da form
    public void InviaRichiestaComunicazione(String indirizzo,Form1 f) throws IOException {
        this.f=f;
        invia.setIndirizzoDestinatario(InetAddress.getByName(indirizzo));
        this.invia.send("a;" + this.NomeMittente);
        
    }
    
}
