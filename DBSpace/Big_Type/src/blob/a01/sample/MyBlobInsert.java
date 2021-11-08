package blob.a01.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
//How to insert an image into database table? or Write an example for inserting BLOB into table.

//BLOB is nothing bug Binary Large Object. 
//BLOB is used to store large amount of binary data into database like images, etc. 
//Below example shows how to store images into database rows.

import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class MyBlobInsert {
 
    public static void main(String a[]){
         
        Connection con = null;
        PreparedStatement ps = null;
        InputStream is = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>" ,"user","password");
            ps = con.prepareCall("insert into student_profile values (?,?)");
            ps.setInt(1, 101);
            is = new FileInputStream(new File("Student_img.jpg"));
            ps.setBinaryStream(2, is);
            int count = ps.executeUpdate();
            System.out.println("Count: "+count);
        } catch (ClassNotFoundException e) {
          
        } catch (SQLException e) {
            
        } catch (FileNotFoundException e) {
          
        } finally{
            try{
                if(is != null) is.close();
                if(ps != null) ps.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
