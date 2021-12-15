//package com.hrms.helper;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import org.apache.commons.lang3.StringUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hrms.model.Login;
//import com.sun.istack.logging.Logger;
//
//
//public class CaptchaFilter extends UsernamePasswordAuthenticationFilter {
//
//	 private final String CAPTCHA_CHALLENGE_FIELD = "recaptcha_challenge_field";
//	    private final String CAPTCHA_RESPONSE_FIELD = "recaptcha_response_field";
//	    private final ReCaptchaImpl reCaptcha;
//	    private String privateKey;
////	    private final Logger log = LoggerFactory.getLogger(ReCaptchaAuthenticationFilter.class);
//	 
//	    public CaptchaFilter() {
//	        this.reCaptcha = new ReCaptchaImpl();
//	    }
//	 
//	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
//	    {
//	 
//	        String reCaptchaChallenge = request.getParameter(CAPTCHA_CHALLENGE_FIELD);
//	        String reCaptchaResponse = request.getParameter(CAPTCHA_RESPONSE_FIELD);
//	        String remoteAddress = request.getRemoteAddr();
//	 
//	        if (!StringUtils.isEmpty(reCaptchaChallenge))
//	        {
//	            log.debug("ReCaptcha Challenge not null");
//	            if (!StringUtils.isEmpty(reCaptchaResponse))
//	            {
//	                log.debug("ReCaptcha Answser not null, call ReCaptcha to verify it");
//	                ReCaptchaResponse reCaptchaCheck = reCaptcha.checkAnswer(remoteAddress, reCaptchaChallenge, reCaptchaResponse);
//	 
//	                if (reCaptchaCheck.isValid())
//	                {
//	                    log.debug("ReCaptcha answer is valid, attempt authentication");
//	                    return super.attemptAuthentication(request, response);
//	                }
//	                else
//	                {
//	                    this.reCaptchaError(request, response, "ReCaptcha failed : " + reCaptchaCheck.getErrorMessage());
//	                    return null;
//	                }
//	            }
//	            else
//	            {
//	                this.reCaptchaError(request, response, "ReCaptcha failed : empty answer");
//	                return null;
//	            }
//	 
//	        }
//	        else
//	        {
//	            return super.attemptAuthentication(request, response);
//	        }
//	    }
//	 
//	    private void reCaptchaError(HttpServletRequest request, HttpServletResponse response, String errorMsg)
//	    {
//	        log.error("ReCaptcha failed : " + errorMsg);
//	        try
//	        {
//	 
//	            RequestDispatcher dispatcher = request.getRequestDispatcher("/login?error=2");
//	 
//	            dispatcher.forward(request, response);
//	        }
//	        catch (ServletException e)
//	        {
//	            throw new AuthenticationServiceException("ReCaptcha failed : " + errorMsg);
//	        }
//	        catch (IOException e)
//	        {
//	            throw new AuthenticationServiceException("Recaptcha failed : " + errorMsg);
//	        }
//	    }
//	 
//	    public void setPrivateKey(String privateKey)
//	    {
//	        this.privateKey = privateKey;
//	    }
//	 
//	    public void afterPropertiesSet()
//	    {
//	        if (StringUtils.isEmpty(this.privateKey))
//	        {
//	            throw new IllegalArgumentException("The 'privateKey' should be set for the bean type 'ReCaptchaAuthenticationFilter'");
//	        }
//	        else
//	        {
//	            reCaptcha.setPrivateKey(this.privateKey);
//	        }
//	    }
//	}