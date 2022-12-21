package member.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Member implements Serializable {
   private static final long serialVersionUID = 1449132512754742285L;
   private String uid;
   private String pwd;
   private String name;
   private String sex;
   private String address;
   private String phone;
   private String email;
   private String admin;
   private Date createDate;
   
   
   public Member() {
    }
    @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Member other = (Member) obj;
      return Objects.equals(uid, other.uid);
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(uid);
   }

   public static class ExistMember extends Exception {
      public ExistMember(String reason) {
         super(reason);
      }
   }
   
   public static class NotExistMember extends Exception {
      public NotExistMember(String reason) {
         super(reason);
      }
   }
   
   public static class NotExistUidPwd extends Exception {
      public NotExistUidPwd(String reason) {
         super(reason);
      }
   }

//    public boolean login(String uid2, String pwd2) {
//        return uid2.equals(uid) && pwd2.equals(pwd);
//    }
}
