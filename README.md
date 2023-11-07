# 구동 방법

````
./gradlew clean && ./gradlew bootRun 
````

---
# 관련 툴 정보
- swagger-ui 주소 : http://localhost:8080/swagger-ui/index.html
- H2 db 주소 : http://localhost:8080/h2-console

---
# 전제 조건
- 툴로 테스트 한다고 하여 전제조건이 없는것들은 구현하지 않는다.(조회시 -> Reuqest의 추가 조건 -> 조회조건, 페이징, 정렬 조건)
- 삭제는 논리삭제 기준으로 구현한다.(개인적으로는 논리 삭제가 맞다고 생각한다.)

---
# ModelMapper, Mapstruct 미사용 이유
- 코드는 깔끔해 보이나 사용하다 보면 End-Point의 Request / Response의 필드이름에 종속되어서 Vo, DTO, ENTITY의 이름이 따라가게 됩니다.
- Request / Response는 client와의 공유 스팩이지, 비지니스로직에서 그대로 따라가지 않아도 된다고 생각합니다.
- 리플랙션 기반이라 컴파일에서 잡지 못하는 실수들이 많이 발생합니다.
- - 수동으로 한번 해놓고 나면 컴파일 / IDE의 도움으로 영향도 조사 용이, 리팩토링시 도움이 많이 된다.

---
# 패키지 흐름
(비지니스 로직) Endpoint <- (비지니스 로직) usecase <- (Domain) Serivce <- (Domain) Repository <- (Domain) Entity
역의존 하지 않는다.

---
# ERD

![ERD.png](img%2FERD.png)

---
# DDL
````
# student TABLE
CREATE TABLE `student` (
  `age` int(11) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `school_type` enum('ELEMENTARY','HIGH','MIDDLE') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i3xrfnuv2icsd1vhvn6c108ec` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# subject TABLE
CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p1jgir6qcpmqnxt4a8105wsot` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

# SCORE TABLE
CREATE TABLE `score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,  
  `student_id` bigint(20) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_SUBJECT_STUDENT` (`subject_id`,`student_id`),
  KEY `FKnap51mbove93yjb09idc9jic6` (`student_id`),
  CONSTRAINT `FK56nv285e8l73fru4sw2152y87` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKnap51mbove93yjb09idc9jic6` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



````