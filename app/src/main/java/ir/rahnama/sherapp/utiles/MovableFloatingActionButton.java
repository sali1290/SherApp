package ir.rahnama.sherapp.utiles;

import android.app.Notification;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import ir.rahnama.sherapp.R;
import ir.rahnama.sherapp.view.HomeFragmentDirections;


public class MovableFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {

    private final static float CLICK_DRAG_TOLERANCE = 10; // Often, there will be a slight, unintentional, drag when the user taps the FAB, so we need to account for this.
    private float dX, dY;
    private float newX, newY;
    private int parentWidth;
    private float oldRawX, oldRawY;
    private FabCustomeListener fabCustomeListener;

    public interface FabCustomeListener {
        public void itemOnClick( int id );
    }

    public MovableFloatingActionButton(Context context) {
        super(context);
        this.fabCustomeListener = null ;
        init();
    }

    public void setFabCustomeListener(FabCustomeListener fabCustomeListener){this.fabCustomeListener = fabCustomeListener;}

    public MovableFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*public void gotoShop () {
        ImageView shopImageView = getRootView().findViewById(R.id.shop_ImageView);
        shopImageView.setOnClickListener(v -> { loadShopFragment(getRootView()); });
    }*/

    private void init() {

        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        int action = motionEvent.getAction();


        if (action == MotionEvent.ACTION_DOWN) {
            oldRawX = motionEvent.getRawX();
            oldRawY = motionEvent.getRawY();
            dX = view.getX() - oldRawX;
            dY = view.getY() - oldRawY;
            return true; // Consumed
        } else if (action == MotionEvent.ACTION_MOVE) {

            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();

            View viewParent = (View) view.getParent();
            parentWidth = viewParent.getWidth();
            int parentHeight = viewParent.getHeight();

            newX = motionEvent.getRawX() + dX;
            newX = Math.max(layoutParams.leftMargin, newX); // Don't allow the FAB past the left hand side of the parent
            newX = Math.min(parentWidth - viewWidth - layoutParams.rightMargin, newX); // Don't allow the FAB past the right hand side of the parent

            newY = motionEvent.getRawY() + dY;
            newY = Math.max(layoutParams.topMargin, newY); // Don't allow the FAB past the top of the parent
            newY = Math.min(parentHeight - viewHeight - layoutParams.bottomMargin, newY); // Don't allow the FAB past the bottom of the parent

            view.animate().x(newX).y(newY).setDuration(0).start();

            return true; // Consumed

        } else if (action == MotionEvent.ACTION_UP) {


            float upRawX = motionEvent.getRawX();
            float upRawY = motionEvent.getRawY();

            float upDX = upRawX - oldRawX;
            float upDY = upRawY - oldRawY;

            if (Math.abs(upDX) < CLICK_DRAG_TOLERANCE && Math.abs(upDY) < CLICK_DRAG_TOLERANCE) {
                // A click
                return performClick();
            } else {
                // A drag

                int halfParentWidth = parentWidth / 2;
                int parentWidth2 = parentWidth - 5;

                /*if (newX > halfParentWidth) {
                    view.animate().x(parentWidth2).y(newY).setDuration(300).start();
                } else {
                    view.animate().x(-45).y(newY).setDuration(300).start();
                }*/

                view.animate().x(-45).y(newY).setDuration(400).start();

                return true; // Consumed
            }


        } else {
            return super.onTouchEvent(motionEvent);
        }

    }

    /*private void loadShopFragment(View view) {
        NavDirections action = HomeFragmentDirections.actionHomeToShop();
        if(view != null){
            Navigation.findNavController(view).navigate(action);
        }
    }*/

}