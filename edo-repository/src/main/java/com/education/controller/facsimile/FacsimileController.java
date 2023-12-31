package com.education.controller.facsimile;


import com.education.service.facsimile.FacsimileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.FacsimileDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.education.mapper.FacsimileMapper.FACSIMILE_MAPPER;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api/repository/facsimile")
@Tag(name = "Контроллер для работы с FacsimileDto")
public class FacsimileController {

    private FacsimileService facsimileService;

    @Operation(summary = "Добавляет новую строку таблицы Facsimile")
    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacsimileDto> save(@RequestBody FacsimileDto facsimileDto){
        log.info("Создание facsimile");
        var save = facsimileService.save(FACSIMILE_MAPPER.toEntity(facsimileDto));
        log.info("facsimile был создан! {}",save);
        return new ResponseEntity<>(FACSIMILE_MAPPER.toDto(facsimileService.findById(save.getId())), HttpStatus.CREATED);
    }

    @Operation(summary = "Производит поиск Facsimile по EmployeeId")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacsimileDto> findFacsimileByEmployeeId(@PathVariable Long id) {
        log.info("Поиск факсимиле по id Employee");
        var facsimileDto = FACSIMILE_MAPPER.toDto(facsimileService.findFacsimileByEmployeeId(id));
        log.info("Операция прошла успешно, был получен факсимиле по EmployeeId = {}", id);
        return new ResponseEntity<>(facsimileDto, HttpStatus.OK);
    }
}
