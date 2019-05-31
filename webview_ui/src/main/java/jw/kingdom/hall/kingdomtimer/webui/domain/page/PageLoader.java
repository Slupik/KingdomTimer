package jw.kingdom.hall.kingdomtimer.webui.domain.page;

/**
 * All rights reserved & copyright ©
 */
public interface PageLoader {
    String getParsedPage(String basePath) throws PageLoadException;
}
