DROP SCHEMA IF EXISTS db CASCADE;

CREATE SCHEMA IF NOT EXISTS db AUTHORIZATION sa;

-- auto-generated definition
create table db.MEMBER
(
    MEMBER_ID   BIGINT auto_increment,
    NAME        VARCHAR2 not null,
    REGIST_DATE DATE not null,
    UPDATE_DATE DATE not null,
    primary key (MEMBER_ID)
);

comment on column MEMBER.MEMBER_ID is '아이디';
comment on column MEMBER.NAME is '이름';
comment on column MEMBER.REGIST_DATE is '등록일시';
comment on column MEMBER.UPDATE_DATE is '수정일시';



CREATE TABLE db.POINT
(
    seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '포인트 시퀀스',
    member_id BIGINT NOT NULL COMMENT '회원 아이디',
    action_type VARCHAR2 NOT NULL COMMENT '행위 유형',
    point DECIMAL(19, 2) NOT NULL COMMENT '포인트',
    remain_point DECIMAL(19, 2) NULL COMMENT '잔여 포인트',
    parent_seq BIGINT NULL COMMENT '사용된 포인트 시퀀스',
    regist_date DATE NOT NULL COMMENT '등록일시',
    update_date DATE NOT NULL COMMENT '수정일시',
    expire_date DATE NULL COMMENT '포인트 아이디',
    PRIMARY KEY (seq)
);