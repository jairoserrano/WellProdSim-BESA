package Peasant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author jairo
 */
public class PeasantHarvestTask extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();
    private int num;

    public PeasantHarvestTask() {

        num = 0;
//        System.out.println("--- Task Recibir Retroalimentacion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recibir Retroalimentacion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) parameters;
        //ServiceDataRequest srb;
        if (blvs.getbEstadoInteraccion().getRetroalimentacionValue() == null) {
            if (blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.RETROCANCIONTOPIC)) {
                if (num == 1) {
                    System.out.println("HOLA 2 " + num + "  " + blvs.getbEstadoInteraccion().getRetroalimentacionValue());
                    infoServicio = new HashMap<>();
                    infoServicio.put("SAY", "Podria hacerte una pregunta?");
                    //srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                    ResPwaUtils.requestService(srb, blvs);
                    num++;
                }
                num++;
            }
            setTaskWaitingForExecution();

        } else {
            String retroalimentacion = blvs.getbEstadoInteraccion().getRetroalimentacionValue();
            List<String> resulset = Arrays.asList(retroalimentacion.split(" "));
            Double respuestaRetroalimentacion = -1.0;
            if (resulset != null) {
                HashMap<String, Long> resultados = new HashMap<>();
                resultados.put("Tres", resulset.stream().filter(retroa -> retroa.equalsIgnoreCase("Tres")).count() * 3);
                resultados.put("Dos", resulset.stream().filter(retroa -> retroa.equalsIgnoreCase("Dos")).count() * 2);
                resultados.put("Uno", resulset.stream().filter(retroa -> retroa.equalsIgnoreCase("Uno")).count());
                Double resulRetroAlimentacion = Double.valueOf(resultados.get("Tres") + resultados.get("Dos") + resultados.get("Uno") / resulset.size());
                if (resulRetroAlimentacion > 2.5) {
                    respuestaRetroalimentacion = 1.0;
                }
                if (resulRetroAlimentacion <= 2.5 && resulRetroAlimentacion >= 1.5) {
                    respuestaRetroalimentacion = 0.5;
                }
                if (resulRetroAlimentacion < 1.5) {
                    respuestaRetroalimentacion = 0.0;
                }

                blvs.feedbackActivity(respuestaRetroalimentacion);
                infoServicio = new HashMap<>();
                infoServicio.put("SAY", "Gracias por tus consejos!");
                srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
                ResPwaUtils.requestService(srb, blvs);
                ResPwaUtils.deactivateTopic(PepperTopicsNames.RETROCANCIONTOPIC, parameters);

            } else {
                setTaskWaitingForExecution();
            }
        }

    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recibir Retroalimentacion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recibir Retroalimentacion ---");
        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
    }

    @Override
    public boolean checkFinish(Believes believes) {

        PeasantAgentBelieves blvs = (PeasantAgentBelieves) believes;
        if (!blvs.getbEstadoInteraccion().isTopicoActivo(PepperTopicsNames.RETROCANCIONTOPIC)) {
//            ResPwaUtils.activateTopic(PepperTopicsNames.BLANKTOPIC, believes);
            num = 0;
            return false;
        }
        return false;
    }
}
