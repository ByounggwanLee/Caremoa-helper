package com.caremoa.helper.domain.dto;

import com.caremoa.helper.domain.model.HelperOffer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelperOfferDto {
	@Schema(description = "고객ID", nullable = false, defaultValue = "1")
	private Long id;

	@Schema(description = "도움미ID", nullable = true, defaultValue = "1")
	private Long helperId;

	@Schema(description = "도움미이름", nullable = true, defaultValue = "이병관")
	private String helperName;

	@Schema(description = "도움미이름", nullable = true, defaultValue = "이병관")
	private String helperPhoneNumber;

	@Schema(description = "도우미업무", nullable = true, defaultValue = "아기돌봄")
	private String helperJobType;

	@Schema(description = "근무지역", nullable = true, defaultValue = "서울, 강서, 염창")
	private String location;

	@Schema(description = "월급", nullable = true, defaultValue = "2000000")
	private Long expense;

	@Schema(description = "회원ID", nullable = true, defaultValue = "0")
	private Long memberId;
	
	@Schema(description = "회원이름", nullable = true, defaultValue = "이병관")
	private String memberName;
	
	@Schema(description = "회원연락처", nullable = true, defaultValue = "010-2345-5432")
	private String memberPhoneNumber;

	@Schema(description = "돌봄대상자", nullable = true, defaultValue = "홍길동")
	private String targetName;

	@Schema(description = "상태", nullable = true, defaultValue = "구직")
	private String status;

	public HelperOffer toModel() {
		return HelperOffer.builder().id(id).helperId(helperId).helperName(helperName)
				.helperPhoneNumber(helperPhoneNumber).helperJobType(helperJobType).location(location).expense(expense)
				.memberId(memberId).memberName(memberName).memberPhoneNumber(memberPhoneNumber).targetName(targetName).status(status)
				.build();
	}

	public static HelperOfferDto toDto(final HelperOffer helperOffer) {
		return HelperOfferDto.builder().id(helperOffer.getId()).helperId(helperOffer.getHelperId()).helperName(helperOffer.getHelperName())
				.helperPhoneNumber(helperOffer.getHelperPhoneNumber()).helperJobType(helperOffer.getHelperJobType())
				.location(helperOffer.getLocation()).expense(helperOffer.getExpense())
				.memberId(helperOffer.getMemberId()).memberName(helperOffer.getMemberName())
				.memberPhoneNumber(helperOffer.getMemberPhoneNumber()).targetName(helperOffer.getTargetName()).status(helperOffer.getStatus()).build();
	}
}
