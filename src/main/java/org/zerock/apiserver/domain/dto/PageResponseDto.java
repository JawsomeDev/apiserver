package org.zerock.apiserver.domain.dto;


import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDto<E> {

    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageRequestDto pageRequestDto;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, currentPage;

    @Builder(builderMethodName = "withAll")
    public PageResponseDto(List<E> dtoList, PageRequestDto pageRequestDto, long totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalCount = (int)totalCount;

        //끝페이지 end
        int end = (int) ((Math.ceil(pageRequestDto.getPage() / 10.0)) * 10);

        int start = end - 9;

        // 진짜 마지막
        int last = (int) Math.ceil(totalCount / (double) pageRequestDto.getSize());

        end = end > last ? last : end;

        this.prev = start > 1;

        this.next = totalCount > (end * pageRequestDto.getSize());

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;

        this.nextPage = next ? end + 1 : 0;

        this.currentPage = pageRequestDto.getPage();

        this.totalPage = 10;

    }

}
