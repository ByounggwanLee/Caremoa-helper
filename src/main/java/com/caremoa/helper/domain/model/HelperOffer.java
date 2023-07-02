package com.caremoa.helper.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.caremoa.helper.domain.listener.HelperOfferListener;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners({ HelperOfferListener.class, AuditingEntityListener.class })
@Table(name = "HELPEROFFER")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC) // AccessLevel.PUBLIC
@AllArgsConstructor
@Builder
public class HelperOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "HELPER_ID", length = 50, nullable = true)
	@Schema(description = "도움미ID", nullable = true, defaultValue = "1")
	private Long helperId;

	@Column(name = "HELPER_NAME", length = 50, nullable = true)
	@Schema(description = "도움미이름", nullable = true, defaultValue = "이병관")
	private String helperName;
	
	@Column(name = "HELPER_PHONE_NUMBER", length = 50, nullable = true)
	@Schema(description = "도움미연락처", nullable = true, defaultValue = "010-1234-1234")
	private String helperPhoneNumber;
	
	@Column(name = "HELPER_JOB_TYPE", length = 50, nullable = true)
	@Schema(description = "도우미업무", nullable = true, defaultValue = "아기돌봄")
	private String helperJobType;
	
	@Column(name = "location", length = 50, nullable = true)
	@Schema(description = "근무지역", nullable = true, defaultValue = "서울, 강서, 염창")
	private String location;

	@Column(name = "expense", nullable = true)
	@Schema(description = "월급", nullable = true, defaultValue = "2000000")
	private Long expense;
	
	
	@Column(name = "MEMBER_ID", length = 50, nullable = true)
	@Schema(description = "회원ID", nullable = true, defaultValue = "0")
	private Long memberId;
	 
    @Column(name = "MEMBER_NAME", length = 50, nullable = true)
	@Schema(description = "회원이름", nullable = true, defaultValue = "장원영")
	private String memberName;
	 
	@Column(name = "MEMBER_PHONE_NUMBER", length = 50, nullable = true)
	@Schema(description = "회원연락처", nullable = true, defaultValue = "010-2314-3456")
	private String memberPhoneNumber;

	@Column(name = "TARGET_NAME", length = 50, nullable = true)
	@Schema(description = "돌봄대상자", nullable = true, defaultValue = "홍길동")
	private String targetName;
	
	@Column(name = "STATUS", length = 50, nullable = true)
	@Schema(description = "상태", nullable = true, defaultValue = "구직")
	private String status;
}
