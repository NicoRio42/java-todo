package com.javatodolist.controlers;

import com.javatodolist.models.Task;
import com.javatodolist.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    @Autowired
    private TaskRepository taskRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @RequestMapping("{id}")
    public Task get(@PathVariable Long id) {
        return taskRepository.getOne(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public Task create(@RequestBody final Task task){
        return taskRepository.saveAndFlush(task);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check for children records before deleting.
        taskRepository.deleteById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Task existingTask = taskRepository.getOne(id);
        BeanUtils.copyProperties(task, existingTask, "task_id");
        return taskRepository.saveAndFlush(existingTask);
    }

}
