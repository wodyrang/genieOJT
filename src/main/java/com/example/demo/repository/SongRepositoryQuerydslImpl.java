package com.example.demo.repository;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.domain.QAlbum;
import com.example.demo.model.domain.QLocale;
import com.example.demo.model.domain.QSong;
import com.example.demo.model.domain.Song;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
public class SongRepositoryQuerydslImpl extends QuerydslRepositorySupport implements SongRepositoryQuerydsl {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public SongRepositoryQuerydslImpl() {
        super(QSong.class);
    }


    @Override
    public List<Song> findByTilte(final String title, final LocaleType localeType) {
        final QAlbum qAlbum = QAlbum.album;
        final QSong qSong = QSong.song;
        final QLocale qLocale = QLocale.locale;

        return super.from(qSong)
                .innerJoin(qAlbum).on(qAlbum.albumId.eq(qSong.album.albumId))
                .where(qSong.title.contains(title)
                        .and(JPAExpressions
                                .select(qLocale.albumId)
                                .from(qLocale)
                                .where(qLocale.albumId.eq(qAlbum).and(qLocale.localeType.eq(localeType)))
                                .exists()))
                .fetch();
    }
}
