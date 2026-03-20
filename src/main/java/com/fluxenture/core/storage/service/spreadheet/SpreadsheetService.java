package com.fluxenture.core.storage.service.spreadheet;

import java.util.List;

public interface SpreadsheetService {
    String createSpreadsheet(String title);
    void writeData(String spreadsheetId, String range, List<List<Object>> values);
    List<List<Object>> readData(String spreadsheetId, String range);
    void updateCell(String spreadsheetId, String cell, Object value);
    void addSheet(String spreadsheetId, String sheetName);
}
