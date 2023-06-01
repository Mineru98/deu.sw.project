package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RankDto {
    @NotNull
    private String rankName;

    public Rank toEntity() {
        return Rank.builder()
                .rankName(rankName)
                .build();
    }
}
