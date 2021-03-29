package com.bisapp.mycusomlint;

import com.android.SdkConstants;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.LayoutDetector;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.android.tools.lint.detector.api.XmlContext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UElement;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.android.SdkConstants.ANDROID_APP_PKG;
import static com.android.SdkConstants.ANDROID_URI;
import static com.android.SdkConstants.ATTR_ADDITIONAL_PADDING_END_FOR_ICON;
import static com.android.SdkConstants.ATTR_MIN_WIDTH;

public class EditTextDetector extends LayoutDetector {

    public static final Issue EDIT_TEXT_ISSUE = Issue.create(
            // ID: used in @SuppressLint warnings etc
            EditTextDetectorInfo.EDIT_TEXT_ID,
            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            EditTextDetectorInfo.EDIT_TEXT_USAGE,
            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            EditTextDetectorInfo.EDIT_TEXT_EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.FATAL,
            EditTextDetectorInfo.EDIT_TEXT_IMPLEMENTATION
    );

    public static final Issue RECYCLERVIEW_ISSUE = Issue.create(
            // ID: used in @SuppressLint warnings etc
            RecyclerViewDetectorInfo.RECYCERVIEW_ID,
            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            RecyclerViewDetectorInfo.RECYCERVIEW_MESSAGE,
            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            RecyclerViewDetectorInfo.RECYCERVIEW_EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.FATAL,
            EditTextDetectorInfo.EDIT_TEXT_IMPLEMENTATION
    );

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return new ArrayList<String>(){
            {
                add(SdkConstants.EDIT_TEXT);
                //add(SdkConstants.RECYCLER_VIEW);
                add("androidx.recyclerview.widget.RecyclerView");
            }
        };
    }


    @Override
    public void visitElement(@NotNull XmlContext context, @NotNull Element element) {
        if (element.getNodeName().contains("androidx.recyclerview.widget.RecyclerView")) {
            if (!element.hasAttributeNS(ANDROID_URI,ATTR_MIN_WIDTH)) {
                context.report(
                        RECYCLERVIEW_ISSUE,
                        context.getElementLocation(element),
                        RecyclerViewDetectorInfo.RECYCERVIEW_MESSAGE,
                        createRecyclerviewQuickFix()
                );
            }
        }else if (element.getNodeName().contains(SdkConstants.EDIT_TEXT)){
            context.report(
                    EDIT_TEXT_ISSUE,
                    context.getElementLocation(element),
                    EditTextDetectorInfo.EDIT_TEXT_MESSAGE,
                    createQuickFix()
            );
        }
    }

    private LintFix createQuickFix(){
        return LintFix.create()
                .replace()
                .text(SdkConstants.EDIT_TEXT)
                .with(EditTextDetectorInfo.CUSTOM_EDIT_TEXT)
                .build();
    }

    private LintFix createRecyclerviewQuickFix(){
        return LintFix.create()
                .replace()
                .text("androidx.recyclerview.widget.RecyclerView")
                .with(RecyclerViewDetectorInfo.CUSTOM_RECYCERVIEW)
                .build();
    }

    public static class EditTextDetectorInfo {
        public static String EDIT_TEXT_MESSAGE = "Do not use EditText directly";
        public static String EDIT_TEXT_ID = "CustomEditTextID";
        public static String CUSTOM_EDIT_TEXT = "com.empower.empowercomponents.custom_views.comreg.FormInputTextHorizCV";
        public static String EDIT_TEXT_USAGE = "EditText Usage: Use the team custom view like **FormInputTextHorizCV**";
        public static String EDIT_TEXT_EXPLANATION = "According to the business rules, we use \n" +
                " **FormInput Custom views** in place of the android widget.\n\n" +
                "This helps to maintain consistency in our code base and implementations";

        public static Implementation EDIT_TEXT_IMPLEMENTATION =
                new Implementation(EditTextDetector.class, Scope.RESOURCE_FILE_SCOPE);

    }

    public static class RecyclerViewDetectorInfo {
        public static String RECYCERVIEW_MESSAGE = "Do not use normal Recyclerview directly";
        public static String RECYCERVIEW_ID = "CustomRecyclerviewID";
        public static String CUSTOM_RECYCERVIEW = "com.empower.empowercomponents.custom_views.comreg.FormInputRecyclerView";
        public static String RECYCERVIEW_USAGE = "Recyclerview Usage: Use the team custom view like **FormInputRecyclerView**";
        public static String RECYCERVIEW_EXPLANATION = "According to the business rules, we use \n" +
                " **FormInput Custom views** in place of the android widget.\n\n" +
                "This helps to maintain consistency in our code base and implementations";

        public static Implementation EDIT_TEXT_IMPLEMENTATION =
                new Implementation(EditTextDetector.class, Scope.RESOURCE_FILE_SCOPE);

    }
}
