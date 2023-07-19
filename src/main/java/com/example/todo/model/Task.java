package com.example.todo.model;


import com.example.todo.enums.DegreeOfImportance;
import com.example.todo.enums.RepetitionType;
import com.example.todo.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @SequenceGenerator(sequenceName = "task_sequence",allocationSize = 1,name = "task_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;

    private String title;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deadline;
    private LocalDateTime dateCreated;
    private LocalDateTime lastModifiedDate;

    private boolean isFavorite;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @Enumerated(value = EnumType.STRING)
    private RepetitionType repetitionType;

    @Enumerated(value = EnumType.STRING)
    private DegreeOfImportance degreeOfImportance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task")
    private List<Reminder> reminders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
