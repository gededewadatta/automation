package led.automation.admin.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.InputStream;

public class UploadFile {

    private String getMediaFile;

    private String Extensions;

    private String uploadType;


    public String getGetMediaFile() {
        return getMediaFile;
    }

    public void setGetMediaFile(String getMediaFile) {
        this.getMediaFile = getMediaFile;
    }

    public String getExtensions() {
        return Extensions;
    }

    public void setExtensions(String extensions) {
        Extensions = extensions;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }
}
