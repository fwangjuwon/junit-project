package site.gaeddo.junitproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import site.gaeddo.junitproject.domain.Book;
import site.gaeddo.junitproject.domain.BookRepository;
import site.gaeddo.junitproject.util.MailSender;
import site.gaeddo.junitproject.web.dto.BookRespDto;
import site.gaeddo.junitproject.web.dto.BookSaveReqDto;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit");
        dto.setAuthor("getinthere");

        // stub
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);
        // when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    public void 책목록보기_테스트() {

        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "메타코딩"));
        books.add(new Book(2L, "spring강의", "겟인데어"));

        books.stream().forEach((b) -> {
            System.out.println(b.getId());
            System.out.println(b.getTitle());
            System.out.println("------------------");
        });

        // stub
        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<BookRespDto> bookRespDtoList = bookService.책목록보기();

        // print
        // 본코드에 문제가 있나???
        // bookRespDtoList.stream().forEach((dto) -> {
        // System.out.println("테스트-------------------");
        // System.out.println(dto.getId());
        // System.out.println(dto.getTitle());
        // });

        // then
        assertThat(bookRespDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookRespDtoList.get(1).getTitle()).isEqualTo("spring강의");
        assertThat(bookRespDtoList.get(0).getAuthor()).isEqualTo("메타코딩");
        assertThat(bookRespDtoList.get(1).getAuthor()).isEqualTo("겟인데어");

    }

    @Test
    public void 책한건보기_test() {

        // given
        Long id = 1L;
        Book book = new Book(1L, "junit강의", "겟인데어");
        Optional<Book> bookOp = Optional.of(book);

        // stub
        when(bookRepository.findById(1L)).thenReturn(bookOp);

        // when
        BookRespDto bookRespDto = bookService.책한건보기(id);

        // then
        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
    }
}
