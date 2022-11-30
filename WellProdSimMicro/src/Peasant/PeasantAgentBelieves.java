/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Peasant;

import Peasant.Utils.EmotionPwA;
import static Peasant.Utils.EmotionPwA.ANGER;
import static Peasant.Utils.EmotionPwA.SADNESS;
import Peasant.Utils.SensorData;
import Peasant.Utils.EmotionalData;
import Peasant.Utils.PeasantActivity;
import Peasant.Utils.PeasantCropPreference;
import Peasant.Utils.PeasantProfile;
import Peasant.Utils.Purpose;
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
    private PeasantAgentBelievesPurpose peasantPurpose;

    public PeasantAgentBelieves(String purpose) {
        peasantAgentBelieveEmotionalState = new PeasantAgentBelieveEmotionalState();
        peasantAgentBelieveState = new PeasantAgentBelieveState();
        peasantAgentBelieveInteractionState = new PeasantAgentBelieveInteractionState();
        peasantAgentBelieveActivityState = new PeasantAgentBelieveActivityState("Agricultor", this);
        peasantPurpose = new PeasantAgentBelievesPurpose(this);
        peasantPurpose.setPurpose(new Purpose("Agricultor"));

        System.out.println("V: " + peasantPurpose.getProfile());
    }

    //AQUI SE MANDA LO DE INFORMATIONFLOW
    //Aqui se accede a BD y se pide info de otros believes. 
    @Override
    public boolean update(InfoData si) {
        if (si != null && si instanceof EmotionalData) {
            EmotionalData se = (EmotionalData) si;
//            System.out.println("Emotional RobotAgentBelieves update Received: " + se.getInfo());
            if (se.getEmoEv() != null) {
                peasantAgentBelieveState.update(se);
            }
            peasantAgentBelieveEmotionalState.update(si);
        } else if (si != null) {
            SensorData infoRecibida = (SensorData) si;
//            System.out.println("RobotAgentBelieves update Received: " + infoRecibida.getDataP());
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
                    peasantPurpose.update(si);
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    public void feedbackActivity(double voiceFeedback) {
        double emotionFeedback = 0.0, emotionFeedbackAux = 0;
        PeasantActivity activity = peasantAgentBelieveActivityState.getCurrentActivity();
        Object activityInCourse = null;
        //List<Antecedente> antecedents = RESPwABDInterface.getActecedents();
        emotionFeedback = peasantAgentBelieveEmotionalState.getFeedbackEmotion();
        emotionFeedback = aproximateEmotionValue(emotionFeedback);

        //List<Antecedente> antecedentsForFeedback = getAntecedentsForFeedback(emotionFeedback, voiceFeedback, antecedents);
        System.out.println("ACTIVIDAD ACTUAL" + activity);

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
        PeasantActivity activity = peasantAgentBelieveActivityState.getCurrentActivity();
        Object activityInCourse = null;

        switch (activity) {
            case FARMING:
                activityInCourse = (PeasantCropPreference) peasantAgentBelieveActivityState.getCurrentCrop();
                break;

            case HARVEST:
                activityInCourse = (PeasantCropPreference) peasantAgentBelieveActivityState.getCurrentCrop();
                break;
        }

        return activityInCourse;

    }

    public double getEmocionPredominante() {
        double aux = getFeedbackEmotion();
        if (aux > 0 && valencia != 1) {
            valencia = 1;
            tiempoEmocionPredominante = System.currentTimeMillis();
        } else if (aux < 0 && valencia != -1) {
            valencia = -1;
            tiempoEmocionPredominante = System.currentTimeMillis();
        }
        return aux;
    }

    public double getFeedbackEmotion() {
        double emotionFeedback = 0.0;
        double auxEmotionAverage = 0.0;
        for (EmotionPwA entry : emoMap.keySet()) {

            auxEmotionAverage = getEmotionAverage(emoMap.get(entry));
            if (entry.equals(ANGER) || entry.equals(SADNESS)) {

                auxEmotionAverage *= -1;
            }
            if (Math.abs(auxEmotionAverage) > Math.abs(emotionFeedback)) {
                emotionFeedback = auxEmotionAverage;
            }
        }
        return emotionFeedback;
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

    public BPerfilPwA getbPerfilPwA() {
        return bPerfilPwA;
    }

    public void setbPerfilPwA(BPerfilPwA bPerfilPwA) {
        this.bPerfilPwA = bPerfilPwA;
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
