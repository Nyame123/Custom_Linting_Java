package com.ecomtrading.mybuttonlintpart3;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class CustomButtonGeneralRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(CustomButtonGeneralDetector.buttonGeneralIssue);
    }

    @Override
    public int getApi() {
        return 8;
    }
}
