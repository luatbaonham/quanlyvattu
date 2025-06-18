package com.example.fe_quanlyvattu.data.model.equipment;

public class UpdateRoomRequest {
    private String room_id;

    public UpdateRoomRequest(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
