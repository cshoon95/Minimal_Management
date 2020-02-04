package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // 스프링과 테스트 통합
@SpringBootTest // 스프링부트 띄우고 테스트 (이게 없다면 @Autowired 다 실패)
/*
    Transactional // 반복 가능한 테스트 지원, 각각의 테스트를 실행할 때 마다 트랜잭션을 시작하고
    테스트가 끝나면 트랜잭션을 강제로 롤백(이 어노테이션이 테스트 케이스에서 사용될 때만 롤백)
 */
@Transactional
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given -- 주어지고
        Member member = new Member();
        member.setName("soohoon");

        //when -- 이렇게 하면
        Long saveId = memberService.join(member);

        //then -- 검증
        assertEquals(member, memberRepository.findOne(saveId)); // assertEquals(a, b) == 객체 a와b의 값이 같은지 확인
    }

    @Test(expected = IllegalAccessException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("soohoon");

        Member member2 = new Member();
        member2.setName("soohoon");
        //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야함 // 이름이 같으니까.
        //then
        fail("예외가 발생해야 한다.");
    }
}