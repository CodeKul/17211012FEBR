package com.codekul.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aniruddha on 2/3/17.
 */

public class BallView extends TextView {

    private Paint paint;

    private int cx = 1, cy = 1, rad = 20, dx = 5, dy = 5;
    public BallView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.BLUE);

        move().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .doOnNext( pos -> {
                    cx = pos.x;
                    cy = pos.y;
                    invalidate();
                })
                .subscribe();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx, cy, rad, paint);
    }

    private Flowable<Pos> move() {
        return Flowable.create(sub -> {

            Pos pos = new Pos();

            while(true) {
                if(pos.x >= getWidth()) dx = -5;
                if(pos.y >= getHeight()) dy = -5;
                if(pos.x < 0) dx = 5;
                if(pos.y < 0 ) dy = 5;

                pos.x += dx;
                pos.y += dy;

                Thread.sleep(50);
                sub.onNext(pos);
             }
        }, BackpressureStrategy.BUFFER);
    }

    class Pos {
        int x;
        int y;
    }
}
