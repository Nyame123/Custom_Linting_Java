package com.ecomtrading.mycustomlintbutton;

import com.android.SdkConstants;
import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.LayoutDetector;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.android.tools.lint.detector.api.XmlContext;
import com.intellij.psi.PsiType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UMethod;
import org.jetbrains.uast.UastCallKind;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.android.SdkConstants.ANDROID_URI;
import static com.android.SdkConstants.ATTR_MIN_WIDTH;

public class CustomButtonDetector extends LayoutDetector implements SourceCodeScanner {

    public static String ANDROID_BUTTON = "android.widget.Button";

    public static Issue buttonIssue = Issue.create(
            ButttonInfo.ID,
            ButttonInfo.USAGES,
            ButttonInfo.EXPLANATION,
            Category.CORRECTNESS,
            10,
            Severity.ERROR,
            new Implementation(CustomButtonDetector.class,
                    Scope.RESOURCE_FILE_SCOPE,
                    Scope.JAVA_AND_RESOURCE_FILES)
    );

    @Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(UCallExpression.class);
    }



    @Nullable
    @Override
    public UElementHandler createUastHandler(@NotNull JavaContext context) {
        return new UElementHandler(){

            @Override
            public void visitCallExpression(@NotNull UCallExpression node) {

                PsiType psiType = node.getExpressionType();
                if (null != psiType && psiType.getCanonicalText().equalsIgnoreCase(ANDROID_BUTTON)
                && node.getKind() == UastCallKind.CONSTRUCTOR_CALL){
                    //create Lint Report with a fix

                    context.report(buttonIssue,
                            context.getLocation(node),
                            ButttonInfo.USAGES,
                            createFix());
                }
            }
        };
    }

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(SdkConstants.BUTTON);
    }

    @Override
    public void visitElement(@NotNull XmlContext context, @NotNull Element element) {
        //super.visitElement(context, element);
        //report the issue to the developer or the user
        context.report(buttonIssue,
                context.getLocation(element),
                ButttonInfo.USAGES,
                createFix());
    }

    private LintFix createFix() {
        return LintFix.create()
                .replace()
                .text(SdkConstants.BUTTON)
                .with(ButttonInfo.CUSTOM_VIEW)
                .build();
    }

    public static class ButttonInfo {
        public static String ID = "Custom_Button_ID";
        public static String USAGES = "Try to use the team custom view";
        public static String EXPLANATION = "The team is trying to use a custom view in place of the normal " +
                " android button widget," +
                " Custom View ==> MyButtonCV";
        public static String CUSTOM_VIEW = "com.ecomtrading.mycustomlint.custom_view.MyButtonCV";
    }





}
