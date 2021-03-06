package com.thomaskioko.podadddict.app.api.model.responses;

import com.thomaskioko.podadddict.app.api.model.Feed;

/**
 * @author Thomas Kioko
 */

public class TopPodCastResponse {
    private Feed feed;

    /**
     * @return The feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * @param feed The feed
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
