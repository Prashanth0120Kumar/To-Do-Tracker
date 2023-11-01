package com.example.TaskService.Repository;

import com.example.TaskService.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<User,String>
{

}
