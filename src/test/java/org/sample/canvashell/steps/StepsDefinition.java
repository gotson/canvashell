package org.sample.canvashell.steps;

import cucumber.api.java8.En;
import org.sample.canvashell.TestApplicationRunner;
import org.sample.canvashell.infrastructure.persistence.CanvasInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestApplicationRunner.class)
public class StepsDefinition implements En {

    @Autowired
    private Shell shell;

    @Autowired
    private CanvasInMemoryRepository canvasRepository;

    private Object shellResult;

    public StepsDefinition() {
        Given("^Reset shell$", ()
                -> canvasRepository.resetCanvas());

        When("^Enter command '(.*)'$", (String command)
                -> shellResult = shell.evaluate(() -> command));

        Then("^result is shown on screen$", (String result)
                -> assertThat(shellResult).isEqualTo(result));

        Then("^result message contains \"([^\"]*)\"$", (String arg0)
                -> assertThat(shellResult.toString()).containsIgnoringCase(arg0));
    }
}
