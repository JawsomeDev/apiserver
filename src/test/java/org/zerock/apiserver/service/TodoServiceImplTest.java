package org.zerock.apiserver.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.zerock.apiserver.domain.dto.PageRequestDto;
import org.zerock.apiserver.domain.dto.TodoDto;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class TodoServiceImplTest {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet(){

        Long tno = 50L;

        log.info(String.valueOf(todoService.get(tno)));
    }

    @Test
    public void testRegister(){

        TodoDto todoDto = TodoDto.builder()
                .title("Title...")
                .content("Content....")
                .dueDate(LocalDate.now())
                .build();

        log.info(todoService.register(todoDto).toString());
    }

    @Test
    public void testGetList(){
        PageRequestDto pageRequestDto = PageRequestDto.builder().page(11).build();

        log.info(todoService.getList(pageRequestDto).toString());
    }
}