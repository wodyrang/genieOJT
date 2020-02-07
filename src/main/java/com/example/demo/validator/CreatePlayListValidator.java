package com.example.demo.validator;

import com.example.demo.model.data.CreatePlayList;
import com.example.demo.service.PlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
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
    public boolean supports(@NonNull final Class<?> clazz) {
        return CreatePlayList.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull final Object target, @NonNull final Errors errors) {
        final CreatePlayList createPlayList = (CreatePlayList) target;

        final Long playGroupId = createPlayList.getPlayGroupId();
        final Long userId = createPlayList.getUserId();
        final String groupName = createPlayList.getGroupName();

        if (playGroupId == null) {
            if (StringUtils.isBlank(groupName)) {
                errors.rejectValue("groupName", null, "이름은 필수입니다.");
            }
        }

        if (StringUtils.isNotBlank(groupName)) {
            final boolean duplName = this.playListService.checkDuplicateGroupName(userId, groupName);
            if (duplName) {
                errors.rejectValue("groupName", null, "이미 사용중인 이름이 존재합니다.");
            }
        }
    }
}
