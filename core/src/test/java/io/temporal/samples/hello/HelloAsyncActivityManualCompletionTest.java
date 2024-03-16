package io.temporal.samples.hello;

import static org.junit.Assert.assertEquals;

import io.temporal.samples.hello.HelloAsyncActivityManualCompletion.GreetingActivitiesImpl;
import io.temporal.testing.TestActivityEnvironment;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

public class HelloAsyncActivityManualCompletionTest {

  @Test
  public void testActivityImpl() throws ExecutionException, InterruptedException {
    TestActivityEnvironment env = TestActivityEnvironment.newInstance();
    env.registerActivitiesImplementations(new GreetingActivitiesImpl());
    HelloAsyncActivityManualCompletion.GreetingActivities activity =
        env.newActivityStub(HelloAsyncActivityManualCompletion.GreetingActivities.class);

    String result = activity.composeGreeting("Hello", "World");

    assertEquals("Hello World!", result);
  }
}
