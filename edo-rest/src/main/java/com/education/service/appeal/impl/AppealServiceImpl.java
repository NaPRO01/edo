package com.education.service.appeal.impl;

import com.education.service.appeal.AppealService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.dto.AppealDto;
import model.dto.FilePoolDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import static model.constant.Constant.EDO_SERVICE_NAME;

@Service
@AllArgsConstructor
public class AppealServiceImpl implements AppealService {

    private static final String SERVICE_URL = "api/service/appeal";

    private final RestTemplate restTemplate;

    /**
     * Отправляет запрос в edo-service на сохранение AppealDTO
     */
    @Override
    public AppealDto save(AppealDto appealDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, SERVICE_URL).toString();
        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(appealDto, headers), AppealDto.class).getBody();
    }

    @Override
    public void moveToArchive(Long id) {
        restTemplate.put(SERVICE_URL + "/move/" + id, AppealDto.class);
    }

    @Override
    public AppealDto findById(Long id) {
        String uri = getUri("/api/service/appeal/") + id;
        return restTemplate.getForObject(uri, AppealDto.class);
    }

    @Override
    public AppealDto upload(Long id, FilePoolDto file) {
        String url = "/api/service/upload";

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", id);
        parameters.add("file", file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<AppealDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AppealDto.class);

        return response.getBody();
    }


    private static String getUri(String path) {
        return URIBuilderUtil.buildURI(EDO_SERVICE_NAME, path)
                .toString();
    }
}
