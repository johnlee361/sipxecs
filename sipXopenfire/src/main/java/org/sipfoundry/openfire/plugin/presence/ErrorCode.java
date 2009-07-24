package org.sipfoundry.openfire.plugin.presence;

public enum ErrorCode {
    USER_NOT_FOUND,
    USER_CREATION_ERROR,
    USER_DESTRUCTION_ERROR, 
    SET_SIP_ID_ERROR, 
    GET_SIP_ID_ERROR, SERVER_CONTROL_FAILED, 
    SIP_ID_NOT_FOUND, PROCESSING_ERROR,
    SET_SIP_PASSWORD_ERROR,
    GET_SIP_PASSWORD_ERROR, GROUP_CREATION_ERROR,
    ADD_USER_TO_GROUP_ERROR, GROUP_DELETION_ERROR, GROUP_EXISTS_ERROR, 
    USER_IN_GROUP_EXCEPTION, REMOVE_USER_FROM_GROUP,
    PRESENCE_LISTENER_FAILED_ERROR,
    INVALID_XMPP_PRESENCE_VALUE
}