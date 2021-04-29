package com.mobile.cyoa;

public class Constant {
    public static final String URL = "http://192.168.43.235:8000/";
    public static final String HOME = URL+"api";
    public static final String LOGIN = HOME+"/login";
    public static final String LOGOUT = HOME+"/logout";
    public static final String USER = HOME+"/user";
    public static final String REGISTER = HOME+"/register";
    public static final String SAVE_USER_INFO = HOME+"/save_user_info";
    public static final String UPDATE_USER_INFO = HOME+"/update";
    public static final String BOOKS = HOME+"/books";
    public static final String COMMENTS = BOOKS+"/comments";
    public static final String CREATE_COMMENTS = HOME+"/comments/create";
    public static final String DELETE_COMMENTS = HOME+"/comments/delete";


}
