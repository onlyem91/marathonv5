package net.sourceforge.marathon.component;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import nl.prorail.verdeling.mmi.beherentoedelingsplan.view.data.PlanelementDataSet;
import nl.prorail.verdeling.mmi.beherentoedelingsplan.view.go.Planelement;
import nl.prorail.verdeling.mmi.beherentoedelingsplan.view.grafiekstrook.Grafiekstrook;

import net.sourceforge.marathon.javaagent.components.IlvChartAreaElement;
import net.sourceforge.marathon.javarecorder.IJSONRecorder;
import net.sourceforge.marathon.javarecorder.JSONOMapConfig;

/**
 * Marathon klasse om te kunnen interacteren met ilog IlvChartArea componenten.
 * 
 * @author NB
 *
 */
public class RIlvChartArea extends RComponent {

    private String componentID;

    private static final Logger LOGGER = Logger.getLogger(IlvChartAreaElement.class.getName());

    public RIlvChartArea(Component source, JSONOMapConfig omapConfig, Point point, IJSONRecorder recorder) {
        super(source, omapConfig, point, recorder);

        component = source;
    }

    /**
     * Determines and sets the {@link #grafiekStrook} object. Sets the value of {{@link #componentID} with the ID of the selected planelement on the
     * grafiekstrook.
     */
    private void processComponent() {
        try {
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
        } catch (ClassCastException e) {
            LOGGER.warning("Class cast exception: " + e.getMessage());
        }
    }

    @Override
    public String getTagName() {
        return componentID;
    }

    @Override
    protected void mouseButton1Pressed(MouseEvent me) {
        // TODO: We must record a click before processing the GrafiekStrook, because we need the selected planelement. (So it has to be selected first)
        Grafiekstrook grafiekStrook = (Grafiekstrook)component.getParent();
        MouseEvent clicked = new MouseEvent(component, MouseEvent.MOUSE_CLICKED, me.getWhen(), me.getModifiers(), me.getX(), me.getY(), me.getClickCount(),
                me.isPopupTrigger());
        grafiekStrook.dispatchEvent(clicked);

        processComponent();
        recorder.recordSelect(this, componentID);
    }

    @Override
    protected void mousePressed(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() == 1 && !me.isAltDown() && !me.isMetaDown() && !me.isAltGraphDown() &&
                !me.isControlDown()) {
            mouseButton1Pressed(me);
        } else {
            recorder.recordClick2(this, me, true);
        }
    }

}
