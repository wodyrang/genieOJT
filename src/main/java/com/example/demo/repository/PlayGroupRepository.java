package com.example.demo.repository;

import com.example.demo.model.domain.PlayGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Repository
public interface PlayGroupRepository extends JpaRepository<PlayGroup, Long> {


    /**
     * play list 정보 조회.
     * @param userId 사용자 ID
     * @param playGroupId 그룹 ID
     * @return 그룹 정보.
     */
    PlayGroup findPlayGroupByUserIdAndPlayGroupId(final Long userId, final Long playGroupId);


    /**
     * 사용자의 해당 그룹 이름으로 Play group 건수 조회
     * @param userId 사용자 ID
     * @param groupName 그룹 이름
     * @return Play group count
     */
    int countByUserIdAndGroupName(final Long userId, final String groupName);


    /**
     * 해당 사용자의 모든 Play list 조회
     * @param userId 사용자 ID
     * @return play list
     */
    List<PlayGroup> findPlayGroupsByUserId(final Long userId);

}
