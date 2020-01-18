package pers.lyning.autodatajpa.education.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * logo
 *
 * @author lyning
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Logo {

    @Column(nullable = false, length = 128, columnDefinition = "varchar(128) comment 'logo url'")
    private String url;
}
