/*
 *
 *
 * Copyright (C) 2008 Pingtel Corp., certain elements licensed under a Contributor Agreement.
 * Contributors retain copyright to elements licensed under a Contributor Agreement.
 * Licensed to the User under the LGPL license.
 *
 *
 */
package org.sipfoundry.sipxconfig.conference;

public class NoSuchConferenceException extends FreeswitchApiException {

    public NoSuchConferenceException(Conference conference) {
        super("error.noSuchConference", conference.getName());
    }

}
