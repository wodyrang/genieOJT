package com.example.demo.repository;

import com.example.demo.model.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wody@genieworks.net on 2020/01/31.
 *
 * 앨범 관련 저장소 처리
 */
@Repository
public interface AlbumRepository extends JpaRepository<Long, Album>, AlbumRepositoryQuerydsl {
}
