package org.yaoqiang.intellij.editor;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import org.yaoqiang.asaf.ASAF;
import org.yaoqiang.asaf.ResourceMap;
import org.yaoqiang.bpmn.editor.actions.AboutYaoqiangAction;
import org.yaoqiang.bpmn.editor.actions.SaveAsPNGAction;
import org.yaoqiang.bpmn.editor.actions.SaveAsSVGAction;
import org.yaoqiang.bpmn.editor.views.BPMNFileTab;
import org.yaoqiang.bpmn.graph.view.BPMNGraph;
import org.yaoqiang.graph.swing.StyleToolBar;

public class BPMNEditorDiagramTab extends JPanel {

    @NotNull
    private final BPMNFileTab fileTab;

    @NotNull
    private final BPMNFileEditor myEditor;

    @NotNull
    private final Project myProject;

    @NotNull
    private final VirtualFile myFile;

    public BPMNEditorDiagramTab(@NotNull BPMNFileEditor editor, Project project, @NotNull VirtualFile file) {
        setLayout(new BorderLayout());
        this.myProject = project;
        this.myEditor = editor;
        this.myFile = file;

        this.fileTab = new BPMNFileTab(new File(file.getPath()), false, false);
        installResizeHandler();

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));
        toolbarPanel.setBorder(null);

        toolbarPanel.add(createSaveToolBar());
        toolbarPanel.add(new StyleToolBar(fileTab));
        toolbarPanel.add(createEditToolBar());

        add(toolbarPanel, BorderLayout.NORTH);
        add(fileTab.getViewToolBar(), BorderLayout.EAST);
        add(fileTab.getAlignToolBar(), BorderLayout.WEST);

        add(fileTab.getDiagramPane());
    }

    private JToolBar createSaveToolBar() {
        JToolBar saveBar = new JToolBar();
        saveBar.setFloatable(false);

        saveBar.add(new SaveAsPNGAction() {
            public boolean isEnabled() {
                return true;
            }

            public void actionPerformed(ActionEvent e) {
                super.actionPerformed(e);
                myFile.getParent().refresh(true, false);
            }
        });
        saveBar.add(new SaveAsSVGAction() {
            public boolean isEnabled() {
                return true;
            }

            public void actionPerformed(ActionEvent e) {
                super.actionPerformed(e);
                myFile.getParent().refresh(true, false);
            }
        });

        return saveBar;
    }

    private JToolBar createEditToolBar() {
        JToolBar editBar = new JToolBar();
        editBar.setFloatable(false);

        editBar.add(ASAF.getAction("undo"));
        editBar.add(ASAF.getAction("redo"));
        editBar.addSeparator();
        editBar.add(ASAF.getAction("cut"));
        editBar.add(ASAF.getAction("copy"));
        editBar.add(ASAF.getAction("paste"));
        editBar.add(ASAF.getAction("delete"));
        editBar.addSeparator();
        Action about = new AboutYaoqiangAction();
        about.putValue(Action.SMALL_ICON, ResourceMap.getImageIcon("yaoqiang.Menu.icon"));
        editBar.add(about);

        return editBar;
    }

    private void installResizeHandler() {
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                fileTab.getGraphComponent().zoomAndCenter();
            }
        });
    }

    public BPMNGraph getGraph() {
        return fileTab.getGraph();
    }

    @NotNull
    public BPMNFileTab getFileTab() {
        return fileTab;
    }

}
