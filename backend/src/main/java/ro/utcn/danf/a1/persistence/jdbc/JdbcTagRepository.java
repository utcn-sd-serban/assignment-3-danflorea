package ro.utcn.danf.a1.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.danf.a1.model.Post;
import ro.utcn.danf.a1.model.Tag;
import ro.utcn.danf.a1.persistence.api.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {
    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) {
        if(tag.getTagid() != null){
            update(tag);
        }else{
            tag.setTagid(insert(tag));
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM tag", new TagMapper());
    }

    @Override
    public Optional<Tag> findByTagTitle(String title) {
        List<Tag> posts = template.query("SELECT * FROM tag WHERE title = ?", new TagMapper(), title);
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts.get(0));
    }

    private void update(Tag tag) {
        template.update("UPDATE tag SET title = ? WHERE tagid = ?", tag.getTitle(), tag.getTagid());
    }

    private int insert(Tag tag) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.setGeneratedKeyName("tagid");
        Map<String,Object> data = new HashMap<>();
        data.put("title", tag.getTitle());

        return insert.executeAndReturnKey(data).intValue();
    }
}
