package com.example.demo.controller;

import com.example.demo.exception.DataValidationException;
import com.example.demo.model.data.*;
import com.example.demo.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PlayListController {

    /** play list Service. */
    private final PlayListService playListService;

    /** Play list 생성시 validator */
    @Resource(name = "createPlayListValidator")
    private final Validator createPlayListValidator;


    /**
     * PlayList 목록 조회.
     * @return PlayList 목록
     */
    @GetMapping(value = "/playList/{userId}")
    public ResponseEntity<ApiResult<List<PlayGroupListResult>>> getPlayList(@PathVariable final Long userId) {
        final List<PlayGroupListResult> list = this.playListService.getAllPlayGroupList(userId);
        return new ResponseEntity<>(new ApiResult<>(HttpStatus.OK, list), HttpStatus.OK);
    }


    /**
     * PlayList 에 등록된 모든 노래 조회.
     * @param userId 사용자 ID
     * @param playGroupId 선택한 PlayList
     * @return 노래 목록.
     */
    @GetMapping(value = "/playList/{userId}/{playListId}")
    public ResponseEntity<ApiResult<List<PlayGroupSongResult>>> getPlayListSong(@PathVariable final Long userId
            , @PathVariable(name = "playListId") final Long playGroupId) {
        final List<PlayGroupSongResult> list = this.playListService.getSongListFromPlayGroup(userId, playGroupId);
        return new ResponseEntity<>(new ApiResult<>(HttpStatus.OK, list), HttpStatus.OK);
    }


    /**
     * Play list 생성 요청.
     * @param createPlayList 요청 정보.
     * @param errors 검증 오류
     * @return 결과.
     */
    @PostMapping(value = "/playList")
    public ResponseEntity<ApiResult<String>> createPlayList(@Valid @RequestBody final CreatePlayList createPlayList
            , final BindingResult errors) {

        // 요청 데이터 검증.
        this.createPlayListValidator.validate(createPlayList, errors);
        if (errors.hasErrors()) {
            throw new DataValidationException(errors);
        }

        // 저장.
        this.playListService.savePlayList(createPlayList);

        return new ResponseEntity<>(new ApiResult<>(HttpStatus.CREATED), HttpStatus.CREATED);
    }


    /**
     * Play list 삭제 요청.
     * @param userId 사용자 ID
     * @param playGroupId 선택한 PlayList
     * @return 결과
     */
    @DeleteMapping(value = "/playList")
    public ResponseEntity<ApiResult<String>> deletePlayList(@Valid @RequestBody final DeletePlayItem deletePlayItem
            , final BindingResult errors) {

        if (errors.hasErrors()) {
            throw new DataValidationException(errors);
        }

        this.playListService.deletePlayList(deletePlayItem.getUserId(), deletePlayItem.getPlayGroupId());
        return new ResponseEntity<>(new ApiResult<>(HttpStatus.OK), HttpStatus.OK);
    }


    /**
     * Play List에 등록된 노래 삭제
     * @param userId 사용자 ID
     * @param playGroupId 선택한 PlayList
     * @param deletePlayItem 삭제요청할 song id list
     * @return 결과.
     */
    @DeleteMapping(value = "/playList/song")
    public ResponseEntity<ApiResult<String>> deletePlayListSongs(@Valid @RequestBody final DeletePlayItem deletePlayItem
            , final BindingResult errors) {

        if (errors.hasErrors()) {
            throw new DataValidationException(errors);
        }

        this.playListService.deletePlayListSongs(
                deletePlayItem.getUserId(), deletePlayItem.getPlayGroupId(), deletePlayItem.getSongs());
        return new ResponseEntity<>(new ApiResult<>(HttpStatus.OK), HttpStatus.OK);
    }



}
