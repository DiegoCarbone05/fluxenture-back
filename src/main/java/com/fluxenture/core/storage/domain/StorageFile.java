package com.fluxenture.core.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageFile {
    private InputStream inputStream;
    private String name;
    private String mimeType;
}
