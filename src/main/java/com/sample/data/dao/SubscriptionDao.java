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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDao {
    private static final String CREATE_SUBSCRIPTION_SQL = "insert into subscriptions (marketplaceUrl,creatorOpenId,editionCode) values (?,?,?)";
    private static final String UPDATE_SUBSCRIPTION_SQL = "update subscriptions set marketplaceUrl=?,creatorOpenId=?,editionCode=?,accountStatus=? where id=?";
    private static final String FIND_SUBSCRIPTION_BY_OPENID_SQL = "select * from subscriptions where creatorOpenId=?";
    private static final String FIND_ALL_SUBSCRIPTIONS = "select * from subscriptions";
    private static final String DELETE_SUBSCRIPTION_SQL = "delete from subscriptions where id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final SubscriptionRowMapper rowMapper = new SubscriptionRowMapper();

    public long create(final Subscription subscription) {
        final KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(CREATE_SUBSCRIPTION_SQL, new String[] { "id" });
            ps.setString(1, subscription.getMarketplaceUrl());
            ps.setString(2, subscription.getCreatorOpenId());
            ps.setString(3, subscription.getEditionCode());
            return ps;
        }, holder);

        return holder.getKey().longValue();
    }

    public void update(final Subscription subscription) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(UPDATE_SUBSCRIPTION_SQL);
            ps.setString(1, subscription.getMarketplaceUrl());
            ps.setString(2, subscription.getCreatorOpenId());
            ps.setString(3, subscription.getAccountStatus());
            ps.setString(4, subscription.getEditionCode());
            ps.setLong(5, subscription.getAccountId());
            return ps;
        });
    }

    public void delete(final long id) {
        jdbcTemplate.update(DELETE_SUBSCRIPTION_SQL, id);
    }

    public List<Subscription> findAll() {
        return jdbcTemplate.query(FIND_ALL_SUBSCRIPTIONS, rowMapper);
    }

    public Optional<Subscription> findByOpenId(final String openId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(FIND_SUBSCRIPTION_BY_OPENID_SQL, rowMapper, openId));
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
    // helper class
    //
    public static class SubscriptionRowMapper implements RowMapper<Subscription> {

        @Override
        public Subscription mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Subscription subscription = new Subscription();
            subscription.setAccountId(rs.getLong("id"));
            subscription.setAccountStatus(rs.getString("accountStatus"));
            subscription.setMarketplaceUrl(rs.getString("marketplaceUrl"));
            subscription.setEditionCode(rs.getString("editionCode"));
            subscription.setCreatorOpenId(rs.getString("creatorOpenId"));
            return subscription;
        }
    }

}
