package com.eduardodev.gameoflife;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Set;

/**
 * This task executes the evolution process.
 */
public class EvolutionTask extends AsyncTask<Void, Position, Void> {

    private final long mPeriodMs;
    private final Population mCurrentPopulation;
    private EvolutionTaskListener mListener;

    public EvolutionTask(Population initialPopulation, long evolutionPeriodMs) {
        mCurrentPopulation = initialPopulation;
        mPeriodMs = evolutionPeriodMs;
    }

    public void setListener(EvolutionTaskListener listener) {
        mListener = listener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (!isCancelled()) {
            publishProgress(getAliveArray());
            tryToSleepForPeriod();
            mCurrentPopulation.evolve();
        }
        return null;
    }

    @NonNull
    private Position[] getAliveArray() {
        Set<Position> alivePositions = mCurrentPopulation.getAlivePositions();
        Position[] alivePositionsArray = new Position[alivePositions.size()];
        alivePositions.toArray(alivePositionsArray);
        return alivePositionsArray;
    }

    private void tryToSleepForPeriod() {
        try {
            Thread.sleep(mPeriodMs);
        } catch (InterruptedException e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    protected void onProgressUpdate(Position... values) {
        if (mListener != null) {
            mListener.onNewAlivePositions(values);
        }
    }

    public interface EvolutionTaskListener {
        void onNewAlivePositions(Position[] alivePositions);
    }

}
