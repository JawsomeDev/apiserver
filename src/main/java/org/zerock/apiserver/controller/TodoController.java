package org.zerock.apiserver.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.domain.dto.PageRequestDto;
import org.zerock.apiserver.domain.dto.PageResponseDto;
import org.zerock.apiserver.domain.dto.TodoDto;
import org.zerock.apiserver.service.TodoService;

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

}
