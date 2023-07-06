package org.yaoqiang.intellij.lang;

import com.intellij.lang.Language;
import com.intellij.lang.xml.XMLLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class BPMNLanguage extends XMLLanguage {

    public final static BPMNLanguage INSTANCE = new BPMNLanguage();

    private BPMNLanguage() {
        super(XMLLanguage.INSTANCE, "BPMN", "application/xml", "text/xml");
    }

    protected BPMNLanguage(@NotNull Language baseLanguage, @NonNls @NotNull String name, @NonNls @NotNull String @NotNull ... mime) {
        super(baseLanguage, name, mime);
    }

}
