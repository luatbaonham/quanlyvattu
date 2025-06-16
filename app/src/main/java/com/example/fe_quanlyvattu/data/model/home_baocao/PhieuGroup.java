package com.example.fe_quanlyvattu.data.model.home_baocao;

import java.util.List;

public class PhieuGroup {
    private String type; // import, borrow, ...
    private List<PhieuStatus> statusList;

    public PhieuGroup(String type, List<PhieuStatus> statusList) {
        this.type = type;
        this.statusList = statusList;
    }

    public String getType() {
        return type;
    }

    public List<PhieuStatus> getStatusList() {
        return statusList;
    }
}
