package com.semenov.repository.impl;

import com.semenov.model.Comment;
import com.semenov.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Comment> mapper = BeanPropertyRowMapper.newInstance(Comment.class);

    @Override
    public void save(Long postId, String text) {
        jdbcTemplate.update(
                "INSERT INTO comments (post_id, text) VALUES (?, ?)",
                postId,
                text
        );
    }

    @Override
    public void update(Long commentId, String text) {
        jdbcTemplate.update(
                "UPDATE comments SET text = ? WHERE id = ?",
                text,
                commentId
        );
    }

    @Override
    public void deleteById(Long commentId) {
        jdbcTemplate.update(
                "DELETE FROM comments WHERE id = ?",
                commentId
        );
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return jdbcTemplate.query(
                "SELECT * FROM comments WHERE post_id = ?",
                mapper,
                postId
        );
    }
}
