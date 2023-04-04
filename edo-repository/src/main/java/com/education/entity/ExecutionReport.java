package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.enum_.Status;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "execution_report")
public class ExecutionReport extends BaseEntity {
    /**
     * Идентификатор отчета
     */
    @Column(name = "id")
    private Long id;

    /**
     * Дата создания отчета
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Коментарий к отчету
     */
    @Column(name = "execution_comment")
    private String executionComment;

    /**
     * Статус выполнения задачи
     */
    @Column(name = "status")
    private Status status;
}
