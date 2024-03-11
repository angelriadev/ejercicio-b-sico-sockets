package ejemploSockets;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
/*paquete para flujo de datos de tipo archivo*/
import java.io.IOException;
/*paquete para usar eventos*/

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
	
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
                
                enviaTexto mievento = new enviaTexto();
                /*se crea objeto "mievento"*/
                
                miboton.addActionListener(mievento);
                /*se añade la accion al boton enviar*/
		
		add(miboton);	
		
	}
	
	
	private class enviaTexto implements ActionListener{
        /*clase encargada de gestionar los eventos del boton*/
            
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(campo1.getText());
            /*mensaje de prueba para indicar que el boton funciona
            el getText obtiene el texto dentro de campo1 y lo escribe por consola*/
            
            
            try {
                Socket misocket = new Socket("192.168.1.33",9999);;
                /*se crea el socket, definiendo hacia donde ira conectado (dir IP)
                y que puerto usara para conectarse al destinatario*/
                
                DataOutputStream flujoSalida = new DataOutputStream(misocket.getOutputStream());
                /*se crea el flujo de datos de tipo datos (DataOutputStream)
                que sera el medio por el cual se envian los datos dentro del socket*/
                
                flujoSalida.writeUTF(campo1.getText());
                /*"flujo de salida" es la instancia de DataOutputStream, le decimos
                que escriba un archivo de tipo texto , obtenido (getText) del cuadro de texto "campo1"*/
                
                flujoSalida.close();
                /*cerramos el flujo de datos*/
                
            } catch (IOException ex) {
                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        }
        }
		
		
		
	private JTextField campo1;
        /*cuadro de texto*/
	
	private JButton miboton;
        /*boton enviar*/
	
}