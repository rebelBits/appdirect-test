package com.sample.api.appdirect;

public class Response {
    private boolean success;
    private String errorCode;
    private String message;
    private Long accountIdentifier;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Long getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(final Long accountId) {
        this.accountIdentifier = accountId;
    }

    public static class Builder {
        private final boolean success;
        private String errorCode;
        private String message;
        private Long accountIdentifier = null;

        public Builder(final boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }

        public Long getAccountIdentifier() {
            return accountIdentifier;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }

        public static Builder ok() {
            return new Builder(Boolean.TRUE);
        }

        public static Builder fail() {
            return new Builder(Boolean.FALSE);
        }

        public Builder withErrorCode(final String code) {
            this.errorCode = code;
            return this;
        }

        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public Builder forAccountIdentifier(final Long accountId) {
            this.accountIdentifier = accountId;
            return this;
        }

        public Response build() {
            final Response response = new Response();
            response.setSuccess(success);
            response.setErrorCode(errorCode);
            response.setMessage(message);
            response.setAccountIdentifier(accountIdentifier);
            return response;
        }
    }
}
