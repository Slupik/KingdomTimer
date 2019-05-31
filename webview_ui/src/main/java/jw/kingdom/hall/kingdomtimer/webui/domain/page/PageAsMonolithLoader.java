package jw.kingdom.hall.kingdomtimer.webui.domain.page;

import jw.kingdom.hall.kingdomtimer.webui.domain.utils.Resource;

import java.io.IOException;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class PageAsMonolithLoader implements PageLoader {
    private static final String CSS_PLACEHOLDER = "<place_for_css></place_for_css>";
    private static final String SCRIPTS_PLACEHOLDER = "<place_for_javascript></place_for_javascript>";

    @Override
    public String getParsedPage(String basePath) throws PageLoadException {
        String rawHTML = getRawHTML(basePath);
        rawHTML = injectJavaScript(rawHTML, basePath);
        rawHTML = injectCSS(rawHTML, basePath);
        return rawHTML;
    }

    private String getRawHTML(String basePath) throws PageLoadException {
        try {
            return Resource.getContent(basePath+"index.html");
        } catch (Exception e) {
            throw new PageLoadException(e);
        }
    }

    private String injectJavaScript(String rawHTML, String basePath) throws PageLoadException {
        return rawHTML.replace(SCRIPTS_PLACEHOLDER, getJavaScriptData(basePath));
    }

    private String getJavaScriptData(String basePath) throws PageLoadException {
        String scriptsPath = basePath+"scripts/";
        return getMergedData(scriptsPath, "script");
    }

    private String injectCSS(String rawHTML, String basePath) throws PageLoadException {
        return rawHTML.replace(CSS_PLACEHOLDER, getCSSData(basePath));
    }

    private String getCSSData(String basePath) throws PageLoadException {
        String scriptsPath = basePath+"styles/";
        return getMergedData(scriptsPath, "style");
    }

    private String getMergedData(String folderPath, String tag) throws PageLoadException {
        try {
            StringBuilder completeData = new StringBuilder();
            List<String> fileNames = Resource.getResourceFiles(folderPath);

            for(String fileName: fileNames) {
                String completePath = folderPath+fileName;
                String content = Resource.getContent(completePath);

                completeData
                        .append("<").append(tag).append(">")
                        .append(content)
                        .append("</").append(tag).append(">");
            }
            return completeData.toString();

        } catch (IOException e) {
            throw new PageLoadException(e);
        }
    }
}
