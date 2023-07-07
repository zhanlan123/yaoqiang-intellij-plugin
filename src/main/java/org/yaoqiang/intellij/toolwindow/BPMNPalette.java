package org.yaoqiang.intellij.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import org.jetbrains.annotations.NotNull;
import org.yaoqiang.bpmn.editor.views.palette.ElementsPalette;
import org.yaoqiang.bpmn.editor.views.palette.PalettePane;

public class BPMNPalette extends SimpleToolWindowPanel {

    private Project myProject;

    public BPMNPalette(@NotNull Project project) {
        super(true, true);
        myProject = project;
        PalettePane elements = new ElementsPalette();
        elements.initArtifactsPalette();
        add(elements);
    }

}
