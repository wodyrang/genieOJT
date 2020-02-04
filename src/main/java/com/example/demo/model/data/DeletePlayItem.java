package com.example.demo.model.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 * Play list에서 삭제요청할 노래 ID
 */
@Getter
@Setter
public class DeletePlayItem {

    private List<Long> songs;
}
