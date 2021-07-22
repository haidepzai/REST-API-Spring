package de.oette.lecture.A01.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController //Spring scannt diese Component und sucht nach Endpunkten (auch wichtig für Swagger)
@RequestMapping(value = "/todo")
public class TodoAppController {

    private final Map<Integer, String> todos = new HashMap<>();

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping//äquivalent zu oben
    public Integer add(@RequestParam(value = "todo", required = false)  String task) {
        Integer newPosition = todos.keySet().stream().reduce(Integer::max).orElse(0) + 1;
        todos.put(newPosition, task);
        return newPosition;
    }
    //curl -XPOST localhost:8090/todo?todo=hi

    @PutMapping
    public void update(Integer position, String todo) {
        todos.put(position, todo);
    }

    @GetMapping
    public String get() {
        return todos.entrySet().stream().map(en -> String.format("%s %s - ", en.getKey(), en.getValue()))
                .collect(Collectors.joining());
    }

    @DeleteMapping
    public void delete(Integer position) {
        todos.remove(position);
    }
}
