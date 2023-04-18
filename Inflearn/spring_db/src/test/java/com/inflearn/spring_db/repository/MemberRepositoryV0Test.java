package com.inflearn.spring_db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.inflearn.spring_db.domain.Member;

class MemberRepositoryV0Test {

	MemberRepositoryV0 repo = new MemberRepositoryV0();

	@Test
	void crud() throws SQLException {
		Member member = new Member("memberV1", 10000);
		repo.save(member);
	}

}