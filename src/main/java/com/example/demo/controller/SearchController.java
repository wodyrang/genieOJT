package com.example.demo.controller;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.data.Pages;
import com.example.demo.model.data.SearchResult;
import com.example.demo.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 검색 처리 Controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    /** 검색 관련 Service. */
    private final SearchService searchService;


    /**
     * 문자열로 앨범/곡을 검색해서 제목이 검색어를 포함하는 앨범과 곡을 찾는 API
     * @param title (필수) 검색어
     * @param locale (필수) 검색을 요청하는 사용자의 지역
     * @return 앨범/노래 목록.
     */
    @GetMapping(value = "/search")
    public ResponseEntity<SearchResult> search(@RequestParam final String title
            , @RequestParam final String locale) {

        final LocaleType localeType = LocaleType.findType(locale);
        if (localeType == null) {
            throw new BadRequestException("검색 지역을 입력해주세요.");
        }

        // 노래 검색
        final SearchResult searchResult = this.searchService.searchAlbum(title, localeType);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }


}
