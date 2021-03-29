package com.ecomtrading.mycustomlintbutton;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.checks.infrastructure.TestLintResult;
import com.android.tools.lint.checks.infrastructure.TestLintTask;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

@RunWith(JUnit4.class)
public class CustomButtonDetectorTest extends LintDetectorTest {
    TestFile java = java("package com.ecomtrading.mycustomlintbutton; " +
            "public class LintCallTest{" +
            "public static void main(String[] args) {" +
            "   MyButtonCV checkTv = new MyButtonCV();" +
            "MyButtonCV checkTv1 = new MyButtonCV();" +

            "}" +
            "}"
    ).indented();

    TestFile java1 = java("package com.ecomtrading.mycustomlint;\n" +
            "\n" +
            "import android.os.Bundle;\n" +
            "import android.view.LayoutInflater;\n" +
            "import android.view.View;\n" +
            "import android.view.ViewGroup;\n" +
            "import android.widget.EditText;\n" +
            "\n" +
            "import androidx.annotation.NonNull;\n" +
            "import androidx.fragment.app.Fragment;\n" +
            "import androidx.navigation.fragment.NavHostFragment;\n" +
            "\n" +
            "import com.ecomtrading.mycustomlint.custom_view.CheckTv;\n" +
            "import com.ecomtrading.mycustomlint.custom_view.MyButtonCV;\n" +
            "\n" +
            "public class FirstFragment extends Fragment {\n" +
            "\n" +
            "    @Override\n" +
            "    public View onCreateView(\n" +
            "            LayoutInflater inflater, ViewGroup container,\n" +
            "            Bundle savedInstanceState\n" +
            "    ) {\n" +
            "        // Inflate the layout for this fragment\n" +
            "        return inflater.inflate(R.layout.fragment_first, container, false);\n" +
            "    }\n" +
            "\n" +
            "    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {\n" +
            "        super.onViewCreated(view, savedInstanceState);\n" +
            "\n" +
            "        EditText editText = new EditText(getContext());\n" +
            "\n" +
            "        MyButtonCV checkTv = new MyButtonCV(getContext());\n" +
            "\n" +
            "        methoid();\n" +
            "        //checkTv.setItem();\n" +
            "\n" +
            "        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {\n" +
            "            @Override\n" +
            "            public void onClick(View view) {\n" +
            "                NavHostFragment.findNavController(FirstFragment.this)\n" +
            "                        .navigate(R.id.action_FirstFragment_to_SecondFragment);\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "\n" +
            "    private void methoid(){\n" +
            "        MyButtonCV myButtonCV = new MyButtonCV(getContext());\n" +
            "    }\n" +
            "}"
    ).indented();


    @Test
    public void shouldDetectMyButtonCvCall() {
        TestLintResult result = lint().files(java1).allowMissingSdk().run();
        //result.expectCount(2);
        result.expect("src/com/ecomtrading/mycustomlint/FirstFragment.java:32: Warning: According to the official docs,'OkHttp performs best when you create a single OkHttpClient instance and reuse it for all of your HTTP calls.' This is because each client holds its own connection pool and thread pools.Reusing connections and threads reduces latency and saves memory.Conversely, creating a client for each request wastes resources on idle pools.More details at https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/ [MoreThanOneOkHttpClientDetector]\n" +
                "        MyButtonCV checkTv = new MyButtonCV(getContext());\n" +
                "                             ~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "src/com/ecomtrading/mycustomlint/FirstFragment.java:47: Warning: According to the official docs,'OkHttp performs best when you create a single OkHttpClient instance and reuse it for all of your HTTP calls.' This is because each client holds its own connection pool and thread pools.Reusing connections and threads reduces latency and saves memory.Conversely, creating a client for each request wastes resources on idle pools.More details at https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/ [MoreThanOneOkHttpClientDetector]\n" +
                "        MyButtonCV myButtonCV = new MyButtonCV(getContext());\n" +
                "                                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "0 errors, 2 warnings");

    }

    @Override
    protected Detector getDetector() {
        return new CustomButtonDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(CustomButtonDetector.buttonIssue);
    }
}
