package com.example.fe_quanlyvattu.data.model.home_baocao;

import java.util.List;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

public class BaoCaoResponse {
    @SerializedName("metadata")
    private Map<String, List<StatusCount>> metadata;

    public Map<String, List<StatusCount>> getMetadata() {
        return metadata;
    }

    public static class StatusCount {
        private String status;
        private int count;

        public String getStatus() {
            return status;
        }

        public int getCount() {
            return count;
        }
    }
}
