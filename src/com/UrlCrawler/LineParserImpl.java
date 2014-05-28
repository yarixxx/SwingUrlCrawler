package com.UrlCrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineParserImpl implements LineParser {

    private Pattern regPattern;
    private List<String> links = new ArrayList<String>();

    public List<String> extractUrl(String line) {
        links.clear();
        Matcher m = regPattern.matcher(line);
        while (m.find()) {
            System.out.println("Converting string to URL: " + m.group(1));
            links.add(m.group(1));
        }
        return links;
    }

    public void setRegularExpression(String regex) {
        this.regPattern = Pattern.compile(regex);
    }

}
