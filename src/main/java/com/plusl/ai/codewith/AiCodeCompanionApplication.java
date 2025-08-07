package com.plusl.ai.codewith;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.Objects;

@SpringBootApplication(scanBasePackages = {"com.plusl.ai.codewith"})
@EnableConfigurationProperties
@Slf4j
public class AiCodeCompanionApplication {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext application = SpringApplication.run(AiCodeCompanionApplication.class, args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		final String applicationName = Objects.requireNonNull(env.getProperty("spring.application.name")).toUpperCase();
		String path = Objects.toString(env.getProperty("server.servlet.context-path"), "/");
		log.info("\n----------------------------------------------------------\n\t" +
				"Application " + applicationName + " is running! Access URLs:\n\t" +
				"External: \thttp://" + ip + ":" + port + path + "\n\t" +
				"External Swagger: \thttp://" + ip + ":" + port + path + "doc.html\n" +
				"----------------------------------------------------------");
	}

}
