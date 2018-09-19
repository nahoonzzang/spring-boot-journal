package apress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JournalEntry {

  private String title;
  private Date created;
  private String summary;

  private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

  public JournalEntry(String title, String summary, String date) throws
      ParseException {
    this.title = title;
    this.summary = summary;
    this.created = format.parse(date);
  }

  public void setCreated(String date) throws ParseException {
    Long _date = null;
    try {
      _date = Long.parseLong(date);
      this.created = new Date(_date);
      return;
    } catch(Exception ex) {
      this.created = format.parse(date);
    }
  }

  public String toString() {
    StringBuilder value = new StringBuilder("* JournalEntry(");
    value.append("제목: ");
    value.append(title);
    value.append(", 요약: ");
    value.append(summary);
    value.append(", 작성일자: ");
    value.append(format.format(created));
    value.append(")");
    return value.toString();
  }

}
