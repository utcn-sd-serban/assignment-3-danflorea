package ro.utcn.danf.a1.persistence.jdbc;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.danf.a1.model.SiteUser;
import ro.utcn.danf.a1.persistence.api.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate template;

    @Override
    public Optional<SiteUser> findByUsername(String username) {
        List<SiteUser> users = template.query("SELECT * FROM siteuser WHERE username = ?", new SiteUserMapper(), username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public SiteUser save(SiteUser siteUser) {
        if (siteUser.getUserid() == null) {
            siteUser.setUserid(insert(siteUser));
        } else {
            update(siteUser);
        }
        return siteUser;
    }

    @Override
    public List<SiteUser> findAll() {
        return template.query("SELECT * FROM siteuser", new SiteUserMapper());
    }


    public int insert(SiteUser siteUser) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("siteuser");
        insert.setGeneratedKeyName("userid");
        Map<String,Object> data = new HashMap<>();
        data.put("username", siteUser.getUsername());
        data.put("password", siteUser.getPassword());
        data.put("email", siteUser.getEmail());
        data.put("banned", siteUser.isBanned());
        data.put("permission", siteUser.getPermission());

        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(SiteUser siteUser) {
        template.update("UPDATE siteuser SET username = ?, password = ?, email = ?, banned = ?, permission = ? WHERE userid = ?",
                siteUser.getUsername(), siteUser.getPassword(), siteUser.getEmail(), siteUser.isBanned(), siteUser.getPermission(), siteUser.getUserid());
    }
}
