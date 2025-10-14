package com.biblioteca.book_service.infraestructure.persistance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_libro")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "library_id", nullable = false)
    private Long libraryId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", titulo='" + title + '\'' +
                ", isbn='" + isbn +
                '}';
    }
}
