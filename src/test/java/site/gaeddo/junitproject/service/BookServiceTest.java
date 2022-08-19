package site.gaeddo.junitproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import site.gaeddo.junitproject.domain.BookRepository;
import site.gaeddo.junitproject.util.MailSenderStub;
import site.gaeddo.junitproject.web.dto.BookRespDto;
import site.gaeddo.junitproject.web.dto.BookSaveReqDto;

@DataJpaTest
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit");
        dto.setAuthor("getinthere");

        // stub
        MailSenderStub mailSenderStub = new MailSenderStub();

        // 가짜로 bookrepository만들기

        // when
        BookService bookService = new BookService(bookRepository, mailSenderStub);
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        assertEquals(dto.getTitle(), bookRespDto.getTitle());
        assertEquals(dto.getAuthor(), bookRespDto.getAuthor());
    }
}
