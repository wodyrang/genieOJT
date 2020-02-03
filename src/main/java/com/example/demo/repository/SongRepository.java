package com.example.demo.repository;

import com.example.demo.model.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wody@genieworks.net on 2020/01/31.
 */
@Repository
public interface SongRepository extends JpaRepository<Long, Song>, SongRepositoryQuerydsl {
}
