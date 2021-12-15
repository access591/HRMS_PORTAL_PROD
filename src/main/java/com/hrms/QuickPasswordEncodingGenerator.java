package com.hrms;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class QuickPasswordEncodingGenerator {
	/**
     * @param args
     */
    public static void main(String[] args) {
            String password = "abc125";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           
            String x=passwordEncoder.encode(password);
            
            System.out.println(passwordEncoder.encode(password));
            System.out.println("decode"+passwordEncoder.matches(password, x));
    }
  
}
