package pers.lyning.autodatajpa.education.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import pers.lyning.autodatajpa.core.JpaCriteriaHelper;
import pers.lyning.autodatajpa.core.page.PageResponse;
import pers.lyning.autodatajpa.education.domain.Education;
import pers.lyning.autodatajpa.education.domain.EducationRepository;
import pers.lyning.autodatajpa.education.query.EducationQuery;
import pers.lyning.autodatajpa.education.vo.EducationVO;

/**
 * @author lyning
 */
@Service
@Transactional(readOnly = true, rollbackFor = TransactionException.class)
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public PageResponse<EducationVO> queryByParams(EducationQuery query) {
        Specification<Education> specification = JpaCriteriaHelper.asSpecification(query);
        Pageable pageable = JpaCriteriaHelper.asPageable(query);
        Page<EducationVO> page = this.educationRepository.findAll(specification, pageable)
                .map(education -> EducationVO.builder()
                        .intro(education.getIntro())
                        .logo(education.getLogo().getUrl())
                        .name(education.getName())
                        .type(education.getType())
                        .build());
        return PageResponse.of(page);
    }
}
