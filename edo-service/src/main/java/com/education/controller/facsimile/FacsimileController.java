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

@Log4j2
@RestController
@RequestMapping("/api/service/facsimile")
@AllArgsConstructor
@Tag(name = "Контроллер для работы с FacsimileDto")
public class FacsimileController  {

    private FacsimileService facsimileService;


    @Operation(summary = "Принимает facsimile, отправляет на edo-repo")
    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacsimileDto> save(@RequestBody FacsimileDto facsimileDto){
        var save =  facsimileService.save(facsimileDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Operation(summary = "Производит поиск Facsimile по EmployeeId")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacsimileDto> findFacsimileByEmployeeId(@PathVariable Long id) {
        log.info("Поиск факсимиле по id Employee");
        var facsimileDto = facsimileService.findFacsimileByEmployeeId(id);
        log.info("Операция прошла успешно, был получен факсимиле по EmployeeId = {}", id);
        return new ResponseEntity<>(facsimileDto, HttpStatus.OK);
    }

}
