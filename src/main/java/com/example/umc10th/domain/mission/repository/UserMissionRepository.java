package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.UserMissionQueryDTO;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
    SELECT new com.example.umc10th.domain.mission.dto.UserMissionQueryDTO(
        mc.id,
        s.name,
        m.conditionAmount,
        m.rewardPoint,
        mc.status,
        m.endDate
    )
    FROM UserMission mc
    JOIN mc.mission m
    JOIN m.store s
    WHERE mc.user.id = :userId
      AND mc.status = :status
      AND (:cursor IS NULL OR mc.id < :cursor)
    ORDER BY mc.id DESC
""")
    List<UserMissionQueryDTO> findMyMissionsByStatusWithCursor(
            @Param("userId") Long userId,
            @Param("status") Status status,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
    SELECT new com.example.umc10th.domain.mission.dto.UserMissionQueryDTO(
        um.id,
        s.name,
        m.conditionAmount,
        m.rewardPoint,
        um.status,
        m.endDate
    )
    FROM UserMission um
    JOIN um.mission m
    JOIN m.store s
    WHERE um.user.id = :userId
      AND um.status = :status
""")
    Page<UserMissionQueryDTO> findAllByUserIdAndStatusWithPaging(
            @Param("userId") Long userId,
            @Param("status") Status status,
            Pageable pageable
    );
}
