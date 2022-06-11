package com.dev.cry;

import com.dev.cry.Service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryApplication {
	final static Logger logger = Logger.getLogger(CryApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CryApplication.class, args);
	}

}
