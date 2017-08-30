package com.ewypass.ezypass;

public class Generator {

    public static String generateUserKey(){
        return "azeazeazeazezaeazeazeazeazezaeazeazeazeaze";
    }

    public static String generateUserPass(String appName, String userKey){
        return userKey + appName;
    }
}
