package de.workshop.smells.misleading_names;

public class GuaranteedDeliveryService {

    public boolean deliverGuaranteed(String shipmentId) {
        return shipmentId != null && !shipmentId.isBlank();
    }
}
