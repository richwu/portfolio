package name.abuchen.portfolio.ui.views.columns;

import name.abuchen.portfolio.model.Account;
import name.abuchen.portfolio.model.Adaptor;
import name.abuchen.portfolio.model.Classification;
import name.abuchen.portfolio.model.InvestmentPlan;
import name.abuchen.portfolio.model.Named;
import name.abuchen.portfolio.model.Portfolio;
import name.abuchen.portfolio.model.Security;
import name.abuchen.portfolio.ui.Messages;
import name.abuchen.portfolio.ui.PortfolioPlugin;
import name.abuchen.portfolio.ui.util.Column;
import name.abuchen.portfolio.ui.util.ColumnViewerSorter;
import name.abuchen.portfolio.ui.util.StringEditingSupport;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

public class NameColumn extends Column
{
    public static class NameColumnLabelProvider extends ColumnLabelProvider
    {
        @Override
        public String getText(Object e)
        {
            Named n = Adaptor.adapt(Named.class, e);
            return n != null ? n.getName() : null;
        }

        @Override
        public Image getImage(Object e)
        {
            Named n = Adaptor.adapt(Named.class, e);

            if (n instanceof Security)
                return PortfolioPlugin.image(PortfolioPlugin.IMG_SECURITY);
            else if (n instanceof Account)
                return PortfolioPlugin.image(PortfolioPlugin.IMG_ACCOUNT);
            else if (n instanceof Portfolio)
                return PortfolioPlugin.image(PortfolioPlugin.IMG_PORTFOLIO);
            else if (n instanceof InvestmentPlan)
                return PortfolioPlugin.image(PortfolioPlugin.IMG_INVESTMENTPLAN);
            else if (n instanceof Classification)
                return PortfolioPlugin.image(PortfolioPlugin.IMG_CATEGORY);
            else
                return null;
        }

        @Override
        public String getToolTipText(Object e)
        {
            Named element = Adaptor.adapt(Named.class, e);
            if (element == null)
                return null;
            else if (element instanceof Security)
                return ((Security) element).toInfoString();
            else
                return element.getName();
        }
    }

    public NameColumn()
    {
        this("name"); //$NON-NLS-1$
    }

    public NameColumn(String id)
    {
        this(id, Messages.ColumnName, SWT.LEFT, 300);
    }

    public NameColumn(String id, String label, int style, int defaultWidth)
    {
        super(id, label, style, defaultWidth);

        setLabelProvider(new NameColumnLabelProvider());
        setSorter(ColumnViewerSorter.create(Named.class, "name")); //$NON-NLS-1$
        new StringEditingSupport(Named.class, "name").setMandatory(true).attachTo(this); //$NON-NLS-1$
    }
}
