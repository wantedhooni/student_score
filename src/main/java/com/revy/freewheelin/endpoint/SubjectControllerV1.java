package com.revy.freewheelin.endpoint;

import com.revy.freewheelin.endpoint.req.CreateSubjectReq;
import com.revy.freewheelin.endpoint.res.SubjectListResData;
import com.revy.freewheelin.endpoint.res.common.CommonRes;
import com.revy.freewheelin.usecase.SubjectUseCaseV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectControllerV1 {

    private final SubjectUseCaseV1 subjectUseCaseV1;

    /**
     * 과목 조회: GET /subjects
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonRes<SubjectListResData> getSubjects() {
        return new CommonRes<>(
                new SubjectListResData(subjectUseCaseV1.searchSubjects())
        );
    }

    /**
     * 과목 추가: POST /subjects
     *
     * @param req
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonRes createSubjects(@RequestBody CreateSubjectReq req) {
        Assert.notNull(req, "CreateSubjectReq must be not null");
        subjectUseCaseV1.createSubject(req.getSubject());
        return new CommonRes<>();
    }

    /**
     * 과목 삭제: DELETE /subjects/{subjectId}
     *
     * @param subjectId
     */
    @DeleteMapping("/{subjectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("subjectId") Long subjectId) {
        Assert.notNull(subjectId, "subjectId must be not null");
        subjectUseCaseV1.deleteSubject(subjectId);

    }
}
