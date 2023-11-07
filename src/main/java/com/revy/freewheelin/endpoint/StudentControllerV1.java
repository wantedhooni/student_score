package com.revy.freewheelin.endpoint;

import com.revy.freewheelin.endpoint.req.CreateStudentReq;
import com.revy.freewheelin.endpoint.res.StudentListResData;
import com.revy.freewheelin.endpoint.res.common.CommonRes;
import com.revy.freewheelin.usecase.StudentUseCaseV1;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentControllerV1 {

    private final StudentUseCaseV1 studentUseCaseV1;

    /**
     * 학생 조회: GET /students
     * 페이징, 검색조건에 대한 요구사항은 없으므로 생략한다.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CommonRes<StudentListResData> getStudent() {
        return new CommonRes<>(
                new StudentListResData(studentUseCaseV1.searchStudents())
        );
    }

    /**
     * 학생 추가: POST /students
     * Created 201: 성공
     * BadRequest 400: phoneNumber 가 이미 존재하는 경우
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonRes createStudent(@RequestBody @Valid CreateStudentReq req) {
        Assert.notNull(req, "CreateStudentReq must be not null");
        studentUseCaseV1.createStudent(req.getStudent());
        return new CommonRes<>();
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        Assert.notNull(studentId, "studentId must be not null");
        studentUseCaseV1.deleteStudent(studentId);
    }


}
