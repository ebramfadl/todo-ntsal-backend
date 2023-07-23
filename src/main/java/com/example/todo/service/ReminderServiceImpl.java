package com.example.todo.service;


import com.example.todo.dao.repo.ReminderRepo;
import com.example.todo.dto.ReminderDto;
import com.example.todo.dto.ReminderPostDto;
import com.example.todo.model.Category;
import com.example.todo.model.Reminder;
import com.example.todo.model.Task;
import com.example.todo.transformer.mapper.ReminderMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class ReminderServiceImpl implements ReminderService{

    @Autowired
    private ReminderRepo repo;
    @Autowired
    private ReminderMapper mapper;

    @Override
    @Transactional
    public ReminderDto create(ReminderPostDto reminderPostDto) {
        Reminder reminder = getMapper().dtoToEntity(reminderPostDto);
        getRepo().save(reminder);
        return getMapper().entityToDto(reminder);
    }

    @Override
    @Transactional
    public ReminderDto deleteReminder(Long reminderId) {
        Reminder reminder = getRepo().findById(reminderId).get();
        reminder.setLastModifiedDate(LocalDateTime.now());
        getRepo().deleteById(reminderId);
        return getMapper().entityToDto(reminder);
    }

    @Override
    @Transactional
    public ReminderDto update(Long reminderId, ReminderPostDto reminderPostDto) {
        Optional<Reminder> optional = getRepo().findById(reminderId);
        Reminder reminder;
        if(!optional.isPresent()){
            throw new IllegalStateException("Reminder does not exist!");
        }
        else {
            reminder = optional.get();
            if (reminderPostDto.getTitle() != null)
                reminder.setTitle(reminderPostDto.getTitle());
            if (reminderPostDto.getDescription() != null)
                reminder.setDescription(reminderPostDto.getDescription());
            if (reminderPostDto.getRepetitionType() != null)
                reminder.setRepetitionType(reminderPostDto.getRepetitionType());
            if (reminderPostDto.getDueDate() != null)
                reminder.setDueDate(reminderPostDto.getDueDate());

            reminder.setLastModifiedDate(LocalDateTime.now());
        }
        return getMapper().entityToDto(reminder);
    }

    @Override
    public ReminderDto viewReminder(Long reminderId) {
        Optional<Reminder> optional = getRepo().findById(reminderId);
        if (!optional.isPresent()){
            throw new IllegalStateException("Reminder does not exist");
        }
        Reminder reminder = optional.get();
        return getMapper().entityToDto(reminder);
    }

    @Override
    public List<ReminderDto> viewAllReminders(Long userId) {
        List<Reminder> reminders = getRepo().findUserReminders(userId);
        return reminders.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}