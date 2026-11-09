package nagikh.dev;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

@Aspect
@Component
public class NotNullAspect {
    @Around("@annotation(notNullArgs)")
    public Object checkNull(ProceedingJoinPoint pjp, NotNullArgs notNullArgs) throws Throwable {
        if (Arrays.stream(pjp.getArgs()).anyMatch(Objects::isNull)) {
            int index = IntStream.range(0, pjp.getArgs().length)
                    .filter(i -> pjp.getArgs()[i] == null)
                    .findFirst()
                    .orElse(-1);
            throw new IllegalArgumentException("Есть null параметры в "
                    + pjp.getSignature().getName() + " в arg[" + index + "]");
        }
        return pjp.proceed();
    }
}
