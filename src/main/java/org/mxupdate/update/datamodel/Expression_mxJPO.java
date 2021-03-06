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

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.mxupdate.mapping.PropertyDef_mxJPO;
import org.mxupdate.typedef.EMxAdmin_mxJPO;
import org.mxupdate.update.AbstractAdminObject_mxJPO;
import org.mxupdate.update.util.AbstractParser_mxJPO.ParseException;
import org.mxupdate.update.util.DeltaUtil_mxJPO;
import org.mxupdate.update.util.ParameterCache_mxJPO;
import org.mxupdate.update.util.UpdateBuilder_mxJPO;
import org.mxupdate.update.util.UpdateException_mxJPO;
import org.mxupdate.update.zparser.MxParser_mxJPO;
import org.mxupdate.util.MqlBuilderUtil_mxJPO;
import org.mxupdate.util.MqlBuilderUtil_mxJPO.MultiLineMqlBuilder;

import matrix.util.MatrixException;

/**
 * Handles the export and the update of the expression configuration item.
 * The handled properties are:
 * <ul>
 * <li>package</li>
 * <li>uuid</li>
 * <li>symbolic name</li>
 * <li>description</li>
 * <li>hidden flag (only if hidden)</li>
 * <li>{@link #value}</li>
 * <li>properties</li>
 * </ul>
 *
 * @author The MxUpdate Team
 */
public class Expression_mxJPO
    extends AbstractAdminObject_mxJPO<Expression_mxJPO>
{
    /** Set of all ignored URLs from the XML definition for expressions. */
    private static final Set<String> IGNORED_URLS = new HashSet<>();
    static  {
        // to be ignored and read from method prepare because
        // the expression export does not work correctly for XML tags
        Expression_mxJPO.IGNORED_URLS.add("/expression");
    }

    /** Hold the expression itself. */
    private String value = null;

    /**
     * Constructor used to initialize the type definition enumeration.
     *
     * @param _mxName   MX name of the expression object
     */
    public Expression_mxJPO(final String _mxName)
    {
        super(EMxAdmin_mxJPO.Expression, _mxName);
    }

    @Override
    public void parseUpdate(final String _code)
        throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException
    {
        new MxParser_mxJPO(new StringReader(_code)).parseExpression(this);
        this.prepare();
    }

    /**
     * {@inheritDoc}
     * Also the value of the expression is extracted from MX
     */
    @Override
    public void parse(final ParameterCache_mxJPO _paramCache)
        throws MatrixException, ParseException
    {
        super.parse(_paramCache);

        this.value = MqlBuilderUtil_mxJPO.mql()
                .cmd("escape print expression ").arg(this.getName()).cmd(" select ").arg("value").cmd(" dump")
                .exec(_paramCache.getContext());
    }

    /**
     * Parses all expression specific expression URLs.
     *
     * @param _paramCache   parameter cache with MX context
     * @param _url          URL to parse
     * @param _content      content of the URL to parse
     * @return <i>true</i> if <code>_url</code> could be parsed; otherwise
     *         <i>false</i>
     * @see #IGNORED_URLS
     */
    @Override
    public boolean parseAdminXMLExportEvent(final ParameterCache_mxJPO _paramCache,
                                            final String _url,
                                            final String _content)
    {
        final boolean parsed;
        if (Expression_mxJPO.IGNORED_URLS.contains(_url))  {
            parsed = true;
        } else  {
            parsed = super.parseAdminXMLExportEvent(_paramCache, _url, _content);
        }
        return parsed;
    }

    @Override
    public void writeUpdate(final UpdateBuilder_mxJPO _updateBuilder)
    {
        _updateBuilder
                //              tag             | default | value                              | write?
                .stringNotNull( "package",                  this.getPackageRef())
                .stringNotNull( "uuid",                     this.getProperties().getValue4KeyValue(_updateBuilder.getParamCache(), PropertyDef_mxJPO.UUID))
                .list(          "symbolicname",             this.getSymbolicNames())
                .string(        "description",              this.getDescription())
                .flag(          "hidden",           false,  this.isHidden())
                .string(        "value",                    this.value)
                .properties(this.getProperties());
    }

    @Override
    public void calcDelta(final ParameterCache_mxJPO _paramCache,
                          final MultiLineMqlBuilder _mql,
                          final Expression_mxJPO _current)
        throws UpdateException_mxJPO
    {
        DeltaUtil_mxJPO.calcPackage(_paramCache, _mql, this, _current);
        DeltaUtil_mxJPO.calcSymbNames(_paramCache, _mql, this, _current);
        DeltaUtil_mxJPO.calcValueDelta(_mql, "description",         this.getDescription(),   _current.getDescription());
        DeltaUtil_mxJPO.calcFlagDelta(_mql,  "hidden",      false,  this.isHidden(),         _current.isHidden());
        DeltaUtil_mxJPO.calcValueDelta(_mql, "value",               this.value,              _current.value);

        this.getProperties().calcDelta(_mql, "", _current.getProperties());
    }
}
