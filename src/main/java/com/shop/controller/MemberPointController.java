package com.shop.controller;

import com.shop.model.request.PointRequest;
import com.shop.model.response.PointResponse;
import com.shop.model.response.RemainPointResponse;
import com.shop.service.MemberService;
import com.shop.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberPointController {
    private final PointService pointService;
    private final MemberService memberService;

    /**
     * 회원 체크
     *
     * @param memberId
     * JPA 사용으로 Entity 단위로 진행되기에
     * 회원 테이블을 직접 조회해서 유효한지 체크하는 서비스
     */
    private void isMember(Long memberId) {
        if (!memberService.existsById(memberId)) throw new IllegalStateException("포인트 서비스를 사용할 수 없는 사용자 입니다.");
    }

    /**
     * 회원별 포인트 합계 조회
     *
     * @param memberId
     * @return
     * 회원 별 적립금 합계는 마이너스가 될 수 없음
     */
    @GetMapping(value = "/{memberId}/point", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RemainPointResponse> getPoint(@PathVariable final Long memberId) {
        this.isMember(memberId);
        return ResponseEntity.ok(pointService.getRemainPoint(memberId));
    }

    /**
     * 회원별 포인트 적립/사용 내역 조회
     *
     * @param memberId
     * @param pageable
     * @return
     * 페이징 처리 필수, 사용 취소된 내역은 조회되지 않음
     * 취소 이력이 필요하면 살려둘텐데 요청과제에는 의미가 없으니 삭제로 처리
     */
    @GetMapping(value = "/{memberId}/points", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PointResponse>> getPoints(@PathVariable final Long memberId,
                                                         @PageableDefault(size = 20, sort = "seq", direction = Sort.Direction.ASC) Pageable pageable) {
        this.isMember(memberId);
        return ResponseEntity.ok(pointService.getPoints(memberId, pageable));
    }

    /**
     * 회원별 포인트 적립
     *
     * @param memberId
     * @param pointRequest
     * 적립된 포인트의 사용기간 구현 (1년)
     */
    @PostMapping(value = "/{memberId}/points/earn", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PointResponse> earnPoint(@PathVariable final Long memberId,
                                                   @RequestBody @Validated final PointRequest pointRequest) {
        this.isMember(memberId);
        return ResponseEntity.ok(pointService.earnPoint(memberId, pointRequest.getPoint()));
    }

    /**
     * 회원별 포인트 사용
     *
     * @param memberId
     * @param pointRequest
     * 적립된 포인트의 사용기간 구현 (1년)
     */
    @PostMapping(value = "/{memberId}/points/use", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PointResponse>> usePoint(@PathVariable final Long memberId,
                                                        @RequestBody @Validated final PointRequest pointRequest) {
        this.isMember(memberId);
        return ResponseEntity.ok(pointService.usePoint(memberId, pointRequest.getPoint()));
    }

    /**
     * 회원별 포인트 사용취소 API 개발
     *
     * @param memberId
     * @param usedPointSequence
     * 포인트 사용 api 호출하는 쪽에서 rollback 처리를 위한 용도
     */
    @DeleteMapping(value = "/{memberId}/point/{usedPointSequence}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deletePoint(@PathVariable final Long memberId,
                                         @PathVariable final Long usedPointSequence) {
        this.isMember(memberId);
        pointService.deletePoint(usedPointSequence);

        return ResponseEntity.ok().build();
    }

}
