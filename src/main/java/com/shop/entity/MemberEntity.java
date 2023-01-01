package com.shop.entity;

import com.shop.model.request.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@ToString
@Entity
@Table(schema = "db", name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "regist_date")
    private LocalDateTime registDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    public static MemberEntity convertToEntity(MemberRequest memberRequest) {
        return MemberEntity.builder()
				.name(memberRequest.getName())
                .registDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }

    public static MemberEntity convertToEntity(Long memberId, MemberRequest memberRequest) {
        return MemberEntity.builder()
                .memberId(memberId)
                .name(memberRequest.getName())
                .registDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }


}
