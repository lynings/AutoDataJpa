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
@Table(name = "teacher")
@org.hibernate.annotations.Table(appliesTo = "teacher", comment = "教师")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8, columnDefinition = "varchar(8) comment '姓名'")
    private String name;
}
