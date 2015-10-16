package com.eduardodev.gameoflife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

/**
 * This fragment holds the view where the game is executing.
 */
public class GameFragment extends Fragment implements
        GeneratePopulationTask.GeneratePopulationTaskListener,
        EvolutionTask.EvolutionTaskListener {

    private GeneratePopulationTask mGeneratePopulationTask;
    private EvolutionTask mEvolutionTask;
    private ProgressBar mProgressBar;
    private View mProgressContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mProgressContainer = view.findViewById(R.id.progress_container);
        view.post(new ViewReadyRunnable(this));
        return view;
    }

    private void onViewReady() {
        setProgress(0);
        changeProgressVisibility(View.VISIBLE);
        startGeneratePopulationTask();
    }

    private void setProgress(int progress) {
        if (mProgressBar != null) {
            mProgressBar.setProgress(progress);
        }
    }

    private void changeProgressVisibility(int visibility) {
        if (mProgressContainer != null) {
            mProgressContainer.setVisibility(visibility);
        }
    }

    private void startGeneratePopulationTask() {
        View rootView = getView();
        if (rootView != null) {
            int maxNumPositions = getResources().getInteger(R.integer.initial_population_size);
            int itemSize = Math.round(getResources().getDimension(R.dimen.item_size));
            mGeneratePopulationTask = new GeneratePopulationTask(maxNumPositions,
                    rootView.getWidth() / itemSize, rootView.getHeight() / itemSize);
            mGeneratePopulationTask.setListener(this);
            mGeneratePopulationTask.execute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGeneratePopulationTask != null) {
            mGeneratePopulationTask.setListener(null);
            mGeneratePopulationTask.cancel(false);
        }
        if (mEvolutionTask != null) {
            mEvolutionTask.setListener(null);
            mEvolutionTask.cancel(false);
        }
    }

    @Override
    public void onNewAlivePositions(Position[] alivePositions) {
        ViewGroup rootView = (ViewGroup) getView();
        if (rootView != null) {
            rootView.removeAllViews();
            for (Position alivePosition : alivePositions) {
                ItemView itemView = new ItemView(rootView);
                rootView.addView(itemView);
                itemView.setPosition(alivePosition);
            }
        }
    }

    @Override
    public void onPopulationGenerated(Population population) {
        changeProgressVisibility(View.GONE);
        long periodMs = getResources().getInteger(R.integer.evolution_period_ms);
        mEvolutionTask = new EvolutionTask(population, periodMs);
        mEvolutionTask.setListener(this);
        mEvolutionTask.execute();
    }

    @Override
    public void onProgressPublished(int percentage) {
        setProgress(percentage);
    }

    private static class ViewReadyRunnable implements Runnable {
        private final WeakReference<GameFragment> mGameFragmentWeakReference;

        public ViewReadyRunnable(GameFragment gameFragment) {
            mGameFragmentWeakReference = new WeakReference<>(gameFragment);
        }

        @Override
        public void run() {
            GameFragment gameFragment = mGameFragmentWeakReference.get();
            if (gameFragment != null) {
                gameFragment.onViewReady();
            }
        }
    }
}
