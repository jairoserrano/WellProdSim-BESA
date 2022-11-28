package wpsMain.agents.peasant;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmotionalAxis {
    private final String positiveName;
    private final String negativeName;
    private final Map<String, Float> eventInfluence;
    private float currentValue;
    private float baseValue;
    private Float forgetFactor = 0.0f;
    private Date lastForgetUpdateTime;

    public EmotionalAxis(String positiveName, String negativeName, float currentValue, float baseValue, Float forgetFactor) {
        this.positiveName = positiveName;
        this.negativeName = negativeName;
        this.forgetFactor = forgetFactor;
        this.setCurrentValue(currentValue);
        this.setBaseValue(baseValue);
        eventInfluence = new HashMap<>();
    }

    private static float applyForgetFactor(float _baseValue, float _currentValue, Date lastUpdateTime, Float forgetFactor) {
        if (forgetFactor != null) {
            Date now = new Date();
            long tDif = now.getTime() - lastUpdateTime.getTime();
            float slope = ((_baseValue - _currentValue > 0) ? 1 : -1) * forgetFactor / 1000;
            float value = (slope * tDif) + _currentValue;

            if (slope > 0) {
                if (value > _baseValue) {
                    value = _baseValue;
                }
            } else if (slope < 0) {
                if (value <= _baseValue) {
                    value = _baseValue;
                }
            }
            return value;
        }
        return _currentValue;
    }

    public String getPositiveName() {
        return positiveName;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public float getCurrentValue() {
        float newValue = applyForgetFactor(getBaseValue(), currentValue, lastForgetUpdateTime, forgetFactor);
        this.setCurrentValue(newValue);
        return currentValue;
    }

    protected final void setCurrentValue(float value) {
        this.currentValue = EmotionalAxisUtils.checkNegativeOneToOneLimits(value);
        this.lastForgetUpdateTime = new Date();
    }

    public float getBaseValue() {
        return baseValue;
    }

    protected final void setBaseValue(float value) {
        this.baseValue = EmotionalAxisUtils.checkNegativeOneToOneLimits(value);
    }

    public float getActivationValue() {
        return Math.abs(baseValue - getCurrentValue());
    }

    public Float getForgetFactor() {
        return forgetFactor;
    }

    public void setForgetFactor(Float forgetFactor) {
        this.forgetFactor = forgetFactor;
    }

    public void setEventInfluence(String eventName, float influence) {
        eventInfluence.put(eventName, EmotionalAxisUtils.checkZeroToOneLimits(influence));
    }

    public Float getEventInfluence(String eventName) {
        return eventInfluence.get(eventName);
    }

    protected Map<String, Float> getEventInfluences() {
        return eventInfluence;
    }

    public void setEventInfluences(Map<String, Float> evInfluences) {
        if (evInfluences != null) {
            Iterator itr = evInfluences.keySet().iterator();
            if (itr != null) {
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    Float value = evInfluences.get(key);
                    setEventInfluence(key, value);
                }
            }
        }
    }

    protected void updateIntensity(String event, float intensity) {
        Float influence = getEventInfluence(event);
        if (influence != null) {
            intensity = influence * intensity;
            setCurrentValue(getCurrentValue() + intensity);
        }
    }

    @Override
    public String toString() {
        String str = this.positiveName + "/" + this.negativeName
                + " {Value:" + this.getCurrentValue() + " Base:" + this.getBaseValue() + "}";
        str += " EvInf: " + eventInfluence.toString();
        return str;
    }

    @Override
    public EmotionalAxis clone() {
        EmotionalAxis e = new EmotionalAxis(this.positiveName, this.negativeName, this.getCurrentValue(), this.baseValue, this.forgetFactor);
        Iterator itr = eventInfluence.keySet().iterator();
        if (itr != null) {
            while (itr.hasNext()) {
                String key = (String) itr.next();
                Float value = eventInfluence.get(key);
                e.setEventInfluence(key, value);
            }
        }
        return e;
    }
}
