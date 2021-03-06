/*
 * Copyright (C) 2010 Avaya, certain elements licensed under a Contributor Agreement.
 * Contributors retain copyright to elements licensed under a Contributor Agreement.
 * Licensed to the User under the LGPL license.
 */
package org.sipfoundry.sipxconfig.admin.commserver.imdb;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sipfoundry.commons.mongo.MongoConstants;
import org.sipfoundry.sipxconfig.TestHelper;
import org.sipfoundry.sipxconfig.common.CoreContext;
import org.sipfoundry.sipxconfig.common.DaoUtils;
import org.sipfoundry.sipxconfig.common.User;
import org.sipfoundry.sipxconfig.domain.DomainManager;
import org.sipfoundry.sipxconfig.permission.PermissionManagerImpl;

import com.mongodb.QueryBuilder;

public class UserStaticTest extends MongoTestCase {
    private final String[][] USER_DATA = {
        {
            "0", "first1", "last1", "8809", "63948809"
        }, {
            "1", "first2", "last2", "8810", "63948810"
        }, {
            "2", "first3", "last3", "8811", "63948811"
        },
    };

    private List<User> m_users;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PermissionManagerImpl impl = new PermissionManagerImpl();
        impl.setModelFilesContext(TestHelper.getModelFilesContext());
        DomainManager dm = getDomainManager();
        replay(dm);
        m_users = new ArrayList<User>();
        for (String[] ud : USER_DATA) {
            User user = new User();
            user.setPermissionManager(impl);

            user.setUniqueId(new Integer(ud[0]));
            user.setFirstName(ud[1]);
            user.setLastName(ud[2]);
            user.setUserName(ud[3]);
            user.setDomainManager(dm);
            user.setSettingValue("voicemail/mailbox/external-mwi", ud[4]);
            m_users.add(user);
        }
        replay(getCoreContext());
    }

    public void testGenerate() throws Exception {
        UserStatic us = new UserStatic();
        us.setDbCollection(getCollection());
        us.setCoreContext(getCoreContext());
        us.generate(m_users.get(0), us.findOrCreate(m_users.get(0)));
        us.generate(m_users.get(1), us.findOrCreate(m_users.get(1)));
        us.generate(m_users.get(2), us.findOrCreate(m_users.get(2)));
        MongoTestCaseHelper.assertCollectionCount(3);
        QueryBuilder qb = QueryBuilder.start(MongoConstants.ID);
        qb.is("User0").and(MongoConstants.STATIC+"."+MongoConstants.CONTACT).is("sip:"+USER_DATA[0][4]+"@"+DOMAIN);
        MongoTestCaseHelper.assertObjectPresent(qb.get());
        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User0", MongoConstants.STATIC+"."+MongoConstants.CONTACT, "sip:"+USER_DATA[0][4]+"@"+DOMAIN);
        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User0", MongoConstants.STATIC+"."+MongoConstants.TO_URI, "sip:"+USER_DATA[0][3]+"@"+DOMAIN);
        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User1", MongoConstants.STATIC+"."+MongoConstants.EVENT, "message-summary");
        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User2", MongoConstants.STATIC+"."+MongoConstants.FROM_URI, "sip:IVR@"+DOMAIN);
    }
}
