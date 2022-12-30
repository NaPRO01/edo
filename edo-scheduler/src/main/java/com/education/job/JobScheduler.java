package com.education.job;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@Getter
@Setter
public class JobScheduler {

    @Value("${integration.url.employee}")
    private String employeeUrl;
}
