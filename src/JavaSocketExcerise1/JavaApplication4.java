/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSocketExcerise1;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dino
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
  


    
    static String ip;
    static int port;   
    static ServerSocket serversocket;
    
    public void handleClient(Socket s)
    {
        try {
            PrintWriter pw;
            Scanner scan;
            Boolean stop = false;
            pw = new PrintWriter(s.getOutputStream(),true);
            scan = new Scanner(s.getInputStream());
//<editor-fold defaultstate="collapsed" desc="comment">
System.out.println("Waiting for data from client...");
pw.println("type #!# for commands...");
Boolean hit;
while(!stop)
{
    hit = false;
    System.out.println("before");
    String line = scan.nextLine();
    System.out.println("after");
    System.out.println(line);
    if(line.equals("#!#"))
    {
        hit = true;
        pw.println("#stop#   this stops the service.");
        pw.println("UPPER#   this returns the string in uppercase. ");
        pw.println("LOWER#   this returns the string in lowercase. ");
        pw.println("REVERSE#   this returns the string in reverse. ");
        pw.println("TRANSLATE#hund   this returns dog");
    }
    if(line.equals("#stop#"))
    {
        System.out.println("hi!");
        hit = true;
        stop = true;
    }
    if(line.startsWith("UPPER#"))
    {
        hit = true;
        String remove = line.replace("UPPER# ", "");
        pw.println(remove.toUpperCase());
    }
    if(line.startsWith("LOWER#"))
    {
        hit = true;
        String remove = line.replace("LOWER# ", "");
        pw.println(remove.toLowerCase());
    }
    if(line.startsWith("REVERSE#"))
    {
        hit = true;
        String remove = line.replace("REVERSE# ", "");
        pw.println(new  StringBuilder(remove).reverse().toString());
    }
    /*if(line.startsWith("TRANSLATE#"))
    {
    hit = true;
    String remove = line.replace("TRANSLATE# ", ""); // tried to use google API to translate for fun. (did not get it to work)...
    //  Translator translate = Translator.getInstance(); String text = translate.translate(remove, Language.ENGLISH, Language.DANISH); System.out.println(text);
    pw.println(remove);
    }*/
    if(line.equals("TRANSLATE#hund"))
    {
      pw.print("dog");
    }
    if(hit = false)
    {
        pw.println("server received message that does not fit the patterns given above and has stopped working...");
        stop = true;
    }
    
}
System.out.println("Stopped...");
//</editor-fold>
            
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     *
     * @throws IOException
     */
    public void startserver() throws IOException
    {
          
        serversocket = new java.net.ServerSocket();
        serversocket.bind(new java.net.InetSocketAddress(ip,port));
        while(true)
        {
            java.net.Socket socket = serversocket.accept();// Remember Blocking call
            handleClient(socket);
      /*      OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            pw.println(new Date().toString()); */
           // pw.flush();
        }
    }
    
       
       
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                port = Integer.parseInt(args[0]);
        ip = args[1];
        
    
        
        
        
 //  port = 8080;
 //  ip = "localhost";
 

        JavaApplication4 fs = new JavaApplication4();
        fs.startserver();
     // fs.startthreads();

    }
    
    public void startthreads()
    {
        new Thread(new FrameRunnable()).start();
    }
    


public class FrameRunnable implements Runnable
{
    public void run()
    {
              JavaApplication4 fs = new JavaApplication4();
        try {  
            fs.startserver();
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    


    
}
