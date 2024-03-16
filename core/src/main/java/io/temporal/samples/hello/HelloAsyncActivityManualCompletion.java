package io.temporal.samples.hello;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ManualActivityCompletionClient;
import java.util.concurrent.ForkJoinPool;

public class HelloAsyncActivityManualCompletion {

  @ActivityInterface
  public interface GreetingActivities {

    String composeGreeting(String greeting, String name);
  }

  static class GreetingActivitiesImpl implements GreetingActivities {
    @Override
    public String composeGreeting(String greeting, String name) {
      ManualActivityCompletionClient completionClient =
          Activity.getExecutionContext().useLocalManualCompletion();

      ForkJoinPool.commonPool()
          .execute(() -> composeGreetingAsync(completionClient, greeting, name));

      return null;
    }

    private void composeGreetingAsync(ManualActivityCompletionClient completionClient, String greeting, String name) {
      String result = greeting + " " + name + "!";

      completionClient.complete(result);
    }
  }
}
