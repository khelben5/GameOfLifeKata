package com.eduardodev.gameoflife;

import android.os.AsyncTask;

import java.util.Random;
import java.util.Set;

/**
 * This task is intended for generating the initial population set.
 */
public class GeneratePopulationTask extends AsyncTask<Void, Integer, Set<Position>> {

    private GeneratePopulationTaskListener mListener;
    private final int mMaxNumPositions;
    private final Random mRandom;
    private final int mMaxX;
    private final int mMaxY;

    public GeneratePopulationTask(int maxNumPositions, int maxX, int maxY) {
        mMaxNumPositions = maxNumPositions;
        mMaxX = maxX;
        mMaxY = maxY;
        mRandom = new Random(System.currentTimeMillis());
    }

    /**
     * @param listener The listener to be notified.
     */
    public void setListener(GeneratePopulationTaskListener listener) {
        mListener = listener;
    }

    @Override
    protected Set<Position> doInBackground(Void... params) {
        EqualsSet<Position> positionSet = new EqualsSet<>();
        for (int i = 0; i < mMaxNumPositions && !isCancelled(); i++) {
            positionSet.add(new Position(mRandom.nextInt(mMaxX), mRandom.nextInt(mMaxY)));
            publishProgress(Math.round((i * 100.0f) / mMaxNumPositions));
        }
        return positionSet.toSet();
    }

    @Override
    protected void onPostExecute(Set<Position> positions) {
        if (mListener != null) {
            Population population = new Population(mMaxX, mMaxY);
            population.setAlivePositions(positions);
            mListener.onPopulationGenerated(population);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (mListener != null) {
            mListener.onProgressPublished(values[0]);
        }
    }

    public interface GeneratePopulationTaskListener {
        void onPopulationGenerated(Population population);

        void onProgressPublished(int percentage);
    }
}
