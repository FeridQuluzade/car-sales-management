package az.turbo.backend.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.valueOf;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "error")
    @ResponseBody
    public ApiError error(WebRequest webRequest, HttpServletResponse response) {
        log.error("Error response {}",response.getStatus());
        HttpStatus status = valueOf(response.getStatus());
        return new ApiError(status);
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
