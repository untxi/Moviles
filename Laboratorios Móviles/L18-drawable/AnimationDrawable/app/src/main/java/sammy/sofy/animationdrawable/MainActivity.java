package sammy.sofy.animationdrawable;

import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.bot);

        final ImageView backgroundTwo = (ImageView)findViewById(R.id.background_two);
        final ImageView backgroundOne = (ImageView)findViewById(R.id.background_one);

        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5500L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundTwo.setTranslationX(translationX);
                backgroundOne.setTranslationX(translationX - width);
            }
        });
    }

    public boolean onTouchEvent (MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            img.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) img.getBackground()).start();
                    animator.start();
                }
            });
            return true;
        }
        return super.onTouchEvent(event);
    }
}
