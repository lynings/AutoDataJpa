package pers.lyning.autodatajpa.education.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lyning.autodatajpa.core.page.PageResponse;
import pers.lyning.autodatajpa.education.query.EducationQuery;
import pers.lyning.autodatajpa.education.service.EducationService;
import pers.lyning.autodatajpa.education.vo.EducationVO;

/**
 * @author lyning
 */
@RestController
@RequestMapping("/educations")
public class EducationResource {

    private final EducationService educationService;

    public EducationResource(EducationService educationService) {this.educationService = educationService;}

    @GetMapping
    public PageResponse<EducationVO> queryByParams(EducationQuery query) {
        return educationService.queryByParams(query);
    }
}
