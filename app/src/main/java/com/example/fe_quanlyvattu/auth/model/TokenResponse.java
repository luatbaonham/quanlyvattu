package com.example.fe_quanlyvattu.auth.model;

public class TokenResponse {
    private Metadata metadata;

    public Metadata getMetadata() {
        return metadata;
    }

    public static class Metadata {
        private Tokens tokens;

        public Tokens getTokens() {
            return tokens;
        }

        public static class Tokens {
            private String accessToken;

            public String getAccessToken() {
                return accessToken;
            }
        }
    }
}