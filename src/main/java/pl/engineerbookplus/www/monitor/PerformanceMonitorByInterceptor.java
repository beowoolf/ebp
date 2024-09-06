package pl.engineerbookplus.www.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
public class PerformanceMonitorByInterceptor implements HandlerInterceptor {

    ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch(handler.toString());
        stopWatch.start(handler.toString());
        stopWatchLocal.set(stopWatch);

        //log.info("Przetwarzanie żądania do ścieżki: " + getURLPath(request));
        //log.info("Przetwarzanie żądania rozpoczęto o: " + getCurrentTime());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.info("Przetwarzanie żądania zakończono o: " + getCurrentTime());
        StopWatch stopWatch = stopWatchLocal.get();
        stopWatch.stop();

        log.info("ŻĄDANY ZASÓB#ŁĄCZNY CZAS PRZETWARZANIA ŻĄDANIA#JEDNOSTKA#" + getURLPath(request) + "#" + stopWatch.getTotalTimeMillis() + "#ms");
        stopWatchLocal.set(null);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
//        StopWatch stopWatch = stopWatchLocal.get();
//        stopWatch.stop();
//
//        log.info("ŻĄDANY ZASÓB#ŁĄCZNY CZAS PRZETWARZANIA ŻĄDANIA#JEDNOSTKA#" + getURLPath(request) + "#" + stopWatch.getTotalTimeMillis() + "#ms");
//        stopWatchLocal.set(null);
        //log.info("=======================================================");
    }

    private String getURLPath(HttpServletRequest request) {
        String currentPath = request.getRequestURI();
        String queryString = request.getQueryString();
        queryString = queryString == null ? "" : "?" + queryString;
        return currentPath + queryString;
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'o' HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }

}
