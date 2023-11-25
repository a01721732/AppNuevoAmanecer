package com.nuevo.nuevoamanecer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class CaraDePapa : AppCompatActivity() {
    //ImageView papaView;
    var ojosView: ImageView? = null
    var bocaLineaView: ImageView? = null
    var topHatView: ImageView? = null
    var blueShoesView: ImageView? = null
    var bootsView: ImageView? = null
    var cartoonyLeftArmView: ImageView? = null
    var cartoonyRightArmView: ImageView? = null
    var mustacheView: ImageView? = null
    var rightMuscleArmView: ImageView? = null
    var leftMuscleArmView: ImageView? = null
    var smileView: ImageView? = null
    var soccerShoesView: ImageView? = null
    private var mainLayout: ViewGroup? = null
    private var xDelta = 0
    private var yDelta = 0
    private lateinit var btnRegresar: Button

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cara_de_papa)
        mainLayout = findViewById<View>(R.id.relativeLayout) as RelativeLayout
        ojosView = findViewById<View>(R.id.ojosView) as ImageView
        bocaLineaView = findViewById<View>(R.id.bocaLineaView) as ImageView
        topHatView = findViewById<View>(R.id.topHatView) as ImageView
        blueShoesView = findViewById<View>(R.id.blueShoesView) as ImageView
        bootsView = findViewById<View>(R.id.bootsView) as ImageView
        cartoonyLeftArmView = findViewById<View>(R.id.cartoonyLeftArmView) as ImageView
        cartoonyRightArmView = findViewById<View>(R.id.cartoonyRightArmView) as ImageView
        mustacheView = findViewById<View>(R.id.mustacheView) as ImageView
        rightMuscleArmView = findViewById<View>(R.id.rightMuscleArmView) as ImageView
        leftMuscleArmView = findViewById<View>(R.id.leftMuscleArmView) as ImageView
        smileView = findViewById<View>(R.id.smileView) as ImageView
        soccerShoesView = findViewById<View>(R.id.soccerShoesView) as ImageView
        ojosView!!.setOnTouchListener(onTouchListener())
        bocaLineaView!!.setOnTouchListener(onTouchListener())
        topHatView!!.setOnTouchListener(onTouchListener())
        blueShoesView!!.setOnTouchListener(onTouchListener())
        bootsView!!.setOnTouchListener(onTouchListener())
        cartoonyLeftArmView!!.setOnTouchListener(onTouchListener())
        cartoonyRightArmView!!.setOnTouchListener(onTouchListener())
        mustacheView!!.setOnTouchListener(onTouchListener())
        rightMuscleArmView!!.setOnTouchListener(onTouchListener())
        leftMuscleArmView!!.setOnTouchListener(onTouchListener())
        smileView!!.setOnTouchListener(onTouchListener())
        soccerShoesView!!.setOnTouchListener(onTouchListener())

        btnRegresar = findViewById(R.id.btnRegresarJuego3) as Button

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        /*
        ojosView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    float x = event.getX();
                    float y = event.getY();
                }

                if (event.getAction() == DragEvent.ACTION_DROP) {
                    float dx = event.getX() - x;
                    float dy = event.getY() - y;

                    ojosView.setX(ojosView.getX() + dx);
                    ojosView.setY(ojosView.getY() + dy);

                }
                return false;
            }
        });
         */

        /*
        ojosView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ActionBar.LayoutParams layoutParams = (ActionBar.LayoutParams) ojosView.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x_cord = (int) event.getRawX();
                        int y_cord = (int) event.getRawY();

                        if (x_cord > windowwidth) {
                            x_cord = windowwidth;
                        }
                        if (y_cord > windowheight) {
                            y_cord = windowheight;
                        }

                        layoutParams.leftMargin = x_cord - 25;
                        layoutParams.topMargin = y_cord - 75;

                        balls.setLayoutParams(layoutParams);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        */

        /*
        papaView.setOnTouchListener(new View.OnTouchListener()) {
            @override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY;
                        movedX = event.getX();
                        movedY = event.getY();

                        float distanceX = movedX - xDown;
                        float distanceY = movedY - yDown;

                        papaView.setX(papaView.getX() + distanceX);
                        papaView.setX(papaView.getY() + distanceY);

                        break;
                }

                return true;
            }
        });
        */
    }

    private fun onTouchListener(): OnTouchListener {
        return OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }

                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = view
                        .layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }
            mainLayout!!.invalidate()
            true
        }
    } /*
    float x, y;
    float dx, dy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            dx = event.getX() - x;
            dy = event.getY() - y;

            ojosView.setX(ojosView.getX() + dx);
            ojosView.setY(ojosView.getY() + dy);

            x = event.getX();
            y = event.getY();
        }

        return super.onTouchEvent(event);
    }
     */
}