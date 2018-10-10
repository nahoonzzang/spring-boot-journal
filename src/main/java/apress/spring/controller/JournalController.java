package apress.spring.controller;

import apress.spring.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController // DispatcherServlet이 갖고 가야할 컨트롤러
public class JournalController {

  private static final String VIEW_INDEX = "index";

  @Autowired
  JournalEntryRepository journalEntryRepository;

  // 메서드 파라미터 modelViewAndView는 요청 시점에 인스턴스화 한다.
  // journalEntryRepository.findAll 메서드로 조회한 전체 데이터를 modelAndView 인스턴스 모델(Journal)에 넣고
  // 렌더링할 뷰를 index.html로 세팅. 그런 다음 modelAndView 인스턴스 반환
  @RequestMapping(value="/", method= RequestMethod.GET)
  public ModelAndView index(ModelAndView modelAndView) {
    modelAndView.setViewName(VIEW_INDEX);
    modelAndView.addObject("journal", journalEntryRepository.findAll());
    return modelAndView;
  }
}
