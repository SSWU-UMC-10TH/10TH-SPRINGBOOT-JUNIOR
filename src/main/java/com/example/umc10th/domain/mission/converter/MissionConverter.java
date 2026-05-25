package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import org.springframework.data.domain.Slice;

import java.util.List;

public class MissionConverter {

    // 식당에서 진행하는 미션 1개 조회
    public static MissionResDTO.GetStoreMission toGetStoreMission(Mission mission) {
        return new MissionResDTO.GetStoreMission(
                mission.getMissionId(),
                mission.getStore().getStoreId(),
                mission.getStore().getStoreName(),
                mission.getMission(),
                mission.getPoint(),
                null,
                mission.getIsActive(),
                mission.getCreatedAt()
        );
    }

    // 식당에서 진행하는 미션 목록 조회(cursor)
    public static MissionResDTO.GetStoreMissions toGetStoreMissions(Slice<Mission> missionSlice) {

        List<MissionResDTO.GetStoreMission> missionList = missionSlice.stream()
                .map(MissionConverter::toGetStoreMission)
                .toList();

        Long nextCursorId = null;

        if (missionSlice.hasNext() && !missionSlice.getContent().isEmpty()) {

            Mission lastMission = missionSlice.getContent()
                    .get(missionSlice.getNumberOfElements() - 1);

            nextCursorId = lastMission.getMissionId();
        }

        return new MissionResDTO.GetStoreMissions(
                missionList,
                missionList.size(),
                missionSlice.hasNext(),
                nextCursorId
        );
    }

    // 사용자가 진행 중인 미션 1개 조회
    public static MissionResDTO.GetMyMission toGetMyMission(
            MissionChoice missionChoice
    ) {

        return new MissionResDTO.GetMyMission(
                missionChoice.getMissionChoiceId(),
                missionChoice.getMission().getMissionId(),
                missionChoice.getUser().getUserId(),
                missionChoice.getMission().getStore().getStoreName(),
                missionChoice.getMission().getMission(),
                missionChoice.getMission().getPoint(),
                null,
                missionChoice.getSuccess(),
                missionChoice.getStartedAt(),
                missionChoice.getSuccessAt()
        );
    }

    // 사용자가 진행 중인 미션 목록 조회(cursor)
    public static MissionResDTO.GetMyMissions toGetMyMissions(
            Slice<MissionChoice> missionChoiceSlice
    ) {

        List<MissionResDTO.GetMyMission> missionList =
                missionChoiceSlice.stream()
                        .map(MissionConverter::toGetMyMission)
                        .toList();

        Long nextCursorId = null;

        if (missionChoiceSlice.hasNext()
                && !missionChoiceSlice.getContent().isEmpty()) {

            MissionChoice lastMissionChoice =
                    missionChoiceSlice.getContent()
                            .get(missionChoiceSlice.getNumberOfElements() - 1);

            nextCursorId = lastMissionChoice.getMissionChoiceId();
        }

        return new MissionResDTO.GetMyMissions(
                missionList,
                missionList.size(),
                missionChoiceSlice.hasNext(),
                nextCursorId
        );
    }

    // 미션 도전 요청 응답
    public static MissionResDTO.ChallengeMission toChallengeMission(
            MissionChoice missionChoice
    ) {

        return new MissionResDTO.ChallengeMission(
                missionChoice.getMissionChoiceId(),
                missionChoice.getMission().getMissionId(),
                missionChoice.getUser().getUserId(),
                missionChoice.getSuccess(),
                missionChoice.getStartedAt()
        );
    }
}