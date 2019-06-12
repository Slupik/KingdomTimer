package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import jw.kingdom.hall.kingdomtimer.webui.domain.screen.ControlledScreenBase;
import netscape.javascript.JSObject;


import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class PanelMainView extends ControlledScreenBase {
    private final static String PAGE_PATH = "layout/window/panel/page/index.html";



    //All delegates must be stored as properties of class, otherwise will be deleted by garbage collector.
    JavaDelegate customApp = new JavaDelegate();
    JavaLoggerDelegate bridge = new JavaLoggerDelegate();
    JavascriptDelegate application = new JavascriptDelegate();

    @FXML
    private StackPane mainContainer;

    @FXML
    private Button checkButton;


    @FXML
    private WebView wvUI;

    @Override
    protected void onSetup() {
        super.onSetup();
        WebEngine webEngine = wvUI.getEngine();
        final URL urlHello = getClass().getClassLoader().getResource(PAGE_PATH);
        webEngine.load(urlHello.toExternalForm());
        webEngine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                    if(newState == Worker.State.SUCCEEDED){
                        JSObject window = (JSObject)webEngine.executeScript("window");
                        window.setMember("app", application);
                        window.setMember("java", bridge);
                        webEngine.executeScript("console.log = function(message)\n" +
                                "{\n" +
                                "    java.log(message);\n" +
                                "};");
                    }
                });

        customApp.parentConnector = new FrontendConnector(wvUI);

    }

    @FXML
    void checkAction(ActionEvent event) {
        customApp.check();
    }

    @FXML
    void printDateAction(ActionEvent event) {
        customApp.printDate(new Date());
    }

    @FXML
    void testAction(ActionEvent event) {
        customApp.test();
    }




    @Override
    protected Region getMainContainer() {
        return mainContainer;
    }


    public class JavaDelegate extends JavaApp{
        @JavaScriptExecutable(name = "check()")
        public void check(){ super.execute(); }

        @JavaScriptExecutable(name = "test()")
        public void test(){ super.execute(); }

        @JavaScriptExecutable(name = "doSomething()")
        public void doSomething(){ super.execute(); }

        @JavaScriptExecutable(name = "printObject()")
        public void printDate(Date date){ super.execute(date); }
    }

    public class JavaLoggerDelegate
    {
        public void log(String text)
        {
            System.out.println(text);
        }
    }

    public class JavascriptDelegate {
        public void callFromJavascript(String msg) {
            System.out.println(msg);
            checkButton.setText("XDDD");
        }
    }

    public class JavaApp {

        FrontendConnector parentConnector;

        private void execute(Object param){
            Class clazz = null;
            try {

                StackTraceElement element = new Throwable()
                        .getStackTrace()[2];
                clazz = Class.forName(element.getClassName());
                for (Method m : clazz.getMethods()) {
                    if (m.getName().equals(element.getMethodName())) {
                        JavaScriptExecutable annotation = m.getAnnotation(JavaScriptExecutable.class);

                        JavaScriptRequest request = new JavaScriptRequest(param,annotation.name());

                        parentConnector.execute(request);
                        return;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            throw new IllegalStateException("Method not found!");
        }



        private void execute(){

            Class clazz = null;
            try {

                StackTraceElement element = new Throwable()
                        .getStackTrace()[2];
                clazz = Class.forName(element.getClassName());
                for (Method m : clazz.getMethods()) {
                    if (m.getName().equals(element.getMethodName())) {
                        JavaScriptExecutable annotation = m.getAnnotation(JavaScriptExecutable.class);

                        JavaScriptRequest request = new JavaScriptRequest(annotation.name());

                        parentConnector.execute(request);
                        return;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




            throw new IllegalStateException("Method not found!");
        }

        public void print(String string) {
            System.out.println(string);
        }

        public void exit() {
            Platform.exit();
        }


    }


    public class JavaScriptRequest {
        Object parameter;
        String methodName;

        public JavaScriptRequest(Object parameter, String methodName) {
            this.parameter = parameter;
            this.methodName = methodName;
        }

        public JavaScriptRequest(String methodName) {
            this.methodName = methodName;
            this.parameter = "";
        }



        public String getValue() {
            String post = methodName.substring(methodName.length()-1);
            String prefix =  methodName.substring(0,methodName.length()-1);
            String value = new Gson().toJson(parameter);
            return prefix + value + post;
        }
    }


    public class FrontendConnector {
        WebView parentView;
        public FrontendConnector(WebView parentView) {
            this.parentView = parentView;
        }


        protected void execute(JavaScriptRequest request) {
            String value = request.getValue();
            System.out.println("RX:" + value);
            wvUI.getEngine().executeScript(value);
        }
    }
}
