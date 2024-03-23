## 목차
- [프로젝트 정보](#-레거시-프로젝트2)
- [변경 사항](#-변경-사항)
- [주요 관심사](#-주요-관심사)

# 🚀 레거시 프로젝트2
[이 전 프로젝트 보기](https://github.com/junheiLee/mini_project_03_team8)
## 🔧 사용 기술 및 개발 환경
&nbsp; Eclipse, Java 11, Spring framework, Oracle, MyBatis, JSP
## 🔍 소개
&nbsp; 부트캠프 네번째 미니 프로젝트로 쇼핑몰 사이트를 고도화하는 과제입니다.<br>
### 기간
&nbsp; 3일: 23. 8.29(화) ~ 23. 8.31(목)
### 인원
&nbsp; 김태섭, 이준희, 임승연, 정성현

# 🪄 변경 사항

## [🔗이 전 코드](https://github.com/junheiLee/mini_project_03_team8)

## Spring framework 적용

### 메서드 분리
- 변경 전: 경로 끝에 행위를 담고, 하나의 메서드에서 if문으로 행위에 대한 service 분배
	- 하나의 메서드가 하는 일이 많음
- 변경 후: @RequestMapping을 사용해 행위당 메서드 분리

### 비즈니스 로직 서비스로 이동
- 변경 전: Controller에서 필요한 data 직접 DTO에 바인딩
- 변경 후: @ModelAttribute를 사용해 Controller는 DTO 내부 관여 X

### @Transcactional 애노테이션
- 변경 전: DAO에서 setAutoCommit() 호출 후, 한 메서드가 다양한 쿼리를 실행
	- 메서드의 책임이 불 분명하고, 트랜잭션에 필요한 코드가 반복
- 변경 후: service 계층에 @Transactinal을 사용
	- DAO에서 하나의 메서드가 하나의 기능을 담당

### IoC 도입
- 변경 전: 각 클래스가 필요한 service 혹은 dao를 직접 주입
- 변경 후: @Autowired를 이용해 어노테이션으로 의존 관계 주입

## 페이징 클래스 분리
- 변경 전: service 클래스에서 page 로직을 포함
- 변경 후: SRP 지킬 수 있음

## Mybatis 도입
- 변경 전: JDBC를 직접 사용해 Database에 저장해 반복 코드 다수 발생 
- 변경 후: SQL문을 xml에 작성해 코드가 간결

## aop 사용
log 사용 코드가 모든 bean에서 공통으로 사용
```java
package com.team8.shopping.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvisor {

   private static Logger log = LoggerFactory.getLogger(LogAdvisor.class);

   @Before("* com.team8.shopping.controller..*(..)")
   public void controller(JoinPoint joinPoint) {
      log.info("controller={}", joinPoint.getSignature());
   }

   @Before("* com.team8.shopping.service..*(..)")
   public void service(JoinPoint joinPoint) {
      log.info("service={}", joinPoint.getSignature());
   }

}
```

# 😍 주요 관심사
- 함께 성장하기
- 객체지향 이해 및 적용

## 🤩느낀 점
- Maven으로 설정하기가 생각보다 복잡했습니다.
- Spring을 도입해 어노테이션만으로 코드가 간소화 되는 것이 신기했습니다.
