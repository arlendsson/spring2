package com.my.framework.mypage;

import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

	private Logger log = LoggerFactory.getLogger(MyPageController.class);

	@Resource(name = "configuration")
	private Properties configuration;

	@RequestMapping("/api")
	public String api() throws Exception {
		String result = "";
		
		
		String url = "https://tpay.smilepay.co.kr/trans/info.jsp?MID=SMTPAY002m&TR_DT=20180214&CODE=SETTLMNT_DETAIL_02&TYPE=XML";
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		SSLConnectionSocketFactory sslCsf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslCsf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		
		
		return result;
	}
}
