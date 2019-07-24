
package entitiy;

import company1.tools;
import dp.connection;
//import db.connection;
import javax.swing.JTable;

/**
 *
 * @author hussein
 */
public class Department implements homeData{
    connection methodsConnection=new connection();
    private int deptNum;
    private String deptName;
    private String deptLocation;

    public int getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(int deptNum) {
        this.deptNum = deptNum;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLocation() {
        return deptLocation;
    }

    public void setDeptLocation(String deptLocation) {
        this.deptLocation = deptLocation;
    }

    @Override
    public void add() {
    String insert="insert into department values("+deptNum +",'"+deptName+"','"+deptLocation+"')";
    boolean isCheck= methodsConnection.runNonQuary(insert);
    if(isCheck){
        tools.showMS("the department is done");
    }
    }

    @Override
    public void update() {
    String update="update  department set deptName='"+deptName+"',deptLocation='"+deptLocation+"' where deptNum="+deptNum;
    boolean isCheck= methodsConnection.runNonQuary(update);
    if(isCheck){
        tools.showMS(" the department is updated");
    }
    }
    @Override
    public void delete() {
     String delete="delete from department  where deptNum = "+deptNum;
    boolean isCheck= methodsConnection.runNonQuary(delete);
    if(isCheck){
        tools.showMS(" the department number "+ deptNum +" is deleted");
    }}

    @Override
    public String getAutoNumber() {
     return methodsConnection.getAutoNum("department", "deptNum");
    }

    @Override //show all data which in database in table which on screen 
    public void getAllRow(JTable table) { 
       methodsConnection.fillTable("department", table);
    }

    @Override
    public void getOneRow(JTable table) {
      String str="select * from department where deptName = "+deptName;
      methodsConnection.fillTable(str, table);
    }


    @Override
    public void getCustomRow(String statement, JTable table) { // file table with constrant with me
     methodsConnection.fillTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
     String str="select deptNum from department where deptName = '"+name+"'";
     String value=(String) methodsConnection.getDataAsTable(str).Items[0][0];
      return value;
    }

    @Override
    public String getNameByValue(String value) {
      String str="select deptName from department where deptNum = "+value;
      String name=(String) methodsConnection.getDataAsTable(str).Items[0][0];
      return name;
    }
}
