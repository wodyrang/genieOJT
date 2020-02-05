package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(new File("sample.json"), )

    }

}
