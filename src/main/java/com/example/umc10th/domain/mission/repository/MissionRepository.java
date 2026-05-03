package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        JOIN FETCH s.region r
        WHERE r.id = :regionId
          AND (:cursor IS NULL OR m.id < :cursor)
          AND NOT EXISTS (
              SELECT mc.id
              FROM UserMission mc
              WHERE mc.mission = m
                AND mc.user.id = :userId
          )
        ORDER BY m.id DESC
    """)
    List<Mission> findHomeMissionsByRegionWithCursor(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
        SELECT COUNT(m)
        FROM Mission m
        JOIN m.store s
        JOIN s.region r
        WHERE r.id = :regionId
    """)
    Integer countTotalMissionsByRegion(@Param("regionId") Long regionId);

    @Query("""
        SELECT COUNT(mc)
        FROM UserMission mc
        JOIN mc.mission m
        JOIN m.store s
        JOIN s.region r
        WHERE mc.user.id = :userId
          AND r.id = :regionId
          AND mc.status = com.example.umc10th.domain.mission.enums.Status.COMPLETED
    """)
    Integer countCompletedMissionsByUserAndRegion(
            @Param("userId") Long userId,
            @Param("regionId") Long regionId
    );
}
