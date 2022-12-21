package qnaBoard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaBoardRepositoryDB {
   Connection conn = null;
   PreparedStatement pstmt = null;

   //마리아DB 연결
   public void open() {
      try {
         Context context = new InitialContext();
         Context envContext = (Context) context.lookup("java:/comp/env");
         DataSource dataSource = (DataSource) envContext.lookup("jdbc/proDB");
         if (dataSource != null) {
            conn = dataSource.getConnection();
         } else {           
         }
         System.out.println("연결됨");         
      } catch (Exception e) {
         e.printStackTrace();
      }      
   }
   
   //마리아DB 해제
   private void close() {
      try {
         if (pstmt != null) {
            pstmt.close();
         }
         if (conn != null) {
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
   }
} 
  