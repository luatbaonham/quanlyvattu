package com.example.fe_quanlyvattu.data.model.phieumuon;

import java.util.List;

public class CreatePhieuMuonRequest {
    private String borrowDate;
    private String note;
    private String roomId;
    private List<Group> groups;

    public CreatePhieuMuonRequest(String borrowDate, String note, String roomId, List<Group> groups) {
        this.borrowDate = borrowDate;
        this.note = note;
        this.roomId = roomId;
        this.groups = groups;
    }

    public static class Group {
        private String groupEquipmentCode;
        private int quantity;

        public Group(String groupEquipmentCode, int quantity) {
            this.groupEquipmentCode = groupEquipmentCode;
            this.quantity = quantity;
        }
    }
}
