package it.uniroma3.siw.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MyErrorController implements ErrorController {
	
	/*===============================================================================================*/
	/*                                         FORBIDDEN AREA                                        */
	/*===============================================================================================*/
	
    @GetMapping("/error")
    public String useAValidLinkNextTimeLad(HttpServletRequest request) {
    	Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    	System.out.println("\tERROR ON REQUEST, EXITED ON STATUSCODE = "+statusCode);
        return "redirect:https://www.youtube.com/watch?v=dQw4w9WgXcQ&err="+statusCode;
    }
	
}
