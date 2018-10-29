package net.sourceforge.marathon.javaagent.components;

import java.awt.Component;
import java.util.logging.Logger;

import nl.prorail.verdeling.mmi.beherentoedelingsplan.view.data.PlanelementDataSet;
import nl.prorail.verdeling.mmi.beherentoedelingsplan.view.go.Planelement;
import nl.prorail.verdeling.mmi.beherentoedelingsplan.view.grafiekstrook.Grafiekstrook;

import ilog.views.chart.IlvChart;
import net.sourceforge.marathon.javaagent.AbstractJavaElement;
import net.sourceforge.marathon.javaagent.IJavaAgent;
import net.sourceforge.marathon.javaagent.JavaAgentException;
import net.sourceforge.marathon.javaagent.JavaTargetLocator.JWindow;

public class IlvChartAreaElement extends AbstractJavaElement {

    private static final Logger LOGGER = Logger.getLogger(IlvChartAreaElement.class.getName());


    private String componentID;

    public IlvChartAreaElement(Component component, IJavaAgent driver, JWindow window) {
        super(component, driver, window);

        this.component = (IlvChart.Area)component;
        Grafiekstrook grafiekStrook = (Grafiekstrook)component.getParent();

        if (grafiekStrook != null) {
            PlanelementDataSet planElement = grafiekStrook.getGeselecteerdeDataSet();
            if (planElement != null) {
                Planelement<?> pe = planElement.getPlanelement();
                if (pe != null) {
                    componentID = pe.getIdentificatie();
                }
            }
        }
    }

    @Override
    public boolean marathon_select(String value) {
        if (component == null) {
            throw new JavaAgentException("Null value returned by getEditor() on IlvChartArea", null);
        } else if (value.equals(componentID)) {
            click();
        }

        return true;
    }

}
