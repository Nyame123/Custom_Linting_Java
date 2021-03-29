package com.bisapp.mycusomlint;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class EditTextDetectorTest extends LintDetectorTest {


    @Test
    public void testEditTextLint() {
        lint().files(
               // xml()
        ).run()
                .expectClean();
    }

    @Override
    protected Detector getDetector() {
        return new EditTextDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(EditTextDetector.EDIT_TEXT_ISSUE);
    }
}
