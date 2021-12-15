package com.hrms.helper;

public class GlobalCaptcha {
	
	public static String captcha = "";
	public static void setCaptcha(String captcha) {
		
		GlobalCaptcha.captcha = captcha;
		
	}
	
	public boolean verifyCaptcha(String captcha) {
		
		if(captcha.equals(GlobalCaptcha.captcha)) {
			return true;
		}
		return false;
	}

}
