package ro.utcn.danf.a1.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.danf.a1.model.Post;
import ro.utcn.danf.a1.persistence.api.PostRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {
    private final JdbcTemplate template;

    @Override
    public Post save(Post post) {
        if(post.getPostid() != null){
            update(post);
        }else{
            int id = insert(post);
            post.setPostid(id);
        }
        return post;
    }

    @Override
    public Optional<Post> findById(int id)
    {
        List<Post> posts = template.query("SELECT * FROM post WHERE postid = ?", new PostMapper(), id);
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts.get(0));
    }

    @Override
    public void remove(Post post) {
        template.update("DELETE FROM post WHERE postid = ?", post.getPostid());
    }

    @Override
    public List<Post> findAll() {
        return template.query("SELECT * FROM post", new PostMapper());
    }

    private int insert(Post post) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("post");
        insert.setGeneratedKeyName("postid");
        Map<String,Object> data = new HashMap<>();
        data.put("userid", post.getUserid());
        data.put("parentid", post.getParentid());
        data.put("title", post.getTitle());
        data.put("text", post.getText());
        data.put("posttype", post.getPosttype());
        data.put("creationdate", post.getCreationdate());

        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Post post) {
       template.update("UPDATE post SET title = ?, text = ? WHERE postid = ?", post.getTitle(), post.getText(), post.getPostid());
    }
}

