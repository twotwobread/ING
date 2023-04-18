package com.inflearn.spring_db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inflearn.spring_db.connection.DBConnectionUtil;
import com.inflearn.spring_db.domain.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

	public Member save(Member member) throws SQLException {
		String sql = "insert into member(member_id, money) values(?, ?)";
		try (
			Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql)
			){
			preparedStatement.setString(1, member.getMemberId());
			preparedStatement.setInt(2, member.getMoney());
			preparedStatement.executeUpdate();
			return member;
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		}

	}

	private static Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}
}

// SQL injection 공격을 예방하기 위해선 PreparedStatement를 통한 파라미터 바인딩 방식 이용 -> 기존의 방식으로 직접 값을 넣는다면 값 대신 다른 SQL을 삽압하여 데이터를 털 수 있음.
// 이걸 막기 위해서 PreparedStatement에서는 들어오는 값을 SQL로 취급하지 않게 그냥 문자열 값으로 인식하게 구현하여 이를 해결했음.