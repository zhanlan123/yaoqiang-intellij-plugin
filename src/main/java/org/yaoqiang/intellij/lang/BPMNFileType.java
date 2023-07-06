package org.yaoqiang.intellij.lang;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.DomSupportEnabled;
import com.intellij.ide.highlighter.XmlLikeFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class BPMNFileType extends XmlLikeFileType {

    public static final BPMNFileType INSTANCE = new BPMNFileType();

    public static final String DEFAULT_EXTENSION = "bpmn";
    public static final String DOT_DEFAULT_EXTENSION = "." + DEFAULT_EXTENSION;

    protected BPMNFileType() {
        super(BPMNLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "BPMN";
    }

    @Override
    public @NotNull String getDescription() {
        return "BPMN 2.0 File";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.Xml;
    }
}
