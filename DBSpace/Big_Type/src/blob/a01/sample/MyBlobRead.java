//How to read an image from database table? or Write an example for reading BLOB from table.
//
//Description:
//BLOB is nothing bug Binary Large Object. BLOB is used to store and retrieve large amount of binary data from database like images, etc. 
//Below example shows how to read images from database rows.

package blob.a01.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MyBlobRead {
 
    public static void main(String a[]){
         
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>" ,"user","password");
            st = con.createStatement();
            rs = st.executeQuery("select student_img from student_profile where id=101");
            if(rs.next()){
                is = rs.getBinaryStream(1);
            }
            is = new FileInputStream(new File("Student_img.jpg"));
            os = new FileOutputStream("std_img.jpg");
            byte[] content = new byte[1024];
            int size = 0;
            while((size = is.read(content)) != -1){
                os.write(content, 0, size);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(is != null) is.close();
                if(os != null) os.close();
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}