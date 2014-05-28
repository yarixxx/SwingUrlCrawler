package com.UrlCrawler;

import java.util.List;

public interface LineParser {
    public List<String> extractUrl(String line);

    public void setRegularExpression(String regex);
}
