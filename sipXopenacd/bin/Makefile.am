include $(top_srcdir)/config/utility.am

EXTRA_DIST = \
	$(bin_SCRIPTS:=.in) \
	$(setup_SCRIPTS:=.in)

OPENACD_VARS = \
	$(filter OPENACD%,$(.VARIABLES))

dist_bin_SCRIPTS = \
	find-erlang-lib

bin_SCRIPTS = \
	sipxopenacd

setupdir = \
	$(SIPX_LIBEXECDIR)/setup.d

setup_SCRIPTS = \
	sipxopenacd-setup

$(setup_SCRIPTS) $(bin_SCRIPTS) : % : %.in Makefile
	@$(call SearchAndReplace, $<, $@, $(OPENACD_VARS))

CLEANFILES = $(setup_SCRIPTS) $(bin_SCRIPTS)
