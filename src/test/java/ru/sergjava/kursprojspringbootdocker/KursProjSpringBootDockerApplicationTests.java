package ru.sergjava.kursprojspringbootdocker;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.sergjava.kursprojspringbootdocker.model.Confirm;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Amount;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Transfer;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KursProjSpringBootDockerApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Container
    private final GenericContainer<?> kursapp = new GenericContainer<>("transferapp:1.0")
            .withExposedPorts(8080);
    //проверяем валидность ответов программы заданному API
    @Test
    void checkValidTransferResponseOKSchemeJSON() {
        Integer port = kursapp.getMappedPort(8080);
        Transfer transfer = new Transfer("23234532452345", "2334", "246", "23424523234234234", new Amount(24000, "USD"));
        ResponseEntity<String> entity = testRestTemplate.postForEntity("http://localhost:" +
                port + "/transfer", transfer, String.class);
        entity.getBody();
        System.out.println(entity);
        Assert.assertTrue(matchesJsonSchemaInClasspath("validPostResponseJSONSchema.json").matches(entity.getBody()));

    }

    @Test
    void checkValidResponseErrorSchemeJSON() {
        Integer port = kursapp.getMappedPort(8080);
        Transfer transfer = new Transfer("23234532452345", "2334", "246", "23424523234234234", new Amount(null, "USD"));
        ResponseEntity<String> entity = testRestTemplate.postForEntity("http://localhost:" +  port + "/transfer", transfer, String.class);
        entity.getBody();
        System.out.println(entity);
        Assert.assertTrue(matchesJsonSchemaInClasspath("errorJSONSchema.json").matches(entity.getBody()));

    }

    @Test
    void checkValidConfirmResponseOKSchemeJSON() {
        Integer port = kursapp.getMappedPort(8080);
        Transfer transfer = new Transfer("23234532452345", "2334", "246", "23424523234234234", new Amount(24000, "USD"));
        testRestTemplate.postForEntity("http://localhost:" + port + "/transfer", transfer, String.class);
        Confirm confirm = new Confirm("1", "2334");
        ResponseEntity<String> entity = testRestTemplate.postForEntity("http://localhost:" + port + "/confirmOperation", confirm, String.class);
        entity.getBody();
        System.out.println(entity);
        Assert.assertTrue(matchesJsonSchemaInClasspath("validPostResponseJSONSchema.json").matches(entity.getBody()));

    }

}
