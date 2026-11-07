package pl.engineerbookplus.www.model.monitor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@Aspect
@Component
public class PerformanceMonitorByAOP {

    @Around("target(pl.engineerbookplus.www.model.repository.Repository)")
    public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        //log.info("Przetwarzanie żądania rozpoczęto o: " + getCurrentTime());
        //log.info("Informacja o punkcie złączenia: " + joinPoint.getSignature().getName());
        long start = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //log.info("Przetwarzanie żądania zakończono o: " + getCurrentTime());
        log.info("PUNKT ZŁĄCZENIA#ŁĄCZNY CZAS PRZETWARZANIA ŻĄDANIA#JEDNOSTKA#" + joinPoint.getSignature().getName() + "#" + (end - start) + "#ms");
        //log.info("=======================================================");
        return o;
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'o' HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }

}
