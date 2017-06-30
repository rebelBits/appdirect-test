package com.sample.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private static final String CREATE_USER_SQL = "insert into users (uid,firstName,lastName,openId,subscriptionId) values (?,?,?,?,?)";
    private static final String UPDATE_USER_SQL = "update users set firstName=?,lastName=?,openId=? where uid=? and subscriptionId=?";
    private static final String FIND_USERS_BY_SUBSCRIPTIONID_SQL = "select * from users where subscriptionId=?";
    private static final String DELETE_USER_SQL = "delete from users where uid=? and subscriptionId=?";
    private static final String FIND_USER_BY_UID_AND_SUBSCRIPTIONID_SQL = "select * from users where uid=? and subscriptionId=?";
    private static final String FIND_ALL_SQL = "select * from users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final UserRowMapper rowMapper = new UserRowMapper();

    public void create(final User user) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(CREATE_USER_SQL);
            ps.setString(1, user.getUid());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getOpenId());
            ps.setLong(5, user.getSubscriptionId());
            return ps;
        });
    }

    public void update(final User user) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SQL);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getOpenId());
            ps.setString(4, user.getUid());
            ps.setLong(5, user.getSubscriptionId());
            return ps;
        });
    }

    public void delete(final String uid, final long subscriptionId) {
        jdbcTemplate.update(DELETE_USER_SQL, uid, subscriptionId);
    }

    public List<User> findAllUsersForSubscription(final long subscriptionId) {
        return jdbcTemplate.query(FIND_USERS_BY_SUBSCRIPTIONID_SQL, rowMapper, subscriptionId);
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, rowMapper);
    }

    public Optional<User> findUser(final String uid, final long subscriptionId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_USER_BY_UID_AND_SUBSCRIPTIONID_SQL,
                    new Object[] { uid, subscriptionId }, rowMapper));
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //
    // Helper class
    //
    public static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final User user = new User();
            user.setUid(rs.getString("uid"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setOpenId(rs.getString("openId"));
            user.setSubscriptionId(rs.getLong("subscriptionId"));
            return user;
        }
    }
}
