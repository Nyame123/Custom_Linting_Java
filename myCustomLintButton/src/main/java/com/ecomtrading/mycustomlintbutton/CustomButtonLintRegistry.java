package com.ecomtrading.mycustomlintbutton;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomButtonLintRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<>();
        issues.add(CustomButtonDetector.buttonIssue);
        return issues;
    }

    @Override
    public int getApi() {
        return 8;
    }
}
