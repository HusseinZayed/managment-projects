
package dp;

import company1.tools;
import company1.tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class connection {
    
    private String url="jdbc:mysql://localhost:3306/company?useUnicode=true&characterEncoding=UTF-8";
    private   Connection con;
    
    
       
    
    
    private   void setconnection(){
        try {
            con=(Connection) DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            tools.showMS(ex.getMessage());
        }
    }
    
    public  boolean isLogin(String user,String password){
        
          setconnection();
        try {
            Statement st=con.createStatement();
            String check="select * from admin where username ='"+user+"' and password = '"+password+"'";
            st.executeQuery(check);
            while(st.getResultSet().next()){
                con.close();
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            tools.showMS(ex.getMessage());
        }
       
       return false;
    }
    
    
    public boolean runNonQuary(String statement ){
          
            try {
                setconnection();
                Statement st = con.createStatement();
                st.execute(statement);
                con.close();
                return true;
            } catch (SQLException ex) {
                tools.showMS(ex.getMessage());}
                
                                       
            try{
            con.close();
        } catch (SQLException ex) {}
                                       
            return false;
                                           }
    
    // stop at last row then add 1 then return
    public String getAutoNum(String nameTable,String nameColumn){
         String num="";
        try {
            setconnection();
            Statement st = con.createStatement();
            String sqlState="select max("+nameColumn+") as autoNum from "+nameTable;
            st.executeQuery(sqlState);
            
            while(st.getResultSet().next()){
                num=st.getResultSet().getString("autoNum");
                if(num==null||"".equals(num)){
                    num="1";
                     con.close();
                     return num;
                                             }
                else{
                  int x=Integer.parseInt(num);
                  x++;
                  num=String.valueOf(x);
                  con.close();
                  return num;
                    }
            }
        } catch (SQLException ex) {
           tools.showMS(ex.getMessage());
           
           
        }
        try{
        con.close();
       }
        
        catch(SQLException ex){}
        return num; 
    }
    
    
    public  Table getDataAsTable(String statement){
        
       
            
            try {
                setconnection();
                Statement st = con.createStatement();
                ResultSet rs=st.executeQuery(statement);   //save done sql statement in rs
                ResultSetMetaData rsmd =rs.getMetaData();  // it have four method to use as table as(getcolumncount,getTableName,....)
                int contColumn=rsmd.getColumnCount();
                Table table=new tools().new Table(contColumn);  //because Table nested class
                while(rs.next()){
                    Object []row=new Object[contColumn];
                    for(int i=0;i<contColumn;i++){
                        row[i]=rs.getString(i+1);
                    }
                    table.addNewRow(row);
                }
                con.close();
                return table;
                
            } catch (SQLException ex) {
                
                tools.showMS(ex.getMessage());
            }
            try{
            con.close();
        } catch (SQLException ex) {
          
            tools.showMS(ex.getMessage());
        }
            return new tools().new Table(0);
    }
    
    public void fillCombo(String tableName,String columnName,JComboBox combobox){
        
        try {
            setconnection();
            Statement st=con.createStatement();
            String statement="select "+columnName+" from "+tableName;
            ResultSet rs=st.executeQuery(statement);
            //pointer is refer to before index with 1
            //will make it refrer to last row to get count row then return pointer to first
            rs.last();
            int contRow=rs.getRow();
            rs.beforeFirst();
            String values[]= new String[contRow];
            int i=0;
            
            while(rs.next()){
                values[i]=rs.getString(1);  //getstring work with datebase as coulmn
                i++;
                
            }
            con.close();
            combobox.setModel(new DefaultComboBoxModel(values));  //setmodel to send values to combobox
                                                                  // DefualtComboboxModel to values take shape combobox
        } catch (SQLException ex) {
            tools.showMS(ex.getMessage());
        }
        try{
        con.close();}
        catch(SQLException ex){
        }
    } 
    
    
    //get on command to get database in complete table  or row from table and show in anther table
    public void fillTable(String statORnameTable,JTable table){
        
        try {
            setconnection();
            Statement st =con.createStatement();
            ResultSet rs;
            if("select ".equals(statORnameTable.substring(0, 7).toLowerCase()))
                rs=st.executeQuery(statORnameTable);
            else
                rs=st.executeQuery("select * from "+statORnameTable);
            ResultSetMetaData rsmd =rs.getMetaData();
            int contColumn = rsmd.getColumnCount();
            
            DefaultTableModel model =(DefaultTableModel) table.getModel();  //make mode take shape table and which take model store in table
            model.setRowCount(0);  // make table empty
            
            if(model.getColumnCount()!=contColumn)
                tools.showMS("numbers column in tabel not equal column in statement quary");
            
            Vector row ;
            while(rs.next()){
                row=new Vector(contColumn);
                for(int i=1;i<=contColumn;i++){
                   row.add(rs.getString(i));
                }
                model.addRow(row);
                
            }
            con.close();
        } catch (SQLException ex) {
            tools.showMS(ex.getMessage());
        }
        
    }
    
}














