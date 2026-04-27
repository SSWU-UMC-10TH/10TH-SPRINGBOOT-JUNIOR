package com.example.umc10th.domain.mission.entity.mapping;


import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission_choice")
public class MissionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionChoiceId;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    private LocalDateTime successAt;

    @Column(nullable = false)
    private Boolean success;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
