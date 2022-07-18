package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@Validated @RequestBody ItemSaveForm form, BindingResult bindingResult) {
        //TypeMismatch 등 객체에 binding 자체가 안될 경우 컨트롤러가 호출되지 않음.
        log.info("컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증로직 에러");
            return bindingResult.getAllErrors();
        }

        log.info("정상처리");
        return form;
    }
}
