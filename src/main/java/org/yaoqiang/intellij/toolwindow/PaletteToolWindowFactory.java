package org.yaoqiang.intellij.toolwindow;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

public class PaletteToolWindowFactory implements ToolWindowFactory, DumbAware {

    public static final String ID = "BPMN Palette";

    private BPMNPalette palette;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        palette = new BPMNPalette(project);
        ContentManager contentManager = toolWindow.getContentManager();
        Content content = contentManager.getFactory().createContent(palette, null, false);
        content.setCloseable(false);
        contentManager.addContent(content);
        contentManager.setSelectedContent(content, true);

    }

    @Override
    public void init(@NotNull ToolWindow toolWindow) {
        toolWindow.setAvailable(true, null);
    }

}
