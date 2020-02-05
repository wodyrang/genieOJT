package com.example.demo.controller;

import com.example.demo.model.data.AlbumJson;
import com.example.demo.model.data.AlbumsResult;
import com.example.demo.model.data.PageRequest;
import com.example.demo.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AlbumController {

    /** 앨범 처리 관련 Service */
    private final AlbumService albumService;


    /**
     * 앨범 목록 조회.
     * @param pageable 페이지 정보.
     * @return 앨범 목록.
     */
    @GetMapping(value = "/albums")
    public ResponseEntity<AlbumsResult> searchAlbums(final PageRequest pageable) {
        final AlbumsResult albumsResult = this.albumService.findAlbumsResult(pageable);
        return new ResponseEntity<>(albumsResult, HttpStatus.OK) ;
    }


    /**
     * 앨범 정보 등록.
     * @param albumJsonList 앨범 정보
     * @return 결과
     */
    @PostMapping(value = "/albums")
    public ResponseEntity<String> saveAlbum(@RequestBody final List<AlbumJson> albumJsonList) {
        this.albumService.saveAllAlbumInfo(albumJsonList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
