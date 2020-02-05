package com.example.demo.repository;

import com.example.demo.model.domain.PlayGroup;
import com.example.demo.model.domain.PlayItem;
import com.example.demo.model.domain.PlayItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Repository
public interface PlayItemRepository extends JpaRepository<PlayItem, PlayItemId> {


    /**
     * 해당하는 그룹의 모든 노래 삭제.
     * @param playGroup 그룹
     */
    void deleteByPlayGroup(final PlayGroup playGroup);

}
