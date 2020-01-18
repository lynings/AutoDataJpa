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
@Table(name = "course")
@org.hibernate.annotations.Table(appliesTo = "course", comment = "课程")
public class Course {

    @ManyToOne
    @JoinColumn(name = "educationId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Education education;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64, columnDefinition = "varchar(64) comment '名称'")
    private String name;
}
