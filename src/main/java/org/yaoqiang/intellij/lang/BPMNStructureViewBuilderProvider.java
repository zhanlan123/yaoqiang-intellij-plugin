package org.yaoqiang.intellij.lang;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.ide.structureView.impl.xml.XmlStructureViewTreeModel;
import com.intellij.ide.structureView.xml.XmlStructureViewBuilderProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.xml.XmlFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaoqiang.intellij.lang.BPMNFileType;

public class BPMNStructureViewBuilderProvider implements XmlStructureViewBuilderProvider {

    @Override
    public @Nullable StructureViewBuilder createStructureViewBuilder(@NotNull XmlFile file) {
        if (file.getViewProvider().getFileType() != BPMNFileType.INSTANCE) return null;

        return new TreeBasedStructureViewBuilder() {
            @Override
            public boolean isRootNodeShown() {
                return false;
            }

            @Override
            @NotNull
            public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
                return new XmlStructureViewTreeModel(file, editor);
            }
        };
    }
}
