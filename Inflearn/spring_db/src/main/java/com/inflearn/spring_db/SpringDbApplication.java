package com.inflearn.spring_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDbApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDbApplication.class, args);
	}
}

// JDBC 이해
//	- JDBC 등장 이유
//		- 일반적으로 app이나 브라우저에서 직접 data access하지 않음. -> was를 거침.
//  	- 주로 was에서 db에 커넥션을 연결하고 sql을 보낸 후 결과를 응답 받음.
//		- 엄청 옛날에는 db마다 연결과 요청을 위한 스펙이 다 달랐음. -> db가 변경되면?? -> 모든 코드 바뀜.
//			- 그럼 각 db마다 따로 스펙을 학습해야함. -> 그래서 JDBC 등장.
//	- JDBC 표준 인터페이스 (Java Database Connectivity)
//		- 자바에서 DB에 접속할 수 있도록 하는 자바 API, JDBC는 DB에서 자료를 쿼리하거나 update하는 방법 제공.
//		- 대표적으로 연결(java.sql.Connection), SQL 담은 내용(java.sql.Statement), SQL 요청 응답(java.sql.ResultSet)
//		  을 표준 인터페이스로 정의해서 제공.
//		- 이제부터 이 표준 인터페이스만 따라서 개발하면 됨. -> 각 DB 벤더에서 자신의 DB에 맞도록 구현 후 라이브러리 제공 -> JDBC Driver
//	- 정리
//		- JDBC 등장으로 2가지 문제 해결
//			1. DB가 변경되는 경우 코드 모두 변경 -> JDBC 표준 인터페이스에만 의존해서 구현이 바뀌어도 상관 X
//			2. 개발자가 JDBC 표준 인터페이스만 공부하면 모든 구현체 사용가능.
//	< 참고 - 표준화의 한계 >
//		- DB마다 SQL 문법, 데이터 타입 등의 일부 사용법 상이. -> JDBC 코드는 변경 X but, SQL은 변경해야함.
//		- JPA는 각 DB 마다의 다른 SQL을 정의해야 하는 문제도 많은 부분 해결 가능.
//	- JDBC와 최신 데이터 접근 기술
//		- 최근에는 JDBC를 더 편리하게 다양한 기술들 존재.
//			- SQL Mapper (JDBC Template, MyBatis)
//				- 장점 : JDBC를 편리하게 사용하도록 도움.
//					- SQL 응답 결과를 객체로 편리하게 변환.
//					- JDBC의 반복 코드를 제거.
//				- 단점 : 개발자가 SQL을 직접 작성.
//			- ORM 기술 (JPA - 하이버네이트, 이클립스링크)
//				- ORM은 객체를 RDB 테이블과 매핑해주는 기술. 이 덕분에 개발자는 반복적인 SQL을 직접 작성 X
//				  ORM 기술이 대신 SQL 동적으로 만들어 실행, 각각의 DB마다 다른 SQL 사용하는 문제도 중간에서 해결.
//			- SQL Mapper vs ORM 기술
//				- 각각의 장단점 존재
//					- SQL Mapper : SQL만 직접 작성 -> 나머지 번거로운 일 SQL Mapper가 해결해줌. SQL만 작성할 줄 알면
//								  쉽게 사용할 수 있음.
//					- ORM 기술 : 개발 생산성 증가 -> but 쉬운 기술 X, 깊이있는 학습 필요.
//		- 중요한건 이런 기술들 전부 내부적으로 JDBC 사용 -> JDBC 직접 사용하지 않아도 어떻게 동작하는지 기본 원리 학습 필요.
//		- 그래야 해당 기술들을 더 깊이있게 이해 가능하고 무엇보다 문제 발생 시 근본적인 문제를 찾아 해결 가능.