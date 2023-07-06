package org.yaoqiang.intellij.editor;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaoqiang.bpmn.graph.io.BPMNCodec;
import org.yaoqiang.model.util.XMLModelUtils;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class BPMNFileEditor extends UserDataHolderBase implements FileEditor {

    @NotNull protected final VirtualFile myFile;

    @NotNull
    private final Document myDocument;

    @NotNull
    private final Project myProject;

    private final BPMNEditorDiagramTab editor;

    public BPMNFileEditor(@NotNull final Project project, @NotNull final VirtualFile file) {
        this.myFile = file;
        this.myDocument = FileDocumentManager.getInstance().getDocument(file);
        this.myProject = project;
        this.editor = new BPMNEditorDiagramTab(this, project, file);
    }

    @Override
    public @NotNull BPMNEditorDiagramTab getComponent() {
        return editor;
    }

    @NotNull
    @Override
    public VirtualFile getFile() {
        return myFile;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return editor;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) @NotNull String getName() {
        return "Diagram";
    }

    @Override
    public void setState(@NotNull FileEditorState state) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return myFile.isValid();
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void dispose() {

    }

    public void saveChanges() {
        ApplicationManager.getApplication().invokeLater(() -> {
            if (myFile.isValid()) {
                String content = XMLModelUtils.getXml(new BPMNCodec(editor.getGraph()).encode().getDocumentElement());
                ApplicationManager.getApplication().runWriteAction(() -> CommandProcessor.getInstance().executeCommand(myProject, () -> myDocument.setText(convertString(content)), "BPMN Diagram edit operation", null));
            }
        });
    }

    public void saveToFile() {
        ApplicationManager.getApplication().invokeLater(() -> {
            if (myFile.isValid()) {
                ApplicationManager.getApplication().runWriteAction(() -> FileDocumentManager.getInstance().saveDocument(myDocument));
            }
        });
    }

    private String convertString(String content) {
        return content.replaceAll("(\r\n|\n\r|\r)", "\n");
    }

}
