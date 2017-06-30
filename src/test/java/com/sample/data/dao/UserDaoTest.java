package com.sample.data.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserDaoTest {
    private static final String FIRST_NAME_SAMPLE = "Test";
    private static final String LAST_NAME_SAMPLE = "User";
    private static final String OPENID_SAMPLE = "http://my-site.com/openid/id/1";
    private static final long SUBSCRIPTIONID_SAMPLE = 1l;

    @Autowired
    public UserDao userDao;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void createUser() {
        final String uid = UUID.randomUUID().toString();
        userDao.create(buildSampleUser(uid));

        final Optional<User> user = userDao.findUser(uid, SUBSCRIPTIONID_SAMPLE);
        assertTrue(user.isPresent());
        final User createdUser = user.get();
        assertThat(uid, equalTo(createdUser.getUid()));
    }

    private User buildSampleUser(final String uid) {
        return User.Builder.forSubscriptionId(SUBSCRIPTIONID_SAMPLE).withFirstName(FIRST_NAME_SAMPLE)
                .withLastName(LAST_NAME_SAMPLE).withOpenId(OPENID_SAMPLE).withUid(uid).build();
    }

    @Test
    public void listUsersForSubscriptions() {
        userDao.create(buildSampleUser(UUID.randomUUID().toString()));
        final List<User> users = userDao.findAllUsersForSubscription(SUBSCRIPTIONID_SAMPLE);
        assertThat(users.size(), greaterThan(0));
    }

    @Test
    public void findUserByUidAndSubscriptionId() {
        final String uid = UUID.randomUUID().toString();
        userDao.create(buildSampleUser(uid));
        final Optional<User> user = userDao.findUser(uid, SUBSCRIPTIONID_SAMPLE);
        assertTrue(user.isPresent());

        final User createdUser = user.get();
        assertThat(SUBSCRIPTIONID_SAMPLE, equalTo(createdUser.getSubscriptionId()));
        assertThat(FIRST_NAME_SAMPLE, equalTo(createdUser.getFirstName()));
        assertThat(LAST_NAME_SAMPLE, equalTo(createdUser.getLastName()));
        assertThat(OPENID_SAMPLE, equalTo(createdUser.getOpenId()));
    }

    @After
    public void cleanup() {
        userDao.findAll().stream().forEach(e -> userDao.delete(e.getUid(), e.getSubscriptionId()));
    }
}
