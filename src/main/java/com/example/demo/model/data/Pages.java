package com.example.demo.model.data;

import com.example.demo.exception.InvalidParameterException;
import com.example.demo.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 *
 * 페이징 정보.
 */
@Getter
@Setter
public class Pages {

    @JsonIgnore
    private String url;
    @JsonIgnore
    private int page;
    @JsonIgnore
    private int lastPage;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String first;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String prev;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String last;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String next;


    /**
     * 페이징 정보 초기화
     * @param page 현재 페이지
     * @param lastPage 마지막 페이지
     * @param url url.
     */
    public Pages(final int page, final int lastPage, final String url) {
        if (lastPage < 1) {
            throw new InvalidParameterException("lastPage값은 1보다 작을 수 없습니다.");
        }
        if (page > lastPage) {
            throw new NotFoundException("잘못된 페이지 정보입니다.");
        }
        this.page = page;
        this.lastPage = lastPage;
        this.url = url;
        this.setPageInfo();
    }


    /**
     * 페이지 정보 세팅
     */
    private void setPageInfo() {
        if (this.page > this.lastPage) {
            throw new InvalidParameterException("잘못된 페이지 정보입니다.");
        }

        final String uriFormat = "?page=%d";

        if (this.page == 1) {
            if (this.lastPage > 1) {
                this.last = this.url + String.format(uriFormat, this.lastPage);
                this.next = this.url + String.format(uriFormat, this.getNextPage());
            }
        } else if (this.page < this.lastPage) {
            this.first = this.url + String.format(uriFormat, 1);
            this.prev = this.url + String.format(uriFormat, this.getPrevPage());
            this.last = this.url + String.format(uriFormat, this.lastPage);
            this.next = this.url + String.format(uriFormat, this.getNextPage());
        } else {
            this.first = this.url + String.format(uriFormat, 1);
            this.prev = this.url + String.format(uriFormat, this.getPrevPage());
        }
    }


    /**
     * 다음 페이지
     * @return 다음 페이지 값.
     */
    private int getNextPage() {
        final int nextPage = this.page + 1;
        return (nextPage > this.lastPage) ? this.lastPage : nextPage;
    }

    /**
     * 이전 페이지
     * @return 이전 페이지 값
     */
    private int getPrevPage() {
        final int prevPage = this.page - 1;
        return Math.max(prevPage, 1);
    }
}
