package com.UrlCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class UrlFetcher {
    private BufferedReader bufferedReader;
    private String inputLine;
    private LineParser lineParser;
    private List<String> todoUrls = new ArrayList<String>();

    public void setLineParser(LineParser lineParser) {
        this.lineParser = lineParser;
    }

    public List<String> getTodoUrls() {
        return todoUrls;
    }

    public void fetchUrl(String url) {
        System.out.println("fetchUrl: " + url);
        initBufferedReader(url);
        while (nextLine()) {
            List<String> links = lineParser.extractUrl(inputLine);
            if (!links.isEmpty()) {
                todoUrls.addAll(links);
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initBufferedReader(String url) {
        try {
            bufferedReader = BufferReaderFactory.getNewBufferReader(url);
        } catch (ConnectException e) {
            System.out.println("Connection failed: " + url);
        } catch (IOException e) {
            bufferedReader = null;
            System.out.println("Failed to load url: " + url);
            e.printStackTrace();
        }
    }

    private boolean nextLine() {
        if (bufferedReader == null) {
            return false;
        }
        try {
            inputLine = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("Error while reading url: " + e.getMessage());
        }
        return !(inputLine == null);
    }

}
