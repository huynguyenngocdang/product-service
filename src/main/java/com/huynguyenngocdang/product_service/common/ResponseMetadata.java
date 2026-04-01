package com.huynguyenngocdang.product_service.common;

import com.huynguyenngocdang.product_service.utils.DateUtils;
import org.slf4j.MDC;

public record ResponseMetadata(String requestId, String requestDatetime, String responseDatetime) {
    public static ResponseMetadata getCurrentMetadata() {
        return new ResponseMetadata(MDC.get("requestId"), MDC.get("requestDatetime"), DateUtils.getCurrentDatetime());
    }
}
