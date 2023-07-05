package org.yaoqiang.intellij.lang;

import com.intellij.lang.Language;

public class BPMNLanguage extends Language {

    public final static BPMNLanguage INSTANCE = new BPMNLanguage();

    private BPMNLanguage() {
        super("BPMN", "application/xml", "text/xml");
    }

}
