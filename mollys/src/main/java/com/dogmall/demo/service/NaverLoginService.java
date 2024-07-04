package com.dogmall.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dogmall.demo.domain.NaverCallback;
import com.dogmall.demo.domain.NaverToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverLoginService {
	
	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.redirect.uri}")
	private String redirectUri;
	
	@Value("${naver.client.secret}")
	private String clientSecret; 
	
	public String getNaverAuthorizeUrl() throws UnsupportedEncodingException {
		
		UriComponents uriComponents = UriComponentsBuilder
				.fromUriString("https://nid.naver.com/oauth2.0/authorize")
				.queryParam("response_type", "code")
				.queryParam("client_id", clientId)
				.queryParam("state", URLEncoder.encode("1234", "UTF-8"))
				.queryParam("redirect_uri", URLEncoder.encode(redirectUri, "UTF-8"))
				.build();
		
		return uriComponents.toString();
	}
	
	public String getNaverTokenUrl(NaverCallback callback) {
		
		try {
			
			UriComponents uriComponents = UriComponentsBuilder
					.fromUriString("https://nid.naver.com/oauth2.0/token")
					.queryParam("grant_type", "authorization_code")
					.queryParam("client_id", clientId)
					.queryParam("client_secret", clientSecret)
					.queryParam("code", callback.getCode())
					.queryParam("state", URLEncoder.encode(callback.getState(), "UTF-8"))
					.build();
			URL url = new URL(uriComponents.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			
			// 입력스트림작업.  
			// conn.getInputStream() : 바이트스트림
			// InputStreamReader클래스 : 바이트기반의 스트림을 문자기반스트림으로 변환하는 기능.
			// BufferedReader : 버퍼기능제공 보조스트림
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
			
			log.info("응답데이터: " + response.toString());
			
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getnaverUserByToken(NaverToken naverToken) {
		String accessToken = naverToken.getAccess_token();
		String tokenType = naverToken.getToken_type()
;
		
		try{
		URL url = new URL("https://openapi.naver.com/v1/nid/me");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", tokenType + " " + accessToken);
		
		int responseCode = conn.getResponseCode();
		BufferedReader br;
		if(responseCode == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
		}
		
			br.close();
			
			log.info("사용자정보 응답데이터:" + response.toString());
			
			return response.toString();
	}	catch(Exception e) {
		e.printStackTrace();
	}
		return null;
}
	
    public void getNaverTokenDelete(String access_token) {
        try {
            // 네이버 인증 서버의 세션을 무효화하는 API를 호출하는 URL 생성
            UriComponents uriComponents = UriComponentsBuilder
                    .fromUriString("https://nid.naver.com/oauth2.0/token")
                    .queryParam("grant_type", "delete")
                    .queryParam("client_id", clientId)
                    .queryParam("client_secret", clientSecret)
                    .queryParam("access_token", URLEncoder.encode(access_token, "UTF-8"))
                    .build();

            URL url = new URL(uriComponents.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            log.info("네이버 로그아웃 상태코드:" + responseCode);

            
        } catch (Exception e) {
            e.printStackTrace();
		} 
	}
	
	
	
	
}
