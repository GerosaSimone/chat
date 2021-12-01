/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author gerosa_simone
 */
public class Messaggio {

    public static final int NOT_SET = -1;
    public static final int ARRIVATO = 0;
    public static final int INVIATO = 1;

    int Type;
    String message;

    public Messaggio() {
        Type = Messaggio.NOT_SET;
        message = "";
    }

    public Messaggio(String message, int Type) {
       this.message=message;
       this.Type=Type;
    }

}
