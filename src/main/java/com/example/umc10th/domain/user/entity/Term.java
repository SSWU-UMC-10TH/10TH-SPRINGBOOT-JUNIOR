package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.UserTermsAgreement;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // 필수 동의 여부, 기본 값 = 필수
    @Column(name = "is_required", nullable = false)
    @Builder.Default
    private Boolean isRequired = true;

    // 연관 관계
    @OneToMany(mappedBy = "term")
    private List<UserTermsAgreement> termAgreementList = new ArrayList<>();
}
