package com.example.ReminderService.Repository;

import com.example.ReminderService.Domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReminderRepository extends MongoRepository<Task,Integer> {
}
