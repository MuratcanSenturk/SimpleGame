
package uzayoyunu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
         
    
    
    
}
public class Oyun extends JPanel implements KeyListener, ActionListener{
    Timer timer = new Timer(4, this);
    
    private BufferedImage image;
    private BufferedImage image2;
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    
    private int atesdirY = 1;
    private int topX = 0;
    private int topdirX = 2;
    private int uzayGemisiX = 0;
    private int dirUzayX = 20;
    
    public boolean kontrolEt(){
        
        for(Ates ates: atesler){
            if(new Rectangle(ates.getX(),ates.getY(),5,14).intersects(new Rectangle(topX,0,20,20))){
                return true;
            }
            
            
        }
        return false;
        
    }
    
    public Oyun(){
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("ben.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*try {
            image2 = ImageIO.read(new FileImageInputStream(new File("nah-png.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        setBackground(Color.black);
        
        timer.start();
        
        
        
    }

    @Override
    public void paint(Graphics g) { 
        super.paint(g); 
        
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(image, uzayGemisiX, 490, (int)(image.getWidth()/1.2), (int)(image.getHeight()/1.2), this);
        
        for(Ates ates: atesler){
            if(ates.getY() <= 0){
                atesler.remove(ates);
           }                       
        }
        
        g.setColor(Color.GREEN);
        
        for(Ates ates: atesler){
            g.fillRect(ates.getX(), ates.getY(), 5, 14);
        }
        
        if(kontrolEt()){
            timer.stop();
            String message = "YOU WIN!";
            
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); 
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        
        if(k == KeyEvent.VK_LEFT){
            if(uzayGemisiX <= 0 ){
                uzayGemisiX = 0;
            }else{
                uzayGemisiX -= dirUzayX;
            }
                                    
        }else if(k == KeyEvent.VK_RIGHT){
            if(uzayGemisiX >= 790){
                uzayGemisiX = 790;
            }else{
                uzayGemisiX += dirUzayX;
            }
        }
        
        if(k == KeyEvent.VK_SPACE){
            atesler.add(new Ates(uzayGemisiX + 15, 490));
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(Ates ates: atesler){
            
           ates.setY(ates.getY() - 2);
            
        }
        
        topX += topdirX;
        
        if(topX >= 790){
            topdirX = -topdirX;
        }if(topX <= 0){
            topdirX = -topdirX;
        }
        
        repaint();
    }
    
    
    
}
