package jw.kingdom.hall.kingdomtimer.webui.view.panel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JavaScriptExecutable {
    public String name() default "";
    //public Class param() default Void.class;
}

