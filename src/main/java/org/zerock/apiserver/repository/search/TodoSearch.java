package org.zerock.apiserver.repository.search;

import org.springframework.data.domain.Page;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.domain.dto.PageRequestDto;

public interface TodoSearch {

   Page<Todo> search1(PageRequestDto pageRequestDto);
}
