package com.example.demo.repository;

import com.example.demo.model.domain.Locale;
import com.example.demo.model.domain.LocaleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Repository
public interface LocaleRepository extends JpaRepository<Locale, LocaleId> {
}
