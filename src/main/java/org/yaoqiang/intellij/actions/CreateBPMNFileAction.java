package org.yaoqiang.intellij.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaoqiang.intellij.lang.BPMNBundle;
import org.yaoqiang.intellij.lang.BPMNFileType;

import java.util.HashMap;
import java.util.Map;

public class CreateBPMNFileAction extends CreateFileFromTemplateAction {

    @NonNls private static final String DEFAULT_BPMN_TEMPLATE_PROPERTY = "DefaultBPMNFileTemplate";

    private static final String INTERNAL_TEMPLATE_NAME = "BPMN 2.0 XML File.bpmn";

    public static final Key<Boolean> NEW_CREATED = Key.create("newCreated");

    public CreateBPMNFileAction() {
        super(BPMNBundle.messagePointer("bpmn.action.new.file.name"), BPMNBundle.messagePointer("bpmn.action.new.file.description"),
                BPMNFileType.INSTANCE.getIcon());
    }

    @Override
    protected String getDefaultTemplateProperty() {
        return DEFAULT_BPMN_TEMPLATE_PROPERTY;
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder
            .setTitle(BPMNBundle.message("bpmn.action.new.file.dialog.title"))
            .addKind(BPMNBundle.message("bpmn.action.new.file.name"), BPMNFileType.INSTANCE.getIcon(), INTERNAL_TEMPLATE_NAME);

    }

    @Override
    protected @NlsContexts.Command String getActionName(PsiDirectory directory, @NonNls @NotNull String newName, @NonNls String templateName) {
        return BPMNBundle.message("bpmn.action.new.file.name");
    }

    protected PsiFile createFileFromTemplate(final String name, final FileTemplate template, final PsiDirectory dir) {
        return createFileFromTemplate(name, template, dir, getDefaultTemplateProperty(), true);
    }

    @Nullable
    public static PsiFile createFileFromTemplate(@Nullable String name,
                                                 @NotNull FileTemplate template,
                                                 @NotNull PsiDirectory dir,
                                                 @Nullable String defaultTemplateProperty,
                                                 boolean openFile) {
        PsiFile psiFile = createFileFromTemplate(name, template, dir, defaultTemplateProperty, openFile, getLiveTemplateDefaults());
        psiFile.getVirtualFile().putUserData(NEW_CREATED, true);
        return psiFile;
    }

    @Nullable
    private static Map<String, String> getLiveTemplateDefaults() {
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("SYSTEM_TIME", "_" + System.currentTimeMillis());
        variables.put("DEF_NAME", "");
        return variables;
    }

}
