package kr.hs.dgsw.web_2_0326.Protocol;

import lombok.Data;

@Data
public class AttechmentProtocol {
    private String storedPath;
    private String originalName;

    public AttechmentProtocol(String storedPath, String originalName) {
        this.storedPath = storedPath;
        this.originalName = originalName;
    }
}
