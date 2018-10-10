package apress.spring.domain;

import apress.spring.utils.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity //  엔티티를 디비와 연결 @Transient 붙은 필드 제외
@Table(name="entry") // entry라는 테이블 생성
public class JournalEntry {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY) // PK
  private Long id;
  private String title;
  private String summary;
  private java.util.Date created;

  @Transient
  private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

  public JournalEntry(String title, String summary, Date date) {
    this.title = title;
    this.summary = summary;
    this.created = date;
  }

  @JsonSerialize(using=JsonDateSerializer.class)
  public Date getCreated() {
    return created;
  }

  @JsonIgnore
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
