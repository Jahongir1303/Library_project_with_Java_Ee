package com.company.library.domains;

import com.company.library.enums.BookStatus;
import com.company.library.enums.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String author;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus = BookStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private Language language;

    private Integer pageCount;


    @Builder.Default
    private Integer downloadCount = 0;

    @OneToOne(fetch = FetchType.EAGER)
    private Uploads cover;

    @OneToOne(fetch = FetchType.EAGER)
    private Uploads file;

    @AllArgsConstructor
    @Getter
    public enum Genre {
        CRIME("Crime"),
        HORROR("Horror"),
        CI_FI("Ci-fi"),
        DRAMA("Drama"),
        ROMANCE("Romance"),
        ROMANCE_DRAMA("Romance Drama"),
        TUTORIAL("Tutorial"),
        FANTASY("Fantasy");
        private final String key;

        public String getKey() {
            return key;
        }
    }

}
