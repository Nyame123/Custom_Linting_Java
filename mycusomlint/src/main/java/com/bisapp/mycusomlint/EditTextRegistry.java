package com.bisapp.mycusomlint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class EditTextRegistry extends IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(EditTextDetector.EDIT_TEXT_ISSUE);
    }


    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }
}
