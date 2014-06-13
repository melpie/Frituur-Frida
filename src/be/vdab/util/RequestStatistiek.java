
package be.vdab.util;

/**
 *
 * @author pieter.mels
 */
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class RequestStatistiek {
    public synchronized void voegRequestToe(HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        @SuppressWarnings("unchecked")
        Map<String, Integer> statistiek = (Map<String, Integer>) context.getAttribute("statistiek");
        String url = request.getRequestURI();
        if (statistiek.containsKey(url)) {
            statistiek.put(url, statistiek.get(url) + 1);
        } else {
            statistiek.put(url, 1);
        }
    }
}
