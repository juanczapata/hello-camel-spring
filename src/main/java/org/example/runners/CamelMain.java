package org.example.runners;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelMain
{
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        CamelContext camelContext = new SpringCamelContext(applicationContext);

        try {
            ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
            camelContext.start();
            producerTemplate.sendBody("activemq:test.queue","Hello Camel");
        } finally {
            camelContext.stop();
        }
    }
}
