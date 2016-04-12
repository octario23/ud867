package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.util.Pair;

import com.example.Jokes;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by omarin on 4/11/16.
 */
public class AsyncTaskTest extends InstrumentationTestCase implements EndpointsAsyncTask.responseListener{
    CountDownLatch latch;
    EndpointsAsyncTask servletPostAsyncTask;
    Context context;
    private String result;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        latch = new CountDownLatch(1);
        context = getInstrumentation().getTargetContext();
        servletPostAsyncTask = new EndpointsAsyncTask(context,AsyncTaskTest.this);
    }

    public void testVerifyAsyncTaskResponse() throws Throwable {
        runTestOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  try {
                                  Jokes joke = new Jokes();
                                  servletPostAsyncTask.execute(new Pair<Context, String>(context, joke.getInitialJoke()));
//                                  result  = servletPostAsyncTask.get(30,TimeUnit.SECONDS);
                                  latch.await(30, TimeUnit.SECONDS);
//                                  assertNotNull(result);
                                  } catch (Exception e) {
                                      e.printStackTrace();
                                  }
                              }
                          }
        );
    }

    @Override
    public void onResponse(String joke) throws IOException {
        Log.d(getClass().getSimpleName(), "Joke : " + joke);
        if (joke.equals("")) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }
        latch.countDown();
    }
}
