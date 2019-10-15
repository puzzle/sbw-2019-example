package ch.puzzle.spring.workshop.beerio.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

  @Around("execution(* ch.puzzle.spring.workshop.beerio.service.CountryService.findAllCountries())")
  Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable {
    long start = System.currentTimeMillis();
    Object obj = jp.proceed();
    log.info("findAllByCountry() took {}ms", System.currentTimeMillis() - start);
    return obj;
  }

  @AfterThrowing(pointcut = "execution(public * ch.puzzle..*.*(..))", throwing = "e")
  void onException(Exception e) {
    log.error("Custom error handling");
  }

  @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  Object getOperations(ProceedingJoinPoint joinPoint) throws Throwable {
    return joinPoint.proceed();
  }
}
