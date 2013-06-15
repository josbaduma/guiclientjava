/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

//import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class Client {

    private String _ip;
    private int _port;
    private Socket _socket;
    private PrintWriter _message;
    private BufferedReader _inMessage;

    public Client(String pIP, int pPort) {
        this._ip = pIP;
        this._port = pPort;
    }

    public void initClient() {
        try {
            this._socket = new Socket(this._ip, this._port);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void sendMessage(String pMessage) {
        try {
            this._message = new PrintWriter(new OutputStreamWriter(
                    this._socket.getOutputStream()), true);
            
            this._message.println(pMessage);
            this._message.flush();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String recibeAnswer()
    {
        String answer = "";
        try {
            this._inMessage = new BufferedReader(new InputStreamReader(this._socket.getInputStream()));
            answer = this._inMessage.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answer;
    }
    public void closeClient() {
        try {
            this._socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
