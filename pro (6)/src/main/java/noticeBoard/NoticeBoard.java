package noticeBoard;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class NoticeBoard implements Serializable {
   private static final long serialVersionUID = 1449132512754742285L;
   private int number;
   private String title;
   private String content;
   private String writeId ; ;
   private Date writeDate;
   private int viewCount;
   
   public NoticeBoard() {
    }
    @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      NoticeBoard other = (NoticeBoard) obj;
      return Objects.equals(title, other.title);
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(title);
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
}
   

