package pers.lyning.autodatajpa.education.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyning
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education")
@org.hibernate.annotations.Table(appliesTo = "education", comment = "教育机构/名企名校")
public class Education extends AbstractEntity {

    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "education")
    private List<Course> courses = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1024, columnDefinition = "varchar(1024) comment '简介'")
    private String intro;

    @Embedded
    private Logo logo;

    @Column(nullable = false, length = 64, columnDefinition = "varchar(64) comment '名称'")
    private String name;

    @OneToOne
    private News news;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16, columnDefinition = "varchar(16) comment '教育机构类型(COLLEGE：学院；ENTERPRISE：企业)'")
    private Type type;

    public enum Type {
        /**
         * 学院
         */
        COLLEGE,
        /**
         * 企业
         */
        ENTERPRISE
    }
}
