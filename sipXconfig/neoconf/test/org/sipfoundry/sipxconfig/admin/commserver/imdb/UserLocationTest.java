/*
 *
 *
 * Copyright (C) 2007 Pingtel Corp., certain elements licensed under a Contributor Agreement.
 * Contributors retain copyright to elements licensed under a Contributor Agreement.
 * Licensed to the User under the LGPL license.
 *
 *
 */
package org.sipfoundry.sipxconfig.admin.commserver.imdb;

import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.List;

import org.sipfoundry.commons.mongo.MongoConstants;
import org.sipfoundry.sipxconfig.TestHelper;
import org.sipfoundry.sipxconfig.branch.Branch;
import org.sipfoundry.sipxconfig.common.User;
import org.sipfoundry.sipxconfig.permission.PermissionManagerImpl;
import org.sipfoundry.sipxconfig.setting.Group;

public class UserLocationTest extends MongoTestCase {

    private final String[][] USER_DATA = {
        {
            "0", "first1", "last1", "mir1", "boston"
        }, {
            "1", "first2", ",last2", "mir2", "seattle"
        }, {
            "2", "first3", ",last3", "mir3", null
        },
    };

    private List<User> m_users;

    public void testGenerate() throws Exception {
        PermissionManagerImpl impl = new PermissionManagerImpl();
        impl.setModelFilesContext(TestHelper.getModelFilesContext());
        replay(getCoreContext());
        m_users = new ArrayList<User>();
        for (String[] ud : USER_DATA) {
            User user = new User();
            user.setPermissionManager(impl);

            user.setUniqueId(new Integer(ud[0]));
            user.setFirstName(ud[1]);
            user.setLastName(ud[2]);
            user.setUserName(ud[3]);
            user.setDomainManager(getDomainManager());
            if (ud[4] != null) {
                Branch branch = new Branch();
                branch.setUniqueId();
                branch.setName(ud[4]);

                Group site = new Group();
                site.setUniqueId();
                site.setName("group" + ud[4]);
                site.setBranch(branch);

                user.addGroup(site);
            }
            m_users.add(user);
        }
        UserLocation ul = new UserLocation();
        ul.setDbCollection(getCollection());
        ul.setCoreContext(getCoreContext());
        ul.generate(m_users.get(0), ul.findOrCreate(m_users.get(0)));
        ul.generate(m_users.get(1), ul.findOrCreate(m_users.get(1)));
        ul.generate(m_users.get(2), ul.findOrCreate(m_users.get(2)));

        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User0", MongoConstants.USER_LOCATION, USER_DATA[0][4]);
        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User1", MongoConstants.USER_LOCATION, USER_DATA[1][4]);
        MongoTestCaseHelper.assertObjectWithIdFieldValuePresent("User2", MongoConstants.USER_LOCATION, USER_DATA[2][4]);
    }
}
