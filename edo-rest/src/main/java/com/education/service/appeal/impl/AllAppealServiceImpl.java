package com.education.service.appeal.impl;

import com.education.service.appeal.AllAppealService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.constant.Constant;
import model.dto.AllAppealDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AllAppealServiceImpl implements AllAppealService {

    private static final String URL = "http://edo-service/api/service/allAppeals";

    private final RestTemplate restTemplate;

    /**
     * Отправляет запрос в edo-service на сохранение AllAppealDTO
     */

    @Override
    public AllAppealDto getAllAppeals(int lastUser, int numberOfUsersToDisplay) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = URIBuilderUtil.buildURI(Constant.EDO_SERVICE_NAME, "api/service/allAppeals").addParameter("lastUser", String.valueOf(lastUser)).addParameter("numberOfUsersToDisplay", String.valueOf(numberOfUsersToDisplay)).toString();
        return restTemplate.getForObject(uri, AllAppealDto.class);
    }
}