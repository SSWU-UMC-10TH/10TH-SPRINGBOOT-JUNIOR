package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MissionChoiceRepository extends JpaRepository<MissionChoice, Long> {

    Boolean existsByUserUserIdAndMissionMissionId(Long userId, Long missionId);

    @EntityGraph(attributePaths = {"mission", "mission.store", "user"})
    @Query("""
            select mc
            from MissionChoice mc
            where mc.user.userId = :userId
            and mc.success = false
            and (:cursorId is null or mc.missionChoiceId < :cursorId)
            order by mc.missionChoiceId desc
            """)
    Slice<MissionChoice> findMyMissions(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"mission", "mission.store", "user"})
    @Query("""
            select mc
            from MissionChoice mc
            where mc.user.userId = :userId
            and mc.missionChoiceId = :missionChoiceId
            and mc.success = false
            """)
    Optional<MissionChoice> findMyMission(
            @Param("userId") Long userId,
            @Param("missionChoiceId") Long missionChoiceId
    );
}