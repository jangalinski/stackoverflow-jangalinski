import com.google.inject.Guice;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import javax.inject.Inject;

public class _24975971 {
    public static class TrainingCommandData extends CommandData {

        private Intent intent;

        @Inject
        public TrainingCommandData(UserCommandResource userCommandResource, Intent intent) {
            super(userCommandResource);
            this.intent = intent;
        }
    }

    public static class CommandData {

        private TelemetryServiceClient telemetryServiceClient;
        private UserCommandResource userCommandResource;

        @Inject
        public void setTelemetryServiceClient(TelemetryServiceClient telemetryServiceClient) {
            this.telemetryServiceClient = telemetryServiceClient;
        }

        public TelemetryServiceClient getTelemetryServiceClient() {
            return telemetryServiceClient;
        }

        public CommandData(UserCommandResource userCommandResource) {
            this.userCommandResource = userCommandResource;
        }
    }

    private static class TelemetryServiceClient {
    }

    private static class UserCommandResource {
    }

    private static class Intent {
    }

    @Inject
    private TrainingCommandData trainingCommandData;

    @Test
    public void create_instance() {
        Guice.createInjector().injectMembers(this);

        Assertions.assertThat(trainingCommandData.getTelemetryServiceClient()).isNotNull();

    }
}
