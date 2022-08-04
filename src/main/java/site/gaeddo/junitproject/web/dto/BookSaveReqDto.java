package site.gaeddo.junitproject.web.dto;

import lombok.Setter;
import site.gaeddo.junitproject.domain.Book;

@Setter
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
