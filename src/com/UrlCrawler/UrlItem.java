package com.UrlCrawler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UrlItem {
    private String title;
    private boolean visited = false;
    private Set<UrlItem> children = new HashSet<UrlItem>();
    private final String url;

    UrlItem(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void visit() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<UrlItem> getChildren() {
        return Collections.unmodifiableSet(children);
    }

    public boolean hasChild(UrlItem child) {
        return children.contains(child);
    }

    public void addChild(UrlItem child) {
        children.add(child);
    }

    public String getValue(String columnName) {
        if (columnName.equals("URL")) {
            return url;
        }

        if (columnName.equals("Title")) {
            return title;
        }

        if (columnName.equals("Children")) {
            return "" + children.size();
        }
        return "";
    }
}
