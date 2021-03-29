package com.ecomtrading.mybuttonlintpart3;

import com.android.SdkConstants;
import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomButtonGeneralDetector extends LayoutDetector implements SourceCodeScanner {

    public static String CUSTOM_VIEW = "com.ecomtrading.mycustomlint.custom_view.MyButtonCV";

    public static Issue buttonGeneralIssue = Issue.create(
            "Button Detector Issue",
            "Let's use the custom button designed by the team",
            "The team is trying to use a custom view in place of the normal \n" +
                    " \n android button widget,\n" +
                    "Custom View ==> MyButtonCV",
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            new Implementation(CustomButtonGeneralDetector.class,
                    Scope.JAVA_AND_RESOURCE_FILES,
                    Scope.JAVA_FILE_SCOPE,
                    Scope.RESOURCE_FILE_SCOPE
                    )
    );

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(SdkConstants.BUTTON);
    }

    @Override
    public void visitElement(@NotNull XmlContext context, @NotNull Element element) {
        //super.visitElement(context, element);
        //report the issue to the developer or the user
        context.report(buttonGeneralIssue,
                context.getLocation(element),
                "Let's use the custom button designed by the team",
                createFix());
    }

    @Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        System.out.println("visit call expression");
        return Collections.singletonList(UCallExpression.class);
    }

    @Nullable
    @Override
    public UElementHandler createUastHandler(@NotNull JavaContext context) {
        return new UElementHandler(){
            @Override
            public void visitCallExpression(@NotNull UCallExpression node) {
                System.out.println("visit call expression");
                context.report(buttonGeneralIssue,
                        context.getLocation(node),
                        "Let's use the custom button designed by the team",
                        createFix());
            }
        };
    }

    private LintFix createFix() {
        return LintFix.create()
                .replace()
                .text(SdkConstants.BUTTON)
                .with(CUSTOM_VIEW)
                .build();
    }
}
