/*
 *  This file is part of MxUpdate <http://www.mxupdate.org>.
 *
 *  MxUpdate is a deployment tool for a PLM platform to handle
 *  administration objects as single update files (configuration item).
 *
 *  Copyright (C) 2008-2016 The MxUpdate Team - All Rights Reserved
 *
 *  You may use, distribute and modify MxUpdate under the terms of the
 *  MxUpdate license. You should have received a copy of the MxUpdate
 *  license with this file. If not, please write to <info@mxupdate.org>,
 *  or visit <www.mxupdate.org>.
 *
 */

package org.mxupdate.update.datamodel;

import java.util.HashSet;
import java.util.Set;

import org.mxupdate.typedef.EMxAdmin_mxJPO;
import org.mxupdate.update.AbstractAdminObject_mxJPO;
import org.mxupdate.update.datamodel.helper.TriggerList_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;

/**
 * Abstract class for all data model administration objects with triggers.
 *
 * @author The MxUpdate Team
 * @param <CLASS> derived from this class
 */
public abstract class AbstractDMWithTriggers_mxJPO<CLASS extends AbstractDMWithTriggers_mxJPO<CLASS>>
    extends AbstractAdminObject_mxJPO<CLASS>
{
    /** Set of all ignored URLs from the XML definition for policies. */
    private static final Set<String> IGNORED_URLS = new HashSet<>();
    static  {
        AbstractDMWithTriggers_mxJPO.IGNORED_URLS.add("/triggerList");
    }

    /** Map with all triggers. The key is the name of the trigger. */
    private final TriggerList_mxJPO triggers = new TriggerList_mxJPO();

    /**
     * Constructor used to initialize the type definition enumeration.
     *
     * @param _mxClassDef   defines the related MX class definition
     * @param _mxName       MX name of the administration object
     */
    public AbstractDMWithTriggers_mxJPO(final EMxAdmin_mxJPO  _mxClassDef,
                                        final String _mxName)
    {
        super(_mxClassDef, _mxName);
    }

    /**
     * Returns all defined {@link #triggers} sorted.
     *
     * @return all defined triggers
     */
    protected TriggerList_mxJPO getTriggers()
    {
        return this.triggers;
    }

    /**
     * Parses the {@link #triggers}.
     *
     * @param _paramCache   parameter cache with MX context
     * @param _url          URL to parse
     * @param _content      content of the URL to parse
     * @return <i>true</i> if <code>_url</code> could be parsed; otherwise
     *         <i>false</i>
     */
    @Override()
    public boolean parseAdminXMLExportEvent(final ParameterCache_mxJPO _paramCache,
                                            final String _url,
                                            final String _content)
    {
        final boolean parsed;
        if (AbstractDMWithTriggers_mxJPO.IGNORED_URLS.contains(_url))  {
            parsed = true;
        } else if (_url.startsWith("/triggerList"))  {
            parsed = this.triggers.parse(_paramCache, _url.substring(12), _content);
        } else  {
            parsed = super.parseAdminXMLExportEvent(_paramCache, _url, _content);
        }
        return parsed;
    }

    /**
     * After the type XML file is parsed, the {@link #triggers} must be sorted.
     */
    @Override()
    protected void prepare()
    {
        // sort all triggers
        this.triggers.prepare();

        super.prepare();
    }
}
