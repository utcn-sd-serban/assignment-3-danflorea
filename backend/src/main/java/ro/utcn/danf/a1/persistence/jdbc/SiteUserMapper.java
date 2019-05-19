package ro.utcn.danf.a1.persistence.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.danf.a1.model.SiteUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteUserMapper implements RowMapper<SiteUser> {

    @Override
    public SiteUser mapRow(ResultSet resultSet, int i) throws SQLException {
        return new SiteUser(resultSet.getInt("userid"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getBoolean("banned"),
                resultSet.getString("permission"));
    }
}
