/*
 *
 *
 * Copyright (C) 2009 Pingtel Corp., certain elements licensed under a Contributor Agreement.
 * Contributors retain copyright to elements licensed under a Contributor Agreement.
 * Licensed to the User under the LGPL license.
 *
 * $
 */
package org.sipfoundry.sipxconfig.admin.commserver.imdb;


import com.mongodb.DBObject;

import org.sipfoundry.sipxconfig.admin.forwarding.CallSequence;
import org.sipfoundry.sipxconfig.common.Replicable;
import org.sipfoundry.sipxconfig.common.User;

import static org.sipfoundry.commons.mongo.MongoConstants.CFWDTIME;

public class UserForward extends DataSetGenerator {

    @Override
    protected DataSet getType() {
        return DataSet.USER_FORWARD;
    }

    public void generate(Replicable entity, DBObject top) {
        if (entity instanceof CallSequence) {
            CallSequence cs = (CallSequence) entity;
            User user = cs.getUser();
            generateUser(user, top);
        }
        if (entity instanceof User) {
            generateUser((User) entity, top);
        }
    }

    private void generateUser(User user, DBObject top) {
        top.put(CFWDTIME, user.getSettingTypedValue(CallSequence.CALL_FWD_TIMER_SETTING));
        getDbCollection().save(top);
    }

}
