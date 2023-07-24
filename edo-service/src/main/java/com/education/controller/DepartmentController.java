package com.education.controller;

import com.education.service.department.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.DepartmentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Usolkin Dmitry
 */
@RestController
@RequestMapping("api/service/department")
@AllArgsConstructor
@Log4j2
@Tag(name = "Rest- контроллер для работы с департаментами")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Предоставление департамента по индентификатору")
    @GetMapping(value = "/{id}")
    private ResponseEntity<DepartmentDto> getDepartment(@PathVariable(name = "id") Long id) {
        log.info("send a response with the department of the assigned id");
        var departmentDto = departmentService.findById(id);
        log.info("The operation was successful, we got the department by id ={}", id);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @Operation(summary = "Предоставление департамента без архивации по идентификатору")
    @GetMapping("/notArchived/{id}")
    private ResponseEntity<DepartmentDto> getDepartmentNotArchived(@PathVariable(name = "id") Long id) {
        log.info("send a response with the department not archived of the assigned ID");
        var depNotArc = departmentService.findByIdNotArchived(id);
        log.info("The operation was successful, they got the non-archived department by id ={}", id);
        return new ResponseEntity<>(depNotArc, HttpStatus.OK);
    }

    @Operation(summary = "Предоставление дапартаментов без архивации по назначеным идентификаторам")
    @GetMapping("/notArchivedAll/{ids}")
    private ResponseEntity<Collection<DepartmentDto>> getDepartmentsNotArchived(@PathVariable(name = "ids") String ids) {
        log.info("send a response with the departments not archived of the assigned IDs");
        var depsNotArc = departmentService.findByAllIdNotArchived(ids);
        log.info("The operation was successful, they got the non-archived department by id ={}", ids);
        return new ResponseEntity<>(depsNotArc, HttpStatus.OK);
    }

    @Operation(summary = "Предоставление дапартаментов  по назначеным идентификаторам")
    @GetMapping("/all/{ids}")
    private ResponseEntity<Collection<DepartmentDto>> getDepartments(@PathVariable(name = "ids") String ids) {
        log.info("send a response with the departments of the assigned IDs");
        var deps = departmentService.findByAllId(ids);
        log.info("The operation was successful, we got the departments by id ={}", ids);
        return new ResponseEntity<>(deps, HttpStatus.OK);
    }

    @Operation(summary = "Добавлнение нового департамента")
    @PostMapping
    private String saveDepartment(@RequestBody DepartmentDto departmentDto) {
        log.info("Starting the save operation");
        departmentService.save(departmentDto);
        log.info("saving the department and displaying its full name in the response");
        return "Добавили " + departmentDto.getFullName();
    }

    @Operation(summary = "Архивация департамента с занесением времени архивации")
    @PostMapping("/{id}")
    private String deleteUser(@PathVariable(name = "id") Long id) {
        log.info("Starting the archiving operation");
        departmentService.removeToArchived(id);
        log.info("Archiving a department");
        return "Удалил " + id;
    }

}
