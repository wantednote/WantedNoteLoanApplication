package com.wn.loanapp.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wn.loanapp.constants.Constants;
import com.wn.loanapp.util.Format;
@Controller
@SessionAttributes({Constants.USERDETAILSFORMSESSION})
public class BaseController {


	private HttpServletRequest request;

	private HttpServletResponse response;

	/**
	 * log variable.
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Is Debug Enabled variable.
	 */
	protected boolean isDebugEnabled = log.isDebugEnabled();
	/**
	 * Is Warn Enabled variable.
	 */
	protected boolean isWarnEnabled = log.isWarnEnabled();
	/**
	 * Is Error Enabled variable.
	 */
	protected boolean isErrorEnabled = log.isErrorEnabled();
	/**
	 * Is Info Enabled variable.
	 */
	protected boolean isInfoEnabled = log.isInfoEnabled();
	/**
	 * Error Messages variable.
	 */
	protected List<String> errorMessages = null;
	/**
	 * Success Messages variable.
	 */
	protected List<String> successMessages = null;
	/**
	 * Messages variable.
	 */
	protected MessageSourceAccessor messages;

	String pageType;
	/**
	 * 
	 */
	public BaseController() {
		if (null != request) {
			if (null == request.getSession(false)) {
				try {
					response.sendRedirect("/sessionExpired.jsp");
				} catch (IOException e) {
					log.error("Exception occurred :::: ", e);
				}
			} else {
				try {
					response.sendRedirect("/showHomeRoleBased.html");
				} catch (IOException e) {
					log.error("Exception occurred :::: ", e);
				}
			}
		}
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

	/**
	 * @author archit.sud
	 * Injected messageSource
	 * @param messageSource
	 */
	@Autowired
	public void setMessages(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	/**
	 * @author mithun.mondal
	 * This method adds error message to the list which
	 * can be used to show on jsp
	 * @param errorMessage
	 */
	public void saveErrorMessage(String errorMessage) {
		if (errorMessages == null) {
			errorMessages = new ArrayList<String>();
		}
		errorMessages.add(errorMessage);
	}

	/**
	 * @author mithun.mondal
	 * This method adds success message to the list which can be used to show on
	 * jsp
	 * @param successMessage
	 */
	public void saveSuccessMessage(String successMessage) {
		if (successMessages == null) {
			successMessages = new ArrayList<String>();
		}
		successMessages.add(successMessage);
	}

	/**
	 * @author mithun.mondal
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * @param msgKey
	 * @param locale
	 * the current locale
	 * @return String
	 */
	public String getText(String msgKey, Locale locale) {
		return messages.getMessage(msgKey, locale);
	}

	/**
	 * @author mithun.mondal
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * @param msgKey - msg key
	 * @param arg - arg
	 * @param locale as Locale
	 * the current locale
	 * @return String
	 */
	public String getText(String msgKey, String arg, Locale locale) {
		return getText(msgKey, new Object[] { arg }, locale);
	}

	/**
	 * @author mithun.mondal
	 * Convenience method for getting a i18n key's value with arguments.
	 * @param msgKey
	 * @param args
	 * @param locale
	 * the current locale
	 * @return String
	 */
	public String getText(String msgKey, Object[] args, Locale locale) {
		return messages.getMessage(msgKey, args, locale);
	}

	/**
	 * @author mithun.mondal
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * @param msgKey
	 * @return String
	 */
	public String getText(String msgKey) {
		return messages.getMessage(msgKey);
	}

	/**
	 * @author mithun.mondal
	 * This method add success or error messages to the model object. These
	 * objects are used on the jsp to show success or error message
	 * @param modelAndView
	 */
	public void addSuccessOrErrorMessageToModel(ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
		if (successMessages != null) {
			redirectAttributes.addFlashAttribute(Constants.MESSAGES_KEY, successMessages);
		}
		if (errorMessages != null) {
			redirectAttributes.addFlashAttribute(Constants.ERROR_KEY, errorMessages);
		}
		successMessages = null;
		errorMessages = null;
	}

	/**
	 * @author mithun.mondal
	 * This method add success or error messages to the model object. These
	 * objects are used on the jsp to show success or error message
	 * @param modelAndView
	 */
	public void addSuccessOrErrorMessageToModel(ModelAndView modelAndView) {
		if (successMessages != null) {
			modelAndView.addObject(Constants.MESSAGES_KEY, successMessages);
		}
	
		if (errorMessages != null) {
			modelAndView.addObject(Constants.ERROR_KEY, errorMessages);
		}
		successMessages = null;
		errorMessages = null;
	}

	/**
	 * @author mithun.mondal
	 * Convenience method for getting a i18n key's value with arguments.
	 * @param msgKey
	 * @param args
	 * @return String
	 */
	public String getText(String msgKey, Object[] args) {
		return messages.getMessage(msgKey, args);
	}

	/**@author mithun.mondal
	 * @return the modelAndView which navigate the user to Login page.
	 */
	public ModelAndView redirectToLoginPage(String userType) {
		ModelAndView modelAndView = new ModelAndView("login");
		if(Format.isStringNotEmptyAndNotNull(userType)){
			if(userType.startsWith("/admin")){
				userType = Constants.USER_TYPE_ADMIN;
			}else if(userType.startsWith("/partner")){
				userType = Constants.USER_TYPE_PARTNER;
			}else if(userType.startsWith("/employee")){
				userType = Constants.USER_TYPE_ADMIN;
			}
		}
		modelAndView.addObject("userType", userType);
		return modelAndView; 
	}
}
