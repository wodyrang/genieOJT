package com.example.demo.validator;

import com.example.demo.model.data.CreatePlayList;
import com.example.demo.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Slf4j
@Component(value = "createPlayListValidator")
@RequiredArgsConstructor
public class CreatePlayListValidator implements Validator {

    private final PlayListService playListService;


    @Override
    public boolean supports(final Class<?> clazz) {
        return CreatePlayList.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final CreatePlayList createPlayList = (CreatePlayList) target;

        final boolean duplName =
                this.playListService.checkDuplicateGroupName(createPlayList.getUserId(), createPlayList.getGroupName());

        if (duplName) {
            errors.rejectValue("playListName", null, "이미 사용중인 이름이 존재합니다.");
        }
    }
}
