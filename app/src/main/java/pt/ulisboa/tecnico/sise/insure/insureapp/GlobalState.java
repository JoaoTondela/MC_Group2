package pt.ulisboa.tecnico.sise.insure.insureapp;

import android.app.Application;

public class GlobalState extends Application {
    private static Integer sessionId;

    private static String userName;


    public static String getUserName() {
        return userName;
    }

    public static void setUserName( String userName ) {
        GlobalState.userName = userName;
    }

    public static Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId){
        this.sessionId = sessionId;
    }
}
