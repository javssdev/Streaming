package com.tmx.dashboard;

import java.io.FileReader;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tmx.dashboard.utils.Cs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(scanBasePackages={"com.tmx.dashboard.*"})
public class DashboardApplication {

	public static void main(String[] args) {
		if(!Cs.DEVELOPMENT)
			System.setProperty("spring.jpa.properties.hibernate.default_schema", getProperty("datasource.schema"));

		SpringApplication.run(DashboardApplication.class, args);
	}

	public static String getProperty(String name_property) {
		String r = "";
		try {
			Properties p = new Properties();
			String PATH_CC = System.getProperty("user.dir") + "/bin/zntwasconfig/application.properties";
			p.load(new FileReader(PATH_CC));
			r = p.getProperty(name_property);
		} catch (Exception e) {
			log.warn(Cs.EXCEPTION, "Error FileProperties.getPropertiy : " +e.getMessage());
		}
		return r;
	}
}
