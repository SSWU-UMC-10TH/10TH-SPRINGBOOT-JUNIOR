package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @EntityGraph(attributePaths = {"store"})
    @Query("""
            select m
            from Mission m
            where m.store.storeId = :storeId
            and m.deletedAt is null
            and m.isActive = true
            and (:cursorId is null or m.missionId < :cursorId)
            order by m.missionId desc
            """)
    Slice<Mission> findStoreMissions(
            @Param("storeId") Long storeId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"store"})
    @Query("""
            select m
            from Mission m
            where m.store.storeId = :storeId
            and m.missionId = :missionId
            and m.deletedAt is null
            and m.isActive = true
            """)
    java.util.Optional<Mission> findStoreMission(
            @Param("storeId") Long storeId,
            @Param("missionId") Long missionId
    );
}