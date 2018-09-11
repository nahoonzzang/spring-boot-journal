package apress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity // 얘는 곧 테이블임
public class Journal {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String title;
  private Date created;
  private String summary;

  @Transient //  DB 테이블에 간섭하지 않고, 엔티티 클래스 내부에서만 동작하게 하는 어노테이션을 사용한다. @Transient 어노테이션을 사용한 필드나 메소드는 DB 테이블에 적용되지 않는다.
  private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

  public Journal(String title, String summary, String date) throws
      ParseException {
    this.title = title;
    this.summary = summary;
    this.created = format.parse(date);
  }

  public String getCreatedAsShort() {
    return format.format(created);
  }

  public String toString() {
    StringBuilder value = new StringBuilder("JournalEntry(");
    value.append("Id : ");
    value.append(id);
    value.append(", 제목: ");
    value.append(title);
    value.append(",요약: ");
    value.append(summary);
    value.append(",일자");
    value.append(getCreatedAsShort());
    value.append(")");
    return value.toString();
  }
}
