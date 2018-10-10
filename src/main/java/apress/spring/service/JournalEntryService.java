package apress.spring.service;

import apress.spring.domain.JournalEntry;
import apress.spring.repository.JournalEntryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryService {
  private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);

  @Autowired
  JournalEntryRepository journalEntryRepository;

  public List<JournalEntry> findAll() {
    return journalEntryRepository.findAll();
  }
}
