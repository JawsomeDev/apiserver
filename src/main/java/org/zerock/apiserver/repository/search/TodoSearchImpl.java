package org.zerock.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.domain.dto.PageRequestDto;

import java.util.List;

@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {


    public TodoSearchImpl() {
        super(Todo.class);
    }


    @Override
    public Page<Todo> search1(PageRequestDto pageRequestDto) {

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() - 1 ,
                pageRequestDto.getSize(),
                Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        List<Todo> list = query.fetch();

        long total = query.fetchCount();

        return new PageImpl<>(list, pageable, total);
    }
}
