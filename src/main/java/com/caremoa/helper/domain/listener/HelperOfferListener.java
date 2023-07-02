package com.caremoa.helper.domain.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.caremoa.helper.domain.model.HelperOffer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.caremoa.HelperOffer.domain.listener
* @fileName       : HelperOfferListener.java
* @author         : 이병관
* @date           : 2023.05.07
* @description    : HelperOfferDto Repository Event Listenner
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.07        이병관       최초 생성
*/
@Slf4j
@RequiredArgsConstructor
public class HelperOfferListener {
	//----------------------------------------------------
    // Load/Persist/Update/Remove(조회/신규/수정/삭제)
    // Entity Pre/Post(이전/이후) 처리에 대한 정의(PreLoad는 없음)
    // * DB의 Trigger와 비슷한 JPA기능
    //----------------------------------------------------
    @PostLoad
    public void onPostLoad(HelperOffer postData) {
        log.info("onPostLoad : Select후 호출({})" , postData.toString() );
    }

    @PrePersist
    public void onPrePersist(HelperOffer preData) {
        log.info("onPrePersist : Insert전 호출({})" , preData.toString() );
    }

    @PostPersist
    public void onPostPersist(HelperOffer postData) {
    	log.info("onPrePersist : Insert후 호출({})" , postData.toString() );
    }

    @PreUpdate
    public void onPreUpdate(HelperOffer preData) {
        log.info("onPreUpdate : Update전 호출({})" , preData.toString() );
    }

    @PostUpdate
    public void onPostUpdate(HelperOffer postData) {
        log.info("onPostUpdate : Update후 호출({})" , postData.toString() );
    }

    @PreRemove
    public void onPreRemove(HelperOffer preData) {
        log.info("onPreRemove  : Delete전 호출({})" , preData.toString() );
    }

    @PostRemove
    public void onPostRemove(HelperOffer postData) {
        log.info("onPostRemove : Delete후 호출({})" , postData.toString() );
    }
}
