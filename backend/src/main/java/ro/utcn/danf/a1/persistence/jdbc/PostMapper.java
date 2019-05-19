package ro.utcn.danf.a1.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.danf.a1.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Post(resultSet.getInt("postid"),
                resultSet.getInt("userid"),
                resultSet.getInt("parentid"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getString("posttype"),
                resultSet.getTime("creationdate"));
    }
}
