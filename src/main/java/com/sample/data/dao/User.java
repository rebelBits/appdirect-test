package com.sample.data.dao;

public class User {
    private String uid;
    private String firstName;
    private String lastName;
    private String openId;
    private long subscriptionId;

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(final String openId) {
        this.openId = openId;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(final long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public static class Builder {
        private String uid;
        private String firstName;
        private String lastName;
        private String openId;
        private long subscriptionId;

        public static Builder forSubscriptionId(final long subscriptionId) {
            final Builder builder = new Builder();
            builder.subscriptionId = subscriptionId;
            return builder;
        }

        public Builder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withOpenId(final String openId) {
            this.openId = openId;
            return this;
        }

        public Builder withUid(final String uid) {
            this.uid = uid;
            return this;
        }

        public User build() {
            final User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setOpenId(openId);
            user.setUid(uid);
            user.setSubscriptionId(subscriptionId);
            return user;
        }
    }
}
