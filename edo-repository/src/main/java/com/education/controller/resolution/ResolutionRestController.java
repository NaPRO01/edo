package com.education.controller.resolution;

import com.education.service.resolution.ResolutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.ResolutionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.education.mapper.ResolutionMapper.RESOLUTION_MAPPER;

/**
 * REST контроллер для отправки запросов к БД
 */
@RestController
@AllArgsConstructor
@Log4j2
@Tag(name = "Rest- контроллер для работы с резолюциями")
@RequestMapping("api/repository/resolution")
public class ResolutionRestController {

    /**
     * Служит для связи с сервисом ResolutionService
     */
    private ResolutionService resolutionService;

    @Operation(summary = "Добавление резолюции")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResolutionDto> saveResolution(@RequestBody ResolutionDto resolutionDto) {
        log.info("POST request has been sent");
        var resolution = resolutionService.save(RESOLUTION_MAPPER.toEntity(resolutionDto));
        log.info("{} has has been added", resolutionDto);
        return new ResponseEntity<>(RESOLUTION_MAPPER.toDto(resolutionService.findById(resolution.getId())), HttpStatus.CREATED);
    }

    @Operation(summary = "Перемещение резолюции в архив")
    @PatchMapping(value = "/move/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResolutionDto> moveToArchive(@PathVariable Long id) {
        log.info("PATCH request has been sent");
        resolutionService.moveToArchive(id);
        var resolutionDto = RESOLUTION_MAPPER.toDto(resolutionService.findById(id));
        log.info("Resolution with id = {} has been moved to archive", id);
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @Operation(summary = "Поиск резолюции по id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResolutionDto> getResolutionById(@PathVariable Long id) {
        log.info("GET request to search for resolution with id = {} has been sent", id);
        var resolutionDto = RESOLUTION_MAPPER.toDto(resolutionService.findById(id));
        log.info("Resolution with id = {} was found", id);
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @Operation(summary = "Поиск всех резолюций")
    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Collection<ResolutionDto>> findAll(@PathVariable Collection <Long> id) {
        log.info("GET request to search for all resolutions has been sent");
        var resolutionDto = RESOLUTION_MAPPER.toDto(resolutionService.findAllById(id));
        log.info("Resolutions was found");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @Operation(summary = "Поиск не архивированной резолюции по id")
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<ResolutionDto> findByIdNotArchived(@PathVariable Long id) {
        log.info("GET request to search for an unarchived resolution has been sent");
        var resolutionDto = RESOLUTION_MAPPER.toDto(resolutionService.findByIdNotArchived(id));
        log.info("Resolution with id = {} was found", id);
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @Operation(summary = "Поиск всех не архивированных резолюций")
    @GetMapping(value = "/notArchived/all/{id}")
    public ResponseEntity<Collection<ResolutionDto>> findAllByIdNotArchived(@PathVariable Collection<Long> id) {
        log.info("GET request to search for all unarchived resolutions has been sent");
        var resolutionDto = RESOLUTION_MAPPER.toDto(resolutionService.findAllByIdNotArchived(id));
        log.info("Resolutions was found");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }


}
