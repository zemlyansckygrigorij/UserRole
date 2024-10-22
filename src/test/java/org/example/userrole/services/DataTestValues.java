package org.example.userrole.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Component
@ContextConfiguration
@PropertySource(
        "classpath:testValues.properties")
public class DataTestValues {
    @Value("${test.localhost}")
    public String localhost;
    @Value("${test.api.all}")
    public String api;
    @Value("${test.api.role}")
    public String role;
    @Value("${test.api.full.size}")
    public int fullSize;
    @Value("${test.api.after.delete.size}")
    public int cuttedSize;

    @Value("${test.user.id}")
    public int userId;
    @Value("${test.role.id}")
    public int roleId;
}
