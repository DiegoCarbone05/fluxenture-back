package com.fluxenture.core.docs.infrastructure.output.persistence.entity;

import com.mongodb.lang.Nullable;
import com.fluxenture.core.docs.domain.EDocType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "docs")
public class DocEntity {
    @Id
    @Nullable
    private String id;
    private String employeeId;
    private EDocType type;
    private String driveFileId;
    private String uploadDate;
    private String description;
    private String user;
}
