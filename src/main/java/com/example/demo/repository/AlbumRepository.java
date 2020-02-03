package com.example.demo.repository;

import com.example.demo.model.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 앨범 관련 저장소 처리
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>, AlbumRepositoryQuerydsl {
}
