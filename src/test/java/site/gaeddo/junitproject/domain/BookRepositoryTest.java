package site.gaeddo.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest // db와 관련된 컴포넌트만 메모리에 등록된다.
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void 데이터준비() {

        String title = "junit";
        String author = "getinthere";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        bookRepository.save(book);
    }

    // 1. 책 등록이 잘 되는지 테스트
    @Test
    public void 책등록_test() {
        // given(데이터 준비)
        String title = "junit5";
        String author = "metacoding";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        // when(테스트 실행)
        Book bookPS = bookRepository.save(book);

        // then(검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    } // transaction이 종료 돼서 저장된 데이터를 초기화시켜 버린다.

    // 2. 책 목록보기 테스트
    @Test
    public void 책목록보기_test() {
        // given
        String title = "junit";
        String author = "getinthere";

        // when
        List<Book> booksPS = bookRepository.findAll();

        System.out.println("사이즈 --------------------------------" + booksPS.size());
        // then
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());
    }

    // 3. 책 한 건 보기 테스트
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책한건보기_test() {
        // given
        String title = "junit";
        String author = "getinthere";

        // when
        Book bookPS = bookRepository.findById(1L).get();

        // then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 4. 책 삭제 테스트
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test() {
        // given
        Long id = 1L;

        // when
        bookRepository.deleteById(id);

        // then
        assertFalse(bookRepository.findById(id).isPresent());

    }

    // 5. 책 수정 테스트
    // 1, junit, getinthere가 저장돼있는 상태
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책수정_test() {
        // given
        Long id = 1L;
        String title = "junit5";
        String author = "metacoding";
        Book book = new Book(id, title, author);

        // when
        Book bookPS = bookRepository.save(book);
        // bookRepository.findAll().stream()
        // .forEach((b) -> {
        // System.out.println(b.getTitle());
        // System.out.println(b.getId());
        // System.out.println(b.getAuthor());

        // });
        // System.out.println(bookPS.getTitle());
        // System.out.println(bookPS.getId());
        // System.out.println(bookPS.getAuthor());

        // then
        assertEquals(id, bookPS.getId());
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }
}
