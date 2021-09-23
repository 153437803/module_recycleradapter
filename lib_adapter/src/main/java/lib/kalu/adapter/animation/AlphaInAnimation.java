package lib.kalu.adapter.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

public final class AlphaInAnimation implements BaseAnimation {

    private static final float DEFAULT_ALPHA_FROM = 0f;
    private final float mFrom;

    public AlphaInAnimation() {
        this(DEFAULT_ALPHA_FROM);
    }

    public AlphaInAnimation(float from) {
        mFrom = from;
    }

    @Override
    public Animator[] getAnimators(@NonNull View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", mFrom, 1f);
        return new Animator[]{animator};
    }
}
