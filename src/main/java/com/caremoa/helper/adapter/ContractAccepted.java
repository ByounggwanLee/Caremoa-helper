package com.caremoa.helper.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.member.adapter
* @fileName       : ClaimCompleted.java
* @author         : 이병관
* @date           : 2023.05.19
* @description    : 클레임종료 Polish Event
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.19        이병관       최초 생성
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractAccepted extends AbstractEvent{
    private Long memberId;
    private String memberName;
    private Long helperId;
    private String helperName;
    private String helperJobType;
    private String targetName;
    private String careRange;
    private Long expense;
    private String location;
    private String helperPhoneNumber;
    private String memberPhoneNumber;
    
    @Builder
    public ContractAccepted(Long memberId, String memberName, Long helperId, String helperName, String helperJobType,
		String targetName, String careRange, Long expense, String location, String helperPhoneNumber,
		String memberPhoneNumber) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.helperId = helperId;
		this.helperName = helperName;
		this.helperJobType = helperJobType;
		this.targetName = targetName;
		this.careRange = careRange;
		this.expense = expense;
		this.location = location;
		this.helperPhoneNumber = helperPhoneNumber;
		this.memberPhoneNumber = memberPhoneNumber;
	}
}
