package apress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //  엔티티를 디비와 연결 @Transient 붙은 필드 제외
@Getter
@Setter
@NoArgsConstructor
public class Journal {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO) // PK
  private Long id;
  private String title;
  private java.util.Date created;
  private String summary;

  @Transient
  private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

  public Journal(String title, String summary, String date) {
    this.title = title;
    this.summary = summary;
    try {
      this.created = format.parse(date);
    } catch (ParseException pe) {
      pe.printStackTrace();
    }
  }

  public String getCreatedAsShort() {
    return format.format(created);
  }

  public String toString() {
    StringBuilder value = new StringBuilder("Journal(");
    value.append("Id : ");
    value.append(id);
    value.append(", 제목: ");
    value.append(title);
    value.append(",요약: ");
    value.append(summary);
    value.append(",작성일자");
    value.append(getCreatedAsShort());
    value.append(")");
    return value.toString();
  }
}
