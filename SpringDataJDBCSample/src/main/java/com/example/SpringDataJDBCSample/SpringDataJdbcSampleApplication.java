package com.example.SpringDataJDBCSample;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJdbcSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcSampleApplication.class, args).getBean(SpringDataJdbcSampleApplication.class).execute();
	}

	@Autowired
	MemberCrudRepository repository;

	private void execute(){
		//등록
		executeInsert();
		//전체 취득
		executeSelect();
	}
	// 등록
	private void executeInsert(){
		//엔티티 생성(id는 자동 부여되기에 null 설정
		Member member = new Member(null, "이순신");
		// 리포지토리를 이용해 등록하고 결과 취득
		member = repository.save(member);
		// 결과 표시
		System.out.println("등록 데이터: "+ member);
	}
	// 전체 취득
	private void executeSelect(){
		System.out.println("--- 전체 데이터를 취득합니다 ---");
		//리포지토리를 이용해 전체 데이터를 취득
		Iterable<Member> members = repository.findAll();
		for(Member member: members){
			System.out.println(member);
		}
	}
}
