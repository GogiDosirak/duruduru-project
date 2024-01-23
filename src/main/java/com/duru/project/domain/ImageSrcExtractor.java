package com.duru.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSrcExtractor {
	public static List<String> extractImageSrcList(String htmlContent) {
        List<String> imageSrcList = new ArrayList<>();
        
        // 이미지 소스 추출 로직
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            imageSrcList.add(matcher.group(1));
        }

        return imageSrcList;
    }

}
