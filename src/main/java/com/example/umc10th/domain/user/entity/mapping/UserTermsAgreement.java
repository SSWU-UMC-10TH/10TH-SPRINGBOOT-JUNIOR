package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_terms_agreement")
public class UserTermsAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자는 약관에 대해 무조건 1개 이상의 값은 선택해야 됨 -> optional=false
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    // 약관에 대한 선택 여부 값이 무조건 1개 이상 있어야 됨 -> optional=false
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id")
    private Term term;

    // 약관 동의 여부, 기본 값 = false
    @Column(name = "is_agreed", nullable = false)
    @Builder.Default
    private Boolean isAgreed = false;
}
