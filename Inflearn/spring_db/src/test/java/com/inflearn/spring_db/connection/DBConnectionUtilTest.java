package com.inflearn.spring_db.connection;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;


import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DBConnectionUtilTest {

	@Test
	void connection() {
		Connection connection = DBConnectionUtil.getConnection();
		assertThat(connection).isNotNull();
	}
}
// 실행결과를 보면  class=class org.h2.jdbc.JdbcConnection 요 부분을 확인할 수 있음.
// 이게 H2 DB 드라이버가 제공하는 H2 전용 커넥션임 -> 이건 표준 커넥션 인터페이스를 구현하고 있음 (java.sql.Connection), 그니까 Connection 인터페이스로 추상화 가능.
// 세부적으로 보면 H2 DB 드라이버가 JdbcConnection 구현체를 제공하는데 DriverManager는 라이브러리에 등록된 드라이버들을 관리하고 커넥션을 획득하는 기능을 제공하여서 DriverManager를 통해서 커넥션을 제공받을 수 있음.
// 이때 URL, 유저네임, 패스워드를 넘기는데 각 드라이버들이 URL을 보고 자기가 처리할 수 있는지를 체크함. -> H2 드라이버면 url에 h2가 붙어있는지 보고 아니면 처리못한다는 결과를 반환하면서 다음 드라이버에게 넘기고 처리할 수 있으면 커넥션을 반환.
