package com.smatoos.b2b.model;

import com.smatoos.nobug.model.BaseModel;

/**
 * Created by SMATOOS_10 on 2016-06-09.
 */
public class VersionItem extends BaseModel {

/*
    {
        "current_apps_version": "20160517224300",
            "maintenance_yn": "N",
            "maintenance_end_date": null,
            "force_update_yn": "N"
    }
*/

    private static final long serialVersionUID = 01L;

    //	==========================================================================================

    public String current_apps_version;
    public String maintenance_yn;
    public String maintenance_end_date;
    public String force_update_yn;

}
