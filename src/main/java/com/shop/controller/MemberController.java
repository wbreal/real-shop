package com.shop.controller;

import com.shop.entity.MemberEntity;
import com.shop.model.request.MemberRequest;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/{memberId}")
    public ResponseEntity<MemberEntity> find(@PathVariable @NotNull @Positive final Long memberId) {
        return ResponseEntity.ok(memberService.findByMemberId(memberId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberEntity> save(@RequestBody @Validated final MemberRequest memberRequest) {
        return ResponseEntity.ok(memberService.save(MemberEntity.convertToEntity(memberRequest)));
    }

    @PutMapping(value = "/{memberId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberEntity> modify(@PathVariable @NotNull @Positive final Long memberId,
                                               @RequestBody @Validated final MemberRequest memberRequest) {
        return ResponseEntity.ok(memberService.save(MemberEntity.convertToEntity(memberId, memberRequest)));
    }

}
