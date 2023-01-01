package com.shop.service;


import com.shop.entity.MemberEntity;
import com.shop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;

@Service
@AllArgsConstructor
@Validated
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberEntity findByMemberId(final Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public MemberEntity save(final MemberEntity memberEntity) {
        return memberRepository.save(memberEntity);
    }

    public Collection<MemberEntity> getMembers(final Pageable pageable) {
//		return memberRepository.findAllBy(pageable);
        return null;
    }


    public boolean existsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }

}
