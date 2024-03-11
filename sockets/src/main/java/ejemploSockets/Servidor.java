package ejemploSockets;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.io.IOException;
import java.net.*;
/*paquete para usar socket de servidor*/
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
                
                Thread mihilo = new Thread (this);
                /*se crea el hilo*/
                
                mihilo.start();
                /*iniciar hilo*/
		
		}
	
	private	JTextArea areatexto;

    @Override
    public void run() {
        System.out.println("estoy a la escucha");
        /*impresion de prueba para saber que la interfaz Runnable funciona*/
            try {
                
                ServerSocket servidor =new ServerSocket(9999);
                /*abre la conexion a traves del puerto especificado*/
                
                while(true){
         
                Socket misocket = servidor.accept();
                /*aceptar todas las conexiones del exterior con el metodo "accept"*/
               
                
               DataInputStream  flujoEntrada = new DataInputStream(misocket.getInputStream());
                    /* - lee lo que viene dentro del flujo de datos
                    - hay un flujo de datos que usara como medio de transporte el socket "misocket.getInputStream" 
                    que afecta todas las conexiones*/
                
                
                    String mensajeTexto= flujoEntrada.readUTF();
                    /*tenemos alamacenada en la variable "mensajeTexto"
                    lo que viaja por el flujo de datos que viene del cliente*/
                
                    areatexto.append("\n"+ mensajeTexto);
                    /*escribe lo que esta almacenado en "mensajeTexto"
                    cada que enviamos un mensaje , lo escriba en una linea diferente*/
             
                    
                    misocket.close();
                }
                    
            } catch (IOException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
