package com.revy.freewheelin.endpoint;

import com.revy.freewheelin.endpoint.req.ScoreReq;
import com.revy.freewheelin.endpoint.res.common.CommonRes;
import com.revy.freewheelin.usecase.ScoreUseCaseV1;
import com.revy.freewheelin.usecase.dto.result.ResultScoreAverageStudentData;
import com.revy.freewheelin.usecase.dto.result.ResultScoreAverageSubjectData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScoreControllerV1 {

    private final ScoreUseCaseV1 scoreUseCaseV1;

    //특정 학생, 특정 과목에 점수 할당:
    // POST /students/{studentId}/subjects/{subjectId}/scores
    @PostMapping("/students/{studentId}/subjects/{subjectId}/scores")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonRes createScore(
            @PathVariable(name = "studentId") Long studentId,
            @PathVariable(name = "subjectId") Long subjectId,
            @RequestBody ScoreReq req
    ) {
        scoreUseCaseV1.createScore(studentId, subjectId, req.getScore());
        return new CommonRes<>();
    }

    //특정 학생, 특정 과목의 점수 수정:
    // PUT /students/{studentId}/subjects/{subjectId}/scores
    @PutMapping("/students/{studentId}/subjects/{subjectId}/scores")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonRes modifyScore(
            @PathVariable(name = "studentId") Long studentId,
            @PathVariable(name = "subjectId") Long subjectId,
            @RequestBody ScoreReq req) {
        scoreUseCaseV1.modifyScore(studentId, subjectId, req.getScore());
        return new CommonRes<>();
    }

    // 특정 학생, 특정 과목의 점수 삭제:
    // DELETE /students/{studentId}/subjects/{subjectId}/scores
    @DeleteMapping("/students/{studentId}/subjects/{subjectId}/scores")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScore(
            @PathVariable(name = "studentId") Long studentId,
            @PathVariable(name = "subjectId") Long subjectId) {
        scoreUseCaseV1.deleteScore(studentId, subjectId);

    }

    // 특정 학생의 평균 점수 조회: GET /students/{studentId}/average-score
    @GetMapping("/students/{studentId}/average-score")
    @ResponseStatus(HttpStatus.OK)
    public CommonRes<ResultScoreAverageStudentData> getAverageScoreForStudent(@PathVariable(name = "studentId") Long studentId) {
        ResultScoreAverageStudentData result = scoreUseCaseV1.getAverageScoreByStudentId(studentId);
        return new CommonRes<>(result);
    }

    // 특정 과목에 대한 전체 학생들의 평균 점수 조회:
    // GET /subjects/{subjectId}/average-score
    @GetMapping("/subjects/{subjectId}/average-score")
    @ResponseStatus(HttpStatus.OK)
    public CommonRes<ResultScoreAverageSubjectData> getAverageScoreForSubject(@PathVariable(name = "subjectId") Long subjectId) {
        ResultScoreAverageSubjectData result = scoreUseCaseV1.getAverageScoreBySubjectId(subjectId);
        return new CommonRes<>(result);
    }


}
