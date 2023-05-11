package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateAllowanceOfRankDto {
    @NotNull
    private Long amount;

    @NotNull
    private Long minInterval;

    @NotNull
    private Long maxInterval;

    @NotNull
    private Long rankId;

    public AllowanceOfRank toEntity(Rank rank) {
        return AllowanceOfRank.builder()
                .amount(amount)
                .amount(minInterval)
                .amount(maxInterval)
                .rank(rank)
                .build();
    }
}
