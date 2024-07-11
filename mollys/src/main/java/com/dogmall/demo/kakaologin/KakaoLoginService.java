package com.dogmall.demo.kakaologin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoLoginService{

	private final KakaoLoginMapper kakaoLoginMapper;
	
	@Value("${kakao.client.id}")
	private String clientId;
	
	@Value("${kakao.redirect.uri}")
	private String redirectUri;
	
	@Value("${kakao.client.secret}")
	private String clientSecret;
	
	@Value("${kakao.oauth.tokenuri}")
	private String tokenUri;
	
	@Value("${kakao.oauth.userinfouri}")
	private String userinfoUri;
	
	@Value("${kakao.user.logout}")
	private String kakaologout;
	
	public String getAccessToken(String code) throws JsonProcessingException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", clientId);
		body.add("redirect_uri", redirectUri);
		body.add("code", code);
		body.add("client_secret", clientSecret);
		
		HttpEntity<MultiValueMap<String, String>> tokenKakaoRequest = new HttpEntity<MultiValueMap<String,String>>(body, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(tokenUri, HttpMethod.POST, tokenKakaoRequest, String.class);
		
		String responseBody = response.getBody();
		
		log.info("응답데이터: " + responseBody);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		
		return jsonNode.get("access_token").asText();
	}
	
	public KakaoLoginVO getKakaoLoginVO(String accessToken) throws JsonProcessingException{
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> userInfoKakaoRequest = new HttpEntity<>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(userinfoUri, HttpMethod.POST, userInfoKakaoRequest, String.class);
		
		String responseBody = responseEntity.getBody();
		
		log.info("응답사용자정보: " + responseBody);
		
		ObjectMapper objctMapper = new ObjectMapper();
		JsonNode jsonNode = objctMapper.readTree(responseBody);
		
		Long id = jsonNode.get("id").asLong();
		String email = jsonNode.get("kakao_account").get("email").asText();
		String nickname = jsonNode.get("properties").get("nickname").asText();
		
		return new KakaoLoginVO(id, nickname, email);
		
	}
	
	public void kakaologout(String accessToken) throws JsonProcessingException {
	
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded");
		
		HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest = new HttpEntity<>(headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(kakaologout, HttpMethod.POST, kakaoLogoutRequest, String.class);
		
		String responseBody = response.getBody();
		log.info("responseBody:" + responseBody);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		
		Long id = jsonNode.get("id").asLong();
		
		
	}
}
