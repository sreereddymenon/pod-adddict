package com.thomaskioko.podadddict.app.util;

/**
 * @author Thomas Kioko
 */
public class ApplicationConstants {
    /**
     * Set to true to Enable Debugging in the API false to disable. This should be false when
     * releasing the app.
     */
    public static final boolean DEBUG = false;
    /**
     * Base iTunes API Endpoint.
     */
    public static final String ITUNES_END_POINT = "https://itunes.apple.com/";
    /**
     * Server Url
     */
    public static final String LOCAL_SERVER_END_POINT = "http://localhost/";
    /**
     * Connection timeout duration
     */
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    /**
     * Connection Read timeout duration
     */
    public static final int READ_TIMEOUT = 60 * 1000;
    /**
     * Connection write timeout duration
     */
    public static final int WRITE_TIMEOUT = 60 * 1000;
    /**
     * Default image size from feed list
     */
    public static final String IMAGE_SIZE_170x170 = "170";
    /**
     * Dimension for a larger image
     */
    public static final String IMAGE_SIZE_600x600 = "600";

    /**
     * Podcast feeed column ID. These must match the order on which they have been created in
     * {@link com.thomaskioko.podadddict.app.data.db.PodCastFeedDbHelper} otherwise data will not
     * match
     */
    public static int COLUMN_PODCAST_FEED_ROW_ID = 0;
    public static int COLUMN_PODCAST_FEED_ID = 1;
    public static int COLUMN_PODCAST_FEED_TITLE = 2;
    public static int COLUMN_PODCAST_FEED_IMAGE_URL = 3;
    public static int COLUMN_PODCAST_FEED_SUMMARY = 4;
    public static int COLUMN_PODCAST_FEED_ARTIST = 5;
    public static int COLUMN_PODCAST_FEED_CATEGORY = 6;
}
