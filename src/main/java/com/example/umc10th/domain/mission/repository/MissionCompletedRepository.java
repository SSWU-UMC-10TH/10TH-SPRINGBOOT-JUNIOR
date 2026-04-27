package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MissionCompleted;
import com.example.umc10th.domain.mission.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface MissionCompletedRepository extends JpaRepository<MissionCompleted, Long> {

    @Query("""
        SELECT mc
        FROM MissionCompleted mc
        JOIN FETCH mc.mission m
        JOIN FETCH m.store s
        WHERE mc.user.id = :userId
          AND mc.status = :status
          AND (:cursor IS NULL OR mc.id < :cursor)
        ORDER BY mc.id DESC
    """)
    List<MissionCompleted> findMyMissionsByStatusWithCursor(
            @Param("userId") Long userId,
            @Param("status") Status status,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
