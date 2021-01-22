package com.example.exampleapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exampleapplication.api.ComService;
import com.example.exampleapplication.fragment.DefaultFragment;
import com.example.exampleapplication.pojos.Task;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView main_text_output = null;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    private ComService comService = retrofit.create(ComService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main_text_output = findViewById(R.id.main_text_output);

        Fragment demoFragment = getSupportFragmentManager().getFragmentFactory()
                .instantiate(getClassLoader(), DefaultFragment.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, demoFragment);
        fragmentTransaction.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            int count = getSupportFragmentManager().getBackStackEntryCount();
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null) {
                actionbar.setDisplayHomeAsUpEnabled(count > 0);
                actionbar.setDisplayShowHomeEnabled(count > 0);
            }
        });

        //postTasks();
        //registerOnClicks();
        //setupGlassGestureDetection();
    }

    private void postTasks() {
        for (int i = 1; i < 10; i++) {
            comService.createTask(new Task(i, "Task " + i, new ArrayList<Task>()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(taskSingleObserver);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //main_text_output.setText(KeyEvent.keyCodeToString(keyCode));
        return true;
        //return super.onKeyDown(keyCode, event);
    }

    private final SingleObserver<Task> taskSingleObserver = new SingleObserver<Task>() {
        @Override
        public void onSubscribe(Disposable d) {
            // we'll come back to this in a moment
        }

        @Override
        public void onSuccess(Task task) {
            // data is ready and we can update the UI
            main_text_output.setText(task.getText());
        }

        @Override
        public void onError(Throwable e) {
            // oops, we best show some error message
            e.printStackTrace();
        }
    };

    private final View.OnClickListener myOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView) {
                String[] text = ((TextView) v).getText().toString().split(" ");

                comService.getTaskByID(Integer.valueOf(text[1]))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(taskSingleObserver);

            }
        }
    };

    private void registerOnClicks() {

        for (int i = 1; i < 10; i++) {
            int id = getResources().getIdentifier("textView" + i, "id", getPackageName());
            TextView myTextView = (TextView) findViewById(id);
            myTextView.setOnClickListener(myOnClick);
        }

    }

    private GestureDetector mGestureDetector; // Google Glass GestureDetector

    private GestureDetector.BaseListener gestureBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
            //return sendFeedback(gesture.name());

            main_text_output.setText(gesture.name());
            return true;
        }
    };

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector != null && mGestureDetector.onMotionEvent(event);
    }

    private void setupGlassGestureDetection() {
        try {
            mGestureDetector = new GestureDetector(this.getApplication());
        } catch (Exception e) {
            Log.e("MainActivity", "Exception setting up glass gesturehandler", e);
            return;
        }
        mGestureDetector.setBaseListener(gestureBaseListener);

    }

}
