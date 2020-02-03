package com.example.demo.repository;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.domain.Album;
import com.example.demo.model.domain.QAlbum;
import com.example.demo.model.domain.QLocale;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
public class AlbumRepositoryQuerydslImpl extends QuerydslRepositorySupport implements AlbumRepositoryQuerydsl {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public AlbumRepositoryQuerydslImpl() {
        super(QAlbum.class);
    }


    @Override
    public List<Album> searchAlbumByTitle(final String title, final LocaleType localeType) {
        final QAlbum qAlbum = QAlbum.album;
        final QLocale qLocale = QLocale.locale;

        return super.from(qAlbum)
                .where(qAlbum.albumTitle.contains(title) // like 검색
                        .and(JPAExpressions
                                .select(qLocale.albumId)
                                .from(qLocale)
                                .where(qLocale.albumId.eq(qAlbum).and(qLocale.localeType.eq(localeType)))
                                .exists()))
                .fetch();
    }
}
