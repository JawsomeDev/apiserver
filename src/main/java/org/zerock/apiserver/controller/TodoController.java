package org.zerock.apiserver.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.domain.dto.PageRequestDto;
import org.zerock.apiserver.domain.dto.PageResponseDto;
import org.zerock.apiserver.domain.dto.TodoDto;
import org.zerock.apiserver.service.TodoService;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDto get(@PathVariable("tno") Long tno){

        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDto<TodoDto> list(PageRequestDto pageRequestDto){

        log.info("list..........." + pageRequestDto);

        return todoService.getList(pageRequestDto);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDto dto){

        Long tno = todoService.register(dto);

        return Map.of("TNO", tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno") Long tno,
                                      @RequestBody TodoDto todoDto){
        todoDto.setTno(tno);
        todoService.modify(todoDto);

        return Map.of("RESULT", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno){

        todoService.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }

}
