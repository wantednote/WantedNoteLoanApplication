package com.wn.loanapp.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wn.loanapp.common.BaseController;
import com.wn.loanapp.constants.Constants;


@Controller
public class ErrorHandlingController extends BaseController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ModelAndView error( HttpServletResponse  response) {
    	ModelAndView modelAndView = new ModelAndView();
    	log.debug("Error Code : "+ response.getStatus());
    	int errorCode = response.getStatus();
    	switch(errorCode){
    	case 400: modelAndView= new ModelAndView("errorPage");modelAndView.addObject("errorCode", errorCode);break;
    	case 403:modelAndView=  new ModelAndView("errorPage");modelAndView.addObject("errorCode", errorCode);break;
    	case 404: modelAndView= new ModelAndView("errorPage");modelAndView.addObject("errorCode", errorCode);break;
    	case 500:modelAndView=  new ModelAndView("errorPage");modelAndView.addObject("errorCode", errorCode);break;
    	default : modelAndView=  new ModelAndView();
    	}
        return modelAndView;
    }

    @RequestMapping(value = "/sessionExpired")
    public ModelAndView loginexpired(HttpServletRequest request, HttpServletRequest response) {
    	log.debug("inside sessionExpired");
    	ModelAndView modelAndView = new ModelAndView("login");
    	modelAndView.addObject("userType",  Constants.USER_TYPE_ADMIN);
        return modelAndView;
    }
    
    @Override
    public String getErrorPath() {
        return PATH;
    }
    
    /*@ExceptionHandler(value = Exception.class)  
    public ModelAndView handleException(Exception e){
    	return new ModelAndView("");
    }*/
}