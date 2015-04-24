package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johannes Graf - graf@synyx.de
 */
@RestController
public class PublicController {
    
    @RequestMapping("/public")
    public String get() {
        return "{\"message\":\"Hello World\"}";
    }
}
