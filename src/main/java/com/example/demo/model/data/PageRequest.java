package com.example.demo.model.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 */
@Getter
@Setter
public class PageRequest {

    private int page = 1;
    private int size = 10;
    private Sort.Direction direction = Sort.Direction.ASC;
    private String[] sort = {"createdDate"};


    /**
     * 페이지 정보 세팅
     * @param page 페이지
     */
    public void setPage(final int page) {
        this.page = (page <= 0) ? 1 : page;
    }

    /**
     * Spring PageReqeust 변환.
     * @return Spring PageReqeust.
     */
    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(this.page - 1, this.size, this.direction, this.sort);
    }

}
