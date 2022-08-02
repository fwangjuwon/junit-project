package site.gaeddo.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // db와 관련된 컴포넌트만 메모리에 등록된다.
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // 1. 책 등록이 잘 되는지 테스트
    @Test
    public void 책등록_test() {
        System.out.println("책등록 테스트 ");
    }

    // 2. 책 목록보기 테스트

    // 3. 책 한 건 보기 테스트

    // 4. 책 수정 및 삭제 테스트
}
