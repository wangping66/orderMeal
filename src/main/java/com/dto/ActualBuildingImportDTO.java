package com.dto;

import com.kedacom.ctsp.web.dto.ImportDTO;
import lombok.Data;

@Data
public class ActualBuildingImportDTO {

    private String buildingCode;

    private String buildingArea;

    private String buildingType;

    private String buildingUsage;

    /*private String livePeopleNumber;*/

    private String province;

    private String city;

    private String district;

    private String street;

    private String blockNo;

    private String buildingNo;

    private String unitNo;

    private String roomNo;

}
