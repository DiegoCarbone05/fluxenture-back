package com.fluxenture.core.cd.infrastructure.output.persistence.entity;

import com.mongodb.lang.Nullable;
import com.fluxenture.core.cd.domain.Cd;
import com.fluxenture.core.cd.domain.Tnt;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "tnt")
public class CdEntity {
    @Id
    private String id;
    private int trackingNumber;
    private String emissionDate;
    private String employeeId;
    private String fileId;
    @Nullable
    private List<Tnt> tnt;
    private String obs;
    private Boolean trackingCompleted;
}
