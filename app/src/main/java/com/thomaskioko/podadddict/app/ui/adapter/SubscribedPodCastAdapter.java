package com.thomaskioko.podadddict.app.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.thomaskioko.podadddict.app.R;
import com.thomaskioko.podadddict.app.data.PodCastContract;
import com.thomaskioko.podadddict.app.ui.util.RecyclerItemChoiceManager;
import com.thomaskioko.podadddict.app.ui.views.ForegroundImageView;
import com.thomaskioko.podadddict.app.util.ApplicationConstants;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.shts.android.library.TriangleLabelView;

/**
 * PodcastFeed adapter class
 *
 * @author Thomas Kioko
 */
public class SubscribedPodCastAdapter extends RecyclerView.Adapter<SubscribedPodCastAdapter.ViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    final private SubscribedPodCastAdapterOnClickHandler mClickHandler;
    final private RecyclerItemChoiceManager mRecyclerItemChoiceManager;
    private static final String LOG_TAG = SubscribedPodCastAdapter.class.getSimpleName();

    /**
     * Constructor
     *
     * @param context Context in which the class is called.
     * @param dh      List of podcast feeds
     */
    public SubscribedPodCastAdapter(Context context, SubscribedPodCastAdapterOnClickHandler dh, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mRecyclerItemChoiceManager = new RecyclerItemChoiceManager(this);
        mRecyclerItemChoiceManager.setChoiceMode(choiceMode);
    }

    public interface SubscribedPodCastAdapterOnClickHandler {
        void onClick(int feedId, ViewHolder vh);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.photo)
        public ForegroundImageView imageView;
        @Bind(R.id.triangleCountView)
        TriangleLabelView triangleLabelView;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mCursor.moveToPosition(adapterPosition);

            int podcastIndex = mCursor.getColumnIndex(PodCastContract.PodcastFeedSubscriptionEntry.COLUMN_SUBSCRIBED_PODCAST_FEED_ID);
            mClickHandler.onClick(mCursor.getInt(podcastIndex), this);
            mRecyclerItemChoiceManager.onClick(this);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(mContext)
                        .inflate(R.layout.podcast_feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCursor.moveToPosition(position);

        String url = mCursor.getString(ApplicationConstants.COLUMN_SUBSCRIBED_PODCAST_FEED_IMAGE_URL);
        String trackCount = mCursor.getString(ApplicationConstants.COLUMN_SUBSCRIBED_PODCAST_FEED_TRACK_COUNT);

        holder.triangleLabelView.setPrimaryText(trackCount);
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.color.placeholder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (null == mCursor) return 0;
        return mCursor.getCount();
    }

    /**
     * Method to swap cursor with a new one
     *
     * @param cursor Cursor
     */
    public void swapCursor(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    /**
     * Method to get the instance of the cursor.
     *
     * @return Cursor
     */
    public Cursor getCursor() {
        return mCursor;
    }

    /**
     * Method to get the selected item position in the recyclerView
     *
     * @return Position
     */
    public int getSelectedItemPosition() {
        return mRecyclerItemChoiceManager.getSelectedItemPosition();
    }

    /**
     * Method to restore saved data.
     *
     * @param savedInstanceState {@link Bundle}
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mRecyclerItemChoiceManager.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Method to save data in a bundle. This is called is specific cases. When the screen orientation
     * changes. and other cases
     *
     * @param outState {@link Bundle}
     */
    public void onSaveInstanceState(Bundle outState) {
        mRecyclerItemChoiceManager.onSaveInstanceState(outState);
    }

    /**
     * @param viewHolder
     */
    public void selectView(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder forecastAdapterViewHolder = (ViewHolder) viewHolder;
            forecastAdapterViewHolder.onClick(forecastAdapterViewHolder.itemView);
        }
    }

}


