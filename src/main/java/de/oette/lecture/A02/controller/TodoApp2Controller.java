package de.oette.lecture.A02.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController //Spring scannt diese Component und sucht nach Endpunkten (auch wichtig für Swagger)
@RequestMapping(value = "/todo")
public class TodoApp2Controller {

    private final Map<Integer, TaskDto> todos = new HashMap<>();

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping//äquivalent zu oben
    public Integer add(@RequestBody TaskDto task) {
        Integer newPosition = todos.keySet().stream()
                .reduce(Integer::max)
                .orElse(0) + 1;

        todos.put(newPosition, task);
        return newPosition;
    }
    //curl -XPOST localhost:8090/todo?todo=hi

    @PutMapping
    public void update(@RequestBody TaskDto todo,
                       @RequestParam Integer position) {
        TaskDto currentTask = todos.get(position);
        currentTask.description = todo.description;
        currentTask.dueAt = todo.dueAt;
        currentTask.version++;

        todos.put(position, currentTask);
    }

    @GetMapping
    public Collection<TaskDto> get() {
        return todos.values();
    }

    @DeleteMapping
    public void delete(Integer position) {
        todos.remove(position);
    }
}
