/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author hussein
 */
public class tools {
    
    public static void showMS(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static void openForm(JFrame frm){
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);
        frm.setVisible(true);
    }
    
    public static boolean confirmMsg(String message){
       int msgC = JOptionPane.showConfirmDialog(null, message);
       if(msgC == JOptionPane.YES_OPTION){
           return true;
       } else {
           return false;
       }
    }
    
    public static void CreateFolder(String folderName){
        File f = new File(folderName);
        f.mkdir();
    }
    
    
  
    
    
    public static void clearText(Container form){
        for(Component c : form.getComponents()){
            if(c instanceof JTextField){
                JTextField txt = (JTextField)c;
                txt.setText("");
            } else if(c instanceof Container){
              clearText((Container)c);  
            }
        }
        
    } // end Clear Text
    
    public static void creatEmptyFile(String fileName){
        try {
            File f = new File(fileName);
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // end Create EmptyFile
    public static void creatFile(String fileName, Object data[]){
        try {
            PrintWriter p = new PrintWriter(fileName);
            for(Object obj : data){
                p.println(obj);
            }
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // end Creat File 
    
    public static Object InputBox(String title){
        Object myObj = JOptionPane.showInputDialog(title);
        return myObj;
    } // end Input Box


        public static String getNumber(String text){
        String val = "";
        for(char c : text.toCharArray()){
         if (c == '0' || c == '1' ||  c == '2' || c == '3' || c == '4' || c == '5'
                    || c == '6' || c == '7' || c == '8' || c == '9'){
             val += c;
         }
         
        } 
        return val;
} // end getnumber
        
 public static int getNumberToIntger(String text){
        String val = "";
        for(char c : text.toString().toCharArray()){
         if (c == '0' || c == '1' ||  c == '2' || c == '3' || c == '4' || c == '5'
                    || c == '6' || c == '7' || c == '8' || c == '9'){
             val += c;
         }
         
        } 
        return Integer.parseInt(val);
} // end getnumber
    
    public static void printScreen(String Imagename){
        try {
            Robot r = new Robot();
            Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage img = r.createScreenCapture(rec);
            ImageIO.write(img, "jpg", new File(Imagename + ".jpg"));
        } catch (Exception ex) {
            Logger.getLogger(tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // end print screen
    
    public class Table{
        public int columns;
        public Object[][] Items;
        public Table(int columns){
            this.columns = columns;
            Items = new Object[0][columns];
        }
        public void addNewRow(Object row[]){
            Object TempItems[][] = Items;
            Items = new Object[Items.length +1][columns];
            for(int x = 0; x < TempItems.length; x++){
                Items[x] = TempItems[x];
            }
            Items[Items.length -1] = row;
        }
    } //end table
    
    public static void setReport(JTable table){
          table.getTableHeader().setBackground(new Color(0, 0, 100));
        table.getTableHeader().setForeground(Color.white);
    }
    
    public static void printReport(JTable table, String title){
             try{
          MessageFormat header = new MessageFormat(title +" Report");
          MessageFormat footer = new MessageFormat("Page - (0)");
          table.print(JTable.PrintMode.FIT_WIDTH,header, footer);
      }catch(PrinterException ex){
          tools.showMS(ex.getMessage());
      }
    }
    
  
    
}
