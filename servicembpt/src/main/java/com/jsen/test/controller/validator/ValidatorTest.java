package com.jsen.test.controller.validator;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.model.Person;
import com.jsen.test.utils.ResponseBase;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.AssertFalse;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author jsen
 * @since 2018-03-28
 */
@RestController
@CrossOrigin
@RequestMapping("/validator")
@Validated
public class ValidatorTest {

    @RequestMapping("/person")
    public JSONObject person(@Valid Person demo,BindingResult result) {


        System.out.println("(((");
        for (ObjectError error : result.getAllErrors()) {
            System.out.println(error.getDefaultMessage());
        }
        System.out.println(")))");
        return ResponseBase.create().code(0).msg("OK").add("name", demo.getName()).add("dead", demo.isDead());

    }

    @GetMapping("/test00")
    public JSONObject parameters(@NotBlank(message = "名字不能为空") String name, @AssertFalse(message = "dead只能为false") boolean dead) {
        return ResponseBase.create().code(0).add("name", name).add("dead", dead);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONObject handle(ValidationException exception) {
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<?> item : violations) {
                message.append(item.getMessage());
                // System.out.println(item.getMessage());
            }
            return ResponseBase.create().code(1).msg(message.toString());
        }
        return ResponseBase.create().code(1).msg(exception.getMessage());
    }
}
