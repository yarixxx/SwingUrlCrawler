package com.UrlCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class BufferReaderFactory {
    public static BufferedReader getNewBufferReader(String url)
            throws IOException {
        return new BufferedReader(getNewInputStreamReader(url));
    }

    public static InputStreamReader getNewInputStreamReader(String url)
            throws IOException {
        return new InputStreamReader(new URL(url).openStream());
    }
}
