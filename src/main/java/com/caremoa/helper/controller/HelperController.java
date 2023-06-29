package com.caremoa.helper.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.caremoa.helper.domain.dto.HelperDto;
import com.caremoa.helper.domain.service.HelperService;
import com.caremoa.helper.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
* @packageName    : com.caremoa.helper.controller
* @fileName       : HelperController.java
* @author         : 이병관
* @date           : 2023.05.31
* @description    :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.05.31        이병관       최초 생성
*/
@Slf4j
@RestController
@Tag(name = "도우미관리", description = "CareMoa 도우미관리")
@RequiredArgsConstructor
public class HelperController {

	final private HelperService service;
	
	/**
	 * @methodName    : getAll
	 * @date          : 2023.05.31
	 * @description   : 전체 리스트를 페이지 단위로 조회한다.(GET)
	 * @param pageable
	 * @return
	*/
	@Operation(summary = "도우미정보 조회" , description = "도우미정보 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the helpers", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = HelperDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@GetMapping("/helpers")
	public ResponseEntity<Page<HelperDto>> getAll(Pageable pageable) {
		try {
			log.info("findAll");
			return ResponseEntity.ok().body(service.getAll(pageable).map(HelperDto::toDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}

	/**
	 * @methodName    : getById
	 * @date          : 2023.05.31
	 * @description   : ID로 조회한다.(GET)
	 * @param id
	 * @return
	*/
	@Operation(summary = "도우미정보 Key조회" , description = "도우미정보 Primary Key로 조회" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the HelperDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = HelperDto.class)) }),
			@ApiResponse(responseCode = "404", description = "HelperDto not found", content = @Content) })
	@GetMapping("/helpers/{id}")
	public ResponseEntity<HelperDto> getById(@PathVariable("id") Long id) {
		try {
		    return new ResponseEntity<>(HelperDto.toDto(service.getById(id)),HttpStatus.OK);
		}catch( ApiException apiEx ) {
		    return new ResponseEntity<>(null, apiEx.getCode());
	    }catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
	}


	/**
	 * @methodName    : postData
	 * @date          : 2023.05.31
	 * @description   : 데이터를 입력한다.(POST)
	 * @param newData
	 * @return
	*/
	@Operation(summary = "도우미정보 등록" , description = "도우미정보 신규 데이터 등록" )
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create the HelperDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = HelperDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping("/helpers")
	ResponseEntity<HelperDto> postData(@RequestBody HelperDto newData) {
		try {
			return new ResponseEntity<>(HelperDto.toDto(service.postData(newData.toModel())), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @methodName    : putData
	 * @date          : 2023.05.31
	 * @description   : DTO 데이터를 전체 수정한다.(PUT)
	 * @param newData
	 * @param id
	 * @return
	*/
	@Operation(summary = "도우미정보 수정" , description = "도우미정보 데이터 수정" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the HelperDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = HelperDto.class)) }),
			@ApiResponse(responseCode = "404", description = "HelperDto not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	@PutMapping("/helpers/{id}")
	ResponseEntity<HelperDto> putData(@RequestBody HelperDto newData,
			@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(HelperDto.toDto(service.putData(newData.toModel(),id)), HttpStatus.OK);
		}catch( ApiException apiEx ) {
		     return new ResponseEntity<>(null, apiEx.getCode());
	    } catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @methodName    : patchData
	 * @date          : 2023.05.31
	 * @description   : 전달받은 데이터만 수정한다.(Patch)
	 * @param newData
	 * @param id
	 * @return
	*/
	@Operation(summary = "도우미정보 수정" , description = "도우미정보 데이터 수정" )
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the HelperDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = HelperDto.class)) }),
			@ApiResponse(responseCode = "404", description = "HelperDto not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	@PatchMapping("/helpers/{id}")
	ResponseEntity<HelperDto> patchData(@RequestBody HelperDto newData,
			@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(HelperDto.toDto(service.putData(newData.toModel(),id)), HttpStatus.OK);
		}catch( ApiException apiEx ) {
		     return new ResponseEntity<>(null, apiEx.getCode());
	    } catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @methodName    : deleteData
	 * @date          : 2023.05.31
	 * @description   : 데이터를 삭제한다.
	 * @param id
	 * @return
	*/
	@Operation(summary = "도우미정보 삭제" , description = "도우미정보 Primary Key로 삭제" )
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Delete the HelperDto", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = HelperDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@DeleteMapping("/helpers/{id}")
	public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") Long id) {
		try {
			service.deleteData(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
