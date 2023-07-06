package org.yaoqiang.intellij.editor;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class BPMNEditorDiagramTab extends JPanel {

    public BPMNEditorDiagramTab(@NotNull BPMNFileEditor editor, Project project, @NotNull VirtualFile file) {
        setLayout(new BorderLayout());
    }

}
