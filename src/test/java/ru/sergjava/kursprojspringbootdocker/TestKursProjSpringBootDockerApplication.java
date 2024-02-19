package ru.sergjava.kursprojspringbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestKursProjSpringBootDockerApplication {

    public static void main(String[] args) {
        SpringApplication.from(KursProjSpringBootDockerApplication::main).with(TestKursProjSpringBootDockerApplication.class).run(args);
    }

}
