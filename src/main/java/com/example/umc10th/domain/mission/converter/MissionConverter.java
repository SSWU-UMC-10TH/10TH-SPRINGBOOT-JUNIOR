package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MissionChoice;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionPreview toMissionPreview(MissionChoice missionChoice) {
        return MissionResDTO.MissionPreview.builder()
                .missionChoiceId(missionChoice.getMissionChoiceId())
                .missionId(missionChoice.getMission().getMissionId())
                .storeName(missionChoice.getMission().getStore().getStoreName())
                .title(missionChoice.getMission().getTitle())
                .point(missionChoice.getMission().getPoint())
                .targetAmount(missionChoice.getMission().getTargetAmount())
                .success(missionChoice.getSuccess())
                .startedAt(missionChoice.getStartedAt())
                .successAt(missionChoice.getSuccessAt())
                .build();
    }

    public static MissionResDTO.MissionPreviewList toMissionPreviewList(Page<MissionChoice> missionChoicePage) {

        List<MissionResDTO.MissionPreview> missionList = missionChoicePage.stream()
                .map(MissionConverter::toMissionPreview)
                .toList();

        return MissionResDTO.MissionPreviewList.builder()
                .missionList(missionList)
                .listSize(missionList.size())

                .currentPage(missionChoicePage.getNumber())
                .pageSize(missionChoicePage.getSize())

                .totalPage(missionChoicePage.getTotalPages())
                .totalElements(missionChoicePage.getTotalElements())
                .isFirst(missionChoicePage.isFirst())
                .isLast(missionChoicePage.isLast())
                .build();
    }

    public static MissionResDTO.ChallengeMission toChallengeMission(MissionChoice missionChoice) {
        return MissionResDTO.ChallengeMission.builder()
                .missionChoiceId(missionChoice.getMissionChoiceId())
                .missionId(missionChoice.getMission().getMissionId())
                .userId(missionChoice.getUser().getUserId())
                .success(missionChoice.getSuccess())
                .build();
    }
}