package com.security.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class MockServer {

    public static void main(String[] args){
        WireMock.configureFor(8060);
        WireMock.removeAllMappings();
        mock("/order/1", "01");

    }

    private static void mock(String url, String file){
        String content = "";
        ClassPathResource classPathResource = new ClassPathResource("mock/response/"+ file +".txt");
        try {
            content = StringUtils.join(FileUtils.readLines(classPathResource.getFile(),"UTF-8").toArray(),"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
