package name.abuchen.portfolio.ui.handlers;

import javax.inject.Named;

import name.abuchen.portfolio.model.Client;
import name.abuchen.portfolio.ui.Messages;
import name.abuchen.portfolio.ui.UIConstants;
import name.abuchen.portfolio.ui.wizards.client.NewClientWizard;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class NewFileHandler
{

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, MApplication app, EPartService partService,
                    EModelService modelService)
    {
        NewClientWizard wizard = new NewClientWizard();
        WizardDialog dialog = new WizardDialog(shell, wizard);
        if (dialog.open() == Window.OK)
        {
            MPart part = partService.createPart(UIConstants.Part.PORTFOLIO);
            part.setLabel(Messages.LabelUnnamedXml);
            part.getTransientData().put(Client.class.getName(), wizard.getClient());

            MPartStack stack = (MPartStack) modelService.find(UIConstants.PartStack.MAIN, app);
            stack.getChildren().add(part);

            partService.showPart(part, PartState.ACTIVATE);
        }
    }
}
