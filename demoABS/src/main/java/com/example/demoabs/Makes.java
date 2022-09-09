package com.example.demoabs;

public class Makes {
    private int UID;
    private int RID;

    public Makes(int UID, int RID) {
        this.UID = UID;
        this.RID = RID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }
}
