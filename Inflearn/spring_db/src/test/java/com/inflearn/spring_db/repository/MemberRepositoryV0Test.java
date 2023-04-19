package com.inflearn.spring_db.repository;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.inflearn.spring_db.domain.Member;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberRepositoryV0Test {
	private static final String MEMBER_ID = "memberTest";
	private static final Member MEMBER = new Member(MEMBER_ID, 10000);
	MemberRepositoryV0 repo = new MemberRepositoryV0();

	@Test
	@Order(1)
	void create() throws SQLException {
		repo.save(MEMBER);
	}

	@Test
	@Order(2)
	void read() {
		Member findMember = repo.findById(MEMBER_ID);
		assertThat(findMember).isNotNull();
		assertThat(findMember).isEqualTo(MEMBER);
	}

	@Test
	@Order(3)
	void update() throws SQLException {
		// given
		int updateMoney = 20000;

		// when
		repo.update(MEMBER_ID, updateMoney);
		Member findMember = repo.findById(MEMBER_ID);

		// then
		assertThat(findMember).isNotNull();
		assertThat(findMember.getMoney()).isEqualTo(updateMoney);
	}

	@Test
	@Order(4)
	void delete() throws SQLException {
		repo.delete(MEMBER_ID);
		assertThatThrownBy(() -> repo.findById(MEMBER_ID))
			.isInstanceOf(NoSuchElementException.class);
	}
}

// 테스트에서 반복해서 테스트할 수 있는 환경을 만드는 것이 중요함.
// 이 테스트는 좋지 않은 테스트임. -> 이 테스트는 Order에 해당하는 모든 테스트가 돌아가야함. -> 예외가 터져서 delete가 동작하지 않는다면??
// 자동으로 동작되는 테스트가 되지 않음. -> 저장은 했는데 삭제가 안되기 때문. -> 트랜잭션을 활용해서 이 문제를 깔끔하게 해결가능 -> 뒤에서 알아보자.