package com.dev.cry;

import com.dev.cry.Entity.Role;
import com.dev.cry.Service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CryApplication {
	final static Logger logger = Logger.getLogger(CryApplication.class);


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CryApplication.class, args);
		RoleService bean = context.getBean(RoleService.class);
		if(bean.findRoleByName("lv1")== null){
		Role role = new Role();
		role.setName("lv1");
		bean.save(role);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}
