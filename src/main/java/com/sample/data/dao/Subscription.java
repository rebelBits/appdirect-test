package com.sample.data.dao;

public class Subscription {

    private long accountId;
    private String marketplaceUrl;
    private String creatorOpenId;

    private String accountStatus;
    private String editionCode;

    public String getMarketplaceUrl() {
        return marketplaceUrl;
    }

    public void setMarketplaceUrl(final String marketplaceUrl) {
        this.marketplaceUrl = marketplaceUrl;
    }

    public String getCreatorOpenId() {
        return creatorOpenId;
    }

    public void setCreatorOpenId(final String creatorOpenId) {
        this.creatorOpenId = creatorOpenId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(final long accountId) {
        this.accountId = accountId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(final String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getEditionCode() {
        return editionCode;
    }

    public void setEditionCode(final String editionCode) {
        this.editionCode = editionCode;
    }

    public static class Builder {
        private String marketplaceUrl;
        private String creatorOpenId;
        private long accountId;
        private String accountStatus;
        private String editionCode;

        public static Builder forCreatorOpenId(final String creatorOpenId) {
            final Builder builder = new Builder();
            builder.creatorOpenId = creatorOpenId;
            return builder;
        }

        public Builder withAccountId(final long accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder withAccountStatus(final String accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public Builder withEditionCode(final String editionCode) {
            this.editionCode = editionCode;
            return this;
        }

        public Builder withMarketplaceUrl(final String marketplaceUrl) {
            this.marketplaceUrl = marketplaceUrl;
            return this;
        }

        public Subscription build() {
            final Subscription subscription = new Subscription();
            subscription.setAccountId(accountId);
            subscription.setAccountStatus(accountStatus);
            subscription.setCreatorOpenId(creatorOpenId);
            subscription.setEditionCode(editionCode);
            subscription.setMarketplaceUrl(marketplaceUrl);
            return subscription;
        }
    }

}
