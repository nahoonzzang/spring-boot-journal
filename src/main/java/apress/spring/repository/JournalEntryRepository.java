package apress.spring.repository;

import apress.spring.domain.JournalEntry;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Transactional // REST호출에 트랜잭션을 걸어 API 동시 호출 시 데이터 정합성에 문제가 없도록 보호
@RepositoryRestResource(collectionResourceRel = "entry", path = "journal") // 경로는 journal로 바꾸고 컬렉션 리소스는 복수형 대신 entry로 한다.
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> { // @Entity를 붙인 클래스와 Serializable 객체인 ID를 받음

  List<JournalEntry> findByCreatedAfter(
      @Param("after") @DateTimeFormat(iso= ISO.DATE) Date date);
  List<JournalEntry> findByCreatedBetween(
      @Param("after") @DateTimeFormat(iso = ISO.DATE) Date after,
      @Param("before") @DateTimeFormat(iso = ISO.DATE) Date before);
  List<JournalEntry> findByTitleContaining(
      @Param("word") String word);
  List<JournalEntry> findBySummaryContaining(
      @Param("word") String word);
}
