package ru.bravery_and_stupidity.secretOfSatan;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  private static final Logger logger = Logger.getLogger(Application.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    SpringApplication.run(Application.class, args);
    logger.info("Secret of satan was running");
  }
}
