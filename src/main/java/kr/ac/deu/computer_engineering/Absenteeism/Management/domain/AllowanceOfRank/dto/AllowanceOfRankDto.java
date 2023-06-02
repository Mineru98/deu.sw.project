package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AllowanceOfRankDto {
    @NotNull(message = "금액을 입력해주세요.")
    private Long amount;

    @NotNull(message = "최소구간을 입력해주세요.")
    private Long minInterval;

    @NotNull(message = "최대구간을 입력해주세요.")
    private Long maxInterval;

    @NotNull(message = "직급을 선택해주세요.")
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
