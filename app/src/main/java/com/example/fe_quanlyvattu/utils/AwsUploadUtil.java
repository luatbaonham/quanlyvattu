package com.example.fe_quanlyvattu.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.utils.FileUtils;

import java.io.File;

public class AwsUploadUtil {

    private static final String BUCKET_NAME = "thinoproawsbucket";
    private static final String ACCESS_KEY = "AKIAUPMYM4DDIN7MCTGH";
    private static final String SECRET_KEY = "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";
    private static final Regions REGION = Regions.AP_SOUTHEAST_1; // chỉnh theo region của bạn

    public static void upload(Context context, Uri uri, ApiCallback<String> callback) {
        File file = FileUtils.getFileFromUri(context, uri); // bạn cần thêm hàm convert URI sang File
        if (file == null) {
            callback.onError("Không thể đọc file từ Uri");
            return;
        }

        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);
        TransferUtility transferUtility = TransferUtility.builder()
                .context(context)
                .s3Client(s3Client)
                .build();

        String key = "images/" + System.currentTimeMillis() + "_" + file.getName();

        TransferObserver observer = transferUtility.upload(BUCKET_NAME, key, file);

        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (state == TransferState.COMPLETED) {
                    String uploadedUrl = "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + key;
                    callback.onSuccess(uploadedUrl);
                } else if (state == TransferState.FAILED) {
                    callback.onError("Tải ảnh thất bại");
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                // Có thể hiển thị % progress nếu muốn
            }

            @Override
            public void onError(int id, Exception ex) {
                callback.onError("Lỗi: " + ex.getMessage());
                Log.e("AWS_UPLOAD", "Exception", ex);
            }
        });
    }
}
