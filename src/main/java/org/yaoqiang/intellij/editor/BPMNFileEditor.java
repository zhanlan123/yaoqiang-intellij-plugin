package org.yaoqiang.intellij.editor;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class BPMNFileEditor extends UserDataHolderBase implements FileEditor {

    @NotNull protected final VirtualFile myFile;

    private final BPMNEditorDiagramTab editor;

    public BPMNFileEditor(@NotNull final Project project, @NotNull final VirtualFile file) {
        this.myFile = file;
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

}
