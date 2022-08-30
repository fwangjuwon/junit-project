package site.gaeddo.junitproject.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.gaeddo.junitproject.domain.Book;

@NoArgsConstructor
@Getter
public class BookRespDto {
    private Long id;
    private String title;
    private String author;

    @Builder
    public BookRespDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // dto로 변환하는 메소드

}
