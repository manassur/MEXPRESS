package com.app.mexpress.utillity;

/**
 * Created by Manasera on 9/14/2018.
 */

public class AppConfig {
    public static final String CLOUDINARY_UNSIGNED = "g8pexvqe";
    static String portal= "http://api.sportixe.com/v1/";
    static String port= "http://api.sportixe.com/v1/";
    public static final String MAKE_POST = port+"post/create";
    public static final String MAKE_FORUM = port+"forum/create";

    public static final String MAKE_COMMENT= port+"comment/create";
    public static final String GET_USERS= port+"user/sort";
    public static final String SEARCH_USERS= port+"user/search";
    public static final String LIST_FORUMS= port+"forum/all";

    public static final String FOLLOW_AND_UNFOLLOW= port+"user/auto_follow_and_unfollow_user";

    public static final String GET_FEED = port+"post/all";
    public static final String GET_SPORT_FEED = port+"post/sport";

    public static final String GET_USER_POST = port+"post/user";

    public static final String VOTE_POST = port+"post/vote";

    public static final String GET_COMMENT = port+"comment/all";

    public static final String GET_SPORT = port+"sport/all";

    public static final String CREATE_USER = port+"user/authentication/register";
    public static final String UPDATE_UPDATE = port+"user/update";

    public static final String LOGIN_STRING_REQUEST = port+"user/authentication/login";

    public static final String GET_USER_DETAILS = port+"user/single";
    public static final String SEARCH = port+"argue";
    public static final String VIDEO_URL = portal+"video_upload";
    public static final String AGREE = port+"contribute";
}
