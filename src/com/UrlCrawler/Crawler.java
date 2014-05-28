package com.UrlCrawler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Crawler {
    private UrlStorage storage;
    private UrlTableModel urlTableModel;
    private Queue<String> todoUrls = new LinkedList<String>();

    public void addUrl(String url) {
        todoUrls.add(url);
    }

    public void setUrlTableModel(UrlTableModel urlTableModel) {
        this.urlTableModel = urlTableModel;
    }

    public void setStorage(UrlStorage storage) {
        this.storage = storage;
    }

    public void fetchUrl() {
        String url = todoUrls.poll();
        UrlFetcher fetcher = new UrlFetcher();
        LineParser lp = new LineParserImpl();
        lp.setRegularExpression("[\'\"](http://[\\S]+)[\"\']");
        fetcher.setLineParser(lp);
        fetcher.fetchUrl(url);

        List<String> fetchedUrls = fetcher.getTodoUrls();
        List<String> newUrls = new ArrayList<String>();
        for (String fetchedUrl : fetchedUrls) {
            if (!todoUrls.contains(fetchedUrl)) {
                todoUrls.add(fetchedUrl);
            }
        }

        urlTableModel.addRows(fetcher.getTodoUrls());
    }
}
