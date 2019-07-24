/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiy;

import javax.swing.JTable;

/**
 *
 * @author hussein
 */
public interface homeData {
    
    public void add();
    
    public void update();
    
    public void delete();
    
    public String getAutoNumber();
    
    public void getAllRow(JTable table); //show data which in database in table which on screen
    
    public void getOneRow(JTable table);
    
    public void getCustomRow(String statement,JTable table);
    
    public String getValueByName(String name);
    
    public String getNameByValue(String value);
    
}
