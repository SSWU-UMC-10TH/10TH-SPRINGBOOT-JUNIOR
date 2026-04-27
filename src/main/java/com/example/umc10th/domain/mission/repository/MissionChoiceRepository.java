package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionChoiceRepository extends JpaRepository<com.example.umc10th.domain.mission.entity.mapping.MissionChoice, Long> {

    Boolean existsByUserUserIdAndMissionMissionId(Long userId, Long missionId);

    @Query("""
            select mc
            from MissionChoice mc
            join fetch mc.mission m
            join fetch m.store s
            where mc.user.userId = :userId
            order by mc.startedAt desc
            """)
    Page<MissionChoice> findMyMissions(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
