package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.app.Application;

public class GlobalState extends Application {
    private static Integer sessionId;

    public static Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId){
        this.sessionId = sessionId;
    }
}
