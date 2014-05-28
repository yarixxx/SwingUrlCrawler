package com.UrlCrawler;

import java.util.ArrayList;
import java.util.List;

public class UrlStorage {
    private List<UrlItem> urlList = new ArrayList<UrlItem>();
    private Crawler crawler;

    public int getRowCount() {
        return urlList.size();
    }

    public String getValueByName(int rowIndex, String columnName) {
        return urlList.get(rowIndex).getValue(columnName);
    }

    public void addItem(String url) {
        addItem(null, url);
    }

    public void addItem(String parentUrl, String url) {
        crawler.addUrl(url);
        UrlItem parent = null;
        UrlItem child = null;
        for (UrlItem item : urlList) {
            String value = item.getValue("Url");
            if (value.equals(url)) {
                child = item;
            }

            if (value.equals(parentUrl)) {
                parent = item;
            }
        }

        if (child == null) {
            child = new UrlItem(url);
            urlList.add(child);
        }

        if (parent != null && child != null) {
            parent.addChild(child);
        }
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public void fetchNextUrl() {
        crawler.fetchUrl();
    }
}
