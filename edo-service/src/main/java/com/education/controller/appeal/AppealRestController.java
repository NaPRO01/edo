package com.education.controller.appeal;

import com.education.service.appeal.AppealService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AppealDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Collection;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/service/appeal")
public class AppealRestController {

    private AppealService appealService;

    @ApiOperation(value = "В строке таблицы Appeal заполняет поле archivedDate", notes = "Строка в Appeal должна существовать")
    @PatchMapping(value = "/move/{id}")
    public ResponseEntity<AppealDto> moveToArchive(@PathVariable Long id) throws URISyntaxException {
        log.info("Adding archived date {} in Appeal with id: {}", ZonedDateTime.now(), id);
        appealService.moveToArchive(id);
        log.info("Moving appeal with id: {} to archive is success!", id);
        return new ResponseEntity<>(appealService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Находит все строки таблицы Appeal с полем acrhivedDate = null", notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/findAllNotArchived", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AppealDto>> findAllNotArchived() throws URISyntaxException {
        log.info("Getting from database all appeals with field acrhivedDate = null");
        var appealDtoCollection = appealService.findAllNotArchived();
        log.info("Appeals: {}", appealDtoCollection);
        return new ResponseEntity<>(appealDtoCollection, HttpStatus.OK);
    }

    @ApiOperation(value = "Находит строку таблицы Appeal c полем acrhivedDate = null, по заданному id", notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/findByIdNotArchived/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> findByIdNotArchived(@PathVariable Long id) throws URISyntaxException {
        log.info("Getting from database appeal with field acrhivedDate = null, with id: {}", id);
        var appealDto = appealService.findByIdNotArchived(id);
        log.info("Response from database: {}", appealDto);
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Находит все строки таблицы Appeal", notes = "Строка в Appeal должна существовать")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AppealDto>> getAllAppeal() throws URISyntaxException {
        log.info("Getting from database all appeals");
        var appealDtoCollection = appealService.findAll();
        log.info("Response from database: {}", appealDtoCollection);
        return new ResponseEntity<>(appealDtoCollection, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаляет строку таблицы Appeal по id", notes = "Строка в Appeal должна существовать")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteAppeal(@PathVariable(value = "id") Long id) throws URISyntaxException {
        log.info("Deleting from database appeal with id: {}", id);
        appealService.delete(id);
        log.info("Deleting from database appeal with id: {}, success!", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавляет новую строку таблицы Appeal")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> saveAppeal(@RequestBody AppealDto appealDto) throws URISyntaxException {
        log.info("Creating appeal");
        appealService.save(appealDto);
        log.info("Creating appeal {}, success!", appealDto.getAnnotation());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Находит строку таблицы Appeal по id", notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> getAppealById(@PathVariable Long id) throws URISyntaxException {
        log.info("Getting from database appeal with id: {}", id);
        var appealDto = appealService.findById(id);
        log.info("Response from database: {}", appealDto);
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }
}
