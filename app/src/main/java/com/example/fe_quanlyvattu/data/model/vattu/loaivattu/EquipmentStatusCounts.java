//package com.example.fe_quanlyvattu.data.model.vattu.loaivattu;
//
//import com.google.gson.annotations.SerializedName;
//
//import java.util.Collections;
//import java.util.Map;
//
//public class EquipmentStatusCounts {
//    @SerializedName("equipmentStatusCounts")
//    private Map<String, Map<String, Integer>> nestedCounts;
//
//    public Map<String, Integer> getStatusCounts() {
//        if (nestedCounts != null && nestedCounts.containsKey("equipmentStatusCounts")) {
//            Map<String, Integer> innerMap = nestedCounts.get("equipmentStatusCounts");
//            return innerMap != null ? innerMap : Collections.emptyMap();
//        }
//        return Collections.emptyMap();
//    }
//
//    public void setStatusCounts(Map<String, Map<String, Integer>> nestedCounts) {
//        this.nestedCounts = nestedCounts;
//    }
//}


