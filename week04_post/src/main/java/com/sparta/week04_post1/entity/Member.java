package com.sparta.week04_post1.entity;

//최소한의 정보만을 갖고 있는 Member Entity 입니다.

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    //권한은 Enum 클래스로 만들었습니다.
    public enum Authority {
        ROLE_USER, ROLE_ADMIN
    }
}