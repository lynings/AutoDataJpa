package pers.lyning.autodatajpa.education.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author lyning
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
@org.hibernate.annotations.Table(appliesTo = "news", comment = "新闻公告")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


}
