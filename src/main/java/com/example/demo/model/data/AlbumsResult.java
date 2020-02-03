package com.example.demo.model.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 *
 * 앨범 조회 결과 객체.
 */
@Getter
@Setter
public class AlbumsResult {

    /** 결과 코드 */
    private int statusCode = HttpStatus.OK.value();

    /** 페이지 정보 */
    private Pages pages;

    /** 앨범 목록 */
    private List<AlbumResult> albums;

}
