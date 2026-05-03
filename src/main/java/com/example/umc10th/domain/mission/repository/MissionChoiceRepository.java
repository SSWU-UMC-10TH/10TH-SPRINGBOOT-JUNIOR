package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionChoiceRepository extends JpaRepository<MissionChoice, Long> {

    Boolean existsByUserUserIdAndMissionMissionId(Long userId, Long missionId);

    @EntityGraph(attributePaths = {"mission", "mission.store"})
    Page<MissionChoice> findByUserUserIdAndSuccessFalseOrderByStartedAtDesc(
            Long userId,
            Pageable pageable
    );
}