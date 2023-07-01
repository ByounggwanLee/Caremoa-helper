package com.caremoa.helper.domain.model;

import java.time.LocalTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.helper.domain.listener.HelperJobListener;
import com.caremoa.helper.domain.model.vo.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @packageName    : com.caremoa.helper.domain.model
* @fileName       : HelperJobDto.java
* @author         : 이병관
* @date           : 2023.05.24
* @description    : 도우미 구직정보
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.24        이병관       최초 생성
*/
@Entity
@EntityListeners({ HelperJobListener.class, AuditingEntityListener.class })
@Table(name = "HELPERJOB")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) // AccessLevel.PUBLIC
@AllArgsConstructor
@Builder
public class HelperJob {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "HELPER_ID", nullable = false)
	@Schema(description = "도움미ID", nullable = false, defaultValue = "1")
	private Long helperId;
	
	@Enumerated(EnumType.STRING)
	@Schema(description = "도우미업무", nullable = true, defaultValue = "BABYSITER")
	private HelperJobType jobType;
	
	
	@Column(name = "TAKE_AGE", nullable = true)
	@Schema(description = "도우미업무", nullable = true, defaultValue = "NEWBORN")
	private TakerLevelType takerAge;
	
	@Embedded
	@AttributeOverride(name = "addressState", column = @Column(name = "JOB_AREA1_ADDRESS_STATE"))
	@AttributeOverride(name = "addressCity", column = @Column(name = "JOB_AREA1_ADDRESS_CITY"))
	@AttributeOverride(name = "addressStreet", column = @Column(name = "JOB_AREA1_ADDRESS_STREET"))
	@Schema(description = "근무선호지역1", nullable = true)
	private Address jobArea1;

	@Embedded
	@AttributeOverride(name = "addressState", column = @Column(name = "JOB_AREA2_ADDRESS_STATE"))
	@AttributeOverride(name = "addressCity", column = @Column(name = "JOB_AREA2_ADDRESS_CITY"))
	@AttributeOverride(name = "addressStreet", column = @Column(name = "JOB_AREA2_ADDRESS_STREET"))
	@Schema(description = "근무선호지역2", nullable = true)
	private Address jobArea2;
	
	@Embedded
	@AttributeOverride(name = "addressState", column = @Column(name = "JOB_AREA3_ADDRESS_STATE"))
	@AttributeOverride(name = "addressCity", column = @Column(name = "JOB_AREA3_ADDRESS_CITY"))
	@AttributeOverride(name = "addressStreet", column = @Column(name = "JOB_AREA3_ADDRESS_STREET"))
	@Schema(description = "근무선호지역3", nullable = true)
	private Address jobArea3;
	
	@Column(name = "WORKING_DAYS", nullable = true, length = 20)
	@Schema(description = "근무가능요일", nullable = true, defaultValue = "11111000")
	String workingDays;     // 근무가능요일은 Sting으로	20글자로 구성(1~8만 우선사용) 월(1)~일(7)휴일(8) : 11111000 (월~금 가능)
	
	@Column(name = "WORK_START_TIME", nullable = true)
	@Schema(description = "근무시작가능시간", nullable = true, defaultValue = "09:00")
	private LocalTime workStartTime;  
	
	@Column(name = "WORK_END_TIME", nullable = true)
	@Schema(description = "근무종료가능시간", nullable = true, defaultValue = "18:00")
	private LocalTime workEndTime;
	
	@Column(name = "WAGE_TYPE", nullable = true)
	@Schema(description = "시급/월급", nullable = true, defaultValue = "HOURLY")
	private WageType wageType;
	
	@Column(name = "WAGE_AMOUNT", nullable = true)
	@Schema(description = "시급/월급", nullable = true, defaultValue = "HOURLY02")
	private WageAmountType wageAmount;
	
	@Column(name = "ONE_LINE_ME", nullable = true, length = 128)
	@Schema(description = "간단자기소개", nullable = true, defaultValue = "HOURLY02")
	private String oneLineMe;
	
	@Column(name = "ABOUT_ME", nullable = true, length = 2048)
	@Schema(description = "자기소개", nullable = true, defaultValue = "HOURLY02")
	private String aboutMe;

	@Column(name = "ACTIVE_FLAG", nullable = true)
	@Schema(description = "활성화여부", nullable = true, defaultValue = "true")
	private Boolean activeFlag;

}
