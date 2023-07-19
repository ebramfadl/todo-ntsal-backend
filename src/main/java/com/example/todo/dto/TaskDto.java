package com.example.todo.dto;


import com.example.todo.enums.Priority;
import com.example.todo.enums.RepetitionType;
import com.example.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TaskDto {

    private String tag;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime dateCreated;
    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;
    @Enumerated(value = EnumType.STRING)
    private Priority priority;
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;
    private String categoryTitle;
}
