package com.example.fe_quanlyvattu.auth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.fe_quanlyvattu.data.model.common.Metadata;

import java.io.IOException;
import java.security.GeneralSecurityException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SessionManager {
    private static final String PREF_NAME = "secure_session";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_USER_CODE = "user_code";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";

    public void savePhone(String phone) {
        prefs.edit().putString(KEY_PHONE, phone).apply();
    }

    public void saveAddress(String address) {
        prefs.edit().putString(KEY_ADDRESS, address).apply();
    }

    public String getPhone() {
        return prefs.getString(KEY_PHONE, null);
    }

    public String getAddress() {
        return prefs.getString(KEY_ADDRESS, null);
    }



    public void saveAvatarUrl(String url) {
        prefs.edit().putString(KEY_AVATAR_URL, url).apply();
    }

    public String getAvatarUrl() {
        return prefs.getString(KEY_AVATAR_URL, null);
    }
    public void saveFirstName(String firstName) {
        prefs.edit().putString(KEY_FIRST_NAME, firstName).apply();
    }

    public void saveLastName(String lastName) {
        prefs.edit().putString(KEY_LAST_NAME, lastName).apply();
    }

    public void saveEmail(String email) {
        prefs.edit().putString(KEY_EMAIL, email).apply();
    }

    public void saveRole(String role) {
        prefs.edit().putString(KEY_ROLE, role).apply();
    }
    public String getFirstName() {
        return prefs.getString(KEY_FIRST_NAME, null);
    }

    public String getLastName() {
        return prefs.getString(KEY_LAST_NAME, null);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }

    public String getRole() {
        return prefs.getString(KEY_ROLE, null);
    }

    public String getFullName() {
        String first = getFirstName();
        String last = getLastName();
        return ((first != null ? first : "") + " " + (last != null ? last : "")).trim();
    }


    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            prefs = EncryptedSharedPreferences.create(
                    context,
                    PREF_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể khởi tạo EncryptedSharedPreferences", e);
        }
    }

    public void saveTokens(String accessToken, String refreshToken) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_REFRESH_TOKEN, refreshToken);
        editor.apply();
    }


    public void saveLoginSession(Metadata metadata) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ACCESS_TOKEN, metadata.getTokens().getAccessToken());
        editor.putString(KEY_REFRESH_TOKEN, metadata.getTokens().getRefreshToken());
        editor.putString(KEY_USER_CODE, metadata.getUser().getUserCode());
        editor.putString(KEY_USERNAME, metadata.getUser().getUsername());
        editor.apply();
    }

    public boolean isLoggedIn() {
        String token = getAccessToken();
        return token != null && !token.isEmpty();
    }

    public String getAccessToken() {
        return prefs.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getRefreshToken() {
        return prefs.getString(KEY_REFRESH_TOKEN, null);
    }

    public String getUserCode() {
        return prefs.getString(KEY_USER_CODE, null);
    }

    public String getUsername() {
        return prefs.getString(KEY_USERNAME, null);
    }

    public void clear() {
        prefs.edit().clear().apply();
    }

}