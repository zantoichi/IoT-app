package com.blue.iotapp.payload;

public class UserDevice {
    private Long userId;
    private Long deviceId;

    public UserDevice() {
    }

    public UserDevice(Long userId, Long deviceId) {
        this.userId = userId;
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
