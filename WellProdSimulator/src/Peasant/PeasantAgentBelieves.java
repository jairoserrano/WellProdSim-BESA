/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import BESA.Log.ReportBESA;
import Peasant.EmotionalModel.PeasantEmotions;
import static Peasant.EmotionalModel.PeasantEmotions.ANGER;
import static Peasant.EmotionalModel.PeasantEmotions.SADNESS;
import Peasant.Utils.SensorData;
import Peasant.EmotionalModel.EmotionalData;
import Peasant.Utils.PeasantActivityType;
import Peasant.Utils.PeasantFarmingPreference;
import Peasant.Utils.PeasantHarvestPreference;
import Peasant.Utils.PeasantProfile;
import Peasant.Utils.PeasantPurpose;
import java.util.ArrayList;
import java.util.List;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author jairo
 */
public class PeasantAgentBelieves implements Believes {

    private PeasantAgentBelieveState peasantAgentBelieveState;
    private PeasantAgentBelieveEmotionalState peasantAgentBelieveEmotionalState;
    private PeasantAgentBelieveActivityState peasantAgentBelieveActivityState;
    private PeasantAgentBelieveInteractionState peasantAgentBelieveInteractionState;
    private PeasantAgentBelievesProfile peasantAgentBelievesProfile;

    public PeasantAgentBelieves(PeasantPurpose purpose) {
        peasantAgentBelieveEmotionalState = new PeasantAgentBelieveEmotionalState();
        peasantAgentBelieveState = new PeasantAgentBelieveState();
        peasantAgentBelieveInteractionState = new PeasantAgentBelieveInteractionState();
        peasantAgentBelieveActivityState = new PeasantAgentBelieveActivityState(purpose, this);
        peasantAgentBelievesProfile = new PeasantAgentBelievesProfile(this);
        ReportBESA.info("V: " + peasantAgentBelievesProfile.getProfile());
    }

    //AQUI SE MANDA LO DE INFORMATIONFLOW
    //Aqui se accede a BD y se pide info de otros believes. 
    @Override
    public boolean update(InfoData si) {
        if (si != null && si instanceof EmotionalData) {
            EmotionalData se = (EmotionalData) si;
            ReportBESA.info("Emotional PeasantAgentBelieves update Received: " + se.getInfo());
            if (se.getEmoEv() != null) {
                peasantAgentBelieveState.update(se);
            }
            peasantAgentBelieveEmotionalState.update(si);
        } else if (si != null) {
            SensorData infoRecibida = (SensorData) si;
            ReportBESA.info("PeasantAgentBelieves update Received: " + infoRecibida.getDataP());
            switch (infoRecibida.getDataType()) {
                case ACTIVITY:
                    peasantAgentBelieveActivityState.update(si);
                    break;
                case INTERACTION:
                    peasantAgentBelieveInteractionState.update(si);
                    break;
                case PEASANT:
                    peasantAgentBelieveState.update(si);
                    break;
                case PURPOSE:
                    peasantAgentBelievesProfile.update(si);
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    public void feedbackActivity(double voiceFeedback) {
        double emotionFeedback = 0.0, emotionFeedbackAux = 0;
        PeasantActivityType activity = peasantAgentBelieveActivityState.getCurrentActivity();
        Object activityInCourse = null;
        //List<Antecedente> antecedents = RESPwABDInterface.getActecedents();
        emotionFeedback = peasantAgentBelieveEmotionalState.getFeedbackEmotion();
        emotionFeedback = aproximateEmotionValue(emotionFeedback);

        //List<Antecedente> antecedentsForFeedback = getAntecedentsForFeedback(emotionFeedback, voiceFeedback, antecedents);
        ReportBESA.info("ACTIVIDAD ACTUAL" + activity);

        switch (activity) {
            case FARMING:
                //activityInCourse = (Preferenciaxcuento) bEstadoActividad.getCuentoActual();
                //ModeloRetroalimentacion<Preferenciaxcuento> modeloRetroCuento = new ModeloRetroalimentacion<>((Preferenciaxcuento) activityInCourse);
                //modeloRetroCuento.activityFeedback(antecedentsForFeedback);
                break;

            case HARVEST:
                //activityInCourse = (Preferenciaxcancion) bEstadoActividad.getCancionActual();
                //ModeloRetroalimentacion<Preferenciaxcancion> modelRetroCancion = new ModeloRetroalimentacion<>((Preferenciaxcancion) activityInCourse);
                //modelRetroCancion.activityFeedback(antecedentsForFeedback);
                break;
        }

    }

    private double aproximateEmotionValue(double emotionFeedback) {
        double aproximation = 0.0;
        final double VERY_PLEASANT = 1;
        final double PLEASANT = 0.65;
        final double LITTLE_PLEASANT = 0.35;
        final double VERY_UNPLEASANT = -1;
        final double UNPLEASANT = -0.65;
        final double LITTLE_UNPLEASANT = -0.35;
        final double ZERO = 0.0;

        if (emotionFeedback > PLEASANT) {
            aproximation = VERY_PLEASANT;
        } else {
            if (emotionFeedback > LITTLE_PLEASANT) {
                aproximation = PLEASANT;
            } else {
                if (emotionFeedback > ZERO) {
                    aproximation = LITTLE_PLEASANT;
                } else {
                    if (emotionFeedback < UNPLEASANT) {
                        aproximation = VERY_UNPLEASANT;
                    } else {
                        if (emotionFeedback < LITTLE_PLEASANT) {
                            aproximation = UNPLEASANT;
                        } else {
                            aproximation = LITTLE_UNPLEASANT;
                        }
                    }
                }
            }
        }

        return aproximation;
    }

    public Object getActivityInCourse() {
        PeasantActivityType activity = peasantAgentBelieveActivityState.getCurrentActivity();
        Object activityInCourse = null;

        switch (activity) {
            case FARMING:
                activityInCourse = (PeasantFarmingPreference) peasantAgentBelieveActivityState.getCurrentFarming();
                break;

            case HARVEST:
                activityInCourse = (PeasantHarvestPreference) peasantAgentBelieveActivityState.getCurrentHarvest();
                break;
        }

        return activityInCourse;

    }

    public PeasantAgentBelieveInteractionState getPeasantAgentBelieveInteractionState() {
        return peasantAgentBelieveInteractionState;
    }

    public void setPeasantAgentBelieveInteractionState(PeasantAgentBelieveInteractionState peasantAgentBelieveInteractionState) {
        this.peasantAgentBelieveInteractionState = peasantAgentBelieveInteractionState;
    }

    public PeasantAgentBelieveEmotionalState getPeasantAgentBelieveEmotionalState() {
        return peasantAgentBelieveEmotionalState;
    }

    public void setPeasantAgentBelieveEmotionalState(PeasantAgentBelieveEmotionalState peasantAgentBelieveEmotionalState) {
        this.peasantAgentBelieveEmotionalState = peasantAgentBelieveEmotionalState;
    }

    public PeasantAgentBelieveActivityState getPeasantAgentBelieveActivityState() {
        return peasantAgentBelieveActivityState;
    }

    public void setPeasantAgentBelieveActivityState(PeasantAgentBelieveActivityState peasantAgentBelieveActivityState) {
        this.peasantAgentBelieveActivityState = peasantAgentBelieveActivityState;
    }

    public PeasantAgentBelievesProfile getPeasantProfile() {
        return peasantAgentBelievesProfile;
    }

    public void setPeasantProfile(PeasantAgentBelievesProfile peasantAgentBelievesProfile) {
        this.peasantAgentBelievesProfile = peasantAgentBelievesProfile;
    }

    public PeasantAgentBelieveState getPeasantAgentBelieveState() {
        return peasantAgentBelieveState;
    }

    public void setPeasantAgentBelieveState(PeasantAgentBelieveState peasantAgentBelieveState) {
        this.peasantAgentBelieveState = peasantAgentBelieveState;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }
}
