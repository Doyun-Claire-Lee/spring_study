package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("hello.exception.api")
public class ExControllerAdvice {

    // 예외처리를 이렇게 하면 서블릿컨테이너에서 예외를 확인하고 다시 호출될 필요 없이 현재 로직에서 응답처리 하고 끝나게 됨.
    // 현재 컨트롤러가 rest controller이기 때문에 ErrorResult가 Json으로 변환됨.
    // @ResponseStatus가 없으면 정상로직으로 변경되기 때문에 원하는 상태코드로 지정하여 변경될 수 있게 해줌.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHadler(IllegalArgumentException e) {
        log.error("[exceptionHanlder] ex", e);

        // 예외를 해결(resolve)하고 정상 로직으로 변경하여 응답해버림. (status code 200)
        return new ErrorResult("BAD", e.getMessage());
    }

    // 어노테이션에 예외 클래스 지정 생략가능
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHanlder] ex", e);

        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());

        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHanlder] ex", e);

        return new ErrorResult("EX", "내부 오류");
    }
}
