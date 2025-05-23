package org.zerock.apiserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1(){

        assertNotNull(todoRepository);

        log.info(todoRepository.getClass().getName());

    }

    @Test
    public void testInsert(){

        for (int i = 0 ; i < 100 ; i++){

            Todo todo = Todo.builder()
                    .title("title" + i)
                    .content("content...." + i)
                    .dueDate(LocalDate.of(2023, 12, 30))
                    .build();

            Todo result = todoRepository.save(todo);

            log.info(result.toString());
        }

    }

    @Test
    public void testRead(){

        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        log.info(todo.toString());

    }

    @Test
    public void testUpdate(){

        // 먼저 로딩 하고 엔티티 객체를 변경
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        todo.changeContent("updated content");
        todo.changeTitle("updated title");
        todo.changeComplete(true);

        todoRepository.save(todo);
    }

    @Test
    public void testPaging(){

        // 페이지 번호는 0부터
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(String.valueOf(result.getTotalElements()));

        log.info(result.getContent().toString());
    }

//    @Test
//    public void testSearch1(){
//
//        todoRepository.search1();
//    }
}