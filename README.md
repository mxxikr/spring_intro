김영한님의 [스프링 입문 강의](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard) 실습 코드 아카이브  

---

### 💻 Development Environment
* java 17
* spring-boot 3.2.4
* gradle
* IDE: IntelliJ

### 🏆 실습 목표
- 스프링 프레임워크 기반의 웹 프로젝트 개발 흐름 전체 체험
- 프로젝트 생성부터 DB 적용 · AOP까지 웹 애플리케이션 개발 전 과정을 실습
- 정적 컨텐츠, MVC, API, DI, 테스트, DB 접근, AOP 등을 코드로 직접 구현

### 📝 Curriculum 

1. **강의 소개**

2. **프로젝트 환경설정**
   - 스프링 프로젝트 생성 및 구조 확인
   - 라이브러리 구성 살펴보기
   - 템플릿 엔진, 뷰 환경설정
   - 빌드 및 실행 실습

3. **스프링 웹 개발 기초**
   - 정적 컨텐츠 서비스 방식 실습
   - MVC 패턴/템플릿 엔진(Thymeleaf 등) 활용
   - API(Controller, @ResponseBody 등) 구현 및 실습

4. **회원 관리 예제 - 백엔드 개발**
   - 비즈니스 요구사항 문서화
   - 회원 도메인, 리포지토리 구현 (메모리 DB)
   - 리포지토리 단위 테스트(JUnit)
   - 회원 서비스 개발 및 테스트

5. **스프링 빈과 의존관계**
   - 컴포넌트 스캔을 통한 DI 자동화 (@Component, @Autowired)
   - 자바 코드 기반 스프링 빈 수동 등록(@Bean, @Configuration)

6. **회원 관리 예제 - 웹 MVC 개발**
   - 홈/회원 등록 · 조회 등 웹 화면 구현 (Controller, View 템플릿 연동)

7. **스프링 DB 접근 기술**
   - H2 DB 설치 및 연동
   - 순수 JDBC DB 접근 및 코드 구성
   - 스프링 통합 테스트(@SpringBootTest)
   - JdbcTemplate, JPA, 스프링 데이터 JPA 단계별 활용 비교 및 실습

8. **AOP**
   - AOP 필요한 상황
   - AOP 적용 실습
