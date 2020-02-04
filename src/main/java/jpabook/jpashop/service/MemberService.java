package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // readOnly = true로 설정하면 조회할 때 더욱 더 최적화 시켜줌. // 읽기가 아닌 쓰기에서 true를 사용하면 데이터 변경이 안되므로 주의
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;
    /*
        회원 가입
     */
    @Transactional // data값을 바꾸거나 할 때는 트랜잭션을 써야함 . javax가 제공하는 거 말고 spring이 제공하는 Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단 건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
