package pers.lyning.autodatajpa.education.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lyning
 */
@Getter
@Setter
@FieldNameConstants
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

    @CreatedDate
    @Column(columnDefinition = "datetime default now() comment '记录创建时间'")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime comment '记录更新时间'")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime modifiedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
