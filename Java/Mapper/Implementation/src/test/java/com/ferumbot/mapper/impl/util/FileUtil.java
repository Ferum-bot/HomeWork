package com.ferumbot.mapper.impl.util;

import java.io.File;
import java.net.URISyntaxException;

public class FileUtil {

    public static File getFileFromTestResource(String path) throws URISyntaxException {
        var absolutePath = "test/resources/" + path;
        var url = FileUtil.class.getResource(absolutePath);
        return new File(url.toURI());
    }
}
