package com.inflearn.spring_db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

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

	public Member findById(String memberId) {
		String sql = "select * from member where member_id = ?";

		try (
			Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			) {
			preparedStatement.setString(1, memberId);
			return convertMember(preparedStatement.executeQuery());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(String memberId, int money) throws SQLException{
		String sql = "update member set money=? where member_id=?";

		try(
			Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			) {
			preparedStatement.setInt(1, money);
			preparedStatement.setString(2, memberId);
			int resultSize = preparedStatement.executeUpdate();
			log.info("resultSize={}", resultSize);
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		}
	}

	public void delete(String memberId) throws SQLException {
		String sql = "delete from member where member_id=?";

		try(
			Connection conn = getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
		) {
			preparedStatement.setString(1, memberId);
			int resultSize = preparedStatement.executeUpdate();
			log.info("resultSize={}", resultSize);
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		}
	}

	private Member convertMember(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Member member = new Member();
			member.setMemberId(rs.getString("member_id"));
			member.setMoney(rs.getInt("money"));
			return member;
		}
		throw new NoSuchElementException("member not found member");
	}

	private Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}
}

// SQL injection 공격을 예방하기 위해선 PreparedStatement를 통한 파라미터 바인딩 방식 이용 -> 기존의 방식으로 직접 값을 넣는다면 값 대신 다른 SQL을 삽압하여 데이터를 털 수 있음.
// 이걸 막기 위해서 PreparedStatement에서는 들어오는 값을 SQL로 취급하지 않게 그냥 문자열 값으로 인식하게 구현하여 이를 해결했음.

// ResultSet은 키-값 형태로 생긴 테이블형 자료구조임.
// 최초의 커서는 데이터를 가르키지 않고 있기에 next()를 이용해서 다음 데이터로 커서를 옮겨서 값이 존재하는지를 확인하고 있으면 column명을 이용해서 값을 찾아옴.
// iterator처럼 쓰면됨. -> 데이터가 없으면 false 반환함. -> while 형시;ㄱ으로 구현해도 됨. -> findById는 무조건 1개만 반환하니까 if로 했음.