package com.caremoa.helper.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.caremoa.helper.adapter.ContractAccepted;
import com.caremoa.helper.domain.model.HelperOffer;
import com.caremoa.helper.domain.repository.HelperOfferRepository;
import com.caremoa.helper.exception.ApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelperOfferService {
	private final HelperOfferRepository repository;
	final private StreamBridge streamBridge;

	// @Transactional(propagation = , isolation = ,noRollbackFor = ,readOnly =
	// ,rollbackFor = ,timeout = )
	/**
	 * @methodName : getAll
	 * @date : 2023.05.14
	 * @description : HelperOffer Repository의 모든 데이터를 Page단위로 검색한다.
	 * @param pageable
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional(readOnly = true)
	public List<HelperOffer> getAll() throws Exception, ApiException {
		log.info("getAll");
		
		return repository.findAll();
	}
 
	/**
	 * @methodName : getPage
	 * @date : 2023.05.14
	 * @description : HelperOffer Repository의 모든 데이터를 Page단위로 검색한다.
	 * @param pageable
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional(readOnly = true)
	public Page<HelperOffer> getPage(Pageable pageable) throws Exception, ApiException {
		log.info("getPage");
		return repository.findAll(pageable);
	}

	/**
	 * @methodName : getById
	 * @date : 2023.05.14
	 * @description : HelperOffer Repository의 id로 검색한다.
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional(readOnly = true)
	public HelperOffer getById(Long id) throws Exception, ApiException {
		Optional<HelperOffer> data = repository.findById(id);

		if (data.isPresent()) {
			return data.get();
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, String.format("Member id=[%d] Not Found", id));
		}
	}

	/**
	 * @methodName : postData
	 * @date : 2023.05.14
	 * @description : HelperOffer를 Repository에 등록한다.
	 * @param newData
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public HelperOffer postData(HelperOffer newData) throws Exception, ApiException {
		newData = repository.save(newData);

		return newData;
	}

	/**
	 * @methodName : putData
	 * @date : 2023.05.14
	 * @description : Helper를 수정한다
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public HelperOffer putData(HelperOffer newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					newData.setId(oldData.getId());
					return repository.save(newData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Helper id=[%d] Not Found", id));
				});
	}

	/**
	 * @methodName : patchData
	 * @date : 2023.05.14
	 * @description : Helper를 수정한다.(전달된 값만[Null 제외])
	 * @param newData
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public HelperOffer patchData(HelperOffer newData, Long id) throws Exception, ApiException {
		return repository.findById(id) //
				.map(oldData -> {
					oldData.setHelperName(
							newData.getHelperName() != null ? newData.getHelperName() : oldData.getHelperName());
					oldData.setHelperPhoneNumber(newData.getHelperPhoneNumber() != null ? newData.getHelperPhoneNumber()
							: oldData.getHelperPhoneNumber());
					oldData.setHelperJobType(newData.getHelperJobType() != null ? newData.getHelperJobType()
							: oldData.getHelperJobType());
					oldData.setLocation(newData.getLocation() != null ? newData.getLocation() : oldData.getLocation());
					oldData.setExpense(newData.getExpense() != null ? newData.getExpense() : oldData.getExpense());
					oldData.setMemberId(
							newData.getMemberId() != null ? newData.getMemberId() : oldData.getMemberId());
					oldData.setMemberName(
							newData.getMemberName() != null ? newData.getMemberName() : oldData.getMemberName());
					oldData.setMemberPhoneNumber(
							newData.getMemberPhoneNumber() != null ? newData.getMemberPhoneNumber() : oldData.getMemberPhoneNumber());
					oldData.setTargetName(
							newData.getTargetName() != null ? newData.getTargetName() : oldData.getTargetName());
					oldData.setStatus(
							newData.getStatus() != null ? newData.getStatus() : oldData.getStatus());
					HelperOffer retData = repository.save(oldData);
					log.info("publishContract({})", retData.toString());
					publishContract(retData);
					return (retData);
				}).orElseGet(() -> {
					throw new ApiException(HttpStatus.NOT_FOUND, String.format("Helper id=[%d] Not Found", id));
				});
	}

	/**
	 * @methodName : deleteData
	 * @date : 2023.05.14
	 * @description : Helper를 삭제한다.
	 * @param id
	 * @throws Exception
	 * @throws ApiException
	 */
	@Transactional
	public void deleteData(@PathVariable("id") Long id) throws Exception, ApiException {
		repository.deleteById(id);
	}
	
	private void publishContract(HelperOffer offer) {
		if( ! "계약요청".equals(offer.getStatus())) return;
		
		try {
			ContractAccepted contractAccepted = new ContractAccepted() ;
			
			BeanUtils.copyProperties(offer, contractAccepted);
			String json = contractAccepted.toJson();
			if (json != null) {
				streamBridge.send("basicProducer-out-0", MessageBuilder.withPayload(json)
						.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
			}
			log.info("Kafka publish : {}", json);
		} catch (Exception e) {
			log.info("publish {}", e.getMessage());
		}
	}

}
