include $(top_srcdir)/config/utility.am

EXTRA_DIST = \
	$(schema_DATA:=.in) \
        $(proc_DATA:=.in)

procdir = \
        $(SIPX_DATADIR)/process.d

proc_DATA = \
	sipxsupervisor-process.xml

schemadir = @SIPX_DATADIR@/schema
schema_DATA = \
	sipXecs-process.xsd \
	alarm.xsd \
	alarm-group.xsd

alarmcfgdir = @SIPX_CONFDIR@/alarms
dist_alarmcfg_DATA = \
	sipXalarms-config.xml

alarmdir = @SIPX_DATADIR@/alarms
dist_alarm_DATA = \
	alarm-strings.xml \
	sipXalarms-strings.xml

mibdir = @SIPX_DATADIR@/mibs
dist_mib_DATA = \
	SIPXECS-ALARM-NOTIFICATION-MIB.mib

$(schema_DATA) $(proc_DATA) : % : %.in Makefile
	@$(call SearchAndReplace, $<, $@)

CLEANFILES = $(proc_DATA) $(schema_DATA)
