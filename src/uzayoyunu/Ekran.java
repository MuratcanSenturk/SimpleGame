
package uzayoyunu;

import java.awt.HeadlessException;
import javax.swing.JFrame;


public class Ekran extends JFrame{
    
     public Ekran(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        
        Ekran ekran = new Ekran("Uzay Oyunu");
        
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(800, 600);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        ekran.setVisible(true);
        
        
    }

   
    
    
    
}
